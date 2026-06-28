package com.GestionInventario.compra_service;

import com.GestionInventario.compra_service.model.OrdenCompra;
import com.GestionInventario.compra_service.repository.OrdenCompraRepository;
import com.GestionInventario.compra_service.service.OrdenCompraService;
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
public class CompraServiceApplicationTests {

    @Mock
    private OrdenCompraRepository ordenCompraRepository;

    @InjectMocks
    private OrdenCompraService ordenCompraService;

    @Test
    @DisplayName("Debería asignar estado EN_PROCESO cuando la orden llega vacía")
    void deberiaAsignarEstadoPorDefectoAlGuardar() {
        OrdenCompra ordenEntrada = new OrdenCompra();
        ordenEntrada.setProveedorId(1L);
        ordenEntrada.setTotal(5000.0);
        ordenEntrada.setEstado("");

        OrdenCompra ordenGuardada = new OrdenCompra();
        ordenGuardada.setId(100L);
        ordenGuardada.setEstado("EN_PROCESO");

        when(ordenCompraRepository.save(any(OrdenCompra.class))).thenReturn(ordenGuardada);

        OrdenCompra resultado = ordenCompraService.guardar(ordenEntrada);

        assertNotNull(resultado);
        assertEquals("EN_PROCESO", resultado.getEstado());
        verify(ordenCompraRepository, times(1)).save(any(OrdenCompra.class));
    }
}