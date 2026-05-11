package com.GestionInventario.compra_service.service;

import com.GestionInventario.compra_service.model.OrdenCompra;
import com.GestionInventario.compra_service.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrdenCompraService {
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public List<OrdenCompra> listarTodos() { return ordenCompraRepository.findAll(); }

    public OrdenCompra buscarPorId(Long id) {
        return ordenCompraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden de compra no encontrada"));
    }

    @Transactional
    public OrdenCompra guardar(OrdenCompra ordenCompra) { return ordenCompraRepository.save(ordenCompra); }

    @Transactional
    public void eliminar(Long id) { ordenCompraRepository.deleteById(id); }
}