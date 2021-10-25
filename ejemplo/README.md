Proyecto de creación de usuario con Java API RESTful

Para probar se debe generar el war a través de maven

mvn clean install

Con el war generado (ejemplo-rest.war), se debe subir a un servidor o contenedor de aplicaciones (probado en tomcat y weblogic) y desplegar.

La url para probar es http://localhost:8080/ejemplo-rest

Los endpoints expuestos son:

http://localhost:8080/ejemplo-rest/user/create
http://localhost:8080/ejemplo-rest/user/update
http://localhost:8080/ejemplo-rest/user/get
http://localhost:8080/ejemplo-rest/user/delete

Dentro del proyecto (ejemplo\src\main\resources\sql) se encuentra la carpeta sql en donde está el archivo de estructura de la base de datos con la creación de las tablas.

Tanto la expresión regular para la validación de password como para validar el email se configuran en el archivo application.properties.