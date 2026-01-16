/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package es.educastur.givanbr90.biblioteca2025;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.directory.ModificationItem;
import jdk.management.jfr.FlightRecorderMXBean;

/**
 *
 * @author 1dawd17
 */
public class Biblioteca2025 {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Libro> libros = new ArrayList();//Declaración del ArrayList
    private static ArrayList<Usuario> usuarios = new ArrayList();
    private static ArrayList<Prestamo> prestamos = new ArrayList();
    private static final ArrayList<Prestamo> prestamosHist = new ArrayList();
    //Lo ideal es que dentro del diamante esté en singular y el nombre en plural. Es decir <Libro> que representa la class/objeto, y libros de cada uno de los libros
    private static String isbn;

    public static void main(String[] args) {
        //FCOM+tab crea el editor para crear tags que permiten plegar y desplegar metodos

        cargaDatos();//Llama al método que carga 10 libros en la colección y añadir uno nuevo
        //<editor-fold defaultstate="collapsed" desc="Menus">
        menuOpciones();
        //</editor-fold>
        //buscaFecha();
        //listadosConStreams();
        ordenarConStream();
        //<editor-fold defaultstate="collapsed" desc="Examen 12-12">
        //cargaDatosPrueba12();
        //uno();
        //dos();
        //tres();
        //cuatro();
        //cinco();
        //</editor-fold>

    }

    //<editor-fold defaultstate="collapsed" desc="Examen 12/12/2025">
    public static void cargaDatosPrueba12() {
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Aventuras", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Aventuras", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        LocalDate hoy = LocalDate.now(); //OBTENEMOS LA FECHA DE HOY CON EL MÉTODO now()

        //PRESTAMOS "NORMALES" REALIZADOS HOY Y QUE SE HAN DE DEVOLVER EN 15 DÍAS
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(4), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(1), hoy, hoy.plusDays(15)));
        //PRESTAMOS QUE YA TENIAN QUE HABER SIDO DEVUELTOS PORQUE SU FECHA DE DEVOLUCIÓN ES ANTERIOR A HOY
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(17), hoy.minusDays(2)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(18), hoy.minusDays(3)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));

        //PRESTAMOS HISTORICOS QUE YA HAN SIDO DEVUELTOS Y POR TANTO ESTÁN EN LA COLECCION prestamosHist
        prestamosHist.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(2), usuarios.get(0), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(4), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(5), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(1), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(2), hoy.minusDays(10), hoy));
        prestamosHist.add(new Prestamo(libros.get(6), usuarios.get(3), hoy.minusDays(10), hoy));
    }

    public static void uno() {
        // EL DÍA 13-1-2026 SE APRENDE A HACER PUSH Y PULL CON EL GITHUB
        /*
        Lo importante es saber organizar el if después de cada bucle
         */

        //Vamos a contar los libros prestados de este usuario, activos, historicos y el total de todos
        System.out.println("Vamos a buscar un libro por su ISBN introducido por teclado");
        System.out.println("ISBN del libro: ");
        String isbn = sc.next();
        int posL = buscaLibro(isbn);
        if (posL == -1) {
            System.out.println("Ese ISBN no existe en la Biblioteca");
        } else {
            System.out.println("Prestamos activos de: " + libros.get(posL).getTitulo());
            int prestAct = 0;
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equalsIgnoreCase(isbn)) {
                    /*Esto es lo mismo que hizo Edu en la solución, p representa cada préstamo de la colección de prestamos*/
                    System.out.println(p);
                    /*Mostramos los libros de dicha colección*/
                    prestAct++;
                    /*Contamos los prestamos de ese ISBN*/
                }
            }
            System.out.println(libros.get(posL).getTitulo() + " está prestado en " + prestAct + " prestamos activos ");

            /*System.out.println("\nPrestamos historicos del libro: " + libros.get(posL).getTitulo());
            int prestHist = 0;
            for (Prestamo p : prestamosHist) {
                if (p.getLibroPrest().getIsbn().equalsIgnoreCase(isbn)) {
                    System.out.println(p);
                    prestHist++;
                }
            }
            System.out.println(libros.get(posL).getTitulo() + " fue prestado en " + prestHist + " prestamos historicos");*/
        }

        //Dime dni del usuario por teclado, consulta si es usuario, si lo es, dime los prestamos activos y los historicos
        /*System.out.print("\n\nVamos a introducir un DNI mediante el teclado");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        System.out.println("\n\nDNI del USUARIO: ");
        String dni = sc.next();
        int pos = buscaUsuario(dni);
        if (pos == -1) {
            System.out.println("Ese DNI no existe en la biblioteca");
        } else {
            System.out.println("Prestamos activos de: " + usuarios.get(pos).getNombre());
            int prestAct = 0;
            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest().getDni().equalsIgnoreCase(dni)) {
                    //(p.getUsuarioPrest().equals(usuarios.get(pos))) estoy comparando el usuario que se llevo el prestamos con el usuario que he tecleao
                    System.out.println(p);
                    prestAct++;
                }
            }
            System.out.println(usuarios.get(pos).getNombre() + " tiene " + prestAct + " prestamos activos");

            System.out.println("\nPrestamos historicos de: " + usuarios.get(pos).getNombre());
            int prestHist = 0;
            for (Prestamo p : prestamosHist) {
                if (p.getUsuarioPrest().getDni().equalsIgnoreCase(dni)) {
                    System.out.println(p);
                    prestHist++;
                }
            }
            System.out.println(usuarios.get(pos).getNombre() + " tiene " + prestHist + " prestamos historicos");
        }
        System.out.println("");*/

 /*Vamos a buscar un prestamo por su fecha de prestamo
        System.out.println("Vamos a buscar un prestamo por su fecha");
        System.out.println("Fecha del prestamo (Año-Mes-Día):");

        // Eliminamos el salto de línea previo
        sc.nextLine();

        String fechaTexto = sc.nextLine();
        LocalDate fechaPrest = LocalDate.parse(fechaTexto);

        int posF = buscaFecha(fechaPrest);

        if (posF == -1) {
            System.out.println("Esta fecha no está asociada a ningún préstamo activo");
        } else {
            System.out.println("Préstamos activos con fecha " + fechaPrest + ":");

            int prestAct = 0;
            for (Prestamo p : prestamos) {
                if (p.getFechaPrest().isEqual(fechaPrest)) {
                    System.out.println(p);
                    prestAct++;
                }
            }

            System.out.println("Total de préstamos activos en esa fecha: " + prestAct);
        }*/
        System.out.println("");
    }

    public static void dos() {
        System.out.println("Vamos a buscar libros que no hayan sido prestados nunca");
        for (Libro l : libros) {
            /*
            Corrección
             */
            int cont = 0;
            for (Prestamo p : prestamos) {//Miro si hay prestamos activos de cada libro
                if (p.getLibroPrest().equals(l)) {
                    cont++;
                    break;//Si encuentro un préstamo paro de contar

                }
            }
            if (cont == 0) {//Primero preguntamos si después de ver los prestamos activos el contador sigue a 0, en caso de que siga a 0, pasamos a revisar los históricos para evitar volver a recorrer los activos antes de entrar en HIST
                for (Prestamo p : prestamosHist) {//Si no tiene prestamos activos pues vemos lo historicos
                    if (p.getLibroPrest().equals(l)) {
                        cont++;
                        break;
                    }

                }
            }
            if (cont == 0) {//Mostramos solo los libros que no tienen ni prestamos activos ni históricos
                System.out.println(l);
            }
        }
    }

    public static void tres() {

        System.out.print("\n\nVamos a listar usuarios con prestamos activos: " + "\n");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        //Corrección
        System.out.println("Usuarios que tiene al menos 1 prestamo ACTIVO:");
        for (Usuario u : usuarios) {//Voy usuario por usuario y lo vamos contando si tiene un préstamo
            int cont = 0;
            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest() == u) {//Demostramos que el usuario tiene prestamos
                    cont++;
                    break;
                }
            }
            if (cont > 0) {//Si el contador es mayor que 0, pues el usuario tiene al menos un préstamo, por lo tanto solo imprimimos estos usuaios
                System.out.println(u.getNombre());
            }
        }

        /*for (Usuario u : usuarios) {
            
            int cont1 = 0;
            try {//Le estamos diciendo a Java que si se produce una excepción nosotros somos capaces de gestionarlas
                for (int i = 0; i < prestamos.size(); i++) {
                    if (prestamos.get(i).getUsuarioPrest()== u) {
                        cont1++;
                        //break;
                    }

                }
            } catch (Exception e) {
            }
            if (cont1>0) {
                System.out.println(u.getNombre());
            }
        }
        
        REPETICIÓN
         */

 /*System.out.print("\n\nVamos a listar usuarios con prestamos activos: " + "\n");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        for (int i = 0; i < usuarios.size(); i++) {
            for (Prestamo p : prestamos) {
                if (i == -1) {
                    System.out.println("No hay prestamos de este usuario");
                }

            }

            System.out.println(usuarios.get(i));
        }
        for (int i = 0; i < usuarios.size(); i++) {
            for (Usuario u : usuarios) {
                //Debes meter un contador para que funcione
                int cont = 0;
                for (Prestamo p : prestamos) {
                    if (p.getUsuarioPrest().equals(u)) {
                        cont++;
                        //break;
                    }
                }
                if (cont > 0) {
                    System.out.println(u.getNombre());
                }

            }
        }
        HABÍA REPETICIÓN DE USUARIOS
         */
    }

    public static void cuatro() {
        /* Se podría resolver con un contador (cont) al igual que los ejercicios
            anteriores, pero en este caso utilizamos una variable booleana (true/false).
            Es otra forma de resolver la lógica de este tipo de ejercicios
         */
        System.out.print("\n\nVamos a listar usuarios con prestamos fuera de plazo: " + "\n");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        //Corrección
        for (Usuario u : usuarios) {//Reviso cada usuario
            boolean tieneFueraPlazo = false;//Es lo mismo que poner int cont=0;
            for (Prestamo p : prestamos) {//Reviso solo los prestamos, no los hist
                if (p.getUsuarioPrest().equals(u) && p.getFechaDev().isBefore(LocalDate.now())) {//Muestro los prestamos del usuario Y las fechas de devolución
                    tieneFueraPlazo = true;// o cont++;
                    break;
                }
            }
            if (tieneFueraPlazo == true) {
                System.out.println(u);
            }
        }
        /*for (Usuario u : usuarios) {
            for (Prestamo p : prestamos) {
                if (p.getFechaDev().isBefore(LocalDate.now())) {
                    //System.out.println(p);
                    break;
                }
            }

        }
        System.out.println(usuarios);*/
    }

    public static void cinco() {
        //Corrección
        System.out.println("PRESTAMOS realizados en el mes de NOVIEMBRE:\n");
        for (Prestamo p : prestamos) {
            if (p.getFechaPrest().getMonthValue() == 11) {
                System.out.println(p);
            }
        }
        for (Prestamo p : prestamosHist) {
            if (p.getFechaPrest().getMonthValue() == 11) {
                System.out.println(p);
            }
        }
        /*Vamos a buscar un prestamo por su fecha de prestamo
        System.out.println("Vamos a buscar un prestamo por su fecha");

        sc.nextLine();

        String fechaTexto = "";
        LocalDate fechaNov = LocalDate.parse(fechaTexto);

        int posF = buscaFecha(fechaNov);

        if (posF == -1) {
            System.out.println("Esta fecha no está asociada a ningún préstamo activo");
        } else {
            System.out.println("Préstamos activos con fecha " + fechaNov + ":");

            int prestAct = 0;
            for (Prestamo p : prestamos) {
                if (p.getFechaPrest().isEqual(fechaNov)) {
                    System.out.println(p);
                    prestAct++;
                }
                System.out.println(p);
            }

            System.out.println("Total de préstamos activos en esa fecha: " + prestAct);
        }*/
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CargaDatos">
    public static void cargaDatos() {

        //Este será el carga datos de los libros
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Novela", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Novela", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        LocalDate hoy = LocalDate.now(); //OBTENEMOS LA FECHA DE HOY CON EL MÉTODO now()

        //PRESTAMOS "NORMALES" REALIZADOS HOY Y QUE SE HAN DE DEVOLVER EN 15 DÍAS
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(4), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(1), hoy, hoy.plusDays(15)));
        //PRESTAMOS QUE YA TENIAN QUE HABER SIDO DEVUELTOS PORQUE SU FECHA DE DEVOLUCIÓN ES ANTERIOR A HOY
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(17), hoy.minusDays(2)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(18), hoy.minusDays(3)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));

        //PRESTAMOS HISTORICOS QUE YA HAN SIDO DEVUELTOS Y POR TANTO ESTÁN EN LA COLECCION prestamosHist
        prestamosHist.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(2), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(5), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(1), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(2), hoy.minusDays(15), hoy));
        prestamosHist.add(new Prestamo(libros.get(6), usuarios.get(3), hoy.minusDays(15), hoy));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Menú Opciones">
    public static void menuOpciones() {
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES");
            System.out.println("\t\t\t\t1 - GESTION DE LIBROS");
            System.out.println("\t\t\t\t2 - GESTION DE USUARIOS");
            System.out.println("\t\t\t\t3 - GESTION DE PRESTAMOS");
            System.out.println("\t\t\t\t4 - LISTAR COLECCIONES");
            System.out.println("\t\t\t\t5 - LISTAR COLECCIONES CON STREAMS");
            System.out.println("\t\t\t\t6 - ORDENAR COLECCIONES CON STREAMS");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    gestionLibros();
                    break;
                }
                case 2: {
                    gestionUsuarios();
                    break;
                }
                case 3: {
                    gestionPrestamos();
                    break;
                }
                case 4: {
                    listarColecciones();
                    break;
                }
                case 5: {
                    listadosConStreams();
                    break;
                }
                case 6: {
                    ordenarConStream();
                    break;
                }

            }

        } while (opcion != 9);

        /* Estamos creando un menú de opciones, dichas opciones son introducidas por teclado y están contenidas en los diferentes métodos para actualizar la información de la agenda
          - Se debe declarar una variable de tipo int para poder navegar en el menú
          - Se declaran "souts" para hacer visibles las posibles opciones
          - Se pide que introduzcan la opción a ejecutar por teclado
          1- Este método permite crear un nuevo contacto
          2- Permite listar todos los contactos, ya sea después de haber creado un contacto o solo para mostrarla
          3- Permite modificar/actualizar datos de un contacto, es decir, actualizar un atributo
          4- Permite borrar un contacto que ya no necesite
          9- Permite salir del bucle del menú*/
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gestión de Libros">
    public static void gestionLibros() {
        //<editor-fold defaultstate="collapsed" desc="Menú Libros">
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES");
            System.out.println("\t\t\t\t1 - NUEVO LIBRO");
            System.out.println("\t\t\t\t2 - LISTAR LIBROS");
            System.out.println("\t\t\t\t3 - MODIFICAR LIBRO");
            System.out.println("\t\t\t\t4 - ELIMINAR LIBRO");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    nuevoLibro();

                    break;
                }
                case 2: {
                    listarLibros();
                    break;
                }
                case 3: {
                    modificarLibro();
                    break;
                }
                case 4: {
                    eliminarLibro();
                    break;
                }

            }

        } while (opcion != 9);
        //</editor-fold>
    }

    public static void nuevoLibro() {
        System.out.println("\n\nVamos a introducir un nuevo LIBRO mediante el teclado");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        System.out.println("\n\nISBN del nuevo LIBRO: ");
        String isbn = sc.next();
        System.out.println("Título: ");
        String titulo = sc.next();
        System.out.println("Autor: ");
        String autor = sc.next();
        System.out.println("Genero: ");
        String genero = sc.next();
        System.out.println("Ejemplares: ");
        int ejemplares = sc.nextInt();
        libros.add(new Libro(isbn, titulo, autor, genero, ejemplares));//Solo se usa add para añadir un nuevo objeto
        System.out.println("Se ha añadido un nuevo Libro");
    }

    public static void listarLibros() {
        System.out.println("\nVamos a listar los LIBROS actuales");
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    public static int buscaLibro(String isbn) {//Enviamos el String nombre introducido por teclado y devuelve un entero
        //Primero debemos saber qué contacto hay que modificar (sus coordenadas)
        int pos = -1;// ponemos -1 por si no lo encontramos
        int i = 0;// Primera posición del ArrayList para empezar a buscar, va recorrer todas las posiciones hasta encontrar el nombre
        for (Libro l : libros) {//Nos estamos moviendo por todos los contactos hasta encontrar el String nombre que hemos teclado
            if (l.getIsbn().equalsIgnoreCase(isbn)) {//Busca el contacto ignorando la diferencia de mayúsculas y minúsculas
                pos = i;//Nos devuelve la posición dónde está el String nombre
                break;// Paramos cuando encontramos la posición de ese String nombre
            }
            i++;//Quiere decir que va seguir buscando entre todas las i hasta encontrar el nombre
        }
        return pos;//Nos regresa la posición del nombre
        /*for (int i = 0; i < contactoss.size(); i++) {
            if (contactoss.get(i).getNombre.equalsIgnoreCase(nombre)) {
               pos=i;//Nos devuelve la posición dónde está el String nombre
               break;// Paramos cuando encontramos la posición de ese String nombre
            }
        }
        return pos;
         */

        //SI BUSCAMOS EL LIBRO POR TITULO DEBEMOS USAR titulo = sc.nextLine(); PORQUE RECOGE LA FRASE ENTERA, next(); SOLO RECOGE UNA PALABRA
    }

    public static void modificarLibro() {
        System.out.println("\nTeclea el ISBN del LIBRO a MODIFICAR: ");

        //Declaramos los atributos que vamos a utulizar, nombre para buscarlo y los demás para ser modificados
        String isbn = sc.next();

        //Declaramos variables para llamar al método de buscaContacto asociado a los atributos para simplificar el código y poder modificarlos
        int n = buscaLibro(isbn);

        if (n == -1) {//Buscamos la posición del contacto del que queremos modificar un atributo, empezamos desde -1 por si el contacto no existe
            System.out.println("Este LIBRO no existe en la biblioteca");
        } else {

            System.out.println("\nVamos a MODIFICAR un LIBRO mediante el teclado");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
            System.out.print("\n\nEJEMPLARES nuevos del LIBRO: ");
            int ejemplares = sc.nextInt();
            libros.get(n).setEjemplares(ejemplares);
            System.out.println("Los EJEMPLARES del libro se han actualizado");

        }
    }

    public static void eliminarLibro() {
        System.out.print("\nTeclea el ISBN del LIBRO a borrar: ");
        String isbn = sc.next();
        int p = buscaLibro(isbn);
        if (p == -1) {
            System.out.println("Este LIBRO no existe en la BIBLIOTECA");
        } else {
            libros.remove(p);
            System.out.println("El LIBRO ha sido eliminado");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gestión de Usuarios">
    public static void gestionUsuarios() {
        //<editor-fold defaultstate="collapsed" desc="Menú Usuarios">

        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES");
            System.out.println("\t\t\t\t1 - NUEVO USUARIO");
            System.out.println("\t\t\t\t2 - LISTAR USUARIOS");
            System.out.println("\t\t\t\t3 - MODIFICAR USUARIO");
            System.out.println("\t\t\t\t4 - ELIMINAR USUARIO");
            System.out.println("\t\t\t\t12 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    nuevoUsuario();
                    break;
                }
                case 2: {
                    listarUsuarios();
                    break;
                }
                case 3: {
                    modificarUsuario();
                    break;
                }
                case 4: {
                    eliminarUsuario();
                    break;
                }

            }

        } while (opcion != 12);
        //</editor-fold>
    }

    public static void nuevoUsuario() {
        System.out.println("\n\nVamos a introducir un nuevo USUARIO mediante el teclado");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        System.out.println("\n\nDNI del nuevo USUARIO: ");
        String dni = sc.next();
        System.out.println("Nombre: ");
        String nombre = sc.next();
        System.out.println("email: ");
        String email = sc.next();
        System.out.println("Telefono: ");
        String telefono = sc.next();
        usuarios.add(new Usuario(dni, nombre, email, telefono));//Solo se usa add para añadir un nuevo objeto

        System.out.println("Se ha añadino un nuevo usuario");
    }

    public static void listarUsuarios() {
        System.out.println("\nVamos a listar los USUARIOS actuales");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    public static int buscaUsuario(String dni) {//Enviamos el String nombre introducido por teclado y devuelve un entero
        //ES MEJOR BUSCARLO CON UN BUCLE FOR

        //Primero debemos saber qué contacto hay que modificar (sus coordenadas)
        int pos = -1;// ponemos -1 por si no lo encontramos
        int i = 0;// Primera posición del ArrayList para empezar a buscar, va recorrer todas las posiciones hasta encontrar el nombre
        for (Usuario u : usuarios) {//Nos estamos moviendo por todos los contactos hasta encontrar el String nombre que hemos teclado
            if (u.getDni().equalsIgnoreCase(dni)) {//Busca el contacto ignorando la diferencia de mayúsculas y minúsculas
                pos = i;//Nos devuelve la posición dónde está el String nombre
                break;// Paramos cuando encontramos la posición de ese String nombre
            }
            i++;//Quiere decir que va seguir buscando entre todas las i hasta encontrar el nombre
        }
        return pos;//Nos regresa la posición del nombre
        /*for (int i = 0; i < contactoss.size(); i++) {
            if (contactoss.get(i).getNombre.equalsIgnoreCase(nombre)) {
               pos=i;//Nos devuelve la posición dónde está el String nombre
               break;// Paramos cuando encontramos la posición de ese String nombre
            }
        }
        return pos;
         */

        //SI BUSCAMOS EL LIBRO POR TITULO DEBEMOS USAR titulo = sc.nextLine(); PORQUE RECOGE LA FRASE ENTERA, next(); SOLO RECOGE UNA PALABRA
        // ES MEJOR HAVER CON UN BUCLE FOR PORQUE NOS CONTROLA MEJOR LA DIRECCIÓN DE MEMORIA EXACTA COMO HIZO EDU, BUSCAMOS UNA POSICIÓN i
    }

    public static void modificarUsuario() {
        System.out.println("\nTeclea el DNI del USUARIO a MODIFICAR: ");

        //Declaramos los atributos que vamos a utulizar, nombre para buscarlo y los demás para ser modificados
        String dni = sc.next();

        //Declaramos variables para llamar al método de buscaContacto asociado a los atributos para simplificar el código y poder modificarlos
        int n = buscaUsuario(dni);

        if (n == -1) {//Buscamos la posición del contacto del que queremos modificar un atributo, empezamos desde -1 por si el contacto no existe
            System.out.println("Este USUARIO no existe en la biblioteca");
        } else {

            System.out.println("\nVamos a MODIFICAR un USUARIO mediante el teclado");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
            System.out.print("\n\nNombre nuevo del Usuario: ");
            String nombre = sc.next();
            System.out.print("email nuevo: ");
            String email = sc.next();
            System.out.print("Teléfono nuevo: ");
            String telefono = sc.next();
            usuarios.get(n).setNombre(nombre);
            usuarios.get(n).setEmail(email);
            usuarios.get(n).setTelefono(telefono);
            System.out.println("El contacto ha sido actualizado");

        }
    }

    public static void eliminarUsuario() {
        System.out.print("\nTeclea el DNI del USUARIO a borrar: ");
        String dni = sc.next();
        int p = buscaUsuario(dni);
        if (p == -1) {
            System.out.println("Este USUARIO no existe en la BIBLIOTECA");
        } else {
            usuarios.remove(p);
            System.out.println("El USUARIO ha sido eliminado");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Gestión de Prestamos">
    public static void gestionPrestamos() {
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES");
            System.out.println("\t\t\t\t1 - NUEVO PRESTAMO");
            System.out.println("\t\t\t\t2 - LISTAR PRESTAMOS");
            System.out.println("\t\t\t\t3 - PRORROGA");
            System.out.println("\t\t\t\t4 - DEVOLUCION");
            System.out.println("\t\t\t\t9 - SALIR");
            System.out.println("\t\t\t\t¿Qué opción quieres ejecurtar?");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1: {
                    nuevoPrestamo();
                    break;
                }
                case 2: {
                    listarPrestamos();
                    break;
                }
                case 3: {
                    prorroga();
                    break;
                }
                case 4: {
                    devolucion();
                }

            }

        } while (opcion != 9);
    }

    public static void listarPrestamos() {

        //Debemos hacer un listado de libros que están fuera de plazo en la devolución
        System.out.println("Antes de ver los prestamos actuales veremos los que ya pasaron el plazo de entrega: ");
        //Is before, nos dice si una fecha va antes que otra
        System.out.println("\nPrestamos activos fuera de plazo: ");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isBefore(LocalDate.now())) {

                System.out.println(p);
            }
        }
        System.out.println("Multa de 1 mes sin prestamos");
        System.out.println("");

        System.out.println("Prestamos activos y en plazo");

        //Este es un poco más complicado, podemos hacerlo copiando y pegando el codigo de arriba y negamos la condición de if con !
        /*for (Prestamo p : prestamos) {
            if (p.getFechaDev().isAfter(LocalDate.now())||p.getFechaDev().equals(LocalDate.now())) {
                System.out.println("Prestamo dentro del plazo");
                System.out.println(p);
            }
        }*/
        //Aquí negaremos la condición
        for (Prestamo p : prestamos) {
            if (!p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p);
            }
        }

        System.out.println("\nVamos a listar el HISTORICO DE PRESTAMOS");
        for (Prestamo p : prestamosHist) {
            System.out.println(p);
        }

        System.out.println("\nVamos a listar todos los PRESTAMOS: ");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    public static void nuevoPrestamo() {

        /*  Este método se debe modificar con lo de edu llamando a la calse !MetodosAuxiliares.validarDNI(dni)
            El código actual es muy sencillo y puede dar lugar a errores    */
        System.out.println("Identificación del usuario:");
        String dni = sc.next();//Con el sc. no hay niguna validación y puede meter información que ni es correcta
        int posUsuario = buscaUsuario(dni);//Más adelante debemos poner DNI correcto que requiere una validación porque muchas cosas están sujetas a formatos, si no no avanzas
        if (posUsuario == -1) {
            System.out.println("No es usuario de la biblioteca");
        } else {
            System.out.println("Identificación del libro:");
            String isbn = sc.next();//Con el sc. no hay niguna validación y puede meter información que ni es correcta
            try {
                //Recogemos posición
                int pos = stockLibro(isbn);//El IDE nos está avisando los errores que pueden pasar, los throw, si le damos a las primeras sugerencias del IDE no es una solución, le pasamos el marrón a este método, lo rodeamos de try catch

                LocalDate hoy = LocalDate.now();
                prestamos.add(new Prestamo(libros.get(pos), usuarios.get(posUsuario), hoy, hoy.plusDays(15)));
                libros.get(pos).setEjemplares(libros.get(pos).getEjemplares() - 1);
            } catch (LibroNoExiste ex) {
                System.out.println(ex.getMessage());
            } catch (LibroNoDisponible ex) {
                System.out.println(ex.getMessage());
            }
            try {

            } catch (Exception e) {
            }
        }
        /*
        FORMA SENCILLA DE HACER UN PÉSTAMO, SOLO PIDE DATOS NECESARIOS DEL PRÉSTAMO Y DESDESCUENTA EL LIBRO
        
        String dni, isbn;
        int posUsuario, posLibro;
        System.out.println("Teclea el DNI del usuario: ");
        dni = sc.next();
        posUsuario = buscaUsuario(dni);
        if (posUsuario == -1) {
            System.out.println("Ese usuario no existe");
        } else {
            System.out.println("Tecela ISBN del libro: ");
            isbn = sc.next();
            posLibro = buscaLibro(isbn);
            System.out.println("Fecha del PRESTAMO: ");
            LocalDate hoy = LocalDate.now();
            System.out.println(hoy);
            LocalDate devolucion = hoy.plusDays(15);
            System.out.println("Fecha de DEVOLUCION: " + "\n" + devolucion);
            prestamos.add(new Prestamo(libros.get(posLibro), usuarios.get(posUsuario), hoy, devolucion));

            //DEBEMOS RESTAR LOS EJEMPLARES DE LOS LIBROS QUE TENEMOS DISPONIBLES DESPÚES DEL PRÉSTAMO
            libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares() - 1);//Usamos SET.EJEMPLARES para restar la cantidad de ejemplares disponibles del libro 
            System.out.println("Prestamo realizado y grabado en el ArrayList");

        }*/

 /*System.out.println("\n\nVamos a introducir un nuevo PRESTAMO mediante el teclado");//Las variables que vamos a declarar es mejor llamarlas igual que los atributos de la clase para evitar la confusión
        System.out.println("\nTeclea el ISBN del LIBRO de este PRESTAMO: ");

        //Declaramos los atributos que vamos a utulizar, nombre para buscarlo y los demás para ser modificados
        String isbn = sc.next();

        //Declaramos variables para llamar al método de buscaContacto asociado a los atributos para simplificar el código y poder modificarlos
        int posLibro = buscaLibro(isbn);

        if (posLibro == -1) {//Buscamos la posición del contacto del que queremos modificar un atributo, empezamos desde -1 por si el contacto no existe
            System.out.println("Este LIBRO no existe en la biblioteca");
        } else {
            //aqui van los usuarios
            System.out.println("\nTeclea el DNI del USUARIO de este PRESTAMO: ");
            String dni= sc.next();
            int posUsuario= buscaUsuario(dni);
            
            
            
          
            System.out.println("Fecha del PRESTAMO: ");
            LocalDate hoy = LocalDate.now();
            System.out.println(hoy);
            LocalDate devolucion = hoy.plusDays(15);
            System.out.println("Fecha de DEVOLUCION: " + "\n" +  devolucion);
            prestamos.add(new Prestamo(libros.get(posLibro), usuarios.get(posUsuario), hoy, devolucion));
            
        }*/
    }

    public static int buscaPrestamo(String dni, String isbn) {
        int pos = -1;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getUsuarioPrest().getDni().equals(dni) && prestamos.get(i).getLibroPrest().getIsbn().equals(isbn)) {
                pos = i;
                break;

                //Las 2 condiciones que se deben cumplikr para encontrar la dirección de memoria del préstamo
                /*prestamos.get(i).getUsuarioPrest().getDni().equalsIgnoreCase(dni);
                prestamos.get(i).getLibroPrest().getIsbn().equalsIgnoreCase(isbn)*/
            }
        }
        return pos;

    }

    public static void prorroga() {
        //Hay que tener en cuenta qué atributo se pide primero para evitar errores
        System.out.println("DNI  del usuario: ");
        String dni = sc.next();
        System.out.println("ISBN  del libro: ");
        String isbn = sc.next();
        int posPrestamo = buscaPrestamo(dni, isbn);
        if (posPrestamo == -1) {
            System.out.println("No hay ningún préstamo con estos datos");
        } else {
            prestamos.get(posPrestamo).setFechaDev(prestamos.get(posPrestamo).getFechaDev().plusDays(15));
            System.out.println("Se amplia el plazo a");
        }
    }

    public static void devolucion() {

        //Debemos declarar un nuevo arrayList de historial de prestamos
        /*
        -Lo más importante es saber la posición del prestamo para poder cambiar sus atributos según la prorroga y devoluciones*/
        System.out.println("DNI  del usuario: ");
        String dni = sc.next();
        System.out.println("ISBN  del libro: ");
        String isbn = sc.next();
        int posPrestamo = buscaPrestamo(dni, isbn);
        if (posPrestamo == -1) {
            System.out.println("No hay ningún préstamo con estos datos");
        } else {//Estas instrucciones no pueden cambiar de orden para evitar errores

            prestamos.get(posPrestamo).setFechaDev(LocalDate.now());//Aquí estamos cambiando la fecha de devolución
            libros.get(buscaLibro(isbn)).setEjemplares(libros.get(buscaLibro(isbn)).getEjemplares() + 1);//Sumamos de nuevo el ejemplrar al ArrayList de libros 
            prestamosHist.add(prestamos.get(posPrestamo));//Añadimos este prestamo al historico para posteriormente quitarlo de los prestamos pendientes qu aún no están devueltos
            prestamos.remove(posPrestamo);
            System.out.println("Se ha realizado la devolución, se elimina el prestamo para pasarlo al historial de prestamos");
        }
    }
    //</editor-fold>

    public static void listarColecciones() {
        System.out.println("Vamos a mostrar todos los libros de la biblioteca: ");
        for (Libro l : libros) {
            System.out.println(l);
            //System.out.print("\n" + l.getIsbn() + "/" + l.getTitulo() + "/" + l.getAutor() + "/" + l.getGenero());
        }
        System.out.println("");

        System.out.println("Vamos a mostrar los usuarios de la biblioteca: ");
        for (Usuario u : usuarios) {
            System.out.println(u);
            //System.out.print("\n" + u.getDni() + "/" + u.getNombre() + "/" + u.getEmail() + "/" + u.getTelefono());
        }
        System.out.println("");

        System.out.println("Vamos a mostrar los prestamos de la biblioteca: ");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Listados con Streams">
    /**
     * Listar con criterios de ordenación
     */
    public static void listadosConStreams() {
        System.out.println("Libros listados desde STREAMS: ");
        libros.stream()//Hacemos un duplicado de la colección de libros a Stream
                .forEach(l -> System.out.println(l));//Estamos haciendo el bucle ForEcah en una solo línea

        System.out.println("\nUsuarios listados desde un STREAM: ");
        usuarios.stream()
                .forEach(u -> System.out.println(u));

        System.out.println("\nPrestamos listados desde un STREAM: ");
        prestamos.stream()
                .forEach(p -> System.out.println(p));

        System.out.println("\nLibros de la seccion aventuras:");
        libros.stream().filter(l -> l.getGenero().equalsIgnoreCase("aventuras"))
                .forEach(l -> System.out.println(l));

        //Listados selectivos (filter) con STREAM, primero hay que hacer el filter antes que el for each para evitar que el programa recorra toda la colección de forma innecesaria
        System.out.println("\nListado de libros con filtros, novela negra o aventuras o del autor jrr tolkien: ");
        libros.stream()
                .filter(l -> l.getGenero().equalsIgnoreCase("novela negra")//Aplicamos el filtro para solo buscar aquellos que pertenecen al género que buscamos
                //&& l.getGenero().equalsIgnoreCase("aventuras")En este caso no hay ningún libro que sea de novela negra  "y" aventuras el && pide que se cumplan ambas condiciones, por lo tanto debemos poner un "o"
                || l.getGenero().equalsIgnoreCase("aventuras")
                || l.getAutor().equalsIgnoreCase("jrr tolkien"))//Podemos agregar más de un filtro poniendo && = nos muestra ambas condiciones, podemos cambiar la condición con un "o" = || este género o este autor
                //Para mezclar condiciones debemos encerrar estas condiciones en paréntesis aquellas que queremos que estén asociadas
                .forEach(l -> System.out.println(l));//Imprime aquellos libros que solo pertenecen al filtro aplicado anteriormente

        System.out.println("\nPrestamos fuera de plazo: ");
        prestamos.stream().filter(p -> p.getFechaDev().isBefore(LocalDate.now()))
                .forEach(p -> System.out.println(p));

        System.out.println("\nPrestamos activos y no activos del usuario (teclear NOMBRE): ");
        //Hay que hacer 2 STREAM, uno para los prestamos y otro para prestamosHist, el nombre introducido por teclado debe estar antes para que lo asocie directamente a estos 2 streams

        String nombre = sc.next();
        System.out.println("\nPrestamos activos del usuario: ");
        prestamos.stream().filter(p -> p.getUsuarioPrest().getNombre().equalsIgnoreCase(nombre))
                .forEach(p -> System.out.println(p));

        System.out.println("\nPrestamos no activos del usuario: ");
        prestamosHist.stream().filter(p -> p.getUsuarioPrest().getNombre().equalsIgnoreCase(nombre))
                .forEach(p -> System.out.println(p));

        System.out.println("\nPrestamos fuera de plazo del usuario: ");
        prestamos.stream().filter(p -> p.getUsuarioPrest().getNombre().equalsIgnoreCase(nombre)
                && p.getFechaDev().isBefore(LocalDate.now()))//Le agregamos una condición más al primer ejemplo del usuario
                .forEach(p -> System.out.println(p));

        System.out.println("\nPrestamos activos de libros del genero aventuras: ");
        prestamos.stream().filter(p -> p.getLibroPrest().getGenero().equalsIgnoreCase("aventuras"))
                .forEach(p -> System.out.println(p));

        /*Forma tradicional
        for (Libro l : libros) {
            if (l.getGenero().equalsIgnoreCase("aventuras")) {
                
            }
        }*/
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Ordenaciones con STREAMS">
    public static void ordenarConStream() {
        System.out.println("Listado de libros ordenados alfabeticamente por título: ");
        //Sorted sirve para determinar el criterio de ordenación que queremos, solo se puede elegir un criterio de ordenación, hay que modificarlo en la clase para que libros pueda ser ordenada con implements y override si no hay nada en el paréntesis
        libros.stream().sorted(Comparator.comparing(Libro::getTitulo)/*.reversed() Lo ordena alreves*/) /*Con esta sintaxis no necesitamos modificar la clase, definimos el atributo para ordenarlo dentro del sorted, también podemos poner el resultado de un método*/
                .forEach(l -> System.out.println(l));

        System.out.println("\nListado de Prestamos ordenados por Fecha de prestramo: ");
        prestamos.stream().sorted(Comparator.comparing(Prestamo::getFechaPrest))
                .forEach(p -> System.out.println(p));

        //Ordenar los libros de mayor a menos según los prestamos, debemos hacer un metodo que cuente los prestamos de ese libro como criterio de ordenación
        System.out.println("\nListado de libros ordenados por Número d Préstamos: ");
        libros.stream().sorted(Comparator.comparing(l -> numPrestamosLibro(l.getIsbn())))//El reversed no funciona cuando usamos un método como criterio de ordenación, debemos hacer un casting y modificar lo que recibe el métod que usamos ocmo criterio
                .forEach(l -> System.out.println(l + " Unidades prestadas: " + numPrestamosLibro(l.getIsbn())));
    }

    public static int numPrestamosLibro(String isbn) { //Edu lo modifica a una forma más simple para poder usar el reversed en las ordenaciones
        int cont = 0;
        for (Prestamo p : prestamos) {
            if (p.getLibroPrest().getIsbn().equalsIgnoreCase(isbn)) {
                cont++;
            }
        }
        for (Prestamo p : prestamosHist) {
            if (p.getLibroPrest().getIsbn().equalsIgnoreCase(isbn)) {
                cont++;
            }
        }
        return cont;

    }

    //</editor-fold>
    public static int buscaFecha(LocalDate fecha) {
        int pos = -1;
        int i = 0;
        for (Prestamo p : prestamos) {
            if (p.getFechaPrest().isEqual(fecha)) {   // o p.getFechaPrest().equals(fecha)
                pos = i;
                break;
            }
            i++;
        }
        return pos;

    }

    //<editor-fold defaultstate="collapsed" desc="Stock Libro">
    /**
     * Hace tres cosas, decirnos la posición del libro o nos avisa de los throws
     * que pueden aparecer si hay anomalías
     *
     * @param isbn
     * @return
     * @throws LibroNoExiste
     * @throws LibroNoDisponible
     */
    public static int stockLibro(String isbn) throws LibroNoExiste, LibroNoDisponible {
        int pos = buscaLibro(isbn);
        if (pos == -1) {
            throw new LibroNoExiste("No existe en esta Biblioteca la referencia: " + isbn);
        } else if (libros.get(pos).getEjemplares() == 0) {
            //FORMA COMPLICADA PERO CON INFO MÁS COMPLETA
            String cadena = "No hay unidades disponibles del libro: " + libros.get(pos).getTitulo()
                    + "\n" + "disponibles actualmente"
                    + "\nFechas de devoluciones previstas : ";
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(isbn)) {
                    cadena = cadena + "\n + " + p.getFechaDev();

                }
            }
            throw new LibroNoDisponible(cadena);

            //FORMA SENCILLA ->  throw new LibroNoDisponible("El libro no se encuentra disponible, todos sus ejemplares están en préstamo");
        }
        return pos;

    }
//</editor-fold>

}
