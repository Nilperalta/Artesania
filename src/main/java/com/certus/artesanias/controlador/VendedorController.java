package com.certus.artesanias.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendedorController {
    @GetMapping("/vendedor")
    public String dashboard() {
        return "dashboard-vendedor";
    }
    
    @GetMapping("/vendedor/ventas")
    public String misVentas() {
        return "vendedor-misventas";
    }
    
    @GetMapping("/vendedor/productos")
    public String misProductos() {
        return "vendedor-misproductos";
    }
    
    @GetMapping("/vendedor/perfil")
    public String miPerfil() {
        return "vendedor-miperfil";
    }
}