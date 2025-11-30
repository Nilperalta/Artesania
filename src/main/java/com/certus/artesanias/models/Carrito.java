package com.certus.artesanias.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private int cantidad;

    // Subtotal calculado
    private BigDecimal subtotal;

    // ===== Getters y Setters =====
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }

    public Producto getProducto() { 
        return producto; 
    }
    public void setProducto(Producto producto) { 
        this.producto = producto; 
    }

    public int getCantidad() { 
        return cantidad; 
    }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }

    public BigDecimal getSubtotal() { 
        return subtotal; 
    }
    public void setSubtotal(BigDecimal subtotal) { 
        this.subtotal = subtotal; 
    }

    // ===== Método para calcular subtotal automáticamente =====
    public void calcularSubtotal() {
        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        } else {
            this.subtotal = BigDecimal.ZERO;
        }
    }
}
