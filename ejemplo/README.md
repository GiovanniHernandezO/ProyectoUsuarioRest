Proyecto de creación de usuario con Java API RESTful

Para probar se debe generar el war a través de maven

mvn clean install

Con el war generado, se debe subir a un servidor o contenedor de aplicaciones (como tomcat) y desplegar.

La url para probar es http://localhost:8080/ejemplo

Los endpoints expuestos son:

http://localhost:8080/ejemplo/user/create
http://localhost:8080/ejemplo/user/update
http://localhost:8080/ejemplo/user/get
http://localhost:8080/ejemplo/user/delete