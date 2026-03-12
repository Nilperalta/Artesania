package com.certus.artesanias.config;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomSuccessHandler(UsuarioRepository usuarioRepository,
                                PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Usuario usuario = null;

        // 🔵 LOGIN CON GOOGLE
        if (authentication.getPrincipal() instanceof OAuth2User oauthUser) {

            String email = oauthUser.getAttribute("email");
            String nombre = oauthUser.getAttribute("name");

            usuario = usuarioRepository.findByEmail(email);

            if (usuario == null) {
                usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setEmail(email);
                usuario.setPassword(passwordEncoder.encode("googleUser123"));
                usuario.setRol("COMPRADOR");
                usuario.setActivo(true);
                usuarioRepository.save(usuario);
            }

            request.getSession().setAttribute("usuarioLogeado", usuario);
            response.sendRedirect("/comprador");
            return;
        }

        // 🟢 LOGIN NORMAL
        String email = authentication.getName();
        usuario = usuarioRepository.findByEmail(email);
        request.getSession().setAttribute("usuarioLogeado", usuario);

        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        String redirectURL = "/login";

        for (GrantedAuthority role : roles) {
            String rol = role.getAuthority();
            if (rol.equals("ROLE_COMPRADOR")) {
                redirectURL = "/comprador";
                break;
            } else if (rol.equals("ROLE_VENDEDOR")) {
                redirectURL = "/vendedor";
                break;
            } else if (rol.equals("ROLE_ADMIN")) {
                redirectURL = "/admin";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}