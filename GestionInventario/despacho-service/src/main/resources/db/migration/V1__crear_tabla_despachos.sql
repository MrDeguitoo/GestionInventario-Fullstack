CREATE TABLE despachos (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           usuario_id BIGINT NOT NULL,
                           bodega_id BIGINT NOT NULL,
                           fecha_despacho DATE NOT NULL,
                           estado VARCHAR(50) DEFAULT 'PENDIENTE',
                           direccion_destino VARCHAR(255)
);