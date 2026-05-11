package com.GestionInventario.usuario_service.repository;

import com.GestionInventario.usuario_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Consulta personalizada para buscar por correo (útil para login)
    Optional<Usuario> findByCorreo(String correo);
}