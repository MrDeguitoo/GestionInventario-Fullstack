package com.GestionInventario.auditoria_service.repository;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjusteInventarioRepository extends JpaRepository<AjusteInventario, Long> {
}