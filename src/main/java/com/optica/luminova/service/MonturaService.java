package com.optica.luminova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.optica.luminova.model.Montura;
import com.optica.luminova.repository.MonturaRepository;
import com.optica.luminova.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class MonturaService {

   @Autowired
   private MonturaRepository monturaRepository;

   @Autowired
   private CategoriaRepository categoriaRepository;

   // Crear o actualizar una montura
   public Montura crearMontura(Montura montura, Long categoriaId) {
      var categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
      montura.setCategoria(categoria);
      return monturaRepository.save(montura);
   }

   // Obtener todas las monturas
   public List<Montura> obtenerTodasLasMonturas() {
      return monturaRepository.findAll();
   }

   // Obtener una montura por su ID
   public Optional<Montura> obtenerMonturaPorId(Long id) {
      return monturaRepository.findById(id);
   }

   // Eliminar una montura por su ID
   public void eliminarMontura(Long id) {
      monturaRepository.deleteById(id);
   }
}
