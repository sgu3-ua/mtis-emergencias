/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

/**
* Iniciar proceso integral de gestión policial
* Localiza la zona del incidente, selecciona el cuerpo competente y registra el despacho inicial.
*
* incidentePolicialRequest IncidentePolicialRequest 
* no response value expected for this operation
* */
const gestiones_policialesPOST = ( requestParams ) => new Promise(
  async (resolve, reject) => {
    try {
      const reqBody = requestParams.body || requestParams.incidentePolicialRequest || {};
      const {
        tipoIncidente,
        gravedad,
        latitud,
        longitud,
      } = reqBody;

      if (!tipoIncidente || typeof gravedad !== 'string' || typeof latitud !== 'number' || typeof longitud !== 'number') {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      const descripcion = `Incidente policial: ${tipoIncidente}`;
      const localizacion = `${latitud},${longitud}`;

      const result = await query(
        `INSERT INTO incidente (
          requiere_bomberos,
          requiere_policia,
          requiere_sanitarios,
          urgencia,
          descripcion,
          localizacion,
          estado
        ) VALUES (?, ?, ?, ?, ?, ?, ?)`
        , [
          false,
          true,
          false,
          gravedad,
          descripcion,
          localizacion,
          'ACTIVO',
        ],
      );

      resolve(Service.successResponse({
        idIncidente: result.insertId,
      }, 201));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        500,
      ));
    }
  },
);

module.exports = {
  gestiones_policialesPOST,
};
