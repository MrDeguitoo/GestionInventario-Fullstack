package com.GestionInventario.bodega_service.service;

import com.GestionInventario.bodega_service.model.Bodega;
import com.GestionInventario.bodega_service.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BodegaService {
    @Autowired
    private BodegaRepository bodegaRepository;

    public List<Bodega> listarTodos() { return bodegaRepository.findAll(); }

    public Bodega buscarPorId(Long id) {
        return bodegaRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Bodega no encontrada"));
    }

    @Transactional
    public Bodega guardar(Bodega bodega) { return bodegaRepository.save(bodega); }

    @Transactional
    public void eliminar(Long id) { bodegaRepository.deleteById(id); }
}