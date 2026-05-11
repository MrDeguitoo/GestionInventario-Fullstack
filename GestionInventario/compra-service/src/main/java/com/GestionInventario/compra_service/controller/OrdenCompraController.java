package com.GestionInventario.compra_service.controller;

import com.GestionInventario.compra_service.model.OrdenCompra;
import com.GestionInventario.compra_service.service.OrdenCompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/compras")
public class OrdenCompraController {
    @Autowired
    private OrdenCompraService ordenCompraService;

    @GetMapping
    public List<OrdenCompra> getAll() { return ordenCompraService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ordenCompraService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdenCompra> create(@Valid @RequestBody OrdenCompra ordenCompra) {
        return new ResponseEntity<>(ordenCompraService.guardar(ordenCompra), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordenCompraService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}