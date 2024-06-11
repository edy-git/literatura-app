package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.IAutorRepository;
import com.alura.literalura.repository.ILibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal
{
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private final ILibroRepository repositorioLibro;
    private final IAutorRepository repositorioAutor;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(ILibroRepository repositoryLibro, IAutorRepository repositoryAutor)
    {
        this.repositorioLibro = repositoryLibro;
        this.repositorioAutor = repositoryAutor;
    }
    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    ______________ MENU PRINCIPAL _________________
                    
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Mostrar estadistica de descargas de libros
                    7 - Top 10 - libros mas descargados
                    
                    0 - Salir
                    _______________________________________________
                    \nDigite un numero del menu para iniciar:
                    """;
            System.out.println(menu);
            while (!teclado.hasNextInt()) {
                System.out.println("Formato incorrecto, ingrese un número que esté disponible en el menú");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPortitulo();
                    break;

                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    mostrarEstadistica();
                    break;
                case 7:
                    top10Libros();
                    break;


                case 0:
                    System.out.println("Cerrando la aplicación...\n");
                    break;
                default:
                    System.out.println("Ninguna opcion seleccionada\n");

            }
        }
    }

    private Datos obtenerDatosLibro() {
        System.out.println("Ingrese una parte o el titulo completo del libro que desea buscar: ");
        var pedazoDeTitulo = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + pedazoDeTitulo.replace(" ", "+"));
        Datos datos= conversor.obtenerDatos(json, Datos.class);
        return datos;
    }

    private void buscarLibroPortitulo()
    {
        Datos datosBusqueda = obtenerDatosLibro();
        if (datosBusqueda != null && !datosBusqueda.resultados().isEmpty()) {
            DatosLibro primerLibro = datosBusqueda.resultados().getFirst();


            Libro libro = new Libro(primerLibro);

            Optional<Libro> libroExiste = repositorioLibro.findByTituloContainsIgnoreCase(libro.getTitulo());
            if (libroExiste.isPresent()){
                System.out.println("\nEl libro ya esta registrado\n");
            }else {

                if (!primerLibro.autores().isEmpty()) {
                    DatosAutor autor = primerLibro.autores().getFirst();
                    Autor datoAutor = new Autor(autor);
                    Optional<Autor> autorOptional = repositorioAutor.findByNombreIgnoreCase(datoAutor.getNombre());

                    if (autorOptional.isPresent()) {
                        Autor autorExiste = autorOptional.get();
                        libro.setAutor(autorExiste);
                        repositorioLibro.save(libro);
                    } else {
                        Autor autorNuevo = repositorioAutor.save(datoAutor);
                        libro.setAutor(autorNuevo);
                        repositorioLibro.save(libro);
                    }

                    System.out.println("\nLibro encontrado\n");
                    System.out.println("---------- Libro ----------");
                    System.out.printf("Titulo: %s%n" +
                                      "Autor: %s%n" +
                                      "Idioma: %s%n" +
                                      "Numero de Descargas: %.1f%n",
                            libro.getTitulo(),
                            datoAutor.getNombre(),
                            libro.getIdioma(),
                            libro.getNumeroDeDescargas());
                    System.out.println("---------------------------\n");
                } else {
                    System.out.println("\nSin autor\n");
                }
            }
        } else {
            System.out.println("\nlibro no encontrado\n");
        }
    }

    private void listarLibrosRegistrados() {
        libros = repositorioLibro.findAll();
        System.out.println("\n--------- Libros registrados ---------\n");
        libros.forEach(System.out::println);

    }

    private void listarAutoresRegistrados() {
        autores = repositorioAutor.findAll();
        System.out.println("\n--------- Autores registrados ---------\n");
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos()
    {
        System.out.println("Ingrese un año: ");
        scannerSoloNumeros();
        var anio = teclado.nextInt();
        autores = repositorioAutor.autoresVivosPorDeterminadoAnio(anio);
        if (autores.isEmpty()) {
            System.out.println("\n----- No hay registro de autores en ese año -----\n");
        } else {
            System.out.println("\n------ Autor(es) vivo(s) en ese año ------\n");
            autores.forEach(System.out::println);
        }
    }

    private void scannerSoloNumeros() {
        while (!teclado.hasNextInt()) {
            System.out.println("Formato incorrecto, ingrese solo numeros: ");
            teclado.nextLine();
        }
    }

    private void datosBusquedaLenguaje(CodigoIdioma idioma){
        try {
            libros = repositorioLibro.findByIdioma(idioma);

            if (libros.isEmpty()) {
                System.out.println("\nNo hay libros registrados en ese idioma, escoja otro numero de las opciones\n");
            } else {
                System.out.printf("\n---- Libro(s) registrado(s) en"+ " idioma: %s%n",
                        libros.getFirst().getIdioma()+" ----\n");

                libros.forEach(System.out::println);
            }
        }catch (Exception e){
            System.out.println("Error en la busqueda");
        }
    }

    private void listarLibrosPorIdioma(){

        var opcion = -1;
        while (opcion != 0) {
            var opciones = """
                    ______________ OPCIONES DE IDIOMA _________________
                    
                    1. [en] - Inglés
                    2. [de] - Alemán
                    3. [fr] - Francés
                    4. [es] - Español
                    5. [it] - Italiano
                    6. [ru] - Ruso
                    7. [zh] - Chino
                    8. [pt] - Portugues
                    
                    0. Volver a Las opciones anteriores
                    
                    ___________________________________________________
                    \nDigite un numero del menu para el idioma que deseas buscar:
                    """;
            System.out.println(opciones);
            while (!teclado.hasNextInt()) {
                System.out.println("Formato incorrecto, ingrese un número que esté disponible en el menú: ");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("ENGLISH"));

                    break;
                case 2:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("GERMAN"));

                    break;
                case 3:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("FRENCH"));

                    break;
                case 4:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("SPANISH"));

                    break;
                case 5:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("ITALIAN"));

                    break;
                case 6:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("RUSSIAN"));

                    break;
                case 7:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("CHINESE"));

                    break;
                case 8:
                    datosBusquedaLenguaje(CodigoIdioma.valueOf("PORTUGUES"));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Ningún idioma seleccionado\n");
            }
        }
    }

    public void mostrarEstadistica()
    {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);

        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(l -> l.numeroDeDescargas()> 0.0)
                .collect(Collectors.summarizingDouble(DatosLibro::numeroDeDescargas));
        System.out.println("\n--------- Estadísticas de descargas de libros de Gutendex -------------\n");
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad maxima de descargas " + est.getMax());
        System.out.println("Cantidad minima de descargas  " + est.getMin());
        System.out.println("Cantidad total de registros evaluados para calcular las estadisticas: " + est.getCount());
        System.out.println("\n------------------------------------------------------------------------\n");
    }

    public void top10Libros()
    {
        var json = consumoApi.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, Datos.class);

        List<DatosLibro> apiBooks = datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
                .limit(10)
                .toList();

        System.out.println("\n---- Titulos de los mejores 10 libros de Gutendex ----\n");
        apiBooks.stream()
                .map(l -> (apiBooks.indexOf(l) + 1) + ". " + l.titulo().toUpperCase())
                .forEach(System.out::println);

        System.out.println("\n----- Top de los mejores 10 libros de Gutendex -----\n");
        apiBooks.forEach(d -> System.out.printf("""
                ------------ API LIBRO ------------
                Título: %s
                Autor: %s
                Idioma: %s
                Número de descargas: %.1f
                -----------------------------------
                
                """, d.titulo(), d.autores().getFirst().nombre(), d.idiomas().getFirst(), d.numeroDeDescargas()));
    }
}