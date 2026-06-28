package com.GestionInventario.usuario_service.service;

import com.GestionInventario.usuario_service.model.Usuario;
import com.GestionInventario.usuario_service.repository.UsuarioRepository;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Debería asignar rol CLIENTE por defecto si se registra sin rol")
    void deberiaAsignarRolPorDefecto() {
        Usuario entrada = new Usuario();
        entrada.setNombre("Juan Perez");
        entrada.setCorreo("juan@correo.com");
        entrada.setRol("");

        Usuario guardado = new Usuario();
        guardado.setId(1L);
        guardado.setNombre("Juan Perez");
        guardado.setCorreo("juan@correo.com");
        guardado.setRol("CLIENTE");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(guardado);

        Usuario resultado = usuarioService.guardarUsuario(entrada);

        assertNotNull(resultado);
        assertEquals("CLIENTE", resultado.getRol());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}
