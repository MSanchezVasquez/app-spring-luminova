package com.optica.luminova.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.optica.luminova.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // importante para login
    boolean existsByEmail(String email); // útil para validaciones
}
