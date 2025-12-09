/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package es.educastur.givanbr90.biblioteca2025;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void main(String[] args) {

        cargaDatos();//Llama al método que carga 10 libros en la colección y añadir uno nuevo

        //FCOM+tab crea el editor para crear tags que permiten plegar y desplegar metodos
        //<editor-fold defaultstate="collapsed" desc="Menus">
        menuOpciones();
        //</editor-fold>
    }

    public static void cargaDatos() {

        //Este será el carga datos de los libros
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Aventuras", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Aventuras", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        //Carga Datos de Usuario
        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        //Carga Datos de Préstamo
        LocalDate hoy = LocalDate.now();
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(4), hoy, hoy.plusDays(2)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(1), hoy, hoy.plusDays(5)));
    }

    public static void menuOpciones() {
        //<editor-fold defaultstate="collapsed" desc="Menú Opciones">
        int opcion;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tMENU DE OPCIONES");
            System.out.println("\t\t\t\t1 - GESTION DE LIBROS");
            System.out.println("\t\t\t\t2 - GESTION DE USUARIOS");
            System.out.println("\t\t\t\t3 - GESTION DE PRESTAMOS");
            System.out.println("\t\t\t\t4 - LISTAR COLECCIONES");
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
        //</editor-fold>
    }

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
        System.out.println("\nVamos a listar los PRESTAMOS: ");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    public static void nuevoPrestamo() {
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
            libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares() - 1);

        }

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
            prestamos.get(posPrestamo).setFechaDev(LocalDate.now());
            libros.get(buscaLibro(isbn)).setEjemplares(libros.get(buscaLibro(isbn)).getEjemplares()+1);
            prestamosHist.add(prestamos.get(posPrestamo));
            prestamos.remove(posPrestamo);
            System.out.println("Se ha realizado la devolución, se elimina el prestamo para pasarlo al historial de prestamos");
        }
    }

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

}
