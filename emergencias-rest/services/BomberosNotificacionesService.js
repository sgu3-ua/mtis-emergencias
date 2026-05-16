/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

/**
* Emitir alerta por retraso
* Envía un aviso al supervisor si el camión no confirma su salida a tiempo.
*
* notificacionesAlertaRetrasoPostRequest NotificacionesAlertaRetrasoPostRequest 
* no response value expected for this operation
* */
const notificacionesAlertaRetrasoPOST = ( requestParams ) => new Promise(
  async (resolve, reject) => {
    try {
      const reqBody = requestParams.body || requestParams.notificacionesAlertaRetrasoPostRequest || {};
      const { idIncidente, mensaje } = reqBody;

      if (typeof idIncidente !== 'number' || Number.isNaN(idIncidente)) {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      const incidentes = await query('SELECT id FROM incidente WHERE id = ? LIMIT 1', [idIncidente]);
      if (!incidentes || incidentes.length === 0) {
        reject(Service.rejectResponse('Recurso no encontrado', 404));
        return;
      }

      const mensajeFinal = mensaje || 'El camión asignado no ha confirmado salida en el tiempo límite.';
      const noti = await query(
        'INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES (?, ?, ?)',
        [idIncidente, mensajeFinal, 'BOMBEROS'],
      );

      await query('UPDATE incidente SET estado = ? WHERE id = ?', ['BOMBEROS_ALERTA_RETRASO', idIncidente]);

      resolve(Service.successResponse({
        idIncidente,
        idNotificacion: noti.insertId,
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        500,
      ));
    }
  },
);
/**
* Enviar orden de movilización
* Envía la hoja de ruta y los datos del siniestro al navegador GPS del camión.
*
* notificacionesOrdenMovilizacionPostRequest NotificacionesOrdenMovilizacionPostRequest 
* no response value expected for this operation
* */
const notificacionesOrdenMovilizacionPOST = ( requestParams ) => new Promise(
  async (resolve, reject) => {
    try {
      const reqBody = requestParams.body || requestParams.notificacionesOrdenMovilizacionPostRequest || {};
      const { idVehiculo, idIncidente } = reqBody;

      if (typeof idIncidente !== 'number' || Number.isNaN(idIncidente) || typeof idVehiculo !== 'number' || Number.isNaN(idVehiculo)) {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      const incidentes = await query('SELECT id FROM incidente WHERE id = ? LIMIT 1', [idIncidente]);
      if (!incidentes || incidentes.length === 0) {
        reject(Service.rejectResponse('Recurso no encontrado', 404));
        return;
      }

      const vehiculos = await query('SELECT idVehiculo FROM vehiculo_bomberos WHERE idVehiculo = ? LIMIT 1', [idVehiculo]);
      if (!vehiculos || vehiculos.length === 0) {
        reject(Service.rejectResponse('Recurso no encontrado', 404));
        return;
      }

      const mensaje = `Orden de movilización enviada al vehículo ${idVehiculo}.`;
      const noti = await query(
        'INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES (?, ?, ?)',
        [idIncidente, mensaje, 'BOMBEROS'],
      );

      await query('UPDATE incidente SET estado = ? WHERE id = ?', ['BOMBEROS_EN_CAMINO', idIncidente]);
      await query('UPDATE vehiculo_bomberos SET estado = ? WHERE idVehiculo = ?', ['en_ruta', idVehiculo]);

      resolve(Service.successResponse({
        idIncidente,
        idVehiculo,
        idNotificacion: noti.insertId,
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
  notificacionesAlertaRetrasoPOST,
  notificacionesOrdenMovilizacionPOST,
};
