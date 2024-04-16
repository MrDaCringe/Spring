package com.example.iemgi.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.iemgi.Entidad.Usuario;
import com.example.iemgi.Repositorio.RepositorioUsuario;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import com.example.iemgi.Servicio.ServicioUsuario;
import java.util.List;
import com.example.iemgi.Servicio.RSAEncryptionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControladorUsuario {
    @Autowired
    private ServicioUsuario usuarioServicio;
    @Autowired
    private RepositorioUsuario repositorioUsuario;


    @Autowired
    private RSAEncryptionService rsaEncryptionService;
    
    @GetMapping("iemgi/vista/login")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "iemgi/vista/login";
    }
    @PostMapping("/iemgi/vista/login")
    public String iniciarSesion(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioServicio.autenticarUsuario(usuario.getMatricula(), usuario.getContrasena())) {
            String matricula = usuario.getMatricula();
            return "redirect:/iemgi/vista/index?matricula=" + matricula; 
        } else {
            model.addAttribute("error", "Credenciales incorrectas, por favor inténtalo de nuevo.");
            return "iemgi/vista/login";
        }
    }
    @PostMapping("/iemgi/vista/actualizar")
    public String actualizarUsuario(@ModelAttribute("usuarioForm") Usuario usuarioForm) {
        Usuario usuario = usuarioServicio.obtenerPorMatricula(usuarioForm.getMatricula());
        usuario.setNombre(usuarioForm.getNombre());
        usuario.setCarrera(usuarioForm.getCarrera());
        usuario.setContrasena(usuarioForm.getContrasena());
        usuarioServicio.actualizar(usuario);
        return "redirect:/iemgi/vista/index?matricula=" + usuario.getMatricula();
    }

    @GetMapping("iemgi/vista/index")
    public String index(Model model, @RequestParam("matricula") String matricula) {
        Usuario usuario = usuarioServicio.obtenerPorMatricula(matricula); 
        model.addAttribute("mostrarBotonRegistro", usuario.getPrivilegios().equals("admin"));
        model.addAttribute("usuario", usuario);
        return "iemgi/vista/index";
    }
    

    @PostMapping("/iemgi/vista/registro")
    public String insertarusuario(@ModelAttribute Usuario usuario) {
        if (usuario.getPrivilegios() == null || usuario.getPrivilegios().isEmpty()) {
            usuario.setPrivilegios("usuario");
        }
        usuarioServicio.insertar(usuario);
        return "redirect:/iemgi/vista/login";
    }
    @PostMapping("/api/verificarMatricula")
    @ResponseBody
    public String verificarMatricula(@RequestParam("matricula") String matricula) {
        Usuario usuario = usuarioServicio.obtenerPorMatricula(matricula);
        if (usuario != null) {
            return "existe";
        } else {
            return "no_existe";
        }
    }
    
    
}
