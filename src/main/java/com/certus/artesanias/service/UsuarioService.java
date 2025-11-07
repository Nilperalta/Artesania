package com.certus.artesanias.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String password) {
        return usuarioRepository.findByEmailAndPassword(email, password);
    }
}
