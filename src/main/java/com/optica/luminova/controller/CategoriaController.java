package com.optica.luminova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.optica.luminova.model.Categoria;
import com.optica.luminova.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

   @Autowired
   private CategoriaService categoriaService;

   // Formulario para crear una nueva categoría
   @GetMapping("/crear")
   public String mostrarFormularioDeCreacion(Model model) {
      model.addAttribute("categoria", new Categoria()); // Creamos un objeto vacío para el formulario
      return "categoria/crear"; // Vista para crear una nueva categoría
   }

   // Crear o actualizar una categoría
   @PostMapping("/crear")
   public String crearCategoria(Categoria categoria) {
      categoriaService.crearCategoria(categoria);
      return "redirect:/gestionar"; // Redirige a la lista de gestionar
   }

   // Obtener una categoría por su ID (para editar)
   @GetMapping("/editar/{id}")
   public String editarCategoria(@PathVariable Long id, Model model) {
      var categoria = categoriaService.obtenerCategoriaPorId(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
      model.addAttribute("categoria", categoria);
      return "categoria/editar"; // Página para editar una categoría
   }

   // Eliminar una categoría
   @GetMapping("/eliminar/{id}")
   public String eliminarCategoria(@PathVariable Long id) {
      categoriaService.eliminarCategoria(id);
      return "redirect:/gestionar"; // Redirige a la lista de categorías
   }
}
