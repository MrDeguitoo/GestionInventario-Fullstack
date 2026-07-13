package com.GestionInventario.auditoria_service.service;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import com.GestionInventario.auditoria_service.repository.AjusteInventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AjusteInventarioService {
    private final AjusteInventarioRepository ajusteInventarioRepository;

    public AjusteInventarioService(AjusteInventarioRepository ajusteInventarioRepository) {
        this.ajusteInventarioRepository = ajusteInventarioRepository;
    }

    public List<AjusteInventario> listarTodos() { return ajusteInventarioRepository.findAll(); }

    public AjusteInventario buscarPorId(Long id) {
        return ajusteInventarioRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Auditoria no encontrada"));
    }

    @Transactional
    public AjusteInventario guardar(AjusteInventario ajusteInventario) { return ajusteInventarioRepository.save(ajusteInventario); }

    @Transactional
    public void eliminar(Long id) { ajusteInventarioRepository.deleteById(id); }
}