package com.GestionInventario.despacho_service.controller;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import com.GestionInventario.despacho_service.service.OrdenSalidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/despacho")
public class OrdenSalidaController {
    @Autowired
    private OrdenSalidaService ordenSalidaService;

    @GetMapping
    public List<OrdenSalida> getAll() { return ordenSalidaService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenSalida> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ordenSalidaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdenSalida> create(@Valid @RequestBody OrdenSalida ordenSalida) {
        return new ResponseEntity<>(ordenSalidaService.guardar(ordenSalida), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordenSalidaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}