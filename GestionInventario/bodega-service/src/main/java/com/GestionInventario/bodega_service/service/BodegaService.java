package com.GestionInventario.bodega_service.service;

import com.GestionInventario.bodega_service.model.Bodega;
import com.GestionInventario.bodega_service.repository.BodegaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Implementamos la mejor práctica de Lombok
public class BodegaService {

    private final BodegaRepository bodegaRepository;

    public List<Bodega> listarTodos() {
        return bodegaRepository.findAll();
    }

    public Bodega buscarPorId(Long id) {
        return bodegaRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Bodega no encontrada"));
    }

    @Transactional
    public Bodega guardar(Bodega bodega) {
        // Antes de guardar, podríamos usar el nuevo método aquí mismo si quisiéramos
        return bodegaRepository.save(bodega);
    }

    // ¡NUEVO MÉTODO QUE EXIGE LA PRUEBA!
    public boolean verificarEspacio(int capacidadMaxima, int volumenActual, int nuevoIngreso) {
        // Si la suma del volumen actual más lo nuevo es menor o igual a la capacidad, hay espacio (true)
        return (volumenActual + nuevoIngreso) <= capacidadMaxima;
    }

    @Transactional
    public void eliminar(Long id) {
        bodegaRepository.deleteById(id);
    }
}