package com.GestionInventario.inventario_service.controller;

import com.GestionInventario.inventario_service.model.Stock;
import com.GestionInventario.inventario_service.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventario")
public class StockController {

    @Autowired
    private StockService stockService;

    // Endpoint para actualizar el stock (sumar o restar)
    @PutMapping("/actualizar")
    public ResponseEntity<Stock> actualizarStock(
            @RequestParam Long productoId,
            @RequestParam Long bodegaId,
            @RequestParam Integer cantidad) {

        Stock stockActualizado = stockService.actualizarStock(productoId, bodegaId, cantidad);
        return ResponseEntity.ok(stockActualizado);
    }
}