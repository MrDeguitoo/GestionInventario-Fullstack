package com.GestionInventario.bodega_service;

import com.GestionInventario.bodega_service.repository.BodegaRepository;
import com.GestionInventario.bodega_service.service.BodegaService; // ¡Faltaba esta importación!
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BodegaServiceApplicationTests {

    @Mock
    private BodegaRepository bodegaRepository;

    @InjectMocks
    private BodegaService bodegaService;

    @Test
    @DisplayName("Debería rechazar el ingreso si el tamaño excede la capacidad máxima de la bodega")
    void deberiaValidarCapacidadMaxima() {
        int capacidadMaxima = 100;
        int volumenActual = 90;
        int nuevoIngreso = 20;
        boolean resultado = bodegaService.verificarEspacio(capacidadMaxima, volumenActual, nuevoIngreso);

        assertFalse(resultado, "No debería permitir el ingreso debido a falta de espacio");
    }
}