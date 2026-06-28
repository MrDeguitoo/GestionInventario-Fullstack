package com.GestionInventario.bodega_service;

import com.GestionInventario.bodega_service.model.Bodega;
import com.GestionInventario.bodega_service.repository.BodegaRepository;
import com.GestionInventario.bodega_service.service.BodegaService;
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
public class BodegaServiceTest {

	@Mock
	private BodegaRepository bodegaRepository;

	@InjectMocks
	private BodegaService bodegaService;

	@Test
	@DisplayName("Debería retornar la bodega cuando el ID existe")
	void deberiaRetornarBodegaCuandoExiste() {
		Long idExistente = 1L;
		Bodega bodegaSimulada = new Bodega();
		bodegaSimulada.setId(idExistente);

		when(bodegaRepository.findById(idExistente)).thenReturn(Optional.of(bodegaSimulada));

		Bodega resultado = bodegaService.buscarPorId(idExistente);

		assertNotNull(resultado);
		assertEquals(idExistente, resultado.getId());
		verify(bodegaRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException si el ID no existe")
	void deberiaLanzarExcepcionCuandoNoExiste() {
		Long idInexistente = 99L;

		when(bodegaRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			bodegaService.buscarPorId(idInexistente);
		});

		assertEquals("Bodega no encontrada", excepcion.getMessage());
		verify(bodegaRepository, times(1)).findById(idInexistente);
	}
}