package com.GestionInventario.bodega_service.controller;

import com.GestionInventario.bodega_service.model.Bodega;
import com.GestionInventario.bodega_service.service.BodegaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bodegas")
public class BodegaController {
    private final BodegaService bodegaService;

    public BodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    @GetMapping
    public List<Bodega> getAll() { return bodegaService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<Bodega> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bodegaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Bodega> create(@Valid @RequestBody Bodega bodega) {
        return new ResponseEntity<>(bodegaService.guardar(bodega), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bodegaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}