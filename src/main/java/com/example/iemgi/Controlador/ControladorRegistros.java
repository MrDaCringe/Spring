package com.example.iemgi.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.iemgi.Entidad.Registro;
import com.example.iemgi.Entidad.Usuario;
import com.example.iemgi.Repositorio.RepositorioRegistro;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.iemgi.Servicio.ServicioRegistro;
import java.util.List;

@Controller
public class ControladorRegistros {
    @Autowired
    private ServicioRegistro servicioRegistro;

    @GetMapping("/iemgi/vista/registros")
    public String listaRegistros(Model model) {
        List<Registro> registros = servicioRegistro.ObtenerRegistros();
        model.addAttribute("registros", registros);
        return "iemgi/vista/registros"; 
    }
    @GetMapping("/iemgi/vista/registros/eliminar/{id}")
    public String delete(Model model, @PathVariable Long id) {
        servicioRegistro.delete(id);
        return "redirect:/iemgi/vista/registros";
    }
}