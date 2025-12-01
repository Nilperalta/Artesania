package com.certus.artesanias.service;

import org.springframework.stereotype.Service;

import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.models.Vendedor;
import com.certus.artesanias.repository.VendedorRepository;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    // Obtener vendedor por ID del usuario logueado
    public Vendedor obtenerPorUsuario(Usuario usuario) {
        return vendedorRepository.findByUsuario_Id(usuario.getId())
                .orElse(null);
    }

    // Guardar cambios en el perfil del vendedor
    public Vendedor guardar(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }
}
