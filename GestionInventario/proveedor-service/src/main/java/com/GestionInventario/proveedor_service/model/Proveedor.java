package com.GestionInventario.proveedor_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT/NIT de la empresa es obligatorio")
    @Column(unique = true, nullable = false, length = 20)
    private String identificacion;

    @NotBlank(message = "La razón social es obligatoria")
    @Column(nullable = false, length = 150)
    private String razonSocial;

    @NotBlank(message = "El correo es obligatorio")
    @Email
    @Column(nullable = false, length = 100)
    private String correoVentas;

    @Column(nullable = false, length = 20)
    private String telefono;
}