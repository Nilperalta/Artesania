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
    
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }
    
    public List<Alert> getActiveAlerts() {
        return alertRepository.findByActivoOrderByFechaCreacionDesc(true);
    }
    
    public Alert createAlert(Alert alert) {
        return alertRepository.save(alert);
    }
    
    public Alert updateAlert(Long id, Alert alertData) {
        Alert alert = alertRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        
        alert.setTitulo(alertData.getTitulo());
        alert.setMensaje(alertData.getMensaje());
        alert.setTipo(alertData.getTipo());
        alert.setPrioridad(alertData.getPrioridad());
        alert.setActivo(alertData.getActivo());
        alert.setFechaExpiracion(alertData.getFechaExpiracion());
        
        return alertRepository.save(alert);
    }
    
    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }
    
    public Alert toggleAlert(Long id) {
        Alert alert = alertRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Alerta no encontrada"));
        alert.setActivo(!alert.getActivo());
        return alertRepository.save(alert);
    }
}