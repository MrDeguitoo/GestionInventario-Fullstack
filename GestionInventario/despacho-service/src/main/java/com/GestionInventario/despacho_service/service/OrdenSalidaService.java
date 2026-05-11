package com.GestionInventario.despacho_service.service;

import com.GestionInventario.despacho_service.model.OrdenSalida;
import com.GestionInventario.despacho_service.repository.OrdenSalidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrdenSalidaService {
    @Autowired
    private OrdenSalidaRepository ordenSalidaRepository;

    public List<OrdenSalida> listarTodos() { return ordenSalidaRepository.findAll(); }

    public OrdenSalida buscarPorId(Long id) {
        return ordenSalidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de salida no encontrada"));
    }

    @Transactional
    public OrdenSalida guardar(OrdenSalida ordenSalida) { return ordenSalidaRepository.save(ordenSalida); }

    @Transactional
    public void eliminar(Long id) { ordenSalidaRepository.deleteById(id); }
}