package com.GestionInventario.notificacion_service;

import com.GestionInventario.notificacion_service.model.Notificacion;
import com.GestionInventario.notificacion_service.repository.NotificacionRepository;
import com.GestionInventario.notificacion_service.service.NotificacionService; // Importación necesaria
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionServiceApplicationTests {

    @Mock
    private NotificacionRepository notificacionRepository;

    @InjectMocks
    private NotificacionService notificacionService;

    @Test
    @DisplayName("Debería marcar una notificación como enviada con éxito")
    void deberiaMarcarComoEnviada() {
        Notificacion notificacion = new Notificacion();
        notificacion.setCorreoDestino("user@test.com");
        notificacion.setAsunto("Actualización de orden");
        notificacion.setMensaje("Tu orden fue procesada");
        notificacion.setEstadoEnvio("PENDIENTE");

        Notificacion guardada = new Notificacion();
        guardada.setId(1L);
        guardada.setEstadoEnvio("ENVIADO");

        when(notificacionRepository.save(any(Notificacion.class))).thenReturn(guardada);

        Notificacion resultado = notificacionService.enviarNotificacion(notificacion);
        
        assertEquals("ENVIADO", resultado.getEstadoEnvio());
    }
}