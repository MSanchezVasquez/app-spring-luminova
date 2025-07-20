package com.optica.luminova.controller;

import com.optica.luminova.model.Usuario;
import com.optica.luminova.repository.UsuarioRepository;
import com.optica.luminova.security.jwt.JwtTokenUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

   private final AuthenticationManager authenticationManager;
   private final JwtTokenUtil jwtTokenUtil;
   private final UsuarioRepository usuarioRepository;
   private final PasswordEncoder passwordEncoder;

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody AuthRequest request) {
      try {
         authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      } catch (BadCredentialsException e) {
         return ResponseEntity.status(401).body("Credenciales inválidas");
      }

      UserDetails user = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

      String token = jwtTokenUtil.generateToken(user.getUsername());
      return ResponseEntity.ok(Collections.singletonMap("token", token));
   }

   @PostMapping("/register")
   public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario) {
      if (usuarioRepository.existsByEmail(usuario.getEmail())) {
         return ResponseEntity.badRequest().body("El correo ya está en uso");
      }
      usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
      usuario.setRol("ROLE_USER"); // rol por defecto
      usuarioRepository.save(usuario);
      return ResponseEntity.ok("Usuario registrado exitosamente");
   }

   @PostMapping("/logout")
   public ResponseEntity<?> logout(HttpServletResponse response) {
      Cookie cookie = new Cookie("jwt", null);
      cookie.setHttpOnly(true);
      cookie.setMaxAge(0);
      cookie.setPath("/");
      response.addCookie(cookie);
      return ResponseEntity.ok().build();
   }

   // DTO interno para login
   public static class AuthRequest {
      private String email;
      private String password;

      // Getters y setters
      public String getEmail() {
         return email;
      }

      public void setEmail(String email) {
         this.email = email;
      }

      public String getPassword() {
         return password;
      }

      public void setPassword(String password) {
         this.password = password;
      }
   }
}
