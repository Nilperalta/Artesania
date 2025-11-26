package com.certus.artesanias.dto;

public class UpdateUsuarioRequest {
    private String nombre;
    private String email;
    // ... otros campos

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}