package com.GestionInventario.auditoria_service.controller;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import com.GestionInventario.auditoria_service.service.AjusteInventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auditoria")
public class AjusteInventarioController {
    @Autowired
    private AjusteInventarioService ajusteInventarioService;

    @GetMapping
    public List<AjusteInventario> getAll() { return ajusteInventarioService.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<AjusteInventario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ajusteInventarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AjusteInventario> create(@Valid @RequestBody AjusteInventario ajusteInventario) {
        return new ResponseEntity<>(ajusteInventarioService.guardar(ajusteInventario), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ajusteInventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}