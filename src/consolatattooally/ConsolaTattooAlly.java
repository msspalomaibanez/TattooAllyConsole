/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consolatattooally;

import cadtattooally.*;
import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author usuario
 */
public class ConsolaTattooAlly {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExceptionTattooAlly {

        try {

            int opcion = -1;
            int opcion2 = -1;

            Scanner in = new Scanner(System.in);
            PropertyConfigurator.configure("logs\\log4j.properties");
            Logger loggerN = LogManager.getLogger("NAVEGACION");
            loggerN.trace("Entra al Menú Pricipal");
            // bucle do while que recoja el menú principal bajo la condición de que la opcion introducida por el usuario se encuentre entre las mostradas
            do {
                mostrarMenuPrincipal();
                opcion = in.nextInt();
                if (opcion < 0 || opcion > 2) {
                    System.out.println("Opción incorrecta, pulse un número de los mostrados en el menú");
                    continue;
                }
                switch (opcion) {
                    case 1: // se pulsa la opcion de gestion de usuario
                        do {
                            mostrarMenuUsuario();
                            in = new Scanner(System.in, "ISO-8859-1");
                            opcion2 = in.nextInt();
                            if (opcion2 < 0 || opcion2 > 5) {
                                System.out.println("Opción incorrecta");
                                continue;
                            }
                            gestionUsuario(opcion2);

                        } while (opcion2 != 0);
                        break;
                    case 2: // se pulsa la opcion de gestion de publicacion
                        do {
                            mostrarMenuPublicacion();
                            in = new Scanner(System.in, "ISO-8859-1");
                            opcion2 = in.nextInt();
                            if (opcion2 < 0 || opcion2 > 5) {
                                System.out.println("Opción incorrecta");
                                continue;
                            }
                            gestionPublicacion(opcion2);
                        } while (opcion2 != 0);
                        break;
                    case 0: // se pulsa la opcion de salir
                        System.out.println("Ha pulsado salir");
                        break;
                    default: // opcion incorrecta
                        System.out.println("Opción incorrecta, inténtelo de nuevo");
                        mostrarMenuPrincipal();
                }
            } while (opcion < 0 || opcion > 2);
            System.out.println("FIN");
            
            PropertyConfigurator.configure("logs\\log4j.properties");
            loggerN.trace("Sale del programa");

        } catch (ExceptionTattooAlly ex) {
            System.out.println(ex.getMensajeErrorUsuario());
            PropertyConfigurator.configure("logs\\log4j.properties");
            Logger logger = LogManager.getLogger("ERRORES");
            logger.error(ex);

        } catch (java.util.InputMismatchException ex) { // se captura la excepcion si el usuario introduce cualquier caracter que no coincida con numeros en el menú
            System.out.println("El valor introducido no es válido: " + ex.getMessage());
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("- - - - MENÚ PRINCIPAL - - - -");
        System.out.println("Pulse 1 para Menú de Usuario");
        System.out.println("Pulse 2 para Menú de Publicación");
        System.out.println("Pulse 0 para Salir");
    }

    private static void mostrarMenuUsuario() {
        System.out.println("- - - - MENÚ USUARIO - - - -");
        System.out.println("Pulse 1 para crear un Nuevo Usuario");
        System.out.println("Pulse 2 para Eliminar un Usuario");
        System.out.println("Pulse 3 para Actualizar un Usuario");
        System.out.println("Pulse 4 para Ver un Usuario");
        System.out.println("Pulse 5 para Ver Todos los Usuarios");
        System.out.println("Pulse 0 para Volver");
    }

    private static void mostrarMenuPublicacion() {
        System.out.println("- - - - MENÚ PUBLICACIÓN - - - -");
        System.out.println("Pulse 1 para crear una Nueva Publicación");
        System.out.println("Pulse 2 para Eliminar una Publicación");
        System.out.println("Pulse 3 para Actualizar una Publicación");
        System.out.println("Pulse 4 para Ver una Publicación");
        System.out.println("Pulse 5 para Ver Todas las Publicaciones");
        System.out.println("Pulse 0 para Volver");
    }

    private static void gestionUsuario(int opcion) throws ExceptionTattooAlly {
        Scanner in = new Scanner(System.in);
        Usuario u = new Usuario();
        CADTattooAlly cad = new CADTattooAlly();
        PropertyConfigurator.configure("logs\\log4j.properties");
        Logger loggerN = LogManager.getLogger("NAVEGACION");
        loggerN.trace("Entra al Menú de Gestión de Usuarios");
        do {
            switch (opcion) {
                case 1:
                    System.out.println("Insertar Usuario");
                    System.out.println("A continuación, inserte el nombre del usuario: ");
                    String nombre = in.nextLine();
                    u.setUsuarioNombre(nombre);

                    System.out.println("Inserte el correo electrónico: ");
                    String email = in.nextLine();
                    u.setUsuarioEmail(email);

                    System.out.println("Inserte el teléfono: ");
                    String tlfn = in.nextLine();
                    u.setUsuarioTlfn(tlfn);

                    System.out.println("Inserte el alias del usuario: ");
                    String alias = in.nextLine();
                    u.setUsuarioAlias(alias);

                    cad.insertarUsuario(u);
                    System.out.println("El usuario " + u.getUsuarioAlias() + " se ha insertado con éxito");
                    break;
                case 2:
                    System.out.println("Eliminar Usuario");
                    System.out.println("A continuación inserte el id del usuario que desee eliminar: ");
                    int id = in.nextInt();

                    cad.eliminarUsuario(id);
                    System.out.println("El usuario con id " + id + " se ha eliminado con éxito");
                    break;
                case 3:
                    System.out.println("Actualizar Usuario");
                    System.out.println("A continuación, inserte el id del usuario que desea actualizar: ");
                    id = in.nextInt();

                    System.out.println("Inserte el nombre del usuario: ");
                    nombre = in.nextLine();

                    do {
                        System.out.println("El nombre no puede quedar vacío, vuélvalo a insertar");
                        nombre = in.nextLine();
                    } while (nombre.isEmpty());

                    u.setUsuarioNombre(nombre);

                    System.out.println("Inserte el correo electrónico: ");
                    email = in.nextLine();
                    u.setUsuarioEmail(email);

                    System.out.println("Inserte el teléfono: ");
                    tlfn = in.nextLine();
                    u.setUsuarioTlfn(tlfn);

                    System.out.println("Inserte el alias del usuario: ");
                    alias = in.nextLine();
                    u.setUsuarioAlias(alias);

                    cad.actualizarUsuario(id, u);
                    System.out.println("El usuario con el id " + id + " se ha actualizado con éxito");
                    break;
                case 4:
                    System.out.println("Ver un Usuario");
                    System.out.println("A continuación, inserte el id del usuario que desea ver: ");
                    id = in.nextInt();

                    System.out.println("Lectura del usuario con el id " + id + ": ");
                    System.out.println(cad.leerUsuario(id));
                    break;
                case 5:
                    System.out.println("Ver Todos Usuarios");
                    System.out.println("A continuación se muestran todos los usuarios: ");

                    System.out.println(cad.leerUsuarios());
                    break;
                case 0:
                    System.out.println("Volver");
                    mostrarMenuPrincipal();
                    opcion = in.nextInt();
                    PropertyConfigurator.configure("logs\\log4j.properties");
                    loggerN.trace("Sale del Menú de Gestión de Usuario");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    mostrarMenuPrincipal();
                    opcion = in.nextInt();
            }
        } while (opcion < 0 || opcion > 5);
    }

    private static void gestionPublicacion(int opcion) throws ExceptionTattooAlly {
        Scanner in = new Scanner(System.in);
        Publicacion p = new Publicacion();
        CADTattooAlly cad = new CADTattooAlly();
        PropertyConfigurator.configure("logs\\log4j.properties");
        Logger loggerN = LogManager.getLogger("NAVEGACION");
        loggerN.trace("Entra al Menú de Gestión de Publicaciones");
        do {
            switch (opcion) {
                case 1:
                    System.out.println("Insertar Publicación");

                    System.out.println("A continuación,inserte la interacción con la publicación: ");
                    int interaccion = in.nextInt();
                    p.setPubliInteraccion(interaccion);

                    System.out.println("Inserte la descripción: ");
                    String descripcion = in.nextLine();
                    do {
                        System.out.println("La descripción no puede quedar vacía, vuélvalo a insertar");
                        descripcion = in.nextLine();
                    } while (descripcion.isEmpty());

                    p.setPubliDescripcion(descripcion);

                    System.out.println("Inserte la imagen para la publicación: ");
                    String imagen = in.nextLine();
                    p.setPubliImagen(imagen);

                    System.out.println("Inserte un comentario: ");
                    String comentario = in.nextLine();
                    p.setPubliComentario(comentario);

                    System.out.println("Inserte el id del usuario al que pertenece: ");
                    int id = in.nextInt();
                    p.setUsuario(new Usuario(id));

                    cad.insertarPublicacion(p);
                    System.out.println("La publicación ha sido insertada con éxito");
                    break;
                case 2:
                    System.out.println("Eliminar Publicación");
                    System.out.println("A continuación inserte el id de la publicación que desee eliminar: ");
                    id = in.nextInt();

                    cad.eliminarPublicacion(id);
                    System.out.println("La publicación con id " + id + " ha sido eliminada con éxito");
                    break;
                case 3:
                    System.out.println("Actualizar Publicación");
                    System.out.println("A continuación, inserte el id de la publicación que desea actualizar: ");
                    id = in.nextInt();

                    System.out.println("Inserte la interacción con la publicación: ");
                    interaccion = in.nextInt();
                    p.setPubliInteraccion(interaccion);

                    System.out.println("Inserte la descripción: ");
                    descripcion = in.nextLine();
                    do {
                        System.out.println("La descripción no puede quedar vacía, vuélvalo a insertar");
                        descripcion = in.nextLine();
                    } while (descripcion.isEmpty());

                    p.setPubliDescripcion(descripcion);

                    System.out.println("Inserte la ruta de la publicación: ");
                    imagen = in.nextLine();
                    p.setPubliImagen(imagen);

                    System.out.println("Inserte un comentario: ");
                    comentario = in.nextLine();
                    p.setPubliComentario(comentario);

                    cad.actualizarPublicacion(id, p);
                    System.out.println("La publicación con id " + id + " se ha actualizado con éxito");
                    break;
                case 4:
                    System.out.println("Ver una Publicación");
                    System.out.println("A continuación, inserte el id de la publicación que desea ver: ");
                    id = in.nextInt();

                    System.out.println("Lectura de la publicación con id " + id + ": ");
                    System.out.println(cad.leerPublicacion(id));
                    break;
                case 5:
                    System.out.println("Ver Todas Publicaciones");
                    System.out.println("A continuación se muestran todas las publicaciones: ");

                    System.out.println(cad.leerPublicaciones());
                    break;
                case 0:
                    System.out.println("Volver");
                    mostrarMenuPrincipal();
                    opcion = in.nextInt();
                    PropertyConfigurator.configure("logs\\log4j.properties");
                    loggerN.trace("Sale del Menú de Gestión de Publicaciones");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    mostrarMenuPrincipal();
                    opcion = in.nextInt();
            }
        } while (opcion < 0 || opcion > 5);
    }
    
        /**
     * Función que comprueba que la cadena de caracteres que se le pasa como
     * argumento contenga sólo números y sea válida para realizar una operación
     *
     * @param cadena String
     * @return true si la cadena es válida y false si no lo es
     */
    public static boolean validar(String cadena) {
        boolean ret = true;
        if (!cadena.matches("[0-9]*")) {
            ret = false;
        }
        return ret;
    }
}
