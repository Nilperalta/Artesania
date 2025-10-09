package com.certus.artesanias.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VendedorController {
    @GetMapping("/vendedor")
    public String dashboard() {
        return "dashboard-vendedor";
    }
}