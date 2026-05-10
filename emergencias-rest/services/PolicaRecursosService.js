/* eslint-disable no-unused-vars */
const Service = require('./Service');

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
      resolve(Service.successResponse({
        cuerpo,
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
  recursosGET,
};
