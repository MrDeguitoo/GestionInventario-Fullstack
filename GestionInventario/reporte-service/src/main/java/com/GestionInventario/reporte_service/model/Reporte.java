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
    private String nombreReporte;

    @NotBlank
    private String tipoReporte;

    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    @NotBlank
    private String urlDescarga;
}