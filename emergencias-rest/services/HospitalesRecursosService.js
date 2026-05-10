/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Buscar hospitales disponibles
* Realiza una búsqueda en todos los hospitales para saber cuál está disponible según requisitos.
*
* sanitarios Integer  (optional)
* transporte String  (optional)
* camas Integer  (optional)
* lat BigDecimal  (optional)
* lon BigDecimal  (optional)
* codigo String  (optional)
* returns List
* */
const hospitalGET = ({ sanitarios, transporte, camas, lat, lon, codigo }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        sanitarios,
        transporte,
        camas,
        lat,
        lon,
        codigo,
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
* Notificar hospital del incidente
* Notifica al hospital del incidente, esperando una respuesta de ellos. Se pueden pasar parámetros de recursos y paciente.
*
* id Integer 
* sanitarios Integer  (optional)
* transporte String  (optional)
* camas Integer  (optional)
* lat BigDecimal  (optional)
* lon BigDecimal  (optional)
* paciente String  (optional)
* notificarHospitalIdPostRequest NotificarHospitalIdPostRequest  (optional)
* returns NotificacionResponse
* */
const notificarHospitalIdPOST = ({ id, sanitarios, transporte, camas, lat, lon, paciente, notificarHospitalIdPostRequest }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        id,
        sanitarios,
        transporte,
        camas,
        lat,
        lon,
        paciente,
        notificarHospitalIdPostRequest,
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
* Guardar datos de un nuevo paciente
* Registra la información básica del paciente necesaria para los procesos sanitarios.
*
* paciente Paciente 
* no response value expected for this operation
* */
const pacientePOST = ({ paciente }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        paciente,
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
* Obtener recursos sanitarios por código
* Obtiene los recursos sanitarios necesarios para el código. Únicamente incluye personal y camas.
*
* codigo String Código de gravedad / triage del paciente
* returns Recursos
* */
const recursosCodigoGET = ({ codigo }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        codigo,
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
* Determinar opciones de transporte
* Dependiendo de la localización puede requerir transporte aéreo. Dependiendo de la cama puede conocer el tipo de ambulancia.
*
* lat BigDecimal 
* lon BigDecimal 
* codigo String 
* returns Transporte
* */
const transporteGET = ({ lat, lon, codigo }) => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
        lat,
        lon,
        codigo,
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
  hospitalGET,
  notificarHospitalIdPOST,
  pacientePOST,
  recursosCodigoGET,
  transporteGET,
};
