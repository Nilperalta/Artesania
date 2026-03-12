package com.certus.artesanias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.certus.artesanias.models.Carrito;
import com.certus.artesanias.models.Producto;
import com.certus.artesanias.models.Usuario;
import com.certus.artesanias.repository.CarritoRepository;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final ProductoService productoService;

    public CarritoService(CarritoRepository carritoRepository, ProductoService productoService) {
        this.carritoRepository = carritoRepository;
        this.productoService = productoService;
    }

    // ====================== OBTENER CARRITO ======================
    public List<Carrito> obtenerCarritoPorUsuarioId(Long usuarioId) {
        return carritoRepository.findByUsuario_Id(usuarioId);
    }

    // ====================== AGREGAR AL CARRITO ======================
    public void agregarAlCarrito(Long usuarioId, Long productoId, int cantidad) {

        Producto producto = productoService.obtenerProductoPorId(productoId);

        if (producto == null) {
            return;
        }

        List<Carrito> carritoUsuario = carritoRepository.findByUsuario_Id(usuarioId);

        // Verificar si el producto ya existe en el carrito
        for (Carrito item : carritoUsuario) {

            if (item.getProducto().getId().equals(productoId)) {

                int nuevaCantidad = item.getCantidad() + cantidad;
                item.setCantidad(nuevaCantidad);

                item.calcularSubtotal();

                carritoRepository.save(item);
                return;
            }
        }

        // Si el producto no está en el carrito
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        Carrito nuevoItem = new Carrito();
        nuevoItem.setUsuario(usuario);
        nuevoItem.setProducto(producto);
        nuevoItem.setCantidad(cantidad);

        nuevoItem.calcularSubtotal();

        carritoRepository.save(nuevoItem);
    }

    // ====================== ELIMINAR ITEM ======================
    public void eliminarDelCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    // ====================== VACIAR CARRITO ======================
    public void vaciarCarrito(Long usuarioId) {
        List<Carrito> items = carritoRepository.findByUsuario_Id(usuarioId);
        carritoRepository.deleteAll(items);
    }
}