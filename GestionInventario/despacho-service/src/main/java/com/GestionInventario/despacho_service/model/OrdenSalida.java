package com.GestionInventario.despacho_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_salida")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @NotNull(message = "El ID de la bodega es obligatorio")
    private Long bodegaId;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "Debe despachar al menos 1 unidad")
    private Integer cantidad;

    @NotBlank(message = "El destino es obligatorio (ej. Tienda Centro)")
    private String destino;

    private LocalDateTime fechaSalida = LocalDateTime.now();
}