package com.GestionInventario.despacho_service.repository;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenSalidaRepository extends JpaRepository<OrdenSalida, Long> {
}