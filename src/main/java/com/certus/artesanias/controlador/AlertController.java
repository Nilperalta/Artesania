package com.certus.artesanias.controlador;

import com.certus.artesanias.models.Alert;
import com.certus.artesanias.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {
    
    @Autowired
    private AlertService alertService;
    
    @GetMapping
    public List<Alert> getAll() {
        return alertService.getAll();
    }
    
    @GetMapping("/unread")
    public List<Alert> getNoLeidas() {
        return alertService.getNoLeidas();
    }
    
    @PatchMapping("/{id}/read")
    public void marcarLeida(@PathVariable Long id) {
        alertService.marcarLeida(id);
    }
}