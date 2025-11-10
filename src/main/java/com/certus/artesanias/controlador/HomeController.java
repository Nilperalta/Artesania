package com.certus.artesanias.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "index"; 
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
    Usuario usuario = usuarioService.login(email, password);

    if (usuario != null) {
        // Guardamos el usuario logueado en la sesión
        session.setAttribute("usuarioLogeado", usuario);

        switch (usuario.getRol()) {
            case "ADMIN": return "redirect:/admin";
            case "COMPRADOR": return "redirect:/comprador";
            case "VENDEDOR": return "redirect:/vendedor";
        }
    }

    model.addAttribute("error", "Credenciales incorrectas");
    return "login";
}

    @GetMapping("/seleccion-registro")
    public String seleccionRegistro() {
        return "SeleccionRegistro";
    }

    // Registro Personal (Comprador)
    @GetMapping("/registro-personal")
    public String registroPersonal() {
        return "registroPersonal";
    }

    @PostMapping("/registro-personal")
    public String registrarComprador(@ModelAttribute Usuario usuario, Model model) {
    // Validación básica de campos obligatorios
        if(usuario.getNombre() == null || usuario.getNombre().trim().isEmpty() ||
        usuario.getEmail() == null || usuario.getEmail().trim().isEmpty() ||
        usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {

            model.addAttribute("error", "Todos los campos son obligatorios");
            return "registroPersonal"; // vuelve al formulario mostrando el error
        }

        // Asigna rol y guarda
        usuario.setRol("COMPRADOR");
        usuarioService.registrarUsuario(usuario);

        return "redirect:/login"; // redirige al login si todo está bien
    }

    // Registro Empresa (Vendedor)
    @GetMapping("/registro-empresa")
    public String registroEmpresa() {
        return "registroEmpresa";
    }

    @PostMapping("/registro-empresa")
    public String registrarVendedor(@ModelAttribute Usuario usuario) {
        usuario.setRol("VENDEDOR");
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login";
    }
}
