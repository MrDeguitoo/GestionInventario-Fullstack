CREATE TABLE inventario (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            producto_id BIGINT NOT NULL,
                            bodega_id BIGINT NOT NULL,
                            cantidad INT NOT NULL DEFAULT 0,
                            stock_minimo INT DEFAULT 0,
                            stock_maximo INT DEFAULT 0,
                            fecha_actualizacion DATE
);