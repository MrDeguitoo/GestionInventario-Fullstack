package com.GestionInventario.notificacion_service.service;

import com.GestionInventario.notificacion_service.model.Notificacion;
import com.GestionInventario.notificacion_service.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public List<Notificacion> listarTodos() {
        return notificacionRepository.findAll();
    }

    public Notificacion buscarPorId(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Notificacion no encontrada"));
    }

    @Transactional
    public Notificacion guardar(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Transactional
    public Notificacion enviarNotificacion(Notificacion notificacion) {
        notificacion.setEstadoEnvio("ENVIADO");

        return notificacionRepository.save(notificacion);
    }

    @Transactional
    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }
}