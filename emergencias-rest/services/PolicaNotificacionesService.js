/* eslint-disable no-unused-vars */
const Service = require('./Service');
const mailer = require('../services/mail');
const db = require('../repository/db');

/**
* Enviar aviso electrónico a patrulla
* Envía un aviso electrónico para su procesamiento y entrega a la patrulla asignada.
*
* no response value expected for this operation
* */
const notificacionesPOST = () => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
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
  notificacionesPOST,
};
