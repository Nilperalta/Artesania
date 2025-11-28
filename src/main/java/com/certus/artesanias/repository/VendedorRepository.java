package com.certus.artesanias.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.certus.artesanias.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
   Optional<Vendedor> findByUsuarioId(Long id);
}