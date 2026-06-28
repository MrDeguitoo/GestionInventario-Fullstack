package com.GestionInventario.auditoria_service;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import com.GestionInventario.auditoria_service.repository.AjusteInventarioRepository;
import com.GestionInventario.auditoria_service.service.AjusteInventarioService;
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
public class AjusteInventarioServiceTest {

	@Mock
	private AjusteInventarioRepository ajusteInventarioRepository;

	@InjectMocks
	private AjusteInventarioService ajusteInventarioService;

	@Test
	@DisplayName("Debería retornar el ajuste de inventario cuando el ID existe")
	void deberiaRetornarAjusteCuandoExiste() {
		Long idExistente = 1L;
		AjusteInventario ajusteSimulado = new AjusteInventario();
		ajusteSimulado.setId(idExistente);

		when(ajusteInventarioRepository.findById(idExistente)).thenReturn(Optional.of(ajusteSimulado));

		AjusteInventario resultado = ajusteInventarioService.buscarPorId(idExistente);

		assertNotNull(resultado);
		assertEquals(idExistente, resultado.getId());
		verify(ajusteInventarioRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException si el ID no existe")
	void deberiaLanzarExcepcionCuandoNoExiste() {
		Long idInexistente = 99L;

		when(ajusteInventarioRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			ajusteInventarioService.buscarPorId(idInexistente);
		});

		assertEquals("Auditoria no encontrada", excepcion.getMessage());
		verify(ajusteInventarioRepository, times(1)).findById(idInexistente);
	}
}