package com.GestionInventario.notificacion_service.service;

import com.GestionInventario.notificacion_service.model.Notificacion;
import com.GestionInventario.notificacion_service.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository; // <-- Corregido con 'n' minúscula

    public List<Notificacion> listarTodos() {
        return notificacionRepository.findAll();
    }

    public Notificacion buscarPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
    }

    @Transactional
    public Notificacion guardar(Notificacion notificacion) { // <-- Nombre de variable corregido
        return notificacionRepository.save(notificacion);
    }

    @Transactional
    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}