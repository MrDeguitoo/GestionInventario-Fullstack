package com.GestionInventario.auditoria_service.controller;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import com.GestionInventario.auditoria_service.service.AjusteInventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

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

@WebMvcTest(AjusteInventarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class AjusteInventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AjusteInventarioService ajusteInventarioService;

    @Test
    void testListarAjustesInventario() throws Exception {
        AjusteInventario ajuste1 = new AjusteInventario(1L, 10L, 20L, 5, "Vencido", "Ana", LocalDateTime.now());
        AjusteInventario ajuste2 = new AjusteInventario(2L, 11L, 21L, -2, "Robo", "Luis", LocalDateTime.now());

        when(ajusteInventarioService.listarTodos()).thenReturn(List.of(ajuste1, ajuste2));

        mockMvc.perform(get("/api/v1/auditoria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].motivo").value("Vencido"));
    }

    @Test
    void testObtenerAjustePorId() throws Exception {
        AjusteInventario ajuste = new AjusteInventario(1L, 10L, 20L, 5, "Vencido", "Ana", LocalDateTime.now());

        when(ajusteInventarioService.buscarPorId(1L)).thenReturn(ajuste);

        mockMvc.perform(get("/api/v1/auditoria/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.usuarioAprobador").value("Ana"));
    }

    @Test
    void testCrearAjusteInventario() throws Exception {
        AjusteInventario request = new AjusteInventario(null, 10L, 20L, 5, "Vencido", "Ana", LocalDateTime.now());
        AjusteInventario response = new AjusteInventario(1L, 10L, 20L, 5, "Vencido", "Ana", LocalDateTime.now());

        when(ajusteInventarioService.guardar(any(AjusteInventario.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auditoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.motivo").value("Vencido"));
    }

    @Test
    void testEliminarAjusteInventario() throws Exception {
        doNothing().when(ajusteInventarioService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/auditoria/1"))
                .andExpect(status().isNoContent());

        verify(ajusteInventarioService).eliminar(1L);
    }
}
