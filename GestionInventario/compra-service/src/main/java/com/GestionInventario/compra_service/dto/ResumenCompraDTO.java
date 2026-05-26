package com.GestionInventario.compra_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResumenCompraDTO {
    private ProveedorDTO proveedor;
    private UsuarioDTO usuario;
    private List<ProductoDTO> productos;
    private Double total;
    private String estado;
}