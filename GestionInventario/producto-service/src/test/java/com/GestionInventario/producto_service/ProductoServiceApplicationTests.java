package com.GestionInventario.producto_service.service;

import com.GestionInventario.producto_service.repository.ProductoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    @DisplayName("Debería lanzar excepción si el precio del producto es negativo")
    void deberiaValidarPrecioNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.crearProducto("SKU123", "Teclado", -1500.0);
        });

        assertEquals("El precio debe ser mayor a cero", exception.getMessage());
    }
}