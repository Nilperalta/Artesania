package com.certus.artesanias.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.certus.artesanias.models.Producto;

@Service
public class ProductoCliente {

    private final WebClient webClient;

    public ProductoCliente(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8091").build();
    }

    public List<Producto> obtenerProductos() {
        return this.webClient.get()
                .uri("/api/productos")
                .retrieve()
                .bodyToFlux(Producto.class)
                .collectList()
                .block();
    }

    public Producto obtenerProductoPorId(Long id) {
        return this.webClient.get()
                .uri("/api/productos/{id}", id)
                .retrieve()
                .bodyToMono(Producto.class)
                .block();
    }
}