-- Inicialización de la base de datos
DROP DATABASE IF EXISTS sistema_emergencias;

CREATE DATABASE sistema_emergencias;

USE sistema_emergencias;

CREATE TABLE IF NOT EXISTS aviso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    telefono VARCHAR(20) NOT NULL,
    localizacion VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    afectados INT DEFAULT 0,
    hay_fuego BOOLEAN DEFAULT FALSE,
    hay_humo BOOLEAN DEFAULT FALSE,
    hay_explosion BOOLEAN DEFAULT FALSE,
    personas_atrapadas BOOLEAN DEFAULT FALSE,
    personas_heridas BOOLEAN DEFAULT FALSE,
    riesgo_seguridad BOOLEAN DEFAULT FALSE,
    alteracion_orden_publico BOOLEAN DEFAULT FALSE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS incidente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    requiere_bomberos BOOLEAN DEFAULT FALSE,
    requiere_policia BOOLEAN DEFAULT FALSE,
    requiere_sanitarios BOOLEAN DEFAULT FALSE,
    urgencia VARCHAR(20) NOT NULL,
    descripcion TEXT NOT NULL,
    localizacion VARCHAR(255) NOT NULL,
    estado VARCHAR(30) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS incidente_aviso (
    incidente_id INT,
    aviso_id INT,
    PRIMARY KEY (incidente_id, aviso_id),
    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (aviso_id) REFERENCES aviso(id)
);

-- Meter datos de prueba
INSERT INTO incidente (
    requiere_bomberos,
    requiere_policia,
    requiere_sanitarios,
    urgencia,
    descripcion,
    localizacion,
    estado
)
VALUES (
    TRUE,
    TRUE,
    TRUE,
    'ALTA',
    'Accidente de trafico con incendio y varios heridos',
    'Avenida de Denia, Alicante',
    'ACTIVO'
);

INSERT INTO aviso (
    telefono,
    localizacion,
    descripcion,
    afectados,
    hay_fuego,
    hay_humo,
    hay_explosion,
    personas_atrapadas,
    personas_heridas,
    riesgo_seguridad,
    alteracion_orden_publico
)
VALUES (
    '600123123',
    'Avenida de Denia, Alicante',
    'Vehiculo ardiendo tras colision',
    3,
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    FALSE,
    FALSE
);

INSERT INTO aviso (
    telefono,
    localizacion,
    descripcion,
    afectados,
    hay_fuego,
    hay_humo,
    hay_explosion,
    personas_atrapadas,
    personas_heridas,
    riesgo_seguridad,
    alteracion_orden_publico
)
VALUES (
    '611987654',
    'Avenida de Denia, Alicante',
    'Hay personas heridas y mucho humo en la carretera',
    2,
    TRUE,
    TRUE,
    FALSE,
    FALSE,
    TRUE,
    TRUE,
    FALSE
);

INSERT INTO incidente_aviso (incidente_id, aviso_id)
VALUES 
    (1, 1),
    (1, 2);
