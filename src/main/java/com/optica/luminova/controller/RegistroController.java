package com.optica.luminova.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.optica.luminova.DTO.RegisterDTO;


@Controller
public class RegistroController {
    @Autowired
    private com.optica.luminova.service.UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarFormulario(@Valid @ModelAttribute RegisterDTO registerDTO,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro";
        }
        usuarioService.registrar(registerDTO);
        return "redirect:/registro?exito";
    }
}

