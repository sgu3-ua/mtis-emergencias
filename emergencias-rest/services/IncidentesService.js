/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Actualizar estado del incidente
* Cambia el estado del aviso en el panel global del 112 (Por ejemplo \"Bomberos en camino\").
*
* id Integer ID del incidente
* estadoIncidente EstadoIncidente 
* no response value expected for this operation
* */
const incidentesIdEstadoPUT = ({ id, estadoIncidente }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        id,
        estadoIncidente,
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
* Guardar registro del incidente
* Guarda un registro del incidente con todos sus datos, además de relacionarlo con el registro de la llamada o mensajes recibidos.
*
* registroIncidente RegistroIncidente 
* paciente String  (optional)
* recursos String  (optional)
* sanitarios Integer  (optional)
* hospital Integer  (optional)
* no response value expected for this operation
* */
const registroIndicentePOST = ({ registroIncidente, paciente, recursos, sanitarios, hospital }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        registroIncidente,
        paciente,
        recursos,
        sanitarios,
        hospital,
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
  incidentesIdEstadoPUT,
  registroIndicentePOST,
};
