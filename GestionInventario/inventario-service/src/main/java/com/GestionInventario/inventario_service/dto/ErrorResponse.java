package com.GestionInventario.inventario_service.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String mensaje;
    private String detalle;
    private int status;
    private LocalDateTime timeStamp;

    public ErrorResponse(String mensaje, String detalle, int status) {
        this.mensaje = mensaje;
        this.detalle = detalle;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }
}