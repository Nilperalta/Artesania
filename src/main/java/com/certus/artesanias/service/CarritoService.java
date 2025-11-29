package com.certus.artesanias.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certus.artesanias.models.Carrito;
import com.certus.artesanias.models.Producto;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.CarritoRepository;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoCliente productoCliente;

    public List<Carrito> obtenerPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    public void agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        Producto producto = productoCliente.obtenerProductoPorId(productoId);
        if (producto == null) return;

        List<Carrito> carrito = carritoRepository.findByUsuarioId(usuarioId);
        Carrito existente = carrito.stream()
                                   .filter(c -> c.getProducto().getId().equals(productoId))
                                   .findFirst()
                                   .orElse(null);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
            carritoRepository.save(existente);
        } else {
            Carrito nuevo = new Carrito();
            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            nuevo.setUsuario(usuario);
            nuevo.setProducto(producto);
            nuevo.setCantidad(cantidad);
            carritoRepository.save(nuevo);
        }
    }

    public void eliminar(Long carritoId) {
        carritoRepository.deleteById(carritoId);
    }

    public void vaciar(Long usuarioId) {
        carritoRepository.deleteByUsuarioId(usuarioId);
    }
}
