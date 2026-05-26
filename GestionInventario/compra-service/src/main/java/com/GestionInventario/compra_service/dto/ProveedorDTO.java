package com.GestionInventario.compra_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {
    private Long id;
    private String razonSocial;
    private String identificacion;
    private String correoVentas;
    private String telefono;
}