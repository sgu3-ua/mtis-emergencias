/* eslint-disable no-unused-vars */
const Service = require('./Service');
const mailer = require('../services/mail');
const { query } = require('../repository/db');

/**
* Enviar aviso electrónico a patrulla
* Envía un aviso electrónico para su procesamiento y entrega a la patrulla asignada.
*
* no response value expected for this operation
* */
const notificacionesPOST = (requestParams) => new Promise(
  async (resolve, reject) => {
    try {
      const reqBody = requestParams.body || requestParams.notificacion || {};
      const { incidenteId, mensaje, tipo, email } = reqBody;

      if (!mensaje || typeof mensaje !== 'string' || mensaje.trim().length === 0) {
        reject(Service.rejectResponse('Campo "mensaje" obligatorio', 400));
        return;
      }

      if (incidenteId !== undefined && incidenteId !== null) {
        if (typeof incidenteId !== 'number' || Number.isNaN(incidenteId)) {
          reject(Service.rejectResponse('incidenteId debe ser un número', 400));
          return;
        }
        const incidentes = await query('SELECT id FROM incidente WHERE id = ? LIMIT 1', [incidenteId]);
        if (!incidentes || incidentes.length === 0) {
          reject(Service.rejectResponse('Incidente no encontrado', 404));
          return;
        }
      }

      const insertResult = await query(
        'INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES (?, ?, ?)',
        [incidenteId || null, mensaje, tipo || 'POLICIA'],
      );

      const idNotificacion = insertResult.insertId;

      let mailResult = null;
      if (email && typeof email === 'string') {
        try {
          await mailer.sendMail({
            from: process.env.MAIL_FROM || 'no-reply@sistema-emergencias.local',
            to: email,
            subject: `Aviso patrulla${tipo ? ' - ' + tipo : ''}`,
            text: mensaje,
          });
          mailResult = 'sent';
        } catch (err) {
          mailResult = `error: ${err.message}`;
        }
      }

      resolve(Service.successResponse({ idNotificacion, mailResult }, 202));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        500,
      ));
    }
  },
);

module.exports = {
  notificacionesPOST,
};
