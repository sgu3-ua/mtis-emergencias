window.ServiciosEdificioInteligente = require('servicios_edificio_inteligente');
document.addEventListener('DOMContentLoaded', function () {
  const Servicios = window.ServiciosEdificioInteligente || window.servicios_edificio_inteligente;
  const api = Servicios ? new Servicios.DispositivosApi() : null;
  const apiNiveles = Servicios ? new Servicios.NivelesApi() : null;
  const apiSalas = Servicios ? new Servicios.SalasApi() : null;
  const apiNotificaciones = Servicios ? new Servicios.NotificacionesApi() : null;

  const fetchForm = document.getElementById('fetchForm');
  const manageForm = document.getElementById('manageForm');
  const result = document.getElementById('result');

  const emergenciaHospital = 'http://localhost:8081/emergenciaHospital';

  function show(msgHtml){ if (result) result.innerHTML = msgHtml; }

  function ApiNotAvailable() {
    return `<p class="muted">Error: la API no está disponible. Asegúrate de que el cliente JS se ha empaquetado correctamente instala cliente-js como node-module.</p>`;
  }

  if (!api) {
    if (result) {
      result.innerHTML = ApiNotAvailable();
    }
  }

  const hospital = document.getElementById('iniciarHospital');
  if (hospital) {
    hospital.addEventListener('click', function (e) {
      e.preventDefault();
      const   incidenteid = document.getElementById('incidenteid') ? Number(document.getElementById('incidenteid').value) : undefined;
      const lat = document.getElementById('lat') ? Number(document.getElementById('lat').value) : undefined;
      const lon = document.getElementById('lon') ? Number(document.getElementById('lon').value) : undefined;
      const nombre = document.getElementById('nombre') ? document.getElementById('nombre').value : undefined;
      const sexo = document.getElementById('sexo') ? document.getElementById('sexo').value : undefined;
      const fechaNacimiento = document.getElementById('fechaNacimiento') ? document.getElementById('fechaNacimiento').value : undefined;
      const alergias = document.getElementById('alergias') ? document.getElementById('alergias').value : undefined;
      const notas = document.getElementById('notas') ? document.getElementById('notas').value : undefined;
      const paciente = [{ nombre, sexo, fechaNacimiento, alergias, notas }];
      show('<p class="muted">Solicitando atención...</p>');
      fetch(emergenciaHospital, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ incidenteid, lat, lon, paciente })
      })
      .then(r => r.json())
      .then(data => show(`<pre class="json">${JSON.stringify(data, null, 2)}</pre>`))
      .catch(err => show(`<p class="muted">Error: ${err.message}</p>`));
    });
  }

});
