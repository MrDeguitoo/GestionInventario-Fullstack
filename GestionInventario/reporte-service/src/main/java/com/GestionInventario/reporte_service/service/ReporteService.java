package com.GestionInventario.reporte_service.service;

import com.GestionInventario.reporte_service.model.Reporte;
import com.GestionInventario.reporte_service.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    public Reporte buscarPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    @Transactional
    public Reporte guardar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Transactional
    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}