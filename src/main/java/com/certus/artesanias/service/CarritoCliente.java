package com.certus.artesanias.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.certus.artesanias.models.Carrito;

@Service
public class CarritoCliente {

    private final WebClient webClient;

    public CarritoCliente(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8092").build();
    }

    public List<Carrito> obtenerCarritoPorUsuarioId(Long usuarioId) {
        return this.webClient.get()
                .uri("/api/carrito/usuario/{usuarioId}", usuarioId)
                .retrieve()
                .bodyToFlux(Carrito.class)
                .collectList()
                .block();
    }

    public void agregarAlCarrito(Long usuarioId, Long productoId, int cantidad) {
        this.webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/carrito/agregar")
                        .queryParam("usuarioId", usuarioId)
                        .queryParam("productoId", productoId)
                        .queryParam("cantidad", cantidad)
                        .build())
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void eliminarDelCarrito(Long id) {
        this.webClient.delete()
                .uri("/api/carrito/eliminar/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void vaciarCarrito(Long usuarioId) {
        this.webClient.delete()
                .uri("/api/carrito/vaciar/{usuarioId}", usuarioId)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
