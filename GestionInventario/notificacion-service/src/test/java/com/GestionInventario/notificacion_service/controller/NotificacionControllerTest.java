package com.GestionInventario.notificacion_service.controller;

import com.GestionInventario.notificacion_service.model.Notificacion;
import com.GestionInventario.notificacion_service.service.NotificacionService;
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

@WebMvcTest(NotificacionController.class)
@AutoConfigureMockMvc(addFilters = false)
class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificacionService notificacionService;

    @Test
    void testListarNotificaciones() throws Exception {
        Notificacion notificacion1 = new Notificacion(1L, "cliente@demo.com", "Bienvenida", "Hola", LocalDateTime.now(), "ENVIADO");
        Notificacion notificacion2 = new Notificacion(2L, "admin@demo.com", "Recordatorio", "Hola", LocalDateTime.now(), "PENDIENTE");

        when(notificacionService.listarTodos()).thenReturn(List.of(notificacion1, notificacion2));

        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].asunto").value("Bienvenida"));
    }

    @Test
    void testObtenerNotificacionPorId() throws Exception {
        Notificacion notificacion = new Notificacion(1L, "cliente@demo.com", "Bienvenida", "Hola", LocalDateTime.now(), "ENVIADO");

        when(notificacionService.buscarPorId(1L)).thenReturn(notificacion);

        mockMvc.perform(get("/api/v1/notificaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.correoDestino").value("cliente@demo.com"));
    }

    @Test
    void testCrearNotificacion() throws Exception {
        Notificacion request = new Notificacion(null, "cliente@demo.com", "Bienvenida", "Hola", LocalDateTime.now(), "PENDIENTE");
        Notificacion response = new Notificacion(1L, "cliente@demo.com", "Bienvenida", "Hola", LocalDateTime.now(), "ENVIADO");

        when(notificacionService.guardar(any(Notificacion.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estadoEnvio").value("ENVIADO"));
    }

    @Test
    void testEliminarNotificacion() throws Exception {
        doNothing().when(notificacionService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/notificaciones/1"))
                .andExpect(status().isNoContent());

        verify(notificacionService).eliminar(1L);
    }
}
