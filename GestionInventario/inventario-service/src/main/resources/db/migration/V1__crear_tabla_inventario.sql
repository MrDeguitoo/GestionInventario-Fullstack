CREATE TABLE stocks (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        producto_id BIGINT NOT NULL,
                        bodega_id BIGINT NOT NULL,
                        cantidad_disponible INT NOT NULL,
                        stock_minimo INT NOT NULL
);