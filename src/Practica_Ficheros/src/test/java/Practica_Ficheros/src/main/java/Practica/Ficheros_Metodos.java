package Practica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;



public class Ficheros_Metodos {

    Scanner sc = new Scanner(System.in);



    public void mostrarTamanoDirectorio() {
        System.out.println("Introduce el directorio: ");
        String ruta = sc.nextLine();
        File f = new File(ruta);
        if (f.exists()) {
            File[] ficheros = f.listFiles();

            if (ficheros != null) {
                for (File fichero : ficheros) {
                    if (fichero.isDirectory()) {
                        double length = calcularTamanoDirectorio(fichero);
                        System.out.println("El directorio " + fichero.getName() + " tiene un tama�o de " + length + " bytes");
                    }
                }
            }
        } else {
            System.out.println("El directorio no existe");
        }
    }

    /**
     * Calcula el tama�o de un directorio dado.
     *
     * @param file El directorio a analizar.
     * @return El tama�o del directorio en bytes.
     */
     double calcularTamanoDirectorio(File file) {
        double suma = 0;
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    suma += calcularTamanoDirectorio(f);
                } else {
                    suma += f.length();
                }
            }
        }
        return suma;
    }

    /**
     * Copia los ficheros de un directorio a otro.
     */
    public void copiarFicheros() {
        System.out.println("Introduce el directorio 1: ");
        String ruta1 = sc.nextLine();
        System.out.println("Introduce el directorio 2: ");
        String ruta2 = sc.nextLine();
        File f1 = new File(ruta1);
        File f2 = new File(ruta2);
        if (f1.exists() && f2.exists()) {
            File[] ficheros = f2.listFiles();

            if (ficheros != null) {
                for (File fichero : ficheros) {
                    String nombreFichero = fichero.getName();
                    File nuevoFichero = new File(f1, nombreFichero);
                    if (!nuevoFichero.exists() && fichero.isFile()) {
                        try {
                            Files.copy(fichero.toPath(), nuevoFichero.toPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("Alguno de los directorios no existe");
        }
    }

    /**
     * En este metodo lo que hacemos es comprobar los archivos de los directorios
     * y creamos una array que guarde esos archivos. Si f3 no existiera diria que el archivo no existe
     * sino compararia el archivo con el mismo nombre y lo cambiaria por el mas reciente
     * Y si ninguno de los dos f3 y f4 existiera nos haria un sout de que alguno de los dos no existe.
     */


    public void mostrarFicherosDiferentes() {


        System.out.println("Introduce el directorio 1: ");
        String ruta3 = sc.next();
        System.out.println("Introduce el directorio 2 : ");
        String ruta4 = sc.next();
        File f3 = new File(ruta3);
        File f4 = new File(ruta4);
        if (f3.exists() && f4.exists()) {
            File[] ficheros1 = f3.listFiles();
            File[] ficheros2 = f4.listFiles();
            if (ficheros1 != null) {
                for (File file : ficheros1) {
                    File fichero2 = new File(f4.getAbsolutePath() + "\\" + file.getName());
                    if (fichero2.exists()) {
                        if (file.lastModified() != fichero2.lastModified()) {
                            System.out.println("El fichero " + file.getName() + " es diferente");
                        }
                    } else {
                        System.out.println("El fichero " + file.getName() + " no existe en el directorio 2");
                    }
                }
            }
            if (ficheros2 != null) {
                for (File file : ficheros2) {
                    File fichero1 = new File(f3.getAbsolutePath() + "\\" + file.getName());
                    if (!fichero1.exists()) {
                        System.out.println("El fichero " + file.getName() + " no existe en el directorio 1");
                    }
                }
            }
        } else {
            System.out.println("Alguno de los directorios no existe");
        }

    }

    /**
     * En este metodo vamos a poder "borrar archivos" moviendolos a una papelera. De las primeras cosas que hace aparte de pedir los directorios
     * es comprobar si ya hay una papelera creada en caso de que no sea asi la crea a traves del mkdir.
     * Luego en caso de que haya ficheros te preguntara si lo quieres mandar a la papelera. Si responde s
     * lo movera a la papelera y en caso de que ya estuviera lo sobreescribiria con el parametro StandardCopyOption.REPLACE_EXISTING
     */

    void gestionarPapelera() {
        System.out.println("Introduce el directorio: ");
        String ruta5 = sc.next();
        File f5 = new File(ruta5);
        if (f5.exists()) {
            File[] ficheros = f5.listFiles();
            File papelera = new File("Papelera");
            if (!papelera.exists()) {
                papelera.mkdir();
            }
            if (ficheros != null) {
                for (File fichero : ficheros) {
                    System.out.println("¿Desea enviar este archivo a la papelera? (s/n)");
                    String respuesta = sc.next();
                    if (respuesta.equals("s")) {
                        Path origenPath = fichero.toPath();
                        Path destinoPath = papelera.toPath().resolve(fichero.getName());
                        try {
                            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("El archivo se ha enviado a la papelera");
                        } catch (IOException e) {
                            System.out.println("No se ha podido enviar el archivo a la papelera");
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            System.out.println("El directorio no existe");
        }
    }

}




