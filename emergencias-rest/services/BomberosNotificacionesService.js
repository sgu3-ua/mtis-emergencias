/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Emitir alerta por retraso
* Envía un aviso al supervisor si el camión no confirma su salida a tiempo.
*
* notificacionesAlertaRetrasoPostRequest NotificacionesAlertaRetrasoPostRequest 
* no response value expected for this operation
* */
const notificacionesAlertaRetrasoPOST = ({ notificacionesAlertaRetrasoPostRequest }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        notificacionesAlertaRetrasoPostRequest,
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
* Enviar orden de movilización
* Envía la hoja de ruta y los datos del siniestro al navegador GPS del camión.
*
* notificacionesOrdenMovilizacionPostRequest NotificacionesOrdenMovilizacionPostRequest 
* no response value expected for this operation
* */
const notificacionesOrdenMovilizacionPOST = ({ notificacionesOrdenMovilizacionPostRequest }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        notificacionesOrdenMovilizacionPostRequest,
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        e.status || 405,
      ));
    }
  },
);

module.exports = {
  notificacionesAlertaRetrasoPOST,
  notificacionesOrdenMovilizacionPOST,
};
