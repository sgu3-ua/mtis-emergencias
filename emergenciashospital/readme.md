"endpoint" localhost:8081/emergenciaHospital
entrada: 
{
  "paciente" : [{
    "nombre" : "Josefa",
    "sexo" : "m",
    "alergias" : "Ninguna",
    "notas" : "No se calla",
    "fechaNacimiento" : "2000-11-12"
  }],
  "lat" : 5,
  "lon" : 3,
  "incidenteid": 1
}

salida:
Signifca que ha terminado, no que hay hospitales disponibles
{
    "estado" : true
}
error controlable: 
{
	"error" : "Datos no validos",
	"detalles" : payload.elementosInvalidos
}
{
	"error" : "No existe el incidente"
}
error: 
{
	"content" : payload,
	"error" : "Ha ocurrido un error durante el flujo",
	"errorMessage" : error.errorMessage,
	"errorType" : error.errorType,
	"errorDescription" : error.description
}