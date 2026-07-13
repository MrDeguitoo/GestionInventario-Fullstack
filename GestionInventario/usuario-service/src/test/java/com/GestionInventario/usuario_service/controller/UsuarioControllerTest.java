package com.GestionInventario.usuario_service.controller;

import com.GestionInventario.usuario_service.model.Usuario;
import com.GestionInventario.usuario_service.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void testListarUsuarios() throws Exception {
        Usuario usuario1 = new Usuario(1L, "Juan", "juan@correo.com", "123456", "CLIENTE");
        Usuario usuario2 = new Usuario(2L, "Ana", "ana@correo.com", "123456", "ADMIN");

        when(usuarioService.listarTodos()).thenReturn(Arrays.asList(usuario1, usuario2));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario(1L, "Juan", "juan@correo.com", "123456", "CLIENTE");

        when(usuarioService.buscarPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.correo").value("juan@correo.com"));
    }

    @Test
    void testCrearUsuario() throws Exception {
        Usuario request = new Usuario(null, "Juan", "juan@correo.com", "123456", "CLIENTE");
        Usuario response = new Usuario(1L, "Juan", "juan@correo.com", "123456", "CLIENTE");

        when(usuarioService.guardar(any(Usuario.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.rol").value("CLIENTE"));
    }

    @Test
    void testActualizarUsuario() throws Exception {
        Usuario request = new Usuario(null, "Juan Actualizado", "juan@correo.com", "123456", "CLIENTE");
        Usuario response = new Usuario(1L, "Juan Actualizado", "juan@correo.com", "123456", "CLIENTE");

        when(usuarioService.actualizar(1L, request)).thenReturn(response);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan Actualizado"));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        doNothing().when(usuarioService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());

        verify(usuarioService).eliminar(1L);
    }
}
