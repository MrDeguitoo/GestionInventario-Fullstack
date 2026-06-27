package com.GestionInventario.auditoria_service.service;

import com.GestionInventario.auditoria_service.model.AjusteInventario;
import com.GestionInventario.auditoria_service.repository.AjusteInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AjusteInventarioService {
    @Autowired
    private AjusteInventarioRepository ajusteInventarioRepository;

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