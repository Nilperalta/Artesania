package com.certus.artesanias.repository;

import com.certus.artesanias.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByLeidaOrderByFechaDesc(Boolean leida);
}