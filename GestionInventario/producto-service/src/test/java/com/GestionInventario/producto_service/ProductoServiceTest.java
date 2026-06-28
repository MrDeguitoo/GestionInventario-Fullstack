package com.GestionInventario.producto_service;

import com.GestionInventario.producto_service.model.Producto;
import com.GestionInventario.producto_service.repository.ProductoRepository;
import com.GestionInventario.producto_service.service.ProductoService;
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
public class ProductoServiceTest {

	@Mock
	private ProductoRepository productoRepository;

	@InjectMocks
	private ProductoService productoService;

	@Test
	@DisplayName("Debería retornar el producto cuando el ID existe")
	void deberiaRetornarProductoCuandoExiste() {
		Long idExistente = 1L;
		Producto productoSimulado = new Producto();
		productoSimulado.setId(idExistente);

		when(productoRepository.findById(idExistente)).thenReturn(Optional.of(productoSimulado));

		Producto resultado = productoService.buscarPorId(idExistente);

		assertNotNull(resultado);
		assertEquals(idExistente, resultado.getId());
		verify(productoRepository, times(1)).findById(idExistente);
	}

	@Test
	@DisplayName("Debería lanzar EntityNotFoundException si el ID no existe")
	void deberiaLanzarExcepcionCuandoNoExiste() {
		Long idInexistente = 99L;

		when(productoRepository.findById(idInexistente)).thenReturn(Optional.empty());

		EntityNotFoundException excepcion = assertThrows(EntityNotFoundException.class, () -> {
			productoService.buscarPorId(idInexistente);
		});

		assertEquals("Producto no encontrado con ID: " + idInexistente, excepcion.getMessage());
		verify(productoRepository, times(1)).findById(idInexistente);
	}
}