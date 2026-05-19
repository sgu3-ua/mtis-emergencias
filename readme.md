# MTIS Emergencias
Proyecto final de la asignatura MTIS.  
Autores:
Javier García Cutillas  
Sergio González Urbán  
Morgana Macías Ruiz  
Antonio Mainar Sánchez  
Sara Vázquez Pons  



# Como ejecutar
Uso básico (al realizar cambios o al ejecutar):

Express + BD
```
docker-compose up --build -d
```

Luego si no se realizan cambios
```
docker-compose up -d
```

Cliente (http:/localhost:5173)
```
cd client
npm install
npm install -g browserify
npm start
```
  
SOAP  
Importar proyectos a eclipse y ejecutar en un solo tomcat  
  
Mulesoft  
Importar todos los proyectos y arrancarlos a la vez  

FakeSMTP  
Arrancar y elegir puerto 25  



Si necesita cargar su propia base de datos SQL, reemplace `db/init.sql` por su script SQL; el contenedor mysql ejecutará los archivos `.sql` en esa carpeta la primera vez que se inicialice la base de datos.

Servicios incluidos docker:
- **app**: la API Express (puerto `8080`).
- **db**: base de datos mysql (puerto `3306`).

Variables relevantes (en `docker-compose.yml`): `DB_HOST`, `DB_PORT`, `DB_USER`, `DB_PASSWORD`, `DB_NAME`, `SMTP_HOST`, `SMTP_PORT`.
