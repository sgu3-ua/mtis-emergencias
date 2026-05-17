/* eslint-disable no-unused-vars */
const Service = require('./Service');
const db = require('../repository/db');

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
      // Validaciones básicas
      if (typeof id !== 'number' || Number.isNaN(id)) {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      // estadoIncidente puede llegar como objeto { estado: '...' } o como string
      let nuevoEstado = null;
      if (typeof estadoIncidente === 'string') nuevoEstado = estadoIncidente;
      else if (estadoIncidente && typeof estadoIncidente.estado === 'string') nuevoEstado = estadoIncidente.estado;

      if (!nuevoEstado || nuevoEstado.trim().length === 0) {
        reject(Service.rejectResponse('Estado inválido', 400));
        return;
      }

      // Comprobar que el incidente existe
      const existe = await db.query('SELECT id FROM incidente WHERE id = ?', [id]);
      if (!existe || existe.length === 0) {
        reject(Service.rejectResponse('Recurso no encontrado', 404));
        return;
      }

      // Actualizar estado del incidente
      await db.query('UPDATE incidente SET estado = ? WHERE id = ?', [nuevoEstado.trim(), id]);

      // Registrar una notificación interna sobre el cambio de estado
      const mensaje = `Estado del incidente actualizado a: ${nuevoEstado.trim()}`;
      try {
        await db.query('INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES (?, ?, ?)', [id, mensaje, 'ESTADO_INCIDENTE']);
      } catch (e) {
        // No bloquear la operación si falla el registro de la notificación
      }

      resolve(Service.successResponse({
        id,
        estado: nuevoEstado.trim(),
        horaActualizacion: new Date().toISOString(),
      }));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        e.status || 500,
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
/*
create table if not exists incidente_hospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_id INT,
    hospital_id INT,
    paciente_id INT,

    FOREIGN KEY (incidente_id) REFERENCES incidente(id),
    FOREIGN KEY (hospital_id) REFERENCES hospital(id)
);

create table if not exists incidente_hospital_recursosHospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    incidente_hospital_id INT,
    recurso_id INT,

    FOREIGN KEY (incidente_hospital_id) REFERENCES incidente_hospital(id),
    FOREIGN KEY (recurso_id) REFERENCES recursosHospital(id)
);
*/
/*
  registroIncidente: {
  "idIncidente": 0,
  "recursosAsignados": [
    {
      "descripcion": "descripcion",
      "codigo": "codigo",
      "personalRequerido": "personalRequerido",
      "camasRequeridas": "camasRequeridas"
    },
    {
      "descripcion": "descripcion",
      "codigo": "codigo",
      "personalRequerido": "personalRequerido",
      "camasRequeridas": "camasRequeridas"
    }
  ],
  "paciente": {
    "idPaciente": 0,
    "fechaNacimiento": "2000-01-23T00:00:00.000Z",
    "notas": "notas",
    "sexo": "sexo",
    "nombre": "nombre",
    "alergias": "alergias"
  },
  "hospitalAsignado": 6,
  "timestamp": "2000-01-23T04:56:07.000Z"
}
*/
const registroIndicentePOST = ({ registroIncidente, paciente, recursos, sanitarios, hospital }) => new Promise(
  async (resolve, reject) => {
    try {

      if (!registroIncidente || typeof registroIncidente !== 'object') {
        reject(Service.rejectResponse('Petición inválida o parámetros incorrectos', 400));
        return;
      }

      const idIncidente = registroIncidente.idIncidente || registroIncidente.id || null;
      if (typeof idIncidente !== 'number' || Number.isNaN(idIncidente) || idIncidente <= 0) {
        reject(Service.rejectResponse('Se requiere un idIncidente válido', 400));
        return;
      }

      // Comprobar que el incidente existe
      const incidenteRows = await db.query('SELECT id FROM incidente WHERE id = ?', [idIncidente]);
      if (!incidenteRows || incidenteRows.length === 0) {
        reject(Service.rejectResponse('Incidente no encontrado', 404));
        return;
      }

      // Insertar/registrar paciente si viene información
      let pacienteId = null;
      const pacienteObj = paciente || registroIncidente.paciente;
      if (pacienteObj) {
        if (typeof pacienteObj.idPaciente === 'number' && pacienteObj.idPaciente > 0) {
          pacienteId = pacienteObj.idPaciente;
        } else if (typeof pacienteObj.id === 'number' && pacienteObj.id > 0) {
          pacienteId = pacienteObj.id;
        } else {
          const nombre = pacienteObj.nombre || null;
          const fechaNacimiento = pacienteObj.fechaNacimiento ? new Date(pacienteObj.fechaNacimiento).toISOString().slice(0, 10) : null;
          const sexo = pacienteObj.sexo || null;
          const alergias = pacienteObj.alergias || null;

          const insertPaciente = await db.query('INSERT INTO paciente (nombre, fecha_nacimiento, sexo, alergias) VALUES (?, ?, ?, ?)', [nombre, fechaNacimiento, sexo, alergias]);
          pacienteId = insertPaciente.insertId || insertPaciente.id || null;
        }
      }

      // Determinar hospital asignado
      const hospitalId = hospital || registroIncidente.hospitalAsignado || null;

      // Registrar la relación incidente-hospital (y paciente si existe)
      const insertIncHospQ = 'INSERT INTO incidente_hospital (incidente_id, hospital_id, paciente_id) VALUES (?, ?, ?)';
      const insertIncHospRes = await db.query(insertIncHospQ, [idIncidente, hospitalId, pacienteId]);
      const incidenteHospitalId = insertIncHospRes.insertId || insertIncHospRes.id || null;

      // Procesar recursos asignados
      const recursosArr = recursos || registroIncidente.recursosAsignados || [];
      if (Array.isArray(recursosArr) && recursosArr.length > 0 && incidenteHospitalId) {
        for (const r of recursosArr) {
          let recursoId = null;
          if (r.codigo !== undefined && r.codigo !== null) {
            const found = await db.query('SELECT id FROM recursosHospital WHERE codigo = ? LIMIT 1', [r.codigo]);
            if (found && found.length > 0) {
              recursoId = found[0].id;
            } else {
              const descripcion = r.descripcion || 'Recurso';
              const camas = r.camasRequeridas || r.camasRequeridas || 0;
              const personal = r.personalRequerido || r.personalRequerido || 0;
              const newRecurso = await db.query('INSERT INTO recursosHospital (descripcion, codigo, camasRequeridas, personalRequerido) VALUES (?, ?, ?, ?)', [descripcion, r.codigo, camas, personal]);
              recursoId = newRecurso.insertId || newRecurso.id || null;
            }
          } else {
            // Si no hay código, crear recurso básico
            const descripcion = r.descripcion || 'Recurso';
            const codigo = r.codigo || 0;
            const camas = r.camasRequeridas || 0;
            const personal = r.personalRequerido || 0;
            const newRecurso = await db.query('INSERT INTO recursosHospital (descripcion, codigo, camasRequeridas, personalRequerido) VALUES (?, ?, ?, ?)', [descripcion, codigo, camas, personal]);
            recursoId = newRecurso.insertId || newRecurso.id || null;
          }

          if (recursoId) {
            await db.query('INSERT INTO incidente_hospital_recursosHospital (incidente_hospital_id, recurso_id) VALUES (?, ?)', [incidenteHospitalId, recursoId]);
          }
        }
      }

      // Actualizar bandera de sanitarios en el incidente si procede
      if (sanitarios && Number(sanitarios) > 0) {
        try {
          await db.query('UPDATE incidente SET requiere_sanitarios = TRUE WHERE id = ?', [idIncidente]);
        } catch (e) {
          // no bloquear si falla esta pequeña actualización
        }
      }

      // Registrar notificación resumen
      try {
        const mensaje = `Registro de incidente guardado. Hospital: ${hospitalId || 'N/D'}. PacienteId: ${pacienteId || 'N/D'}`;
        await db.query('INSERT INTO notificacion (incidente_id, mensaje, tipo) VALUES (?, ?, ?)', [idIncidente, mensaje, 'HOSPITAL']);
      } catch (e) {
        // ignorar fallos en notificación
      }

      resolve(Service.successResponse({
        idIncidente,
        incidenteHospitalId,
        pacienteId,
        recursosProcesados: Array.isArray(recursosArr) ? recursosArr.length : 0,
        timestamp: registroIncidente.timestamp || new Date().toISOString(),
      }, 201));
    } catch (e) {
      reject(Service.rejectResponse(
        e.message || 'Database error',
        e.status || 500,
      ));
    }
  },
);

module.exports = {
  incidentesIdEstadoPUT,
  registroIndicentePOST,
};
