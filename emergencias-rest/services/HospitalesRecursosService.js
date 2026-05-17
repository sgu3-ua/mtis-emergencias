/* eslint-disable no-unused-vars */
const Service = require('./Service');
const db = require('../repository/db');
const mailer = require('../services/mail.js');

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
      if (sanitarios <= 0 || camas <= 0) {
        reject(Service.rejectResponse(
          'Los valores de sanitarios y camas no pueden ser negativos o cero',
          400,
        ));
        return;
      }
      const query = 'SELECT id, nombre, direccion FROM hospitales WHERE personal_medico >= ? AND capacidad >= ? and camiones_ambulancia >= ? and helipuerto = ?'
      const needsHelipuerto = (lat > 40.0 || lon > -3.0) || transporte.includes('aereo') || transporte.includes('heli') ? 1 : 0 ? 1 : 0;
      transporte = transporte.toLowerCase();
      const camiones = (transporte.includes('aereo') || transporte.includes('heli') ? 0 : 1 * camas);
      const params = [sanitarios || 1, camas || 0, camiones, needsHelipuerto];

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
    const queryHospital = 'SELECT nombre, email FROM hospitales WHERE id = ?';
    let hospital;
    try {
      const result = await db.query(queryHospital, [id]);
      if (result.length === 0) {
        reject(Service.rejectResponse(
          'No se encontró el hospital con el ID proporcionado',
          404,
        ));
        return;
      }
      hospital = result[0];
    } catch (e) {
      reject(Service.rejectResponse(
        'Error al consultar el hospital en la base de datos: ' + e.message,
        500,
      ));
      return;
    }

    const fecha = new Date().toISOString().slice(0, 19).replace('T', ' ');
    const asunto = `Notificación de incidente para hospital ${hospital.nombre}`;
    const mensaje = `Notificación al hospital ${hospital.nombre} para incidente con paciente ${paciente || 'desconocido'}. Requiere ${sanitarios || 'desconocidos'} sanitarios, ${camas || 'desconocidas'} camas, transporte: ${transporte || 'desconocido'}. Latitud: ${lat || 'desconocida'}, Longitud: ${lon || 'desconocida'}. Detalles adicionales: ${notificarHospitalIdPostRequest ? JSON.stringify(notificarHospitalIdPostRequest) : 'ninguno'}.`;
    const tipo = 'HOSPITAL';

    //Primero lo insertamos en la base de datos
    try {
      const query = 'INSERT INTO notificacion (mensaje, fecha, tipo) VALUES (?,?,?)';
      const params = [mensaje, fecha, tipo];
      await db.query(query, params);
    } catch (e) {
      reject(Service.rejectResponse(
        'Error al registrar la notificación en la base de datos: ' + e.message,
        500,
      ));
      return;
    }

    //Le enviamos un correo al hospital
    await mailer.sendMail({
      from: 'emergencias@mail.com',
      to: hospital.email,
      subject: asunto,
      text: mensaje
    });

    //Simulamos que el hospital responde
    const random = Math.random();
    let mensajeHospital;
    let subject;
    let aceptaNotificacion = random < 0.5; // Simulamos que el hospital acepta la notificación con un 50% de probabilidad
    if (aceptaNotificacion) {
        // El hospital acepta la notificación
        mensajeHospital = `El hospital ${hospital.nombre} ha aceptado la notificación y está preparando los recursos necesarios.`;
        subject = `Respuesta del hospital ${hospital.nombre} para incidente`;
    } else {
        // El hospital rechaza la notificación
        mensajeHospital = `El hospital ${hospital.nombre} ha rechazado la notificación debido a la falta de recursos disponibles.`;
        subject = `Rechazo del hospital ${hospital.nombre} para incidente`;
    }
    await mailer.sendMail({
        from: hospital.email,
        to: 'emergencias@mail.com',
        subject: subject,
        text: mensajeHospital
    });
    return resolve(Service.successResponse({"estado": true, "mensajeHospital": mensajeHospital, "hospitalID": id}));
  
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
      // Registramos el paciente en la base de datos y obtenemos su ID
      const query = 'INSERT INTO pacientes (nombre, fecha_nacimiento, sexo, alergias) VALUES (?, ?, ?, ?)';
      const params = [paciente.nombre, paciente.fecha_nacimiento, paciente.sexo, paciente.alergias];
      const result = await db.query(query, params);

      const pacienteId = result.id; // Suponiendo que el ID del paciente se devuelve en result.id
      resolve(Service.successResponse({
        paciente,
        id: pacienteId,
        code: 201
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Invalid input',
        e.status || 400,
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
      // Realizamos la consulta a la base de datos para obtener los recursos según el código
     const query = 'SELECT descripcion, camasRequeridas, personalRequerido FROM recursosHospital WHERE codigo = ?';
     const params = [codigo];
     const result = await db.query(query, params);

      if (result.length === 0) {
        reject(Service.rejectResponse(
          'No se encontraron recursos para el código proporcionado',
          404,
        ));
        return;
      }
      resolve(Service.successResponse({
        codigo,
        descripcion: result[0].descripcion,
        camasRequeridas: result[0].camasRequeridas,
        personalRequerido: result[0].personalRequerido
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
      const aereo = (lat > 40.0 || lon > -3.0);
      const tipoAmbulancia = aereo ? 'Ambulancia aérea' : 'Ambulancia básica';
      let random = Math.random();
      const tiempoEstimado = aereo ? (random < 0.5 ? '30' : '45') : (random < 0.5 ? '15' : '25');

      resolve(Service.successResponse({
        tiempoEstimado: tiempoEstimado,
        requiereAereo: aereo,
        tipoAmbulancia: codigo%2 === 0 && !aereo ? 'Ambulancia de cuidados intensivos' : tipoAmbulancia,
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
