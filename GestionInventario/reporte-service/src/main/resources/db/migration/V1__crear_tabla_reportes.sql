CREATE TABLE reportes (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre_reporte VARCHAR(100) NOT NULL,
                          tipo_reporte VARCHAR(50) NOT NULL,
                          fecha_generacion DATETIME,
                          url_descarga VARCHAR(255) NOT NULL
);