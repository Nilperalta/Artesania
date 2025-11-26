package com.certus.artesanias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certus.artesanias.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmailAndPassword(String email, String password);
    Usuario findByEmail(String email);
    Long countByRol(String rol);
    Long countByActivo(Boolean activo);
}