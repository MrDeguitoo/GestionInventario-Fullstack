package com.GestionInventario.notificacion_service;

import com.GestionInventario.notificacion_service.model.Notificacion;
import com.GestionInventario.notificacion_service.repository.NotificacionRepository;
import com.GestionInventario.notificacion_service.service.NotificacionService;
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
public class NotificacionServiceTest {

	@Mock
	private NotificacionRepository notificacionRepository;

	@InjectMocks
	private NotificacionService notificacionService;

	@Test
	@DisplayName("Debería retornar la notificación cuando el ID existe")
	void deberiaRetornarNotificacionCuandoExiste() {
		Long idExistente = 1L;
		Notificacion notificacionSimulada = new Notificacion();
		notificacionSimulada.setId(idExistente);

		when(notificacionRepository.findById(idExistente)).thenReturn(Optional.of(notificacionSimulada));

		Notificacion resultado = notificacionService.buscarPorId(idExistente);

		assertNotNull(resultado);
		assertEquals(idExistente, resultado.getId());
		verify(notificacionRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException si el ID no existe")
	void deberiaLanzarExcepcionCuandoNoExiste() {
		Long idInexistente = 99L;

		when(notificacionRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			notificacionService.buscarPorId(idInexistente);
		});

		assertEquals("Notificacion no encontrada", excepcion.getMessage());
		verify(notificacionRepository, times(1)).findById(idInexistente);
	}
}