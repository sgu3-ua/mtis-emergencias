(function(){function r(e,n,t){function o(i,f){if(!n[i]){if(!e[i]){var c="function"==typeof require&&require;if(!f&&c)return c(i,!0);if(u)return u(i,!0);var a=new Error("Cannot find module '"+i+"'");throw a.code="MODULE_NOT_FOUND",a}var p=n[i]={exports:{}};e[i][0].call(p.exports,function(r){var n=e[i][1][r];return o(n||r)},p,p.exports,r,e,n,t)}return n[i].exports}for(var u="function"==typeof require&&require,i=0;i<t.length;i++)o(t[i]);return o}return r})()({1:[function(require,module,exports){

document.addEventListener('DOMContentLoaded', function () {

  const fetchForm = document.getElementById('fetchForm');
  const manageForm = document.getElementById('manageForm');
  const result = document.getElementById('result');

  const baseIP = 'http://localhost';

  const emergenciaHospital = `${baseIP}:8081/emergenciaHospital`;
  const emergenciaPolicia = `${baseIP}:9092/policia`;
  const emergenciaBomberos = `${baseIP}:8082/despachos-bomberos`;

  const llamadaEmergencia = `${baseIP}:8080/llamadaEmergencia`;
  const cierreIncidente = '/cerrarIncidente';

  function show(msgHtml){ if (result) result.innerHTML = msgHtml; }

  function ApiNotAvailable() {
    return `<p class="muted">Error: la API no está disponible. Asegúrate de que el cliente JS se ha empaquetado correctamente instala cliente-js como node-module.</p>`;
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
      show('<p class="muted">Solicitando atención...</p>');
      //Es SOAP por tanto asi no sera
      fetch(llamadaEmergencia, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ incidenteid })
      })
      .then(r => r.json())
      .then(data => show(`<pre class="json">${JSON.stringify(data, null, 2)}</pre>`))
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

},{}]},{},[1]);
