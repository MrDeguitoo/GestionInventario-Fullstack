package com.GestionInventario.inventario_service.repository;

import com.GestionInventario.inventario_service.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductoIdAndBodegaId(Long productoId, Long bodegaId);
}