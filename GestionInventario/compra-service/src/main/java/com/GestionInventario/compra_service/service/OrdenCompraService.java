package com.GestionInventario.compra_service.service;

import com.GestionInventario.compra_service.dto.*;
import com.GestionInventario.compra_service.model.OrdenCompra;
import com.GestionInventario.compra_service.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private WebClient webClient;

    public List<OrdenCompra> listarTodos() { return ordenCompraRepository.findAll(); }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada"));
    }

    @Transactional
    public OrdenCompra guardar(OrdenCompra ordenCompra) { return ordenCompraRepository.save(ordenCompra); }

    @Transactional
    public void eliminar(Long id) { ordenCompraRepository.deleteById(id); }

    public Mono<ResumenCompraDTO> simularOrdenCompraParalela(Long proveedorId, Long usuarioId, List<Long> productoIds) {

        Mono<ProveedorDTO> llamadaProveedor = webClient.get()
                .uri("http://localhost:8084/api/v1/proveedores/{id}", proveedorId)
                .retrieve()
                .bodyToMono(ProveedorDTO.class)
                .onErrorReturn(new ProveedorDTO(0L, "00.000.000-0", "Proveedor No Disponible (Error de Conexión)", "", ""));

        Mono<UsuarioDTO> llamadaUsuario = webClient.get()
                .uri("http://localhost:8090/api/v1/usuarios/{id}", usuarioId)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .onErrorReturn(new UsuarioDTO(0L, "Usuario No Disponible", "", ""));

        String idsParam = productoIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        Mono<List<ProductoDTO>> llamadaProductos = webClient.get()
                .uri("http://localhost:8081/api/v1/productos?ids={ids}", idsParam)
                .retrieve()
                .bodyToFlux(ProductoDTO.class)
                .collectList()
                .onErrorReturn(List.of());

        return Mono.zip(llamadaProveedor, llamadaUsuario, llamadaProductos)
                .map(tupla -> {
                    ProveedorDTO proveedor = tupla.getT1();
                    UsuarioDTO usuario = tupla.getT2();
                    List<ProductoDTO> productos = tupla.getT3();

                    double total = productos.stream().mapToDouble(ProductoDTO::precio).sum();

                    return new ResumenCompraDTO(proveedor, usuario, productos, total);
                });
    }}