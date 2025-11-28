package com.certus.artesanias.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String foto;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
