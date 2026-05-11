package com.GestionInventario.proveedor_service.controller;

import com.GestionInventario.proveedor_service.model.Proveedor;
import com.GestionInventario.proveedor_service.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getAll() { return proveedorService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable Long id) {
        return ResponseEntity.ok(proveedorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Proveedor> create(@Valid @RequestBody Proveedor proveedor) {
        return new ResponseEntity<>(proveedorService.guardar(proveedor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}