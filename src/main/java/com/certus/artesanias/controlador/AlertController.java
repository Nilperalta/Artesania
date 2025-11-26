package com.certus.artesanias.controlador;

import com.certus.artesanias.models.Alert;
import com.certus.artesanias.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/alertas")
public class AlertController {
    
    @Autowired
    private AlertService alertService;
    
    @GetMapping
    public String showAlertas(Model model) {
        model.addAttribute("alertas", alertService.getAllAlerts());
        return "admin-alertas";
    }
    
    @GetMapping("/api/all")
    @ResponseBody
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
    
    @PostMapping("/api/create")
    @ResponseBody
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }
    
    @PutMapping("/api/update/{id}")
    @ResponseBody
    public ResponseEntity<Alert> updateAlert(@PathVariable Long id, @RequestBody Alert alert) {
        return ResponseEntity.ok(alertService.updateAlert(id, alert));
    }
    
    @DeleteMapping("/api/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/api/toggle/{id}")
    @ResponseBody
    public ResponseEntity<Alert> toggleAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.toggleAlert(id));
    }
}