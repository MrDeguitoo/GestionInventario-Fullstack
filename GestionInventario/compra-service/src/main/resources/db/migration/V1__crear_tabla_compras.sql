CREATE TABLE compras (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         proveedor_id BIGINT NOT NULL,
                         usuario_id BIGINT NOT NULL,
                         fecha_compra DATE NOT NULL,
                         total DECIMAL(10,2) NOT NULL,
                         estado VARCHAR(50) DEFAULT 'PENDIENTE'
);