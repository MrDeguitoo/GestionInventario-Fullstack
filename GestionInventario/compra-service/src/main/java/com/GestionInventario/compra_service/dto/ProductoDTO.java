package com.GestionInventario.compra_service.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductoDTO(Long id, String sku, String nombre, String descripcion, @JsonProperty("precioBase") double precio) {
}