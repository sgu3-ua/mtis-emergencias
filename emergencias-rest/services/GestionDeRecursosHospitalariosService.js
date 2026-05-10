/* eslint-disable no-unused-vars */
const Service = require('./Service');

/**
* Gestion de recursos hospitalarios
* Recolecta datos para decidir rapidamente que recursos y que    hospital esta disponible
*
* returns _hospitales_solicitarRecursos_post_200_response
* */
const hospitalesSolicitarRecursosPOST = () => new Promise(
  async (resolve, reject) => {
    try {
      resolve(Service.successResponse({
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
  hospitalesSolicitarRecursosPOST,
};
