package com.certus.artesanias.service;

import com.certus.artesanias.dto.CreateUsuarioRequest;
import com.certus.artesanias.dto.UpdateUsuarioRequest;
import com.certus.artesanias.models.Usuario;
import java.util.List;

// ESTO DEBE SER SOLO UNA INTERFAZ - sin @Service, sin implementaciones
public interface UsuarioService {
    
    // Métodos de autenticación
    Usuario login(String email, String password);
    void registrarUsuario(Usuario usuario);
    
    // Métodos CRUD  
    List<Usuario> getAllUsuarios();
    Usuario obtenerPorIdDTO(Long id);
    Usuario crear(CreateUsuarioRequest createRequest);
    Usuario actualizar(Long id, UpdateUsuarioRequest updateRequest);
    void eliminar(Long id);
    Usuario toggleEstado(Long id);
}