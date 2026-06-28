package com.GestionInventario.auditoria_service;

import com.GestionInventario.auditoria_service.model.Auditoria;
import com.GestionInventario.auditoria_service.repository.AuditoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditoriaServiceTest {

    @Mock
    private AuditoriaRepository auditoriaRepository;

    @InjectMocks
    private AuditoriaService auditoriaService;

    @Test
    @DisplayName("Debería registrar la traza de auditoría con la fecha y hora actual")
    void deberiaRegistrarFechaHora() {
        Auditoria entrada = new Auditoria();
        entrada.setAccion("LOGIN");

        Auditoria guardada = new Auditoria();
        guardada.setId(123L);
        guardada.setAccion("LOGIN");
        guardada.setFechaRegistro(LocalDateTime.now());

        when(auditoriaRepository.save(any(Auditoria.class))).thenReturn(guardada);

        Auditoria resultado = auditoriaService.registrarAccion(entrada);

        assertNotNull(resultado.getFechaRegistro(), "La fecha de registro no debe ser nula");
        verify(auditoriaRepository, times(1)).save(any(Auditoria.class));
    }
}