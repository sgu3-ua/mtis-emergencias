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

create table if not exists recursosHospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL,
    codigo INT NOT NULL,
    camasRequeridas INT NOT NULL,
    personalRequerido INT NOT NULL
);

CREATE TABLE IF NOT EXISTS paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    alergias VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS hospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL, -- Numero de camas disponibles
    camiones_ambulancia INT NOT NULL, -- Numero de ambulancias disponibles
    correo_electronico VARCHAR(255) NOT NULL,
    helipuerto BOOLEAN DEFAULT FALSE,
    personal_medico INT NOT NULL -- Numero de personal medico disponible
);

create table if not exists incidente_hospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    hospital_id INT,
    paciente_id INT,

    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

create table if not exists incidente_hospital_recursosHospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_hospital_id INT,
    recurso_id INT,

    FOREIGN KEY (incidente_hospital_id) REFERENCES incidente_hospital(id),
    FOREIGN KEY (recurso_id) REFERENCES recursosHospital(id)
);

create table if not exists notificacion ( -- Notificaciones a hospitales, bomberos, policia, etc.
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    mensaje TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(50) NOT NULL, -- Ejemplo: 'HOSPITAL', 'AMBULANCIA', 'POLICIA'
    FOREIGN KEY (incidente_id) REFERENCES incidente(id)
);

create table if not exists comisaria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL, -- Numero de agentes disponibles
    correo_electronico VARCHAR(255) NOT NULL
);

create table if not exists unidad_polica (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comisaria_id INT,
    cuerpo VARCHAR(50) NOT NULL, -- Ejemplo: 'local', 'nacional', 'guardia civil'
    disponibilidad BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (comisaria_id) REFERENCES comisaria(id)
);

create table if not exists registro_despliegue (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    unidad_polica_id INT,
    hora_despacho TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (unidad_polica_id) REFERENCES unidad_polica(id)
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

INSERT INTO hospital (
    nombre,
    direccion,
    capacidad,
    camiones_ambulancia,
    correo_electronico,
    helipuerto,
    personal_medico
)
VALUES (
    'Hospital General de Alicante',
    'Calle Pintor Baeza, Alicante',
    150,
    5,
    'hospital@alicante.es',
    FALSE,
    20
);

INSERT INTO hospital (
    nombre,
    direccion,
    capacidad,
    camiones_ambulancia,
    correo_electronico,
    helipuerto,
    personal_medico
)
VALUES (
    'Hospital Universitario de San Juan',
    'Calle Alicante, San Juan de Alicante',
    10,
    1,
    'hospital@sanjuan.es',
    FALSE,
    3
);

insert into recursosHospital (
    descripcion,
    codigo,
    camasRequeridas,
    personalRequerido
) values (
    'Cama de hospitalizacion',
    100,
    1,
    2
);

insert into recursosHospital (
    descripcion,
    codigo,
    camasRequeridas,
    personalRequerido
) values (
    'Unidad de cuidados intensivos',
    101,
    1,
    3
);

insert into recursosHospital (
    descripcion,
    codigo,
    camasRequeridas,
    personalRequerido
) values (
    'Equipo de cirugia',
    102,
    2,
    4
);

insert into recursosHospital (
    descripcion,
    codigo,
    camasRequeridas,
    personalRequerido
) values (
    'Equipo de reanimacion',
    103,
    1,
    2
);

insert into comisaria (
    nombre,
    direccion,
    capacidad,
    correo_electronico
) values (
    'Comisaria de Alicante',
    'Calle de la Policia, Alicante',
    50,
    'comisaria@alicante.es'
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    1,
    'local',
    TRUE
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    1,
    'nacional',
    TRUE
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    1,
    'guardia civil',
    TRUE
);

insert into comisaria (
    nombre,
    direccion,
    capacidad,
    correo_electronico
) values (
    'Comisaria de San Juan',
    'Calle de la Policia, San Juan de Alicante',
    20,
    'comisaria@sanjuan.es'
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    2,
    'local',
    TRUE
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    2,
    'nacional',
    TRUE
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    2,
    'guardia civil',
    TRUE
);

insert into comisaria (
    nombre,
    direccion,
    capacidad,
    correo_electronico
) values (
    'Comisaria de Elche',
    'Calle de la Policia, Elche',
    30,
    'comisaria@elche.es'
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    3,
    'local',
    TRUE
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    3,
    'nacional',
    TRUE
);

insert into unidad_polica (
    comisaria_id,
    cuerpo,
    disponibilidad
) values (
    3,
    'guardia civil',
    TRUE
);