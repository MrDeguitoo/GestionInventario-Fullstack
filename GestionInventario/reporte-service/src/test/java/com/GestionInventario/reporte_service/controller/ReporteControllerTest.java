package com.GestionInventario.reporte_service.controller;

import com.GestionInventario.reporte_service.model.Reporte;
import com.GestionInventario.reporte_service.service.ReporteService;
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

@WebMvcTest(ReporteController.class)
@AutoConfigureMockMvc(addFilters = false)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReporteService reporteService;

    @Test
    void testListarReportes() throws Exception {
        Reporte reporte1 = new Reporte(1L, "Reporte A", "PDF", LocalDateTime.now(), "https://example.com/a");
        Reporte reporte2 = new Reporte(2L, "Reporte B", "EXCEL", LocalDateTime.now(), "https://example.com/b");

        when(reporteService.listarTodos()).thenReturn(List.of(reporte1, reporte2));

        mockMvc.perform(get("/api/v1/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreReporte").value("Reporte A"));
    }

    @Test
    void testObtenerReportePorId() throws Exception {
        Reporte reporte = new Reporte(1L, "Reporte A", "PDF", LocalDateTime.now(), "https://example.com/a");

        when(reporteService.buscarPorId(1L)).thenReturn(reporte);

        mockMvc.perform(get("/api/v1/reportes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipoReporte").value("PDF"));
    }

    @Test
    void testCrearReporte() throws Exception {
        Reporte request = new Reporte(null, "Reporte C", "PDF", LocalDateTime.now(), "https://example.com/c");
        Reporte response = new Reporte(1L, "Reporte C", "PDF", LocalDateTime.now(), "https://example.com/c");

        when(reporteService.guardar(any(Reporte.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreReporte").value("Reporte C"));
    }

    @Test
    void testEliminarReporte() throws Exception {
        doNothing().when(reporteService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/reportes/1"))
                .andExpect(status().isNoContent());

        verify(reporteService).eliminar(1L);
    }
}
