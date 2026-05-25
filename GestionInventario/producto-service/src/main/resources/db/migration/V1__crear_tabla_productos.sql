CREATE TABLE productos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           sku VARCHAR(50) NOT NULL UNIQUE,
                           nombre VARCHAR(150) NOT NULL,
                           descripcion VARCHAR(500),
                           precio_base DOUBLE NOT NULL
);