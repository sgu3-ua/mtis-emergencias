/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Iniciar proceso de movilización completa
* Actúa como el servicio organizador. Inicia la solicitud, cálculo de ruta y envío de la orden al camión.
*
* despachoBomberos DespachoBomberos 
* no response value expected for this operation
* */
const despachos_bomberosPOST = ({ despachoBomberos }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        despachoBomberos,
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
  despachos_bomberosPOST,
};
