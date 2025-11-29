package com.certus.artesanias.service;

import com.certus.artesanias.dto.CreateUsuarioRequest;
import com.certus.artesanias.dto.UpdateUsuarioRequest;
import com.certus.artesanias.dto.UsuarioDTO;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getPassword())) {
            return usuario;
        }
        return null;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorIdDTO(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO crear(CreateUsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol());
        usuario.setActivo(true);
        
        Usuario saved = usuarioRepository.save(usuario);
        return toDTO(saved);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UpdateUsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (request.getNombre() != null) usuario.setNombre(request.getNombre());
        if (request.getEmail() != null) usuario.setEmail(request.getEmail());
        if (request.getRol() != null) usuario.setRol(request.getRol());
        
        Usuario updated = usuarioRepository.save(usuario);
        return toDTO(updated);
    }

    @Override
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDTO toggleEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        usuario.setActivo(!usuario.getActivo());
        Usuario updated = usuarioRepository.save(usuario);
        return toDTO(updated);
    }

    private UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(
            u.getId(),
            u.getNombre(),
            u.getEmail(),
            u.getRol(),
            u.getActivo(),
            u.getFechaRegistro() != null 
                ? u.getFechaRegistro().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "--"
        );
    }
}