package com.GestionInventario.compra_service.controller;

import com.GestionInventario.compra_service.dto.ProductoDTO;
import com.GestionInventario.compra_service.dto.ProveedorDTO;
import com.GestionInventario.compra_service.dto.ResumenCompraDTO;
import com.GestionInventario.compra_service.dto.UsuarioDTO;
import com.GestionInventario.compra_service.model.OrdenCompra;
import com.GestionInventario.compra_service.service.OrdenCompraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdenCompraController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrdenCompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrdenCompraService ordenCompraService;

    @Test
    void testListarOrdenesCompra() throws Exception {
        OrdenCompra orden1 = new OrdenCompra(1L, 10L, LocalDateTime.now(), "EN_PROCESO", 5000.0, "FAC-001", null);
        OrdenCompra orden2 = new OrdenCompra(2L, 11L, LocalDateTime.now(), "CERRADA", 12000.0, "FAC-002", null);

        when(ordenCompraService.listarTodos()).thenReturn(List.of(orden1, orden2));

        mockMvc.perform(get("/api/v1/compras"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].estado").value("EN_PROCESO"));
    }

    @Test
    void testObtenerOrdenCompraPorId() throws Exception {
        OrdenCompra orden = new OrdenCompra(1L, 10L, LocalDateTime.now(), "EN_PROCESO", 5000.0, "FAC-001", null);

        when(ordenCompraService.buscarPorId(1L)).thenReturn(orden);

        mockMvc.perform(get("/api/v1/compras/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numeroFactura").value("FAC-001"));
    }

    @Test
    void testCrearOrdenCompra() throws Exception {
        OrdenCompra request = new OrdenCompra(null, 10L, LocalDateTime.now(), "", 5000.0, "FAC-001", null);
        OrdenCompra response = new OrdenCompra(1L, 10L, LocalDateTime.now(), "EN_PROCESO", 5000.0, "FAC-001", null);

        when(ordenCompraService.guardar(any(OrdenCompra.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/compras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("EN_PROCESO"));
    }

    @Test
    void testEliminarOrdenCompra() throws Exception {
        doNothing().when(ordenCompraService).eliminar(1L);

        mockMvc.perform(delete("/api/v1/compras/1"))
                .andExpect(status().isNoContent());

        verify(ordenCompraService).eliminar(1L);
    }

    @Test
    void testSimularCompraParalela() throws Exception {
        ResumenCompraDTO resumen = new ResumenCompraDTO();
        resumen.setEstado("VISTA_PREVIA_SIMULADA");
        resumen.setTotal(1500.0);
        resumen.setProveedor(new ProveedorDTO(10L, "Proveedor Demo", "", "", ""));
        resumen.setUsuario(new UsuarioDTO(2L, "Usuario Demo", "", ""));
        resumen.setProductos(List.of(new ProductoDTO(100L, "SKU-1", "Producto 1", 1500.0)));

        when(ordenCompraService.simularOrdenCompraParalela(anyLong(), anyLong(), anyList()))
                .thenReturn(Mono.just(resumen));

        mockMvc.perform(post("/api/v1/compras/simular-paralelo")
                        .param("proveedorId", "10")
                        .param("usuarioId", "2")
                        .param("productoIds", "100", "101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("VISTA_PREVIA_SIMULADA"));
    }
}
