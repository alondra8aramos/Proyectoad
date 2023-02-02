# Proyectoad
ApiRest para administracion de usuarios
GET/POST/PUT/DELETE



Herramientas:
JDK: Motor de lenguaje con todas las librerías.


IDE ECLIPSE + spring tools 4: Editor para escribir el código + plugin spring tools para crear proyecto con spring actualizado y 
facilitando la configuración, librerías y dependencias de Maven.
Spring initializr
Hibernate (No es como tal un framework)
Maven: Para administrar dependencias, generar los proyectos, descargar librerías, ya incluido dentro de eclipse y configurado.

Correr proyecto: mvn spring-boot:run

spring version: 2.7.8
java version: 11

Dependencias: 
springfox
Spring Boot DevTools 
Spring Data JP
Spring Web

Frameworks:
Spring Framework:
Realizar inyeccion de dependencias de forma sencilla en archivo xml.
Simplificacion en la configuracion del proyecto.
Facil creacion con sprint initializr.
Se pudo testear la aplicacion de manera mas simple.
Facilidad para programacion MVC.
Facil mapeo y manejo de objetos gracias a las librerias, asi como el facil manejo de catalogo de beans que se quieren usar.
Lo mas importante, comunidad muy grande y activa para encontrar documentacion, ayuda, ejemplo y tutoriales para cualquieir problema que surga, como el que tuve con las versiones.


DIFICULTADES:

Dificultas en las versiones, antigua version con java 17 y spring 3, Error de compatibilidad entre dependencias spring fox y jakarta por lo que swagger no funcionaba,
se opto por cambiar las versiones para volver a paquete javax y solucionar la compatibilidad con springfox.
