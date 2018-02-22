# Proyecto Noticias

## Propiedades
* Uso de javaEE y PostgreSQL
* Carga noticias en la página principal desde BD.
* Carga una noticia desde BD al acceder a ella.
* Añadir noticias (titulo, desripcion, imagen) con roles glashfish.
* Mensajes de confirmación/carga, etc.

## Changelog

### Version 0.5
* Pequeñas correcciones de errores

### Version 0.4
* Implementación del FileImput para subir imagenes

### Version 0.3
* Al hacer clic en una noticia, la carga entera desde BD en una plantilla jsp
* Se han añadido loaders y mensajes de carga

### Version 0.2
* Añadidos permisos en glashfish
* Pagina de creación de noticias incluida
* Implementación del tinymc
* Guarda noticias en BD (solo titulo y descipción)

### Version 0.1
* Creación de noticias en la BD para realizar pruebas
* Carga noticias desde BD al jsp

### Version 0.0
* Creación del proyecto con Java EE a partir del proyecto del año pasado
* Configuración de la base de datos en Postgree, entidades, servlets, etc.


## ¿Cómo funciona?

### Cargar notcias en pag principal
* Al iniciar la app llamo al servlet "Noticias" que busca en la BD las primeras noticias por orden de fecha y las carga como una lista en un jsp
* La lista de noticias se ejecuta en un bucle

Continuar...!!!


