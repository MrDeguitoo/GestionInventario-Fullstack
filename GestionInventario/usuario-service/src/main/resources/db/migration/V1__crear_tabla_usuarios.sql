CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          correo VARCHAR(100) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL,
                          rol VARCHAR(50) NOT NULL
);