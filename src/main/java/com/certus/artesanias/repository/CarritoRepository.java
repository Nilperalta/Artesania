package com.certus.artesanias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.certus.artesanias.models.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Método para obtener todos los items del carrito de un usuario
    List<Carrito> findByUsuarioId(Long usuarioId);

    // Método para eliminar todos los items de un usuario
    void deleteByUsuarioId(Long usuarioId);
}
