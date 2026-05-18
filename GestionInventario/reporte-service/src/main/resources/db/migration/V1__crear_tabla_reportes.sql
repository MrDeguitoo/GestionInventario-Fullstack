CREATE TABLE reportes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          titulo VARCHAR(100) NOT NULL,
                          tipo VARCHAR(50) NOT NULL,
                          usuario_id BIGINT NOT NULL,
                          fecha_generacion DATETIME NOT NULL,
                          contenido TEXT
);