# API de Precios - Documentación y Guía de Uso

## Descripción.
Esta aplicación Spring Boot proporciona un servicio REST para consultar precios de productos en función de una fecha y un identificador de cadena (marca).

## Tecnologías Utilizadas
* Spring Boot
* H2 Database (en memoria)
* Swagger (OpenAPI) para documentación y pruebas
* JUnit y Cucumber para pruebas unitarias, de integración y E2E

## Requisitos Previos
* JDK 17 o superior
* Maven (para la construcción del proyecto)

## Instalación
* Compilacion
```
mvn clean install
```

* iniciar aplicacion
````
mvn spring-boot:run
````

## USO
### Acceder a la documentación API
* Desde el navegador:
  http://localhost:8080/swagger-ui/index.html
#### Aqui tendras acceso a los endpoints, podras probar la aplicacion directamente.

### Acceder a la base de datos H2 en memoria
* Desde el navegador:
  http://localhost:8080/h2-console

#### Aqui tendras acceso a la base de datos
#### Los datos de acceso se encuentran en el fichero de configuración

## Test unitarios y test de integracion

````
mvn test
````

## FEATURE FILES
#### Disponemos de una bateria de pruebas E2E
