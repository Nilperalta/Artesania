package com.certus.artesanias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomSuccessHandler successHandler;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ENCRIPTACIÓN
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AQUÍ SPRING SECURITY LEE EL USUARIO DESDE LA BD
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Usuario usuario = usuarioRepository.findByEmail(email);

            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }

            return org.springframework.security.core.userdetails.User
                    .withUsername(usuario.getEmail())
                    .password(usuario.getPassword())
                    .roles(usuario.getRol().replace("ROLE_", ""))
                    .disabled(usuario.getActivo() == null || !usuario.getActivo())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Deshabilitar CSRF para rutas de API
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/admin/api/**"))
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/login", "/registro", "/login.js", "/style.css",
                    "/SeleccionRegistro.html", "/seleccion-registro",  
                    "/registroEmpresa.html", "/registroPersonal.html",
                    "/carrito.js", "/productos.js", "/registroEmpresa.js", "/registroPersonal.js",
                    "/script.js", "/maqueta.html",
                    "/images/**", "/registro-empresa","/productos","/vendedor-miperfil.html",
                    "/registro-personal"
                ).permitAll()


                .requestMatchers("/comprador/**").permitAll()
                .requestMatchers("/vendedor/**").hasRole("VENDEDOR")
                .requestMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")  
                .successHandler(successHandler)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager();
    }
}