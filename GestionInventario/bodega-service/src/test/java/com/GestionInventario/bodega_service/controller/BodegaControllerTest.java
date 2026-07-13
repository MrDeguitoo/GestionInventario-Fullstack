package com.GestionInventario.bodega_service.controller;

import com.GestionInventario.bodega_service.model.Bodega;
import com.GestionInventario.bodega_service.service.BodegaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

@WebMvcTest(BodegaController.class)
class BodegaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BodegaService bodegaService;

    @Test
    void testListarBodegas() throws Exception {
        Bodega bodega1 = new Bodega(1L, "Bodega Norte", "Calle 123", "Central");
        Bodega bodega2 = new Bodega(2L, "Bodega Sur", "Avenida 456", "Frigorífica");

        when(bodegaService.listarTodos()).thenReturn(Arrays.asList(bodega1, bodega2));

        mockMvc.perform(get("/api/v1/bodegas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Bodega Norte"));
    }

    @Test
    void testObtenerBodegaPorId() throws Exception {
        Bodega bodega = new Bodega(1L, "Bodega Norte", "Calle 123", "Central");

        when(bodegaService.buscarPorId(1L)).thenReturn(bodega);

        mockMvc.perform(get("/api/v1/bodegas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Bodega Norte"));
    }

    @Test
    void testCrearBodega() throws Exception {
        Bodega request = new Bodega(null, "Bodega Nueva", "Calle 999", "Central");
        Bodega response = new Bodega(1L, "Bodega Nueva", "Calle 999", "Central");

        when(bodegaService.guardar(any(Bodega.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Bodega Nueva"));
    }

    @Test
    void testEliminarBodega() throws Exception {
        doNothing().when(bodegaService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/bodegas/1"))
                .andExpect(status().isNoContent());

        verify(bodegaService).eliminar(1L);
    }
}
