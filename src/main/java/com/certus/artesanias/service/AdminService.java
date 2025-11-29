package com.certus.artesanias.service;

import com.certus.artesanias.dto.DashboardStatsDTO;
import com.certus.artesanias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public DashboardStatsDTO getDashboardStatistics() {
        long totalUsuarios = usuarioRepository.count();
        long totalVendedores = usuarioRepository.countByRol("VENDEDOR");
        long totalCompradores = usuarioRepository.countByRol("COMPRADOR");
        long totalAdmins = usuarioRepository.countByRol("ADMIN");
        long usuariosActivos = usuarioRepository.countByActivo(true);
        long usuariosInactivos = totalUsuarios - usuariosActivos;
        long alertasActivas = 0L; // Sin alertas activas por ahora
        double tasaCrecimiento = 15.5;
        
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
}