-- Inicialización de la base de datos
DROP DATABASE IF EXISTS sistema_emergencias;

CREATE DATABASE sistema_emergencias;

USE sistema_emergencias;

CREATE TABLE IF NOT EXISTS aviso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    telefono VARCHAR(20) NOT NULL,
    localizacion VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    afectados INT DEFAULT NULL,
    hay_fuego BOOLEAN DEFAULT FALSE,
    hay_humo BOOLEAN DEFAULT FALSE,
    personas_atrapadas BOOLEAN DEFAULT FALSE,
    personas_heridas BOOLEAN DEFAULT FALSE,
    riesgo_seguridad BOOLEAN DEFAULT FALSE,
    riesgo_estructural BOOLEAN DEFAULT FALSE,
    alteracion_orden_publico BOOLEAN DEFAULT FALSE,
    hora_llamada TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS incidente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    requiere_bomberos BOOLEAN DEFAULT FALSE,
    requiere_policia BOOLEAN DEFAULT FALSE,
    requiere_sanitarios BOOLEAN DEFAULT FALSE,
    urgencia VARCHAR(20) NOT NULL,
    descripcion TEXT NOT NULL,
    localizacion VARCHAR(255) NOT NULL,
    estado VARCHAR(30) DEFAULT 'ACTIVO'
);

CREATE TABLE IF NOT EXISTS incidente_aviso (
    incidente_id INT,
    aviso_id INT,
    PRIMARY KEY (incidente_id, aviso_id),
    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (aviso_id) REFERENCES aviso(id)
);

CREATE TABLE IF NOT EXISTS recursosHospital (
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
    capacidad INT NOT NULL,
    camiones_ambulancia INT NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    helipuerto BOOLEAN DEFAULT FALSE,
    personal_medico INT NOT NULL
);

CREATE TABLE IF NOT EXISTS incidente_hospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    hospital_id INT,
    paciente_id INT,
    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

CREATE TABLE IF NOT EXISTS incidente_hospital_recursosHospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_hospital_id INT,
    recurso_id INT,
    FOREIGN KEY (incidente_hospital_id) REFERENCES incidente_hospital(id),
    FOREIGN KEY (recurso_id) REFERENCES recursosHospital(id)
);

CREATE TABLE IF NOT EXISTS notificacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    mensaje TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (incidente_id) REFERENCES incidente(id)
);

CREATE TABLE IF NOT EXISTS comisaria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS unidad_policia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comisaria_id INT,
    cuerpo VARCHAR(50) NOT NULL,
    disponibilidad BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (comisaria_id) REFERENCES comisaria(id)
);

CREATE TABLE IF NOT EXISTS registro_despliegue (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    unidad_policia_id INT,
    hora_despacho TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (unidad_policia_id) REFERENCES unidad_policia(id)
);

CREATE TABLE IF NOT EXISTS parque_bomberos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    capacidad_vehiculos INT NOT NULL
);

CREATE TABLE IF NOT EXISTS vehiculo_bomberos (
    idVehiculo INT AUTO_INCREMENT PRIMARY KEY,
    parque_id INT,
    tipo VARCHAR(50) NOT NULL,
    estado VARCHAR(50) DEFAULT 'disponible',
    matricula VARCHAR(20) NOT NULL,
    FOREIGN KEY (parque_id) REFERENCES parque_bomberos(id)
);

create table if not exists registro_despliegue_bomberos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    vehiculo_bomberos_id INT,
    hora_despacho TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (vehiculo_bomberos_id) REFERENCES vehiculo_bomberos(idVehiculo)
);

CREATE TABLE IF NOT EXISTS expediente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    clasificacion VARCHAR(100),
    estado VARCHAR(30) DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre TIMESTAMP NULL,
    observaciones TEXT,
    FOREIGN KEY (incidente_id) REFERENCES incidente(id)
);

CREATE TABLE IF NOT EXISTS dato_servicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    expediente_id INT,
    servicio VARCHAR(100) NOT NULL,
    hora_salida TIMESTAMP NULL,
    hora_llegada TIMESTAMP NULL,
    actuaciones TEXT,
    recursos_utilizados TEXT,
    observaciones TEXT,
    FOREIGN KEY (expediente_id) REFERENCES expediente(id)
);

CREATE TABLE IF NOT EXISTS informe (
    id INT AUTO_INCREMENT PRIMARY KEY,
    expediente_id INT,
    tipo VARCHAR(50) NOT NULL,
    resumen TEXT NOT NULL,
    fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (expediente_id) REFERENCES expediente(id)
);

-- DATOS DE PRUEBA EXTENSOS


-- INCIDENTES (20 registros)

INSERT INTO incidente (requiere_bomberos, requiere_policia, requiere_sanitarios, urgencia, descripcion, localizacion, estado) VALUES
(TRUE,  TRUE,  TRUE,  'ALTA',   'Accidente de tráfico con incendio y varios heridos', 'Avenida de Denia, Alicante', 'ACTIVO'),
(TRUE,  TRUE,  FALSE, 'ALTA',   'Incendio forestal en zona de montaña', 'Sierra de Aitana, Alicante', 'ACTIVO'),
(FALSE, TRUE,  FALSE, 'MEDIA',  'Robo con fuerza en vivienda', 'Calle del Mar, Alicante', 'RESUELTO'),
(FALSE, FALSE, TRUE,  'MEDIA',  'Persona mayor desmayada en domicilio', 'Plaza de la Montañeta, Alicante', 'ACTIVO'),
(TRUE,  TRUE,  TRUE,  'ALTA',   'Explosión en fábrica de productos químicos', 'Polígono Industrial de Elche', 'ACTIVO'),
(FALSE, TRUE,  FALSE, 'BAJA',   'Altercado en local de ocio nocturno', 'Calle Castaños, Alicante', 'RESUELTO'),
(TRUE,  FALSE, TRUE,  'ALTA',   'Colapso parcial de edificio por explosión de gas', 'Avenida de la Estación, Elche', 'ACTIVO'),
(TRUE,  TRUE,  TRUE,  'MEDIA',  'Accidente de tráfico múltiple en autovía', 'Autovía A-31, km 145', 'ACTIVO'),
(FALSE, TRUE,  FALSE, 'ALTA',   'Toma de rehenes en entidad bancaria', 'Calle del Teatro, Alicante', 'RESUELTO'),
(FALSE, FALSE, TRUE,  'MEDIA',  'Niño con fractura en parque infantil', 'Parque de Canalejas, Alicante', 'RESUELTO'),
(TRUE,  TRUE,  TRUE,  'ALTA',   'Incendio industrial en nave de reciclaje', 'Polígono Industrial de San Vicente', 'ACTIVO'),
(FALSE, TRUE,  FALSE, 'MEDIA',  'Desaparición de menor en zona residencial', 'Urbanización Bonalba, Mutxamel', 'ACTIVO'),
(TRUE,  FALSE, TRUE,  'ALTA',   'Incendio en hospital con evacuación de pacientes', 'Hospital General de Alicante', 'ACTIVO'),
(FALSE, TRUE,  FALSE, 'BAJA',   'Control de alcoholemia y denuncias por exceso de velocidad', 'Avenida de Elche, Alicante', 'RESUELTO'),
(TRUE,  TRUE,  FALSE, 'MEDIA',  'Fuga de gas en edificio de viviendas', 'Calle de la Virgen, Elche', 'ACTIVO'),
(FALSE, FALSE, TRUE,  'MEDIA',  'Parto de urgencia en domicilio', 'Calle del Sol, San Juan de Alicante', 'RESUELTO'),
(TRUE,  TRUE,  TRUE,  'ALTA',   'Colisión frontal entre autobús y camión', 'N-340, km 732, Elche', 'ACTIVO'),
(FALSE, TRUE,  FALSE, 'MEDIA',  'Manifestación no autorizada bloqueando tráfico', 'Rambla de Méndez Núñez, Alicante', 'RESUELTO'),
(TRUE,  FALSE, TRUE,  'ALTA',   'Rescate de montañista accidentado en barranco', 'Barranco del Salt, Aitana', 'ACTIVO'),
(FALSE, TRUE,  TRUE,  'MEDIA',  'Agresión con arma blanca en zona de ocio', 'Puerto de Alicante, Muelle 4', 'ACTIVO');


-- AVISOS (40 registros)

INSERT INTO aviso (telefono, localizacion, descripcion, afectados, hay_fuego, hay_humo, personas_atrapadas, personas_heridas, riesgo_seguridad, riesgo_estructural, alteracion_orden_publico) VALUES
('600123123', 'Avenida de Denia, Alicante', 'Vehículo ardiendo tras colisión', 3, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, FALSE),
('611987654', 'Avenida de Denia, Alicante', 'Hay personas heridas y mucho humo en la carretera', 2, TRUE, TRUE, FALSE, FALSE, TRUE, TRUE, FALSE),
('622111222', 'Sierra de Aitana, Alicante', 'Se ve fuego y humo desde la carretera', 0, TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('633333444', 'Calle del Mar, Alicante', 'Han entrado en mi casa destrozando la puerta', 1, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE),
('644555666', 'Plaza de la Montañeta, Alicante', 'Mi madre se ha desmayado y no responde', 1, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
('655777888', 'Polígono Industrial de Elche', 'Explosión muy fuerte y ahora hay fuego en la nave', 5, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE),
('666999000', 'Calle Castaños, Alicante', 'Hay una pelea muy grande en la puerta de la discoteca', 4, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE),
('677000111', 'Avenida de la Estación, Elche', 'Se ha oído una explosión y se ha derrumbado parte del edificio', 8, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE),
('688222333', 'Autovía A-31, km 145', 'Accidente múltiple con varios coches implicados y fuego', 6, TRUE, TRUE, FALSE, TRUE, TRUE, TRUE, FALSE),
('699444555', 'Calle del Teatro, Alicante', 'Hay un hombre armado dentro del banco reteniendo a la gente', 15, FALSE, FALSE, FALSE, TRUE, FALSE, TRUE, TRUE),
('600555666', 'Parque de Canalejas, Alicante', 'Mi hijo se ha caído del tobogán y no puede mover el brazo', 1, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE),
('611777888', 'Polígono Industrial de San Vicente', 'Hay mucho humo negro saliendo de la nave', 0, TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('622999000', 'Urbanización Bonalba, Mutxamel', 'No encuentro a mi hija desde hace dos horas', 1, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE),
('633111222', 'Hospital General de Alicante', 'Se ha declarado un incendio en la planta 3 y hay que evacuar', 30, TRUE, TRUE, FALSE, FALSE, TRUE, TRUE, FALSE),
('644333444', 'Avenida de Elche, Alicante', 'Control de tráfico', 0, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
('655555666', 'Calle de la Virgen, Elche', 'Huele mucho a gas en el portal y los vecinos están mareados', 6, FALSE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('666777888', 'Calle del Sol, San Juan de Alicante', 'Mi mujer está de parto y no llegamos al hospital', 2, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
('677999000', 'N-340, km 732, Elche', 'Autobús y camión chocados, hay muchos heridos', 20, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, FALSE),
('688111222', 'Rambla de Méndez Núñez, Alicante', 'Hay un grupo bloqueando la calle con pancartas', 0, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE),
('699333444', 'Barranco del Salt, Aitana', 'Un senderista se ha caído por el barranco y no se mueve', 1, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, FALSE),
('600444555', 'Puerto de Alicante, Muelle 4', 'Pelea con navaja entre varios jóvenes', 3, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE),
('611666777', 'Calle del Doctor Gadea, Alicante', 'Incendio en cocina de restaurante', 4, TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('622888999', 'Plaza de los Luceros, Alicante', 'Manifestación pacífica con gran afluencia', 0, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE),
('633000111', 'Carrer de la Pau, Elche', 'Colapso de fachada en edificio antiguo', 2, FALSE, TRUE, FALSE, TRUE, FALSE, TRUE, FALSE),
('644222333', 'Avenida de la Constitución, San Vicente', 'Accidente de motocicleta', 1, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, FALSE),
('655444555', 'Calle Mayor, Alcoy', 'Robo en joyería con arma de fuego', 2, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE),
('666666777', 'Polígono Industrial de Ibi', 'Fuga de producto químico en fábrica de juguetes', 3, FALSE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('677888999', 'Avenida del Puerto, Alicante', 'Varado embarcación con vuelco', 2, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, FALSE),
('688000111', 'Calle San Nicolás, Alicante', 'Desprendimiento de cornisa', 1, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE),
('699222333', 'Carretera de Murcia, Elche', 'Accidente de camión con derrame de mercancía peligrosa', 1, FALSE, FALSE, TRUE, FALSE, FALSE, TRUE, FALSE),
('600333444', 'Plaza de Toros, Alicante', 'Desvanecimiento masivo por golpe de calor', 5, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE),
('611555666', 'Calle del Mar, Alicante', 'Inundación por rotura de tubería principal', 0, FALSE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE),
('622777888', 'Parque de la Ereta, Alicante', 'Rescate de excursionista con luxación de tobillo', 1, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE),
('633999000', 'Avenida de Elche, Alicante', 'Atropello de peatón en paso de cebra', 1, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, FALSE),
('644111222', 'Calle de las Tiendas, Alcoy', 'Incendio en contenedores que se propaga a fachada', 0, TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('655333444', 'Plaza del Ayuntamiento, Elche', 'Persona con crisis de ansiedad y dificultad para respirar', 1, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE),
('666555666', 'Carretera de la costa, Campello', 'Ahogamiento en playa con rescate en curso', 1, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE),
('677777888', 'Polígono Industrial de Crevillente', 'Incendio en almacén de textiles', 0, TRUE, TRUE, FALSE, FALSE, FALSE, TRUE, FALSE),
('688999000', 'Avenida de la Constitución, Villena', 'Derrumbe parcial de techo en nave deportiva', 4, FALSE, TRUE, FALSE, TRUE, TRUE, TRUE, FALSE),
('699111222', 'Calle del Carmen, Alicante', 'Persecución policial con colisiones', 2, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE);


-- INCIDENTE_AVISO

INSERT INTO incidente_aviso (incidente_id, aviso_id) VALUES
(1, 1), (1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 9),
(9, 10),
(10, 11),
(11, 12),
(12, 13),
(13, 14),
(14, 15),
(15, 16),
(16, 17),
(17, 18),
(18, 19),
(19, 20),
(20, 21),
(1, 22), (2, 23), (3, 24), (4, 25), (5, 26), (6, 27), (7, 28), (8, 29), (9, 30),
(10, 31), (11, 32), (12, 33), (13, 34), (14, 35), (15, 36), (16, 37), (17, 38), (18, 39), (19, 40), (20, 40);


-- HOSPITALES (8 registros)

INSERT INTO hospital (nombre, direccion, capacidad, camiones_ambulancia, correo_electronico, helipuerto, personal_medico) VALUES
('Hospital General Universitario de Alicante', 'Calle Pintor Baeza, 15, Alicante', 350, 8, 'info.hgua@gva.es', TRUE, 120),
('Hospital Universitario de San Juan', 'Calle Alicante, 83, San Juan de Alicante', 200, 4, 'contacto.husj@gva.es', FALSE, 75),
('Hospital General de Elche', 'Calle de la Salud, 4, Elche', 280, 6, 'info.hgelche@gva.es', TRUE, 95),
('Hospital de Villena', 'Calle Ronda de Salinas, 5, Villena', 120, 2, 'hospital.villena@gva.es', FALSE, 40),
('Hospital de Alcoy', 'Calle del Dos de Mayo, 2, Alcoy', 180, 3, 'hospital.alcoy@gva.es', FALSE, 55),
('Hospital Marina Baixa', 'Av. de la Alqueria, 21, Villajoyosa', 150, 3, 'marina.baixa@gva.es', TRUE, 60),
('Hospital de la Vega Baja', 'Ctra. de Orihuela, s/n, Orihuela', 220, 5, 'hospital.vega@gva.es', FALSE, 80),
('Hospital de Denia', 'Av. Marina Española, 50, Denia', 160, 3, 'hospital.denia@gva.es', TRUE, 50);


-- RECURSOS HOSPITALARIOS (8 registros)

INSERT INTO recursosHospital (descripcion, codigo, camasRequeridas, personalRequerido) VALUES
('Cama de hospitalización general', 100, 1, 2),
('Unidad de cuidados intensivos (UCI)', 101, 1, 3),
('Equipo de cirugía de emergencia', 102, 2, 4),
('Equipo de reanimación avanzada', 103, 1, 2),
('Unidad de quemados', 104, 1, 3),
('Sala de radiología móvil', 105, 1, 2),
('Equipo de traumatología de emergencia', 106, 2, 3),
('Unidad de neonatología', 107, 2, 3);


-- PACIENTES (25 registros)

INSERT INTO paciente (nombre, fecha_nacimiento, sexo, alergias) VALUES
('Juan García Martínez', '1985-03-15', 'Masculino', 'Penicilina'),
('María López Sánchez', '1990-07-22', 'Femenino', 'Ninguna'),
('Pedro Rodríguez Gómez', '1978-11-05', 'Masculino', 'Yodo'),
('Ana Fernández Ruiz', '2002-01-30', 'Femenino', 'Sulfamidas'),
('Luis Torres Navarro', '1965-09-12', 'Masculino', 'Ninguna'),
('Carmen Jiménez Moreno', '1988-04-18', 'Femenino', 'Látex'),
('José Martín Ortega', '1995-06-25', 'Masculino', 'Ninguna'),
('Laura Serrano Vidal', '1972-12-08', 'Femenino', 'Aspirina'),
('Miguel Rubio Castro', '2000-09-03', 'Masculino', 'Ninguna'),
('Isabel Molina Bravo', '1983-02-14', 'Femenino', 'Penicilina'),
('Francisco Vega Delgado', '1958-05-28', 'Masculino', 'Ninguna'),
('Sonia Pascual Guerrero', '1998-08-19', 'Femenino', 'Ibusitano'),
('Manuel Reyes Peña', '1970-10-07', 'Masculino', 'Yodo'),
('Elena Aguilera Cabrera', '2005-03-11', 'Femenino', 'Ninguna'),
('David Herrera Fuentes', '1992-12-24', 'Masculino', 'Ninguna'),
('Patricia Mendoza Calvo', '1980-07-06', 'Femenino', 'Sulfamidas'),
('Javier Medina Bravo', '1963-11-17', 'Masculino', 'Ninguna'),
('Raquel Cano Arias', '1996-01-29', 'Femenino', 'Látex'),
('Daniel Cortés Moya', '1989-09-21', 'Masculino', 'Ninguna'),
('Beatriz León Pardo', '1975-04-03', 'Femenino', 'Penicilina'),
('Alejandro Márquez Salas', '2001-06-14', 'Masculino', 'Ninguna'),
('Natalia Flores Ibáñez', '1986-10-31', 'Femenino', 'Aspirina'),
('Ricardo Gallardo Cruz', '1969-02-22', 'Masculino', 'Ninguna'),
('Marta Ramos Espejo', '1994-05-09', 'Femenino', 'Yodo'),
('Sergio Vázquez Soto', '1977-08-26', 'Masculino', 'Ninguna');


-- INCIDENTE_HOSPITAL (15 registros)

INSERT INTO incidente_hospital (incidente_id, hospital_id, paciente_id) VALUES
(1, 1, 1), (1, 1, 2), (1, 1, 3),
(4, 2, 4),
(5, 3, 5), (5, 3, 6), (5, 3, 7),
(7, 3, 8), (7, 3, 9),
(8, 1, 10), (8, 1, 11), (8, 2, 12),
(13, 1, 13), (13, 1, 14),
(17, 3, 15), (17, 3, 16), (17, 3, 17), (17, 1, 18), (17, 1, 19), (17, 1, 20),
(19, 6, 21), (19, 6, 22),
(20, 1, 23), (20, 1, 24),
(2, 7, 25);


-- INCIDENTE_HOSPITAL_RECURSOSHOSPITAL

INSERT INTO incidente_hospital_recursosHospital (incidente_hospital_id, recurso_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1),
(3, 4),
(4, 1),
(5, 2), (5, 3), (5, 5),
(6, 2), (6, 4),
(7, 6),
(8, 7),
(9, 3), (9, 4),
(10, 1), (10, 2),
(11, 1),
(12, 3),
(13, 5),
(14, 2), (14, 4), (14, 5),
(15, 1), (15, 3),
(16, 1), (16, 7),
(17, 2), (17, 4), (17, 6),
(18, 1), (18, 7),
(19, 1), (19, 2), (19, 3),
(20, 3), (20, 7),
(21, 2),
(22, 1), (22, 4),
(23, 6),
(24, 1), (24, 4),
(25, 2), (25, 5);


-- NOTIFICACIONES (25 registros)

INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES
(1, 'Envío urgente de unidades de bomberos y sanitarios a accidente en Avenida de Denia', 'BOMBEROS'),
(1, 'Se requiere ambulancia y personal médico para atención de heridos', 'AMBULANCIA'),
(1, 'Policía local necesaria para control de tráfico y seguridad', 'POLICIA'),
(2, 'Incendio forestal activo, solicitan refuerzos aéreos', 'BOMBEROS'),
(2, 'Se despliega unidad de policía para cortar carreteras de acceso', 'POLICIA'),
(3, 'Robo denunciado, se requiere investigación de la unidad de delitos', 'POLICIA'),
(4, 'Ambulancia enviada a domicilio por desvanecimiento de persona mayor', 'AMBULANCIA'),
(5, 'Emergencia química, se activa el plan de emergencias exterior', 'BOMBEROS'),
(5, 'Se solicita personal especializado en quemados', 'HOSPITAL'),
(5, 'Policía de seguridad ciudadana desplegada para acordonar zona', 'POLICIA'),
(6, 'Unidad de prevención de disturbios en camino a la zona', 'POLICIA'),
(7, 'Despliegue de equipos de rescate urbano por colapso de edificio', 'BOMBEROS'),
(7, 'Solicitud de múltiples ambulancias para evacuación', 'AMBULANCIA'),
(8, 'Activado plan de emergencias de tráfico en autovía', 'POLICIA'),
(8, 'Bomberos en ruta para excarcelación', 'BOMBEROS'),
(9, 'GEO en camino para toma de rehenes en banco', 'POLICIA'),
(10, 'Ambulancia pediátrica enviada a parque infantil', 'AMBULANCIA'),
(11, 'Refuerzo de bomberos por incendio en nave de reciclaje', 'BOMBEROS'),
(12, 'Activada búsqueda de persona desaparecida', 'POLICIA'),
(13, 'Evacuación parcial del hospital y traslado de pacientes', 'HOSPITAL'),
(14, 'Refuerzo de unidades para control de tráfico', 'POLICIA'),
(15, 'Fuga de gas, bomberos en ruta para ventilación', 'BOMBEROS'),
(16, 'Ambulancia de urgencias para parto en domicilio', 'AMBULANCIA'),
(17, 'Múltiples ambulancias y helicóptero médico para accidente grave', 'AMBULANCIA'),
(17, 'Policía de tráfico para cortar la carretera nacional', 'POLICIA'),
(18, 'Unidad de intervención policial para desalojo pacífico', 'POLICIA'),
(19, 'Unidad de bomberos para rescate en altura/montaña', 'BOMBEROS'),
(19, 'Helicóptero sanitario para evacuación del accidentado', 'AMBULANCIA'),
(20, 'Policía portuaria y local para control de zona', 'POLICIA'),
(20, 'Ambulancia para traslado de heridos por arma blanca', 'AMBULANCIA');


-- COMISARÍAS (6 registros)

INSERT INTO comisaria (nombre, direccion, capacidad, correo_electronico) VALUES
('Comisaría Provincial de Alicante', 'Calle de la Policía, 12, Alicante', 80, 'cp.alicante@policia.es'),
('Comisaría de San Juan de Alicante', 'Calle de la Policía, 5, San Juan', 35, 'sanjuancp@policia.es'),
('Comisaría de Elche', 'Avenida de la Universidad, 10, Elche', 50, 'elchecp@policia.es'),
('Comisaría de Alcoy', 'Calle del Santo Domingo, 3, Alcoy', 40, 'alcoycp@policia.es'),
('Comisaría de Villena', 'Calle del Medio, 8, Villena', 25, 'villenacp@policia.es'),
('Comisaría de Denia', 'Calle del Marqués de Campo, 15, Denia', 30, 'deniacp@policia.es');


-- UNIDADES DE POLICÍA (18 registros)

INSERT INTO unidad_policia (comisaria_id, cuerpo, disponibilidad) VALUES
(1, 'local', TRUE),
(1, 'local', TRUE),
(1, 'nacional', TRUE),
(1, 'nacional', FALSE),
(1, 'guardia civil', TRUE),
(1, 'guardia civil', TRUE),
(2, 'local', TRUE),
(2, 'nacional', TRUE),
(2, 'guardia civil', TRUE),
(3, 'local', TRUE),
(3, 'local', FALSE),
(3, 'nacional', TRUE),
(3, 'guardia civil', TRUE),
(4, 'local', TRUE),
(4, 'nacional', TRUE),
(4, 'guardia civil', TRUE),
(5, 'local', TRUE),
(5, 'nacional', TRUE),
(6, 'local', TRUE),
(6, 'nacional', TRUE),
(6, 'guardia civil', TRUE);


-- REGISTROS DE DESPLIEGUE (15 registros)

INSERT INTO registro_despliegue (incidente_id, unidad_policia_id) VALUES
(1, 1), (1, 3), (1, 5),
(3, 4),
(5, 5), (5, 6), (5, 12),
(6, 1), (6, 2),
(8, 3), (8, 9),
(9, 5), (9, 13),
(11, 5), (11, 12), (11, 13),
(12, 8), (12, 9),
(14, 1), (14, 3),
(15, 4), (15, 10),
(17, 1), (17, 3), (17, 5), (17, 12),
(18, 1), (18, 2), (18, 5),
(20, 1), (20, 3);


-- PARQUES DE BOMBEROS (6 registros)

INSERT INTO parque_bomberos (nombre, direccion, capacidad_vehiculos) VALUES
('Parque Central de Alicante', 'Av. Locutor Vicente Hipólito, 20, Alicante', 15),
('Parque de Bomberos de Elche', 'Ctra. de la Carrús, s/n, Elche', 12),
('Parque de Bomberos de Alcoy', 'Carrer de Sant Vicent, 5, Alcoy', 8),
('Parque de Bomberos de San Juan', 'Av. de la Constitución, 30, San Juan', 6),
('Parque de Bomberos de Villena', 'Ctra. de Yecla, km 2, Villena', 7),
('Parque de Bomberos de Denia', 'Carrer del Parc, 12, Denia', 6);


-- VEHÍCULOS DE BOMBEROS (20 registros)

INSERT INTO vehiculo_bomberos (parque_id, tipo, estado, matricula) VALUES
(1, 'autobomba', 'disponible', '1234-ABC'),
(1, 'autoescala', 'disponible', '9876-XYZ'),
(1, 'autobomba', 'en_ruta', '4567-DEF'),
(1, 'vehiculo de mando', 'disponible', '1122-BOM'),
(1, 'ambulancia SVB', 'disponible', '3344-SOS'),
(1, 'camion cisterna', 'mantenimiento', '5566-AGU'),
(1, 'unidad de rescate', 'disponible', '7788-RES'),
(2, 'autobomba', 'disponible', 'ELCH-001'),
(2, 'autobomba', 'disponible', 'ELCH-002'),
(2, 'autoescala', 'en_ruta', 'ELCH-003'),
(2, 'camion cisterna', 'disponible', 'ELCH-004'),
(2, 'unidad de rescate', 'disponible', 'ELCH-005'),
(3, 'autobomba', 'disponible', 'ALCO-01'),
(3, 'autobomba', 'mantenimiento', 'ALCO-02'),
(3, 'unidad de rescate', 'disponible', 'ALCO-03'),
(4, 'autobomba', 'disponible', 'SJUA-01'),
(4, 'ambulancia SVB', 'disponible', 'SJUA-02'),
(5, 'autobomba', 'disponible', 'VILL-01'),
(5, 'autoescala', 'disponible', 'VILL-02'),
(6, 'autobomba', 'disponible', 'DENI-01'),
(6, 'camion cisterna', 'disponible', 'DENI-02');


-- REGISTROS DE DESPLIEGUE DE BOMBEROS (20 registros)

INSERT INTO registro_despliegue_bomberos (incidente_id, vehiculo_bomberos_id, hora_despacho) VALUES
-- Incidente 1: Accidente de trafico con incendio (Alicante)
(1, 1, '2025-04-15 18:12:00'),
(1, 7, '2025-04-15 18:13:00'),
-- Incidente 2: Incendio forestal (Aitana) - refuerzos desde Alicante y Elche
(2, 1, '2025-04-20 13:05:00'),
(2, 8, '2025-04-20 13:10:00'),
(2, 11, '2025-04-20 13:15:00'),
-- Incidente 5: Explosion quimica (Elche) - parque de Elche
(5, 8, '2025-05-01 08:47:00'),
(5, 9, '2025-05-01 08:48:00'),
(5, 12, '2025-05-01 08:50:00'),
-- Incidente 7: Colapso edificio por gas (Elche)
(7, 8, '2025-05-03 11:22:00'),
(7, 12, '2025-05-03 11:23:00'),
-- Incidente 8: Accidente multiple en autovia (A-31) - parque Alicante
(8, 1, '2025-05-05 07:16:00'),
(8, 2, '2025-05-05 07:17:00'),
(8, 7, '2025-05-05 07:18:00'),
-- Incidente 11: Incendio industrial (San Vicente) - parque Alicante
(11, 1, '2025-05-08 16:42:00'),
(11, 4, '2025-05-08 16:43:00'),
(11, 7, '2025-05-08 16:44:00'),
-- Incidente 13: Incendio en hospital (Alicante)
(13, 1, '2025-05-10 22:02:00'),
(13, 7, '2025-05-10 22:03:00'),
-- Incidente 15: Fuga de gas en edificio (Elche)
(15, 16, '2025-05-12 09:10:00'),
(15, 18, '2025-05-12 09:15:00'),
-- Incidente 17: Colision frontal autobus-camion (Elche) - varios parques
(17, 1, '2025-05-14 14:20:00'),
(17, 7, '2025-05-14 14:21:00'),
(17, 8, '2025-05-14 14:22:00'),
-- Incidente 19: Rescate montanista (Aitana) - parque Alcoy + Alicante
(19, 15, '2025-05-16 11:05:00'),
(19, 13, '2025-05-16 11:06:00');


-- EXPEDIENTES (15 registros)

INSERT INTO expediente (incidente_id, clasificacion, estado, fecha_creacion, fecha_cierre, observaciones) VALUES
(3, 'Robo con fuerza', 'CERRADO', '2025-01-10 14:30:00', '2025-02-15 10:00:00', 'Detenido el autor, recuperados los efectos'),
(6, 'Altercado en vía pública', 'CERRADO', '2025-01-12 23:45:00', '2025-01-13 04:20:00', 'Imputados 3 individuos por desorden público'),
(9, 'Toma de rehenes', 'CERRADO', '2025-02-05 09:15:00', '2025-02-05 14:30:00', 'Negociación exitosa, rehenes liberados sin heridos, autor detenido'),
(10, 'Accidente infantil', 'CERRADO', '2025-02-18 16:00:00', '2025-02-18 19:30:00', 'Fractura de brazo tratada, menor dado de alta'),
(14, 'Control de tráfico', 'CERRADO', '2025-03-01 20:00:00', '2025-03-01 23:00:00', '15 denuncias por alcoholemia positiva'),
(16, 'Parto de urgencia', 'CERRADO', '2025-03-10 03:20:00', '2025-03-10 06:00:00', 'Parto asistido en domicilio, madre e hija en buen estado'),
(18, 'Manifestación', 'CERRADO', '2025-03-22 11:00:00', '2025-03-22 14:00:00', 'Desalojo pacífico, ninguna incidencia'),
(1, 'Accidente de tráfico', 'ACTIVO', '2025-04-15 18:10:00', NULL, 'Investigación en curso sobre causas del siniestro'),
(2, 'Incendio forestal', 'ACTIVO', '2025-04-20 13:00:00', NULL, 'Perímetro estabilizado, en fase de control'),
(4, 'Desvanecimiento', 'ACTIVO', '2025-04-25 09:30:00', NULL, 'Paciente en observación por posible ictus'),
(5, 'Explosión química', 'ACTIVO', '2025-05-01 08:45:00', NULL, 'Análisis de causas en proceso'),
(7, 'Colapso edificio', 'ACTIVO', '2025-05-03 11:20:00', NULL, 'Evaluación estructural pendiente'),
(8, 'Accidente múltiple', 'ACTIVO', '2025-05-05 07:15:00', NULL, 'Reconstrucción del accidente solicitada'),
(11, 'Incendio industrial', 'ACTIVO', '2025-05-08 16:40:00', NULL, 'Identificación de sustancias ardientes en curso'),
(13, 'Incendio hospital', 'ACTIVO', '2025-05-10 22:00:00', NULL, 'Peritaje para determinar origen del fuego');


-- DATOS DE SERVICIO (20 registros)

INSERT INTO dato_servicio (expediente_id, servicio, hora_salida, hora_llegada, actuaciones, recursos_utilizados, observaciones) VALUES
(1, 'Policía Científica', '2025-01-10 15:00:00', '2025-01-10 16:30:00', 'Levantamiento de huellas, fotografía del lugar', 'Equipo de dactiloscopia, cámara forense', 'Huellas dactilares encontradas en la entrada'),
(1, 'Policía Judicial', '2025-01-10 16:45:00', '2025-01-10 18:00:00', 'Entrevista a testigos y vecinos', 'Vehículo patrulla, grabadora', 'Testigo vio al sospechoso huir en coche rojo'),
(2, 'Unidad de Prevención', '2025-01-12 23:50:00', '2025-01-13 00:30:00', 'Desalojo y detención de los implicados', 'Vehículos patrulla, defensa personal', 'Detenidos sin resistencia'),
(3, 'GEO', '2025-02-05 09:20:00', '2025-02-05 09:45:00', 'Asalto controlado y liberación de rehenes', 'Blindados, armamento no letal', 'Entrada por dos puntos simultáneos'),
(3, 'Negociador', '2025-02-05 09:15:00', '2025-02-05 14:30:00', 'Diálogo continuo con el secuestrador', 'Vehículo de mando, comunicaciones', 'Negociación clave para la rendición pacífica'),
(4, 'SAMU', '2025-02-18 16:05:00', '2025-02-18 16:25:00', 'Inmovilización y traslado a urgencias', 'Ambulancia UVI, férulas', 'Atención rápida crucial para el menor'),
(5, 'Tráfico', '2025-03-01 20:10:00', '2025-03-01 22:30:00', 'Control de alcoholemia masivo', 'Etilómetros, chalecos reflectantes', 'Zona de control bien señalizada'),
(6, 'Matrona de urgencias', '2025-03-10 03:25:00', '2025-03-10 04:00:00', 'Asistencia al parto en domicilio', 'Kit de parto, oxígeno neonatal', 'Nacimiento sin complicaciones'),
(7, 'Unidad de Intervención', '2025-03-22 11:10:00', '2025-03-22 11:30:00', 'Cortes de tráfico y escolta de la manifestación', 'Vallas móviles, megafonía', 'Actuación proporcionada y ordenada'),
(8, 'Bomberos', '2025-04-15 18:15:00', '2025-04-15 18:30:00', 'Extinción de incendio y excarcelación', 'Autobomba, herramientas hidráulicas', 'Vehículo calcinado, excarcelación compleja'),
(8, 'SAMU', '2025-04-15 18:12:00', '2025-04-15 18:40:00', 'Triaje y atención de heridos graves', 'Ambulancia UVI, material de vendajes', '3 heridos graves estabilizados en el lugar'),
(9, 'Bomberos Forestales', '2025-04-20 13:15:00', '2025-04-20 18:00:00', 'Perímetro de seguridad y extinción', 'Autobombas, mochilas extintoras', 'Terreno escarpado dificultó el acceso'),
(10, 'SAMU', '2025-04-25 09:35:00', '2025-04-25 09:50:00', 'Evaluación neurológica y traslado', 'Ambulancia SVB, monitor de signos', 'Paciente consciente a la llegada del hospital'),
(11, 'Bomberos', '2025-05-01 08:50:00', '2025-05-01 10:00:00', 'Control de la combustión y búsqueda de víctimas', 'Autobombas, trajes de proximidad', 'Nave completamente destruida'),
(11, 'Unidad Militar de Emergencias', '2025-05-01 09:30:00', '2025-05-01 14:00:00', 'Apoyo en descontaminación y control', 'Vehículos NBC, detectores', 'Intervención especializada por productos tóxicos'),
(12, 'Bomberos', '2025-05-03 11:25:00', '2025-05-03 13:00:00', 'Búsqueda con binomio canino y apuntalamiento', 'Unidad de rescate, perros', 'No se encontraron más víctimas bajo los escombros'),
(13, 'SAMU', '2025-05-05 07:20:00', '2025-05-05 08:00:00', 'Triaje masivo y evacuación a múltiples hospitales', '5 ambulancias, helicóptero', 'Dispositivo de emergencias ampliado'),
(13, 'Policía', '2025-05-05 07:18:00', '2025-05-05 10:00:00', 'Corte de autovía y desvío de tráfico', 'Patrullas, señalización', 'Tráfico restablecido a las 10:00'),
(14, 'Bomberos', '2025-05-08 16:45:00', '2025-05-08 19:00:00', 'Extinción con espuma y refrigeración', 'Autobombas, camión cisterna', 'Posible reignición controlada'),
(15, 'Bomberos', '2025-05-10 22:05:00', '2025-05-10 23:30:00', 'Evacuación y extinción de incendio en planta 3', 'Autobombas, unidad de mando', 'Evacuación ordenada de 30 pacientes');


-- INFORMES (15 registros)

INSERT INTO informe (expediente_id, tipo, resumen) VALUES
(1, 'Pericial', 'Análisis de huellas y vestigios en escena del robo. Coincidencia con individuo fichado previamente.'),
(2, 'Operativo', 'Relato detallado de la intervención de la Unidad de Prevención. 3 detenidos, ningún agente herido.'),
(3, 'Negociación', 'Informe del negociador sobre el diálogo mantenido. Estrategia de empatía clave para la resolución.'),
(4, 'Sanitario', 'Informe médico de urgencias. Fractura supracondílea izquierda. Ingreso para reducción quirúrgica.'),
(5, 'Tráfico', 'Resumen del dispositivo de control. 200 pruebas realizadas, 15 positivas, 2 detenciones.'),
(6, 'Sanitario', 'Parto eutócico en domicilio. Recién nacido APGAR 9/10. Traslado rutinario a maternidad.'),
(7, 'Orden público', 'Análisis de la manifestación. 500 asistentes, ningún incidente relevante, desalojo en 45 minutos.'),
(8, 'Bomberos', 'Informe técnico del siniestro. Vehículo con fallo mecánico como causa probable del incendio.'),
(9, 'Forestal', 'Análisis de la afección. 15 hectáreas quemadas, sin viviendas afectadas. Causa probable: colilla.'),
(10, 'Médico', 'Informe de neurología. Ictus isquémico leve. Paciente estable y en rehabilitación.'),
(11, 'Técnico', 'Informe de los bomberos. Explosión originada por reacción química incontrolada. Recomendaciones de seguridad.'),
(12, 'Estructural', 'Informe de perito sobre el edificio. Cimiento dañado por fuga de gas. Demolición controlada recomendada.'),
(13, 'Sanitario', 'Informe del dispositivo de emergencias. 6 heridos graves, 12 leves. Todos dados de alta en menos de 15 días.'),
(14, 'Técnico', 'Informe sobre incendio en nave. Causa: cortocircuito en maquinaria. Daños estimados en 200.000 euros.'),
(15, 'Técnico', 'Informe sobre incendio hospitalario. Origen en fallo eléctrico de equipo médico. Ningún paciente herido.');
