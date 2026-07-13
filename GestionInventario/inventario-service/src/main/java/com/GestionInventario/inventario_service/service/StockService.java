package com.GestionInventario.inventario_service.service;

import com.GestionInventario.inventario_service.model.Stock;
import com.GestionInventario.inventario_service.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public Stock actualizarStock(Long productoId, Long bodegaId, Integer cantidad) {
        Stock stock = stockRepository.findByProductoIdAndBodegaId(productoId, bodegaId)
                .orElse(new Stock(null, productoId, bodegaId, 0, 5));

        stock.setCantidadDisponible(stock.getCantidadDisponible() + cantidad);

        if (stock.getCantidadDisponible() < 0) {
            throw new IllegalArgumentException("No hay suficiente stock para realizar esta operación. Producto ID: " + productoId);
        }

        return stockRepository.save(stock);
    }
}