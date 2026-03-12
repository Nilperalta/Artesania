package com.certus.artesanias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.certus.artesanias.models.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Obtener items del carrito por usuario
    List<Carrito> findByUsuario_Id(Long usuarioId);

    // Eliminar items del carrito por usuario
    void deleteByUsuario_Id(Long usuarioId);
}