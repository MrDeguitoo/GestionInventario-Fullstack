CREATE TABLE ajustes_inventario (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    producto_id BIGINT NOT NULL,
                                    bodega_id BIGINT NOT NULL,
                                    cantidad_ajustada INT NOT NULL,
                                    motivo VARCHAR(255) NOT NULL,
                                    usuario_aprobador VARCHAR(100) NOT NULL,
                                    fecha_ajuste DATETIME
);