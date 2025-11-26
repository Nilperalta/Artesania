package com.certus.artesanias.models;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "setting_key", unique = true)
    private String key;
    
    @Column(name = "setting_value")
    private String value;
    
    private String categoria;
    
    public Settings() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}