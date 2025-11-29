package com.certus.artesanias.service;

import com.certus.artesanias.dto.CreateUsuarioRequest;
import com.certus.artesanias.dto.UpdateUsuarioRequest;
import com.certus.artesanias.dto.UsuarioDTO;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrarUsuario(Usuario usuario) {
        // encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    
}
