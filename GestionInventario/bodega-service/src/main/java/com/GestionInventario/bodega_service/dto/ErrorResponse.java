package com.GestionInventario.bodega_service.dto;
//De tanto poner dtos me acorde de Dtoke xd
//nada que ver pero ahi un dato curioso

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