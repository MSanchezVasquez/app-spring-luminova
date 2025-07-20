package com.optica.luminova.security;

import com.optica.luminova.security.jwt.JwtAuthenticationFilter;
import com.optica.luminova.security.jwt.JwtAuthenticationEntryPoint;
import com.optica.luminova.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
   private final CustomSuccessHandler successHandler;

   private final JwtAuthenticationFilter jwtFilter;
   private final JwtAuthenticationEntryPoint authEntryPoint;
   private final UsuarioService usuarioService;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/auth/**").permitAll()
                  .requestMatchers("/css/**", "/js/**", "/images/**", "/fonts/**", "/favicon.ico", "/", "/login",
                        "/registro", "/index", "/login", "/productos",
                        "/horarios")
                  .permitAll()
                  .requestMatchers("/admin/**").hasRole("ADMIN")
                  .anyRequest().authenticated())
            .formLogin(form -> form
                  .loginPage("/login")
                  .successHandler(successHandler) // Aquí redirige según el rol
                  .permitAll())
            .logout(logout -> logout
                  .logoutUrl("/logout")
                  .logoutSuccessUrl("/login?logout")
                  .invalidateHttpSession(true)
                  .deleteCookies("JSESSIONID")
                  .permitAll())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .userDetailsService(usuarioService)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();

   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
         throws Exception {
      return config.getAuthenticationManager();
   }
}
