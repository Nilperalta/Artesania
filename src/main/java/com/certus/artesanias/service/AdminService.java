package com.certus.artesanias.service;

import com.certus.artesanias.dto.DashboardStatsDTO;
import com.certus.artesanias.repository.AlertRepository;
import com.certus.artesanias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AlertRepository alertRepository;
    
    public DashboardStatsDTO getDashboardStatistics() {
        Long totalUsuarios = usuarioRepository.count();
        Long totalVendedores = usuarioRepository.countByRol("VENDEDOR");
        Long totalCompradores = usuarioRepository.countByRol("COMPRADOR");
        Long totalAdmins = usuarioRepository.countByRol("ADMIN");
        Long usuariosActivos = usuarioRepository.countByActivo(true);
        Long usuariosInactivos = usuarioRepository.countByActivo(false);
        Long alertasActivas = alertRepository.countByActivo(true);
        
        Double tasaCrecimiento = calcularTasaCrecimiento();
        
        return new DashboardStatsDTO(
            totalUsuarios,
            totalVendedores,
            totalCompradores,
            totalAdmins,
            usuariosActivos,
            usuariosInactivos,
            alertasActivas,
            tasaCrecimiento
        );
    }
    
    private Double calcularTasaCrecimiento() {
        // Implementación simple - puedes mejorarla
        return 15.5;
    }
}