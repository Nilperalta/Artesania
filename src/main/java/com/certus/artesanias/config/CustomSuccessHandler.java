package com.certus.artesanias.config;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // Guardamos el usuario completo en la sesión
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);
        request.getSession().setAttribute("usuarioLogeado", usuario);

        // Redirección según rol
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
