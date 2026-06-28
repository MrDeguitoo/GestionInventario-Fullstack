package com.GestionInventario.proveedor_service;

import com.GestionInventario.proveedor_service.model.Proveedor;
import com.GestionInventario.proveedor_service.repository.ProveedorRepository;
import com.GestionInventario.proveedor_service.service.ProveedorService;
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
public class ProveedorServiceTest {

	@Mock
	private ProveedorRepository proveedorRepository;

	@InjectMocks
	private ProveedorService proveedorService;

	@Test
	@DisplayName("Debería retornar el proveedor cuando el ID existe")
	void deberiaRetornarProveedorCuandoExiste() {
		Long idExistente = 1L;
		Proveedor proveedorSimulado = new Proveedor();
		proveedorSimulado.setId(idExistente);

		when(proveedorRepository.findById(idExistente)).thenReturn(Optional.of(proveedorSimulado));

		Proveedor resultado = proveedorService.buscarPorId(idExistente);

		assertNotNull(resultado, "El proveedor no debería ser nulo");
		assertEquals(idExistente, resultado.getId());
		verify(proveedorRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException con el mensaje dinámico correcto si el ID no existe")
	void deberiaLanzarExcepcionConMensajeDinamico() {
		Long idInexistente = 55L;

		when(proveedorRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			proveedorService.buscarPorId(idInexistente);
		});

		String mensajeEsperado = "Proveedor no encontrado con ID: " + idInexistente;
		assertEquals(mensajeEsperado, excepcion.getMessage());

		verify(proveedorRepository, times(1)).findById(idInexistente);
	}
}