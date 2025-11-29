package com.certus.artesanias.service;

import com.certus.artesanias.dto.CreateUsuarioRequest;
import com.certus.artesanias.dto.UpdateUsuarioRequest;
import com.certus.artesanias.dto.UsuarioDTO;
import com.certus.artesanias.models.Usuario;
import java.util.List;

public interface UsuarioService {
    Usuario login(String email, String password);
    void registrarUsuario(Usuario usuario);
    Usuario findByEmail(String email);
    List<Usuario> getAllUsuarios();
    Usuario obtenerPorIdDTO(Long id);
    UsuarioDTO crear(CreateUsuarioRequest createRequest);
    UsuarioDTO actualizar(Long id, UpdateUsuarioRequest updateRequest);
    void eliminar(Long id);
    UsuarioDTO toggleEstado(Long id);
}