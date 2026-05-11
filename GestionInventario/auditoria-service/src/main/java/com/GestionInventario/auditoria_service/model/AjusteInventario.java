package com.GestionInventario.auditoria_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "ajustes_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AjusteInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long productoId;

    @NotNull
    private Long bodegaId;

    @NotNull(message = "La cantidad ajustada es obligatoria (puede ser negativa)")
    private Integer cantidadAjustada; // Ej: -2 (Se perdieron 2)

    @NotBlank(message = "El motivo es obligatorio (Ej: Producto Vencido, Robo)")
    private String motivo;

    @NotBlank(message = "El usuario que aprueba el ajuste es obligatorio")
    private String usuarioAprobador; // Podría ser el ID del usuario

    private LocalDateTime fechaAjuste = LocalDateTime.now();
}