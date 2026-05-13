package com.GestionInventario.bodega_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bodegas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la bodega es obligatorio")
    @Column(unique = true, nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(length = 50)
    private String tipo;
}