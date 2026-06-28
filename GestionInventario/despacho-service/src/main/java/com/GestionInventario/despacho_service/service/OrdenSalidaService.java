package com.GestionInventario.despacho_service.service;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import com.GestionInventario.despacho_service.repository.OrdenSalidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdenSalidaService {

    private final OrdenSalidaRepository ordenSalidaRepository;

    public List<OrdenSalida> listarTodos() {
        return ordenSalidaRepository.findAll();
    }

    public OrdenSalida buscarPorId(Long id) {
        return ordenSalidaRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Orden de salida no encontrada"));
    }

    @Transactional
    public OrdenSalida guardar(OrdenSalida ordenSalida) {
        return ordenSalidaRepository.save(ordenSalida);
    }

    @Transactional
    public OrdenSalida procesarDespacho(OrdenSalida ordenSalida) {
        String codigoGenerado = "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        ordenSalida.setTrackingCode(codigoGenerado);

        return ordenSalidaRepository.save(ordenSalida);
    }

    @Transactional
    public void eliminar(Long id) {
        ordenSalidaRepository.deleteById(id);
    }
}