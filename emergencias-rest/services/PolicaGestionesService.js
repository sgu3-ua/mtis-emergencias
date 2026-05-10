/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Iniciar proceso integral de gestión policial
* Localiza la zona del incidente, selecciona el cuerpo competente y registra el despacho inicial.
*
* incidentePolicialRequest IncidentePolicialRequest 
* no response value expected for this operation
* */
const gestiones_policialesPOST = ({ incidentePolicialRequest }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        incidentePolicialRequest,
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
  gestiones_policialesPOST,
};
