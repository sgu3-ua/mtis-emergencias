/* eslint-disable no-unused-vars */
const Service = require('./Service');
const db = require('../repository/db');

/**
* Cerrar asignación policial
* Cierra una asignación asociada a un incidente policial.
*
* id Integer 
* no response value expected for this operation
* */
const despachosIdPUT = ({ id }) => new Promise(
  async (resolve, reject) => {
    try {
      //Validar datos de entrada
      if (!id ) {
        return reject(Service.rejectResponse(
          'Datos de entrada inválidos',
          400,
        ));
      }
      //Validar tipos de datos
      if (typeof id !== 'number') {
        return reject(Service.rejectResponse(
          'Tipos de datos inválidos',
          400,
        ));
      }
      const registro_despliegue = await db.query(
        'SELECT * FROM registro_despliegue WHERE id = ?',
        [id],
      );
      if (registro_despliegue.length === 0) {
        return reject(Service.rejectResponse(
          'Registro de despliegue no encontrado',
          404,
        ));
      }
      // Actualizar disponibilidad de la unidad a true
      const unidadId = registro_despliegue[0].unidad_policia_id;
      await db.query(
        'UPDATE unidad_policia SET disponibilidad = TRUE WHERE id = ?',
        [unidadId],
      );

      resolve(Service.successResponse({
        id,
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        e.status || 405,
      ));
    }
  },
);
/**
* Registrar despacho policial
* Crea el registro oficial del despacho de una unidad para un incidente.
*
* despachoPolicial DespachoPolicial 
* no response value expected for this operation
* */
const despachosPOST = ({ despachoPolicial }) => new Promise(
  async (resolve, reject) => {
    try {
      //Validar datos de entrada
      if (!despachoPolicial.incidenteId || !despachoPolicial.unidadId) {
        return reject(Service.rejectResponse(
          'Datos de entrada inválidos',
          400,
        ));
      }
      //Validar tipos de datos
      if (typeof despachoPolicial.incidenteId !== 'number' || typeof despachoPolicial.unidadId !== 'number') {
        return reject(Service.rejectResponse(
          'Tipos de datos inválidos',
          400,
        ));
      }

      //Validar que el incidente existe
      const incidente = await db.query(
        'SELECT * FROM incidente WHERE id = ?',
        [despachoPolicial.incidenteId],
      );
      if (incidente.length === 0) {
        return reject(Service.rejectResponse(
          'Incidente no encontrado',
          404,
        ));
      }
      // despachoPolicial {unidadId, incidenteId, id, horaDespacho}
      const horaDespacho = new Date().toISOString();
      // Comprobar que la unidad esta disponible (lógica de negocio)
      const unidadDisponible = await db.query(
        'SELECT * FROM unidad_policia WHERE id = ? AND disponibilidad = TRUE',
        [despachoPolicial.unidadId],
      );
      if (unidadDisponible.length === 0) {
        return reject(Service.rejectResponse(
          'Unidad no disponible',
          400,
        ));
      }
      // Actualizar disponibilidad de la unidad a false
      await db.query(
        'UPDATE unidad_policia SET disponibilidad = FALSE WHERE id = ?',
        [despachoPolicial.unidadId],
      );
      // Insertar el despacho policial
      /*
      create table if not exists registro_despliegue (
          id INT AUTO_INCREMENT PRIMARY KEY,
          incidente_id INT,
          unidad_policia_id INT,
          hora_despacho TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
          FOREIGN KEY (incidente_id) REFERENCES incidente(id),
          FOREIGN KEY (unidad_policia_id) REFERENCES unidad_policia(id)
      );
      */
      await db.query(
        'INSERT INTO registro_despliegue (incidente_id, unidad_policia_id, hora_despacho) VALUES (?, ?, ?)',
        [despachoPolicial.incidenteId, despachoPolicial.unidadId, horaDespacho],
      );
      despachoPolicial.horaDespacho = horaDespacho;

      resolve(Service.successResponse(
        despachoPolicial
      ,
      201
    ));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        e.status || 405,
      ));
    }
  },
);

module.exports = {
  despachosIdPUT,
  despachosPOST,
};
