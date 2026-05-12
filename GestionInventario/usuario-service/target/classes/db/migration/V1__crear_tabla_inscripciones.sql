CREATE TABLE inscripciones (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               nombre_estudiante VARCHAR(100) NOT NULL,
                               curso VARCHAR(100) NOT NULL,
                               estado VARCHAR(50),
                               fecha DATE,
                               nivel VARCHAR(50)
);