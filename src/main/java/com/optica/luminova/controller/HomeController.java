package com.optica.luminova.controller;

import com.optica.luminova.model.Horario;
import com.optica.luminova.service.CategoriaService;
import com.optica.luminova.service.MonturaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

   private final MonturaService monturaService;

   private final CategoriaService categoriaService;

   HomeController(CategoriaService categoriaService, MonturaService monturaService) {
      this.categoriaService = categoriaService;
      this.monturaService = monturaService;
   }

   // Método para mostrar la página principal (index.html)
   @GetMapping("/")
   public String showHomePage() {
      return "index"; // Thymeleaf buscará en /src/main/resources/templates/index.html
   }

   // Método para mostrar los horarios de los optometristas (horarios.html)
   @GetMapping("/horarios")
   public String showHorarios(Model model) {
      List<Horario> horarios = new ArrayList<>();
      horarios.add(new Horario("Dra. Laura Fernández", "Optometría General", "Lunes a Miércoles", "9:00 AM - 1:00 PM"));
      horarios.add(new Horario("Dr. Marcos Herrera", "Especialista en Lentes de Contacto", "Martes y Jueves",
            "2:00 PM - 6:00 PM"));
      horarios.add(new Horario("Dra. Camila Soto", "Oftalmología Pediátrica", "Viernes", "10:00 AM - 3:00 PM"));
      horarios.add(
            new Horario("Dr. Iván Castillo", "Cirugía Refractiva", "Lunes, Miércoles y Viernes", "3:00 PM - 7:00 PM"));
      horarios.add(new Horario("Dra. Valeria Ríos", "Exámenes Visuales Avanzados", "Sábado", "9:00 AM - 1:00 PM"));
      model.addAttribute("horarios", horarios); // Pasamos la lista al modelo
      return "horarios"; // Thymeleaf buscará en /src/main/resources/templates/horarios.html
   }

   // Método para mostrar la página de contacto (contactanos.html)
   @GetMapping("/contactanos")
   public String showContactPage() {
      return "contactanos"; // Thymeleaf buscará en /src/main/resources/templates/contactanos.html
   }

   // Método para mostrar los productos (productos.html)
   @GetMapping("/productos")
   public String showProductosPage(Model model) {
      model.addAttribute("monturas", monturaService.obtenerTodasLasMonturas());
      return "productos"; // Thymeleaf buscará en /src/main/resources/templates/productos.html
   }

   @GetMapping("/gestionar")
   public String showGestionarPage(Model model) {
      model.addAttribute("categorias", categoriaService.obtenerTodasLasCategorias());
      model.addAttribute("monturas", monturaService.obtenerTodasLasMonturas());
      return "gestionar"; // Thymeleaf buscará en /src/main/resources/templates/index.html
   }

   @GetMapping("/login")
   public String showLoginPage() {
      return "login"; // Thymeleaf buscará en /src/main/resources/templates/login.html
   }
}
