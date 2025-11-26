package com.certus.artesanias.controlador;

import com.certus.artesanias.dto.DashboardStatsDTO;
import com.certus.artesanias.service.AdminService;
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
    
    @GetMapping("/dashboard")
    public String dashboardPage(Model model) {
        model.addAttribute("stats", adminService.getDashboardStatistics());
        return "dashboard-admin";
    }
    
    @GetMapping("/controlcuentas")
    public String controlCuentas() {
        return "admin-controlcuentas";
    }
    
    @GetMapping("/ajustes")
    public String ajustes() {
        return "admin-ajustes";
    }
    
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        model.addAttribute("stats", adminService.getDashboardStatistics());
        return "admin-estadisticas";
    }
    
    @GetMapping("/api/stats")
    @ResponseBody
    public DashboardStatsDTO getStats() {
        return adminService.getDashboardStatistics();
    }
}