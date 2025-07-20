package com.optica.luminova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.optica.luminova.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
