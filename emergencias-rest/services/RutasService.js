/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Calcular ruta óptima
* Calcula la ruta y el tiempo estimado desde el origen asignado hasta el lugar del incidente.
*
* origenLongitud Float Coordenadas de longitud GPS del origen
* origenLatitud Float Coordenadas de latitud GPS del origen
* destinoLongitud Float Coordenadas de longitud GPS del incidente
* destinoLatitud Float Coordenadas de latitud GPS del incidente
* returns Ruta
* */
const rutasGET = ({ origenLongitud, origenLatitud, destinoLongitud, destinoLatitud }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        origenLongitud,
        origenLatitud,
        destinoLongitud,
        destinoLatitud,
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
  rutasGET,
};
