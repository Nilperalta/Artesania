package com.certus.artesanias.service;

import com.certus.artesanias.models.Alert;
import com.certus.artesanias.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlertService {
    
    @Autowired
    private AlertRepository alertRepository;
    
    public List<Alert> getAll() {
        return alertRepository.findAll();
    }
    
    public List<Alert> getNoLeidas() {
        return alertRepository.findByLeidaOrderByFechaDesc(false);
    }
    
    public void marcarLeida(Long id) {
        Alert alert = alertRepository.findById(id).orElseThrow();
        alert.setLeida(true);
        alertRepository.save(alert);
    }
}