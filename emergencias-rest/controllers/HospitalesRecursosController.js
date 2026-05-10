/**
 * The HospitalesRecursosController file is a very simple one, which does not need to be changed manually,
 * unless there's a case where business logic routes the request to an entity which is not
 * the service.
 * The heavy lifting of the Controller item is done in Request.js - that is where request
 * parameters are extracted and sent to the service, and where response is handled.
 */

const Controller = require('./Controller');
const service = require('../services/HospitalesRecursosService');
const hospitalGET = async (request, response) => {
  await Controller.handleRequest(request, response, service.hospitalGET);
};

const notificarHospitalIdPOST = async (request, response) => {
  await Controller.handleRequest(request, response, service.notificarHospitalIdPOST);
};

const pacientePOST = async (request, response) => {
  await Controller.handleRequest(request, response, service.pacientePOST);
};

const recursosCodigoGET = async (request, response) => {
  await Controller.handleRequest(request, response, service.recursosCodigoGET);
};

const transporteGET = async (request, response) => {
  await Controller.handleRequest(request, response, service.transporteGET);
};


module.exports = {
  hospitalGET,
  notificarHospitalIdPOST,
  pacientePOST,
  recursosCodigoGET,
  transporteGET,
};
