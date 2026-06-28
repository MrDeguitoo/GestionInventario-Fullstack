package com.GestionInventario.inventario_service.service;

import com.GestionInventario.inventario_service.model.Inventario;
import com.GestionInventario.inventario_service.repository.InventarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    @DisplayName("Debería reducir el stock disponible correctamente")
    void deberiaReducirStock() {
        Inventario productoStock = new Inventario();
        productoStock.setProductoId(5L);
        productoStock.setCantidadDisponible(10);

        when(inventarioRepository.findByProductoId(5L)).thenReturn(Optional.of(productoStock));
        when(inventarioRepository.save(any(Inventario.class))).thenAnswer(i -> i.getArguments()[0]);

        Inventario resultado = inventarioService.descontarStock(5L, 3);

        assertEquals(7, resultado.getCantidadDisponible(), "El stock debió bajar de 10 a 7");
    }
}