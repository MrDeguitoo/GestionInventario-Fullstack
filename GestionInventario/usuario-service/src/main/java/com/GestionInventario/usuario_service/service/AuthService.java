package com.GestionInventario.usuario_service.service;

import com.GestionInventario.usuario_service.model.Usuario;
import com.GestionInventario.usuario_service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String login(String correo, String contrasenaIngresada) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas (Usuario no encontrado)"));
        if (!passwordEncoder.matches(contrasenaIngresada, usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas (Contraseña inválida)");
        }
        return jwtService.generateToken(usuario.getCorreo());
    }
}