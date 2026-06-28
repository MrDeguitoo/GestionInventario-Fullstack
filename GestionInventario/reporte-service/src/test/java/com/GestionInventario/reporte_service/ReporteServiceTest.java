package com.GestionInventario.reporte_service;

import com.GestionInventario.reporte_service.model.Reporte;
import com.GestionInventario.reporte_service.repository.ReporteRepository;
import com.GestionInventario.reporte_service.service.ReporteService;
import jakarta.persistence.EntityNotFoundException;
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
public class ReporteServiceTest {

	@Mock
	private ReporteRepository reporteRepository;

	@InjectMocks
	private ReporteService reporteService;

	@Test
	@DisplayName("Debería retornar el reporte cuando el ID existe")
	void deberiaRetornarReporteCuandoExiste() {
		Long idExistente = 1L;
		Reporte reporteSimulado = new Reporte();
		reporteSimulado.setId(idExistente);

		when(reporteRepository.findById(idExistente)).thenReturn(Optional.of(reporteSimulado));

		Reporte resultado = reporteService.buscarPorId(idExistente);

		assertNotNull(resultado, "El reporte no debería ser nulo");
		assertEquals(idExistente, resultado.getId());
		verify(reporteRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException cuando el ID no existe")
	void deberiaLanzarExcepcionCuandoNoExiste() {
		Long idInexistente = 99L;

		when(reporteRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			reporteService.buscarPorId(idInexistente);
		});

		assertEquals("Reporte no encontrado", excepcion.getMessage());
		verify(reporteRepository, times(1)).findById(idInexistente);
	}
}