package com.certus.artesanias.service;

import com.certus.artesanias.models.Settings;
import com.certus.artesanias.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SettingsService {
    
    @Autowired
    private SettingsRepository settingsRepository;
    
    public List<Settings> getAll() {
        return settingsRepository.findAll();
    }
    
    public Settings update(Long id, String value) {
        Settings s = settingsRepository.findById(id).orElseThrow();
        s.setValue(value);
        return settingsRepository.save(s);
    }
}