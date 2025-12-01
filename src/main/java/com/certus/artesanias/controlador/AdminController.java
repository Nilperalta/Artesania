package com.certus.artesanias.controlador;

import com.certus.artesanias.dto.DashboardStatsDTO;
import com.certus.artesanias.repository.UsuarioRepository;
import com.certus.artesanias.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    // ESTADÍSTICAS 100% REALES Y ACTUALIZADAS
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {

        long totalUsuarios      = usuarioRepository.count();

        // Contamos tanto con prefijo como sin prefijo para que nunca falle
        long totalAdmins = usuarioRepository.countByRol("ADMIN") 
                         + usuarioRepository.countByRol("ROLE_ADMIN");

        long totalVendedores = usuarioRepository.countByRol("VENDEDOR") 
                              + usuarioRepository.countByRol("ROLE_VENDEDOR");

        long totalCompradores = usuarioRepository.countByRol("COMPRADOR") 
                               + usuarioRepository.countByRol("ROLE_COMPRADOR");

        long usuariosActivos    = usuarioRepository.countByActivo(true);
        long usuariosInactivos  = totalUsuarios - usuariosActivos;

        model.addAttribute("totalUsuarios", totalUsuarios);
        model.addAttribute("totalVendedores", totalVendedores);
        model.addAttribute("totalCompradores", totalCompradores);
        model.addAttribute("totalAdmins", totalAdmins);
        model.addAttribute("usuariosActivos", usuariosActivos);
        model.addAttribute("usuariosInactivos", usuariosInactivos);

        // Gráfico de registros por mes
        model.addAttribute("datosGrafico", adminService.getUsuariosPorMesEsteAnio());

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