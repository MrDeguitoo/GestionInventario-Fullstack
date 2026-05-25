CREATE TABLE bodegas (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL UNIQUE,
                         direccion VARCHAR(255) NOT NULL,
                         tipo VARCHAR(50)
);