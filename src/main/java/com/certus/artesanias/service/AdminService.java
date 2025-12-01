package com.certus.artesanias.service;

import com.certus.artesanias.dto.DashboardStatsDTO;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Estadísticas básicas del dashboard
    public DashboardStatsDTO getDashboardStatistics() {
        long totalUsuarios = usuarioRepository.count();
        long totalVendedores = usuarioRepository.countByRol("VENDEDOR");
        long totalCompradores = usuarioRepository.countByRol("COMPRADOR");
        long totalAdmins = usuarioRepository.countByRol("ADMIN");
        long usuariosActivos = usuarioRepository.countByActivo(true);
        long usuariosInactivos = totalUsuarios - usuariosActivos;
        long alertasActivas = 0L;
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

    // MÉTODO PARA EL GRÁFICO DE ESTADÍSTICAS
    public List<Object[]> getUsuariosPorMesEsteAnio() {
        return usuarioRepository.usuariosAgrupadosPorMesEsteAnio();
    }

    // ==================== CRUD USUARIOS ====================
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario actualizarUsuario(Long id, Usuario datos) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (datos.getNombre() != null && !datos.getNombre().isBlank()) usuario.setNombre(datos.getNombre());
            if (datos.getEmail() != null && !datos.getEmail().isBlank()) usuario.setEmail(datos.getEmail());
            if (datos.getRol() != null && !datos.getRol().isBlank()) usuario.setRol(datos.getRol());
            if (datos.getActivo() != null) usuario.setActivo(datos.getActivo());
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }

    public Usuario cambiarEstadoUsuario(Long id, Boolean nuevoEstado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setActivo(nuevoEstado);
            return usuarioRepository.save(usuario);
        }).orElse(null);
    }

    public boolean eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}