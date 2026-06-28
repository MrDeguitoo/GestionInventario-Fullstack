package com.GestionInventario.despacho_service;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import com.GestionInventario.despacho_service.repository.OrdenSalidaRepository;
import com.GestionInventario.despacho_service.service.OrdenSalidaService;
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
public class OrdenSalidaServiceTest {

	@Mock
	private OrdenSalidaRepository ordenSalidaRepository;

	@InjectMocks
	private OrdenSalidaService ordenSalidaService;

	@Test
	@DisplayName("Debería retornar la orden de salida cuando el ID existe")
	void deberiaRetornarOrdenSalidaCuandoExiste() {
		Long idExistente = 1L;
		OrdenSalida ordenSimulada = new OrdenSalida();
		ordenSimulada.setId(idExistente);

		when(ordenSalidaRepository.findById(idExistente)).thenReturn(Optional.of(ordenSimulada));

		OrdenSalida resultado = ordenSalidaService.buscarPorId(idExistente);

		assertNotNull(resultado);
		assertEquals(idExistente, resultado.getId());
		verify(ordenSalidaRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException si el ID no existe")
	void deberiaLanzarExcepcionCuandoNoExiste() {
		Long idInexistente = 99L;

		when(ordenSalidaRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			ordenSalidaService.buscarPorId(idInexistente);
		});

		assertEquals("Orden de salida no encontrada", excepcion.getMessage());
		verify(ordenSalidaRepository, times(1)).findById(idInexistente);
	}
}