package com.GestionInventario.notificacion_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    private String correoDestino;

    @NotBlank
    private String asunto;

    @NotBlank
    @Column(length = 500)
    private String mensaje;

    private LocalDateTime fechaEnvio = LocalDateTime.now();

    @NotBlank
    private String estadoEnvio;
}