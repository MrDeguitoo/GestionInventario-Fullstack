package com.GestionInventario.compra_service.dto;

import java.util.List;

public record ResumenCompraDTO(
        ProveedorDTO proveedor,
        UsuarioDTO usuarioAutorizador,
        List<ProductoDTO> productos,
        double totalEstimado
) {
}