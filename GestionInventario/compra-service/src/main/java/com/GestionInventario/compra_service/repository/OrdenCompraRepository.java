package com.GestionInventario.compra_service.repository;

import com.GestionInventario.compra_service.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
}