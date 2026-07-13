package com.GestionInventario.inventario_service.controller;

import com.GestionInventario.inventario_service.model.Stock;
import com.GestionInventario.inventario_service.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
@AutoConfigureMockMvc(addFilters = false)
class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    void testActualizarStock() throws Exception {
        Stock stockActualizado = new Stock(1L, 10L, 20L, 25, 5);

        when(stockService.actualizarStock(10L, 20L, 5)).thenReturn(stockActualizado);

        mockMvc.perform(put("/api/v1/inventario/actualizar")
                        .param("productoId", "10")
                        .param("bodegaId", "20")
                        .param("cantidad", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.cantidadDisponible").value(25));
    }
}
