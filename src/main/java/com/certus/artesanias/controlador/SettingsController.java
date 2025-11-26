package com.certus.artesanias.controlador;

import com.certus.artesanias.models.Settings;
import com.certus.artesanias.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/settings")
@CrossOrigin(origins = "*")
public class SettingsController {
    
    @Autowired
    private SettingsService settingsService;
    
    @GetMapping
    public List<Settings> getAll() {
        return settingsService.getAll();
    }
    
    @PutMapping("/{id}")
    public Settings update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return settingsService.update(id, body.get("value"));
    }
}