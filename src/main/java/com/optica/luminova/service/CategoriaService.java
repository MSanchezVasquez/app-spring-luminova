package com.optica.luminova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.optica.luminova.model.Categoria;
import com.optica.luminova.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

   @Autowired
   private CategoriaRepository categoriaRepository;

   // Crear o actualizar una categoría
   public Categoria crearCategoria(Categoria categoria) {
      return categoriaRepository.save(categoria);
   }

   // Obtener todas las categorías
   public List<Categoria> obtenerTodasLasCategorias() {
      return categoriaRepository.findAll();
   }

   // Obtener una categoría por su ID
   public Optional<Categoria> obtenerCategoriaPorId(Long id) {
      return categoriaRepository.findById(id);
   }

   // Eliminar una categoría por su ID
   public void eliminarCategoria(Long id) {
      categoriaRepository.deleteById(id);
   }
}
