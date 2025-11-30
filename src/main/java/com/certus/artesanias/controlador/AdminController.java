package com.certus.artesanias.controlador;

import com.certus.artesanias.dto.DashboardStatsDTO;
import com.certus.artesanias.service.AdminService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller


@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @GetMapping("")
    public String dashboard(Model model) {
        model.addAttribute("stats", adminService.getDashboardStatistics());

        return "dashboard-admin";
    }
    
    @GetMapping("/controlcuentas")
    public String controlCuentas() {
        return "admin-controlcuentas";
    }
    
    @GetMapping("/alertas")
    public String alertas() {
        return "admin-alertas";
    }
    
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        model.addAttribute("stats", adminService.getDashboardStatistics());
        return "admin-estadisticas";
    }
    
    @GetMapping("/ajustes")
    public String ajustes() {
        return "admin-ajustes";
    }
    
    @GetMapping("/api/stats")
    @ResponseBody
    public DashboardStatsDTO getStats() {
        return adminService.getDashboardStatistics();
    }

        @GetMapping("/salir")
    public String salir(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}


