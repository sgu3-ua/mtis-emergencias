/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

/**
* Consultar disponibilidad de vehículos
* Obtiene la lista de vehículos disponibles en un parque específico según su estado y tipo.
*
* id Integer ID parque de bomberos
* estado String Filtrar por estado actual (disponible, en_ruta, mantenimiento) (optional)
* tipo String Filtrar por tipo de camión (autobomba, autoescala) (optional)
* returns List
* */
const parquesIdFlotaGET = ({ id, estado, tipo }) => new Promise(
  async (resolve, reject) => {
    try {
      if (typeof id !== 'number' || Number.isNaN(id)) {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      let sql = 'SELECT idVehiculo, tipo, estado, matricula FROM vehiculo_bomberos WHERE parque_id = ?';
      const params = [id];

      if (typeof estado === 'string' && estado.trim().length > 0) {
        sql += ' AND estado = ?';
        params.push(estado.trim());
      }

      if (typeof tipo === 'string' && tipo.trim().length > 0) {
        sql += ' AND tipo = ?';
        params.push(tipo.trim());
      }

      const rows = await query(sql, params);
      resolve(Service.successResponse(rows || []));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        500,
      ));
    }
  },
);

module.exports = {
  parquesIdFlotaGET,
};
