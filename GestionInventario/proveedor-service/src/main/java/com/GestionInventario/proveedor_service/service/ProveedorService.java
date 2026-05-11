package com.GestionInventario.proveedor_service.service;

import com.GestionInventario.proveedor_service.model.Proveedor;
import com.GestionInventario.proveedor_service.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listarTodos() { return proveedorRepository.findAll(); }

    public Proveedor buscarPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @Transactional
    public Proveedor guardar(Proveedor proveedor) { return proveedorRepository.save(proveedor); }

    @Transactional
    public void eliminar(Long id) { proveedorRepository.deleteById(id); }
}