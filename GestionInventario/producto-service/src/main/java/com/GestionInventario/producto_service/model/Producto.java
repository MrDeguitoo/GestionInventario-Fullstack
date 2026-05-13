package com.GestionInventario.producto_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El SKU es obligatorio")
    @Column(unique = true, nullable = false, length = 50)
    private String sku;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @NotNull(message = "El precio base es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    @Column(nullable = false)
    private Double precioBase;
}