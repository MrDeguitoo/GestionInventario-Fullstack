package com.GestionInventario.despacho_service.controller;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import com.GestionInventario.despacho_service.service.OrdenSalidaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdenSalidaController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrdenSalidaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrdenSalidaService ordenSalidaService;

    @Test
    void testListarOrdenesSalida() throws Exception {
        OrdenSalida orden1 = new OrdenSalida(1L, 10L, 20L, 3, "Tienda Centro", "TRK-001", LocalDateTime.now());
        OrdenSalida orden2 = new OrdenSalida(2L, 11L, 21L, 5, "Tienda Norte", "TRK-002", LocalDateTime.now());

        when(ordenSalidaService.listarTodos()).thenReturn(Arrays.asList(orden1, orden2));

        mockMvc.perform(get("/api/v1/despacho"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].destino").value("Tienda Centro"));
    }

    @Test
    void testObtenerOrdenSalidaPorId() throws Exception {
        OrdenSalida orden = new OrdenSalida(1L, 10L, 20L, 3, "Tienda Centro", "TRK-001", LocalDateTime.now());

        when(ordenSalidaService.buscarPorId(1L)).thenReturn(orden);

        mockMvc.perform(get("/api/v1/despacho/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.trackingCode").value("TRK-001"));
    }

    @Test
    void testCrearOrdenSalida() throws Exception {
        OrdenSalida request = new OrdenSalida(null, 10L, 20L, 3, "Tienda Centro", null, LocalDateTime.now());
        OrdenSalida response = new OrdenSalida(1L, 10L, 20L, 3, "Tienda Centro", "TRK-001", LocalDateTime.now());

        when(ordenSalidaService.guardar(any(OrdenSalida.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/despacho")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.destino").value("Tienda Centro"));
    }

    @Test
    void testEliminarOrdenSalida() throws Exception {
        doNothing().when(ordenSalidaService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/despacho/1"))
                .andExpect(status().isNoContent());

        verify(ordenSalidaService).eliminar(1L);
    }
}
