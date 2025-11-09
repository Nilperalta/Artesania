package com.certus.artesanias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.certus.artesanias.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
