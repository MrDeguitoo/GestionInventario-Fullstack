CREATE TABLE notificaciones (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                usuario_id BIGINT NOT NULL,
                                titulo VARCHAR(100) NOT NULL,
                                mensaje VARCHAR(500) NOT NULL,
                                tipo VARCHAR(50),
                                leido BOOLEAN DEFAULT FALSE,
                                fecha DATETIME NOT NULL
);