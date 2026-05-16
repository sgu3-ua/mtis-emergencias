/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

/**
* Consultar disponibilidad de unidades policiales
* Obtiene las unidades policiales disponibles y permite filtrar por cuerpo.
*
* cuerpo String Filtrar por cuerpo (Local, Nacional, Guardia Civil) (optional)
* returns List
* */
const recursosGET = ({ cuerpo }) => new Promise(
  async (resolve, reject) => {
    try {
      let sql = 'SELECT * FROM unidad_polica WHERE disponibilidad = TRUE';
      const params = [];

      if (typeof cuerpo === 'string' && cuerpo.trim().length > 0) {
        sql += ' AND LOWER(cuerpo) = LOWER(?)';
        params.push(cuerpo.trim());
      }

      const rows = await query(sql, params);
      const recursos = (rows || []).map((row) => ({
        unidadId: String(row.id),
        cuerpo: row.cuerpo,
        disponible: Boolean(row.disponibilidad),
      }));

      resolve(Service.successResponse(recursos));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        500,
      ));
    }
  },
);

module.exports = {
  recursosGET,
};
