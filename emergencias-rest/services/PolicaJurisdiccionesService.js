/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

/**
* Obtener jurisdicción por coordenadas
* Devuelve la jurisdicción policial aplicable para una latitud y longitud concretas.
*
* latitud BigDecimal 
* longitud BigDecimal 
* returns JurisdiccionPolicial
* */
const jurisdiccionesGET = ({ latitud, longitud }) => new Promise(
  async (resolve, reject) => {
    try {
      const rows = await query(
        'SELECT id, nombre FROM comisaria ORDER BY id ASC LIMIT 1',
        [],
      );

      if (!rows || rows.length === 0) {
        reject(Service.rejectResponse('No se encontró información para los parámetros indicados', 404));
        return;
      }

      const comisaria = rows[0];

      resolve(Service.successResponse({
        id: comisaria.id,
        nombre: comisaria.nombre,
        tipoZona: 'URBANA',
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        500,
      ));
    }
  },
);

module.exports = {
  jurisdiccionesGET,
};
