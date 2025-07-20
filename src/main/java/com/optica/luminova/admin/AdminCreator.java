package com.optica.luminova.admin;

import com.optica.luminova.model.Usuario;
import com.optica.luminova.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminCreator implements CommandLineRunner {

   @Autowired
   private UsuarioRepository usuarioRepo;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public void run(String... args) {
      String adminEmail = "admin@gmail.com";
      if (usuarioRepo.findByEmail(adminEmail).isEmpty()) {
         Usuario admin = new Usuario();
         admin.setNombre("admin");
         admin.setEmail(adminEmail);
         admin.setPassword(passwordEncoder.encode("admin123"));
         admin.setRol("ADMIN");

         usuarioRepo.save(admin);
         System.out.println("✅ Usuario admin creado con éxito.");
      } else {
         System.out.println("⚠️ Usuario admin ya existe.");
      }
   }
}
