package com.GestionInventario.notificacion_service.repository;

import com.GestionInventario.notificacion_service.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}