
document.addEventListener('DOMContentLoaded', function () {

  const fetchForm = document.getElementById('fetchForm');
  const manageForm = document.getElementById('manageForm');
  const result = document.getElementById('result');

  const baseIP = 'http://localhost';

  const emergenciaHospital = `${baseIP}:8081/emergenciaHospital`;
  const emergenciaPolicia = `${baseIP}:9092/policia`;
  const emergenciaBomberos = `${baseIP}:8082/despachos-bomberos`;

  const llamadaEmergencia = `/llamadas`; // Soap
  const cierreIncidente = '/cerrarIncidente'; // Soap

  function show(msgHtml){ if (result) result.innerHTML = msgHtml; }

  function ApiNotAvailable() {
    return `<p class="muted">Error: la API no está disponible. Asegúrate de que el cliente JS se ha empaquetado correctamente instala cliente-js como node-module.</p>`;
  }
  function escapeXml(unsafe) {
    if (unsafe === undefined || unsafe === null) return '';
    return String(unsafe)
      .replace(/&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
      .replace(/'/g, '&apos;');
  }
  // region Hospital
  const hospital = document.getElementById('iniciarHospital');
  if (hospital) {
    hospital.addEventListener('click', function (e) {
      e.preventDefault();
      const  incidenteid = document.getElementById('incidenteid') ? Number(document.getElementById('incidenteid').value) : undefined;
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
  // region Llamada emergencia
  const llamada = document.getElementById('llamadaEmergencia');
  if (llamada) {
    llamada.addEventListener('click', function (e) {
      e.preventDefault();
      const telefono = document.getElementById('telefono') ? document.getElementById('telefono').value : '';
      const localizacion = document.getElementById('localizacion') ? document.getElementById('localizacion').value : '';
      const descripcion = document.getElementById('descripcion') ? document.getElementById('descripcion').value : '';
      const afectados = document.getElementById('afectados') ? document.getElementById('afectados').value : '';
      const hayFuego = document.getElementById('hayFuego') ? (document.getElementById('hayFuego').checked ? 'true' : 'false') : 'false';
      const hayHumo = document.getElementById('hayHumo') ? (document.getElementById('hayHumo').checked ? 'true' : 'false') : 'false';
      const personasAtrapadas = document.getElementById('personasAtrapadas') ? (document.getElementById('personasAtrapadas').checked ? 'true' : 'false') : 'false';
      const personasHeridas = document.getElementById('personasHeridas') ? (document.getElementById('personasHeridas').checked ? 'true' : 'false') : 'false';
      const riesgoSeguridad = document.getElementById('riesgoSeguridad') ? (document.getElementById('riesgoSeguridad').checked ? 'true' : 'false') : 'false';
      const riesgoEstructural = document.getElementById('riesgoEstructural') ? (document.getElementById('riesgoEstructural').checked ? 'true' : 'false') : 'false';
      const alteracionOrdenPublico = document.getElementById('alteracionOrdenPublico') ? (document.getElementById('alteracionOrdenPublico').checked ? 'true' : 'false') : 'false';

      show('<p class="muted">Solicitando atención...</p>');

      const xml = `<?xml version="1.0" encoding="UTF-8"?>\n` +
        `<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns0="http://www.example.org/ServicioGestionLlamadaEmergencia/" xmlns:typ="http://www.example.org/tipos">\n` +
        `  <soap:Body>\n` +
        `    <ns0:gestionarLlamadaEmergencia>\n` +
        `      <typ:aviso>\n` +
        `        <typ:telefono>${escapeXml(telefono)}</typ:telefono>\n` +
        `        <typ:localizacion>${escapeXml(localizacion)}</typ:localizacion>\n` +
        `        <typ:descripcion>${escapeXml(descripcion)}</typ:descripcion>\n` +
        (afectados !== '' ? `        <typ:afectados>${escapeXml(afectados)}</typ:afectados>\n` : '') +
        `        <typ:hayFuego>${hayFuego}</typ:hayFuego>\n` +
        `        <typ:hayHumo>${hayHumo}</typ:hayHumo>\n` +
        `        <typ:personasAtrapadas>${personasAtrapadas}</typ:personasAtrapadas>\n` +
        `        <typ:personasHeridas>${personasHeridas}</typ:personasHeridas>\n` +
        `        <typ:riesgoSeguridad>${riesgoSeguridad}</typ:riesgoSeguridad>\n` +
        `        <typ:riesgoEstructural>${riesgoEstructural}</typ:riesgoEstructural>\n` +
        `        <typ:alteracionOrdenPublico>${alteracionOrdenPublico}</typ:alteracionOrdenPublico>\n` +
        `      </typ:aviso>\n` +
        `    </ns0:gestionarLlamadaEmergencia>\n` +
        `  </soap:Body>\n` +
        `</soap:Envelope>`;

      fetch(llamadaEmergencia, {
        method: 'POST',
        headers: {
          'Content-Type': 'text/xml; charset=utf-8',
          'SOAPAction': 'http://www.example.org/ServicioGestionLlamadaEmergencia/gestionarLlamadaEmergencia'
        },
        body: xml
      })
      .then(r => r.text())
      .then(text => {
        const doc = new DOMParser().parseFromString(text, 'text/xml');
        const body = doc.querySelector('soap\\:Body, Body');
        const resp = body?.firstElementChild;
        const obj = resp ? Object.fromEntries([...resp.children].map(n => [n.localName, n.textContent])) : { raw: text };
        show(`<pre class="json">${JSON.stringify(obj, null, 2)}</pre>`);
      })
      .catch(err => show(`<p class="muted">Error: ${err.message}</p>`));
    });
  }










  // region Cierre incidente
  const cierre = document.getElementById('cerrarIncidente');
  if (cierre) {
    cierre.addEventListener('click', function (e) {
      e.preventDefault();
      const incidenteid = document.getElementById('incidenteid')?.value;
      show('<p class="muted">Cerrando incidente...</p>');

      const xml = `<?xml version="1.0" encoding="UTF-8"?>
                    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns0="http://www.example.org/ServicioProcesoCierreIncidente/">
                      <soap:Body>
                        <ns0:Cerrar>
                          <ns0:incidenteId>${incidenteid}</ns0:incidenteId>
                        </ns0:Cerrar>
                      </soap:Body>
                    </soap:Envelope>`;

      fetch(cierreIncidente, {
        method: 'POST',
        headers: {
          'Content-Type': 'text/xml; charset=utf-8',
          'SOAPAction': 'http://www.example.org/ServicioProcesoCierreIncidente/Cerrar'
        },
        body: xml
      })
      .then(r => r.text())
      .then(text => {
        const doc = new DOMParser().parseFromString(text, 'text/xml');
        const body = doc.querySelector('soap\\:Body, Body');
        const resp = body?.firstElementChild;
        const obj = resp ? Object.fromEntries([...resp.children].map(n => [n.localName, n.textContent])) : { raw: text };
        show(`<pre class="json">${JSON.stringify(obj, null, 2)}</pre>`);
      })
      .catch(err => show(`<p class="muted">Error: ${err.message}</p>`));
    });
  }









  // region Policia
  const policia = document.getElementById('llamadaPolicia');
  if (policia) {
    policia.addEventListener('click', function (e) {
      e.preventDefault();
      const incidenteid = document.getElementById('incidenteid') ? Number(document.getElementById('incidenteid').value) : undefined;
      show('<p class="muted">Solicitando atención policial...</p>');
      fetch(emergenciaPolicia, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ incidenteid })
      })
      .then(r => r.json())
      .then(data => show(`<pre class="json">${JSON.stringify(data, null, 2)}</pre>`))
      .catch(err => show(`<p class="muted">Error: ${err.message}</p>`));
    });
  }








  // region Bomberos
  const bomberos = document.getElementById('llamadaBomberos');
  if (bomberos) {
    bomberos.addEventListener('click', function (e) {
      e.preventDefault();
      const idIncidente = document.getElementById('incidenteid') ? Number(document.getElementById('incidenteid').value) : undefined;
      const gravedad = document.getElementById('gravedad') ? document.getElementById('gravedad').value : undefined;
      const vehiculosRequeridos = document.getElementById('vehiculosRequeridos') ? Number(document.getElementById('vehiculosRequeridos').value) : undefined;
      
      show('<p class="muted">Solicitando atención de bomberos...</p>');
      fetch(emergenciaBomberos, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ idIncidente, gravedad, vehiculosRequeridos })
      })
      .then(r => r.json())
      .then(data => show(`<pre class="json">${JSON.stringify(data, null, 2)}</pre>`))
      .catch(err => show(`<p class="muted">Error: ${err.message}</p>`));
    });
  }








});
