/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

/**
* Iniciar proceso de movilización completa
* Actúa como el servicio organizador. Inicia la solicitud, cálculo de ruta y envío de la orden al camión.
*
* despachoBomberos DespachoBomberos 
* no response value expected for this operation
* */
const despachos_bomberosPOST = ( requestParams ) => new Promise(
  async (resolve, reject) => {
    try {
      const reqBody = requestParams.body || requestParams.despachoBomberos || {};
      const { idIncidente, gravedad, vehiculosRequeridos } = reqBody;

      if (typeof idIncidente !== 'number' || Number.isNaN(idIncidente) || typeof gravedad !== 'string') {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      const incidentes = await query('SELECT id FROM incidente WHERE id = ? LIMIT 1', [idIncidente]);
      if (!incidentes || incidentes.length === 0) {
        reject(Service.rejectResponse('Recurso no encontrado', 404));
        return;
      }

      await query(
        'UPDATE incidente SET requiere_bomberos = TRUE, urgencia = ?, estado = ? WHERE id = ?',
        [gravedad, 'BOMBEROS_DESPACHADOS', idIncidente],
      );

      const mensaje = `Despacho de bomberos iniciado. Vehículos requeridos: ${vehiculosRequeridos || 'N/D'}.`;
      const noti = await query(
        'INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES (?, ?, ?)',
        [idIncidente, mensaje, 'BOMBEROS'],
      );

      resolve(Service.successResponse({
        idIncidente,
        idNotificacion: noti.insertId,
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
  despachos_bomberosPOST,
};
