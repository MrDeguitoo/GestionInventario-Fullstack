CREATE TABLE auditoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           usuario_id BIGINT NOT NULL,
                           accion VARCHAR(100) NOT NULL,
                           entidad VARCHAR(100) NOT NULL,
                           entidad_id BIGINT,
                           detalle VARCHAR(500),
                           fecha DATETIME NOT NULL
);