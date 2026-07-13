package com.GestionInventario.proveedor_service.controller;

import com.GestionInventario.proveedor_service.model.Proveedor;
import com.GestionInventario.proveedor_service.service.ProveedorService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProveedorController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProveedorService proveedorService;

    @Test
    void testListarProveedores() throws Exception {
        Proveedor proveedor1 = new Proveedor(1L, "123", "Proveedor A", "proveedorA@test.com", "123456789");
        Proveedor proveedor2 = new Proveedor(2L, "456", "Proveedor B", "proveedorB@test.com", "987654321");

        when(proveedorService.listarTodos()).thenReturn(Arrays.asList(proveedor1, proveedor2));

        mockMvc.perform(get("/api/v1/proveedor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].razonSocial").value("Proveedor A"));
    }

    @Test
    void testObtenerProveedorPorId() throws Exception {
        Proveedor proveedor = new Proveedor(1L, "123", "Proveedor A", "proveedorA@test.com", "123456789");

        when(proveedorService.buscarPorId(1L)).thenReturn(proveedor);

        mockMvc.perform(get("/api/v1/proveedor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.correoVentas").value("proveedorA@test.com"));
    }

    @Test
    void testCrearProveedor() throws Exception {
        Proveedor request = new Proveedor(null, "789", "Proveedor C", "proveedorC@test.com", "111222333");
        Proveedor response = new Proveedor(1L, "789", "Proveedor C", "proveedorC@test.com", "111222333");

        when(proveedorService.guardar(any(Proveedor.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/proveedor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.razonSocial").value("Proveedor C"));
    }

    @Test
    void testEliminarProveedor() throws Exception {
        doNothing().when(proveedorService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/proveedor/1"))
                .andExpect(status().isNoContent());

        verify(proveedorService).eliminar(1L);
    }
}
