package com.GestionInventario.inventario_service;

import com.GestionInventario.inventario_service.model.Stock;
import com.GestionInventario.inventario_service.repository.StockRepository;
import com.GestionInventario.inventario_service.service.StockService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private StockService stockService;

	@Test
	@DisplayName("Debería incrementar el stock cuando ya existe un registro previo")
	void deberiaIncrementarStockExistente() {
		Long productoId = 1L;
		Long bodegaId = 2L;
		Stock stockExistente = new Stock(100L, productoId, bodegaId, 10, 5);

		when(stockRepository.findByProductoIdAndBodegaId(productoId, bodegaId))
				.thenReturn(Optional.of(stockExistente));
		when(stockRepository.save(any(Stock.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Stock resultado = stockService.actualizarStock(productoId, bodegaId, 5);

		assertNotNull(resultado);
		assertEquals(15, resultado.getCantidadDisponible());
		verify(stockRepository, times(1)).save(any(Stock.class));
	}

	@Test
	@DisplayName("Debería crear un nuevo stock con valores por defecto cuando no existe")
	void deberiaCrearNuevoStockSiNoExiste() {
		Long productoId = 1L;
		Long bodegaId = 2L;

		when(stockRepository.findByProductoIdAndBodegaId(productoId, bodegaId))
				.thenReturn(Optional.empty());
		when(stockRepository.save(any(Stock.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Stock resultado = stockService.actualizarStock(productoId, bodegaId, 8);

		assertNotNull(resultado);
		assertEquals(8, resultado.getCantidadDisponible());
		verify(stockRepository, times(1)).save(any(Stock.class));
	}

	@Test
	@DisplayName("Debería lanzar IllegalArgumentException si el stock resultante es negativo")
	void deberiaLanzarExcepcionSiStockEsNegativo() {
		Long productoId = 1L;
		Long bodegaId = 2L;
		Stock stockExistente = new Stock(100L, productoId, bodegaId, 5, 5);

		when(stockRepository.findByProductoIdAndBodegaId(productoId, bodegaId))
				.thenReturn(Optional.of(stockExistente));

		IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
			stockService.actualizarStock(productoId, bodegaId, -10);
		});

		assertEquals("No hay suficiente stock para realizar esta operación. Producto ID: " + productoId, excepcion.getMessage());
		verify(stockRepository, never()).save(any(Stock.class));
	}
}