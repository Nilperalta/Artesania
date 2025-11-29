package com.certus.artesanias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.certus.artesanias.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
