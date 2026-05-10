/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Consultar disponibilidad de vehículos
* Obtiene la lista de vehículos disponibles en un parque específico según su estado y tipo.
*
* id Integer ID parque de bomberos
* estado String Filtrar por estado actual (disponible, en_ruta, mantenimiento) (optional)
* tipo String Filtrar por tipo de camión (autobomba, autoescala) (optional)
* returns List
* */
const parquesIdFlotaGET = ({ id, estado, tipo }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        id,
        estado,
        tipo,
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
  parquesIdFlotaGET,
};
