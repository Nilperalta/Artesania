package com.certus.artesanias.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompradorController {

    @GetMapping("/comprador")
    public String dashboard() {
        return "dashboard-comprador";
    }
}
