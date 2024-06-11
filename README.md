<h1 align="center"> LITERALURA  APP - JAVA + SPRING BOOT</h1>

![Imagende portada Java](https://solace.com/wp-content/uploads/2018/12/spring-boot-java.jpg)

## Descripcion
Literalura es una aplicación de consola desarrollada en Java con el framework Spring Boot, JPA, Hibernate, al tiempo que se realizan consultas y guardado de datos en una base de datos tipo PostgreSQL.

La aplicación fue desarrollada en Java, permite buscar libros mediante la API de Gutendex y asi poder guardar los libros buscados, al mismo tiempo guardar los datos de autores relacionados, para así tener una aplicación dinámica que permita las opciones como: listar libros, listar autores, listar libros por idiomas, mostrar los autores vivos en una fecha determinada, obtener estadísticas sobre los libros más descargados y mostrar los libros más populares.

## Estado del proyecto
- El proyecto se encuentra en su versión 1.0.

## Caracteristicas de la aplicacion
- Muestra un menú con opciones para la busqueda de libros.
- Comprende una interfaz intuitiva para el usuario de facil selección.
- Obtiene una basta y enorme cantidad de registro de libros digitales actualizados desde una API externa, garantizando asi la obtencion de los datos.
- Realiza la obtencion de informacion de los libros digitales previamente seleccionada por el usuario, segun las opciones previstas.


## Requisitos del sistema
>[!WARNING]
>- Windows 10 (8u51 y superiores).
>- Java 8 o superior.
>- Conexión a Internet.
>- Seguir las instrucciones de la pagina [Gutendex][1] para la utilizacion correcta de su API_KEY.

## Instalación
1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE de Java preferido.
3. Compila y ejecuta el archivo Principal.java para iniciar la aplicación.

>[!NOTE]
>No se te olvide implementar las siguientes dependencias en tu archivo pom.xml si hiciera falta:
>- spring-boot-starter-data-jpa.
>- postgresql.
>- jackson-databind.

## Uso de la aplicacion
- Al iniciar la aplicación es importante usar la primera opcion pues la busqueda de un libro tambien hara posible el guadado automatico de un libro en tu base de datos local.
- Luego accesaras facilmente a las demas opciones del menu, una vez que tengas varios registros de libros en tu base de datos.
- La aplicación mostrara los datos solicitados de un libro mediante un menu de opciones que podras accesar facilmente al inicio.
- Tambien puedes optar por salir de la apliacion segun la opcion provista por el menu.

## Demostracion de la aplicacion
### Menu
#### 1.  Inicio al sistema.
![Menu]()
#### 2. Opcion 1: Buscar 1er libro por titulo
![Opciones]()
#### 3. Resultado de la busqueda del 1er libro
![ResultadoConverion1]()
#### 4. Opcion 1: Buscar 2do libro por titulo
![opciones2]()
#### 5. Resultado de la busqueda del 2do libro
![ResultadoConversion2]()
#### 6. Listar libros registrados
![Historial]()
#### 7. Listar autores registrados
![Historial]()
#### 8. Buscar autores vivos por un determinado año
![Historial]()
#### 9. Listar autores vivos por un determinado año
![Historial]()
#### 10. Buscar libros regitrados por idioma
![Historial]()
#### 11. Listar libros regitrados por idioma
![Historial]()
#### 12. Mostrar estadistica de descargas de libros
![Historial]()
#### 13. Top 10 de los mejores titulos de libros de Gutendex
![Historial]()
#### 14. Top 10 de los mejores libros de Gutendex
![Historial]()
#### 15. Control de Errores
En esta aplicacion se ha prestado atención al manejo de errores, para garantizar un comportamiento robusto y una experiencia de usuario fluida. A continuación, se mostrara el manejo de errores que se han implementado en el presente proyecto.

>[!CAUTION]
>- Error al ingresar un caracter desconocido y un valor fuera del menú principal.
>- ![ErrorMenuValor]()
>- Error al ingresar un caracter no numerico y un valor numerico no previsto de un año en la base de datos.
>- ![ErrorMenuCaracter]()
>- Error al ingresar un caracter desconocido y un valor fuera del menú de idioma.
>- ![ErrorValorConvertido]()

##### 7.1 Captura de Excepciones
Tambien se ha implementado bloques try-catch para capturar excepciones y manejarlas adecuadamente.

## Tecnologías utilizadas
- Intellij IDEA.
- JDK de Java.
- Framework Spring Boot.
- Gutendex - JSON web API.

## Contribuir
¡Siéntete libre de contribuir al proyecto! Si tienes ideas para nuevas características, mejoras en el código o correcciones de errores, abre un pull request y estaré encantado de revisarlo.

## Autor
Este proyecto fue creado por: Eduardo Argana Rodriguez.

>[!IMPORTANT]
> ¡Siéntete libre de contactarme si tienes alguna pregunta o sugerencia!
>- [LinkedIn][2]

## End
[1]: https://gutendex.com/ "GutendexI"
[2]: https://www.linkedin.com/in/eduardo-argana-igs/ "LinkedIn"