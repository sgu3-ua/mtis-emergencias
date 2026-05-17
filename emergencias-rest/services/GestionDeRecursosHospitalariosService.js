/* eslint-disable no-unused-vars */
const Service = require('./Service');
const db = require('../repository/db');

/**
* Gestion de recursos hospitalarios
* Recolecta datos para decidir rapidamente que recursos y que    hospital esta disponible
*
* returns _hospitales_solicitarRecursos_post_200_response
* */
const hospitalesSolicitarRecursosPOST = () => new Promise(
  async (resolve, reject) => {
    try {
      //Devolvemos un codigo de recurso al azar
      const random = Math.random();
      if (random < 0.1) { //10% de no saber que recursos asignar
        reject(Service.rejectResponse(
          'No se han podido asignar recursos en este momento. Intente nuevamente.',
          404,
        ));
        return;
      }
      const query = 'SELECT codigo FROM recursos ORDER BY RAND() LIMIT 1';
      const result = await db.query(query, []);
      resolve(Service.successResponse({
        codigo: result[0].codigo
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
