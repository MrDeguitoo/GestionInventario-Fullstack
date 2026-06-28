package com.GestionInventario.proveedor_service.service;

import com.GestionInventario.proveedor_service.model.Proveedor;
import com.GestionInventario.proveedor_service.repository.ProveedorRepository;
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
public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    @DisplayName("Debería convertir la identificación del proveedor a mayúsculas al guardar")
    void deberiaConvertirIdentificacionAMayusculas() {
        Proveedor entrada = new Proveedor();
        entrada.setRazonSocial("Proveedor Alfa");
        entrada.setIdentificacion("12345678-k"); // minúscula

        Proveedor guardado = new Proveedor();
        guardado.setId(1L);
        guardado.setRazonSocial("Proveedor Alfa");
        guardado.setIdentificacion("12345678-K"); // Mayúscula esperada

        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(guardado);

        Proveedor resultado = proveedorService.registrarProveedor(entrada);

        assertEquals("12345678-K", resultado.getIdentificacion());
    }
}