CREATE TABLE productos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           descripcion VARCHAR(255),
                           precio DECIMAL(10,2) NOT NULL,
                           stock INT NOT NULL DEFAULT 0,
                           categoria VARCHAR(50),
                           activo BOOLEAN DEFAULT TRUE
);