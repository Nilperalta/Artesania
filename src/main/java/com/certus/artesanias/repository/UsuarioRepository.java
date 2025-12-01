package com.certus.artesanias.repository;

import com.certus.artesanias.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Long countByRol(String rol);

    Long countByActivo(Boolean activo);

    // MÉTODO NUEVO PARA EL GRÁFICO DE ESTADÍSTICAS
    @Query("SELECT MONTH(u.fechaRegistro), COUNT(u) " +
           "FROM Usuario u " +
           "WHERE YEAR(u.fechaRegistro) = YEAR(CURRENT_DATE) " +
           "GROUP BY MONTH(u.fechaRegistro) " +
           "ORDER BY MONTH(u.fechaRegistro)")
    List<Object[]> usuariosAgrupadosPorMesEsteAnio();
}