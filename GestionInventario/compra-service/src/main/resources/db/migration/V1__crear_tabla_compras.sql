CREATE TABLE ordenes_compra (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                proveedor_id BIGINT NOT NULL,
                                fecha_orden DATETIME NOT NULL,
                                estado VARCHAR(50) NOT NULL,
                                total DOUBLE NOT NULL,
                                numero_factura VARCHAR(50) NULL,
                                fecha_recepcion DATETIME NULL
);