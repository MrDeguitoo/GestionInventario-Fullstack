package com.GestionInventario.compra_service.service;

import com.GestionInventario.compra_service.dto.*;
import com.GestionInventario.compra_service.model.OrdenCompra;
import com.GestionInventario.compra_service.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private WebClient webClient;

    public List<OrdenCompra> listarTodos() {
        return ordenCompraRepository.findAll();
    }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Orden de compra no encontrada con ID: " + id));
    }

    public void eliminar(Long id) {
        ordenCompraRepository.deleteById(id);
    }

    public OrdenCompra guardar(OrdenCompra orden) {
        if (orden.getEstado() == null || orden.getEstado().isEmpty()) {
            orden.setEstado("EN_PROCESO");
        }
        return ordenCompraRepository.save(orden);
    }

    public Mono<ResumenCompraDTO> simularOrdenCompraParalela(Long proveedorId, Long usuarioId, List<Long> productoIds) {

        ProveedorDTO fallbackProveedor = new ProveedorDTO(proveedorId, "Proveedor Temporalmente No Disponible", "", "", "");
        UsuarioDTO fallbackUsuario = new UsuarioDTO(usuarioId, "Usuario Autorizador No Disponible", "", "");
        List<ProductoDTO> fallbackProductos = Collections.emptyList();

        Mono<ProveedorDTO> proveedorMono = webClient.get()
                .uri("http://proveedor-service:8083/api/v1/proveedor/{id}", proveedorId)
                .retrieve()
                .bodyToMono(ProveedorDTO.class)
                .onErrorResume(e -> Mono.just(fallbackProveedor));

        Mono<UsuarioDTO> usuarioMono = webClient.get()
                .uri("http://usuario-service:8081/api/v1/usuarios/{id}", usuarioId)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .onErrorResume(e -> Mono.just(fallbackUsuario));

        String idsParam = productoIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        Mono<List<ProductoDTO>> productosMono = webClient.get()
                .uri("http://producto-service:8084/api/v1/productos?ids={ids}", idsParam)
                .retrieve()
                .bodyToFlux(ProductoDTO.class)
                .collectList()
                .onErrorResume(e -> Mono.just(fallbackProductos));

        return Mono.zip(proveedorMono, usuarioMono, productosMono)
                .map(tuple -> {
                    ProveedorDTO proveedor = tuple.getT1();
                    UsuarioDTO usuario = tuple.getT2();
                    List<ProductoDTO> productos = tuple.getT3();

                    double totalCalculado = 0.0;
                    if (productos != null) {
                        totalCalculado = productos.stream()
                                .filter(p -> p != null && p.getPrecioBase() != null)
                                .mapToDouble(ProductoDTO::getPrecioBase)
                                .sum();
                    }

                    ResumenCompraDTO resumen = new ResumenCompraDTO();
                    resumen.setProveedor(proveedor);
                    resumen.setUsuario(usuario);
                    resumen.setProductos(productos != null ? productos : fallbackProductos);
                    resumen.setTotal(totalCalculado);
                    resumen.setEstado("VISTA_PREVIA_SIMULADA");

                    return resumen;
                });
    }
}