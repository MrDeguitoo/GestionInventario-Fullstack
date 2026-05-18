CREATE TABLE bodegas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         ubicacion VARCHAR(255),
                         capacidad INT,
                         activo BOOLEAN DEFAULT TRUE
);