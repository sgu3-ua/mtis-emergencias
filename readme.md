# MTIS Emergencias
Proyecto final de la asignatura MTIS.  
Autores:
Javier Gárcia Cutillas  
Sergio González Urbán  
Morgana Macías Ruiz  
Antonio Máinar Sanchez  
Sara Vasquez Pons  



# Como ejecutar
Uso básico (al realizar cambios o al ejecutar):

```
docker-compose up --build -d
```

Luego si no se realizan cambios
```
docker-compose up -d
```


Si necesita cargar su propia base de datos SQL, reemplace `db/init.sql` por su script SQL; el contenedor Postgres ejecutará los archivos `.sql` en esa carpeta la primera vez que se inicialice la base de datos.

Servicios incluidos:
- **app**: la API Express (puerto `8080`).
- **db**: base de datos Postgres (puerto `3306`).

Variables relevantes (en `docker-compose.yml`): `DB_HOST`, `DB_PORT`, `DB_USER`, `DB_PASSWORD`, `DB_NAME`.
