package com.optica.luminova.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.optica.luminova.service.UsuarioService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   private final JwtTokenUtil jwtTokenUtil;
   private final UsuarioService usuarioService;

   @Override
   protected void doFilterInternal(HttpServletRequest request,
         HttpServletResponse response,
         FilterChain filterChain)
         throws ServletException, IOException {

      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      final String username;

      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
         filterChain.doFilter(request, response);
         return;
      }

      jwt = authHeader.substring(7); // Eliminar "Bearer "
      username = jwtTokenUtil.extractUsername(jwt);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         UserDetails userDetails = usuarioService.loadUserByUsername(username);

         if (jwtTokenUtil.validateToken(jwt, userDetails.getUsername())) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);
         }
      }

      filterChain.doFilter(request, response);
   }
}
