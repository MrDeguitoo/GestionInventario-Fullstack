package com.GestionInventario.bodega_service.repository;

import com.GestionInventario.bodega_service.model.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}