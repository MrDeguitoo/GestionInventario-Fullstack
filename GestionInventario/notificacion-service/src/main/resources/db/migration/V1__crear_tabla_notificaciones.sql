CREATE TABLE notificaciones (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                correo_destino VARCHAR(100) NOT NULL,
                                asunto VARCHAR(255) NOT NULL,
                                mensaje VARCHAR(500) NOT NULL,
                                fecha_envio DATETIME,
                                estado_envio VARCHAR(50) NOT NULL
);