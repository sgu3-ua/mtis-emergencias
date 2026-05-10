/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Cerrar asignación policial
* Cierra una asignación asociada a un incidente policial.
*
* id Integer 
* no response value expected for this operation
* */
const despachosIdPUT = ({ id }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        id,
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
* Registrar despacho policial
* Crea el registro oficial del despacho de una unidad para un incidente.
*
* despachoPolicial DespachoPolicial 
* no response value expected for this operation
* */
const despachosPOST = ({ despachoPolicial }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        despachoPolicial,
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
  despachosIdPUT,
  despachosPOST,
};
