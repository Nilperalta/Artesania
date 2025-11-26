package com.certus.artesanias.service;

import com.certus.artesanias.dto.CreateUsuarioRequest;
import com.certus.artesanias.dto.UpdateUsuarioRequest;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario login(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorIdDTO(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario crear(CreateUsuarioRequest createRequest) {
        Usuario usuario = new Usuario();
        // Mapear campos del DTO a la entidad Usuario
        usuario.setNombre(createRequest.getNombre());
        usuario.setEmail(createRequest.getEmail());
        // ... otros campos
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, UpdateUsuarioRequest updateRequest) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            // Actualizar campos
            if (updateRequest.getNombre() != null) {
                usuarioExistente.setNombre(updateRequest.getNombre());
            }
            if (updateRequest.getEmail() != null) {
                usuarioExistente.setEmail(updateRequest.getEmail());
            }
            // ... otros campos
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario toggleEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setActivo(!usuario.getActivo());
            return usuarioRepository.save(usuario);
        }
        return null;
    }
}