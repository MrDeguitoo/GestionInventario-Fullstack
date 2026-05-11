package com.GestionInventario.producto_service.service;

import com.GestionInventario.producto_service.model.Producto;
import com.GestionInventario.producto_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Transactional
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}