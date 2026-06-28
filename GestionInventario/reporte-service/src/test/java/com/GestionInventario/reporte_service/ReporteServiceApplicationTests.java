package com.GestionInventario.reporte_service.service;

import com.GestionInventario.reporte_service.repository.ReporteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @Test
    @DisplayName("Debería lanzar excepción si la fecha de inicio del reporte es nula")
    void deberiaLanzarExcepcionConFechaNula() {
        Exception excepcion = assertThrows(IllegalArgumentException.class, () -> {
            reporteService.generarReporteMensual(null, "2026-06-30");
        });

        assertEquals("La fecha de inicio no puede ser nula", excepcion.getMessage());
    }
}