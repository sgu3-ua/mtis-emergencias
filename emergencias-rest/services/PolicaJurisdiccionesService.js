/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Obtener jurisdicción por coordenadas
* Devuelve la jurisdicción policial aplicable para una latitud y longitud concretas.
*
* latitud BigDecimal 
* longitud BigDecimal 
* returns JurisdiccionPolicial
* */
const jurisdiccionesGET = ({ latitud, longitud }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        latitud,
        longitud,
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
  jurisdiccionesGET,
};
