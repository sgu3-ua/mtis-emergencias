/* eslint-disable no-unused-vars */
const Service = require('../services/Service');
const { query } = require('../repository/db');

const toRadians = (degrees) => (degrees * Math.PI) / 180;

// Distancia aproximada en km (Haversine)
const haversineKm = ({ lat1, lon1, lat2, lon2 }) => {
  const R = 6371;
  const dLat = toRadians(lat2 - lat1);
  const dLon = toRadians(lon2 - lon1);
  const a = Math.sin(dLat / 2) ** 2
    + Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) * Math.sin(dLon / 2) ** 2;
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c;
};
/**
* Calcular ruta óptima
* Calcula la ruta y el tiempo estimado desde el origen asignado hasta el lugar del incidente.
*
* origenLongitud Number Coordenadas de longitud GPS del origen
* origenLatitud Number Coordenadas de latitud GPS del origen
* destinoLongitud Number Coordenadas de longitud GPS del incidente
* destinoLatitud Number Coordenadas de latitud GPS del incidente
* returns Ruta
* */
const rutasGET = ({ origenLongitud, origenLatitud, destinoLongitud, destinoLatitud }) => new Promise(
  async (resolve, reject) => {
    try {
      if (
        typeof origenLongitud !== 'number'
        || typeof origenLatitud !== 'number'
        || typeof destinoLongitud !== 'number'
        || typeof destinoLatitud !== 'number'
      ) {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      const distanciaKm = haversineKm({
        lat1: origenLatitud,
        lon1: origenLongitud,
        lat2: destinoLatitud,
        lon2: destinoLongitud,
      });

      // Simulación: velocidad media 45 km/h
      const velocidadMediaKmH = 45;
      const tiempoEstimadoMin = Math.max(1, Math.round((distanciaKm / velocidadMediaKmH) * 60));

      const ruta = {
        distanciaKm: Number(distanciaKm.toFixed(3)),
        tiempoEstimadoMin,
        coordenadasNavegacion: `${origenLatitud},${origenLongitud} -> ${destinoLatitud},${destinoLongitud}`,
      };

      resolve(Service.successResponse(ruta));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        500,
      ));
    }
  },
);

module.exports = {
  rutasGET,
};
