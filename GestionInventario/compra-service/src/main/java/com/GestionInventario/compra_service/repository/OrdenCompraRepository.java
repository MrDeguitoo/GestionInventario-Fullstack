package com.GestionInventario.compra_service.repository;

import com.GestionInventario.compra_service.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
}