package com.optica.luminova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.optica.luminova.model.Montura;
import com.optica.luminova.service.MonturaService;
import com.optica.luminova.repository.CategoriaRepository;

import java.util.List;

@Controller
@RequestMapping("/montura")
public class MonturaController {

   @Autowired
   private MonturaService monturaService;

   @Autowired
   private CategoriaRepository categoriaRepository;

   // Crear Montura - Vista de formulario
   @GetMapping("/crear")
   public String crearMontura(Model model) {
      model.addAttribute("montura", new Montura());
      model.addAttribute("categorias", categoriaRepository.findAll());
      return "montura/crear"; // Página para crear la montura
   }

   // Crear o actualizar una montura (POST)
   @PostMapping("/crear")
   public String crearMontura(Montura montura, @RequestParam Long categoriaId) {
      monturaService.crearMontura(montura, categoriaId); // Llama al servicio para guardar
      return "redirect:/gestionar";
   }

   // Obtener todas las monturas (AJAX)
   @GetMapping("/listarMonturas")
   @ResponseBody
   public List<Montura> listarMonturasAJAX() {
      return monturaService.obtenerTodasLasMonturas(); // Devuelve la lista de monturas en formato JSON
   }

   // Obtener una montura por su ID (para editar)
   @GetMapping("/editar/{id}")
   public String editarMontura(@PathVariable Long id, Model model) {
      var montura = monturaService.obtenerMonturaPorId(id)
            .orElseThrow(() -> new RuntimeException("Montura no encontrada"));
      model.addAttribute("montura", montura);
      model.addAttribute("categorias", categoriaRepository.findAll()); // Para seleccionar una categoría
      return "montura/editar"; // Página para editar una montura
   }

   // Eliminar una montura
   @GetMapping("/eliminar/{id}")
   public String eliminarMontura(@PathVariable Long id) {
      monturaService.eliminarMontura(id);
      return "redirect:/gestionar";
   }
}
