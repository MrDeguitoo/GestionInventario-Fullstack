package com.GestionInventario.auditoria_service.repository;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AjusteInventarioRepository extends JpaRepository<AjusteInventario, Long> {
}