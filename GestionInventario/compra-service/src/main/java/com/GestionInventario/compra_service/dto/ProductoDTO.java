package com.GestionInventario.compra_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Long id;
    private String sku;
    private String nombre;
    private Double precio;
    private String descripcion;
}
