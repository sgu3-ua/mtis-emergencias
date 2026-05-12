-- Inicialización de la base de datos para el servicio Postgres
-- Reemplace este archivo con su script SQL completo que crea tablas y datos.

-- Ejemplo simple de esquema inicial
CREATE TABLE IF NOT EXISTS incidents (
  id SERIAL PRIMARY KEY,
  description TEXT NOT NULL,
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

INSERT INTO incidents (description) VALUES ('Registro inicial de prueba');
