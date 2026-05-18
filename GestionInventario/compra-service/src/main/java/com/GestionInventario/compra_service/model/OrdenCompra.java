package com.GestionInventario.compra_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del proveedor es obligatorio")
    @Column(name = "proveedor_id", nullable = false)
    private Long proveedorId;

    @Column(nullable = false)
    private LocalDateTime fechaOrden = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String estado;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser mayor a cero")
    @Column(nullable = false)
    private Double total;
}