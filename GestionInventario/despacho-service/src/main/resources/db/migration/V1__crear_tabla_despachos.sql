CREATE TABLE ordenes_salida (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                producto_id BIGINT NOT NULL,
                                bodega_id BIGINT NOT NULL,
                                cantidad INT NOT NULL,
                                destino VARCHAR(255) NOT NULL,
                                fecha_salida DATETIME,
                                tracking_code VARCHAR(255) NOT NULL
);