CREATE TABLE proveedores (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             identificacion VARCHAR(20) NOT NULL UNIQUE,
                             razon_social VARCHAR(150) NOT NULL,
                             correo_ventas VARCHAR(100) NOT NULL,
                             telefono VARCHAR(20) NOT NULL
);