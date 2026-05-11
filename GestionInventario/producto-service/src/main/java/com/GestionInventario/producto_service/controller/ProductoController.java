package com.GestionInventario.producto_service.controller;

import com.GestionInventario.producto_service.model.Producto;
import com.GestionInventario.producto_service.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Producto> create(@Valid @RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.guardar(producto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}