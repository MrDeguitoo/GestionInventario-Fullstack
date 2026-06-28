package com.GestionInventario.despacho_service;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import com.GestionInventario.despacho_service.repository.OrdenSalidaRepository;
import com.GestionInventario.despacho_service.service.OrdenSalidaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DespachoServiceApplicationTests {

    @Mock
    private OrdenSalidaRepository ordenSalidaRepository;

    @InjectMocks
    private OrdenSalidaService ordenSalidaService;

    @Test
    @DisplayName("Debería autogenerar un código de seguimiento único al solicitar despacho")
    void deberiaGenerarTrackingCode() {
        OrdenSalida entrada = new OrdenSalida();
        entrada.setDestino("Av Standard 123");

        OrdenSalida guardado = new OrdenSalida();
        guardado.setId(1L);
        guardado.setTrackingCode("TRK-GENERADO-99");

        when(ordenSalidaRepository.save(any(OrdenSalida.class))).thenReturn(guardado);

        OrdenSalida resultado = ordenSalidaService.procesarDespacho(entrada);

        assertNotNull(resultado.getTrackingCode());
        assertTrue(resultado.getTrackingCode().startsWith("TRK-"));
    }
}