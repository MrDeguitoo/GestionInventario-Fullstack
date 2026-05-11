package com.GestionInventario.reporte_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del reporte es obligatorio")
    private String nombreReporte; // Ej: "Cierre Mensual Agosto 2024"

    @NotBlank
    private String tipoReporte; // Ej: "VALORIZACION_INVENTARIO", "PRODUCTOS_VENCIDOS"

    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    @NotBlank
    private String urlDescarga; // Un link simulado donde se guardó el PDF/Excel
}