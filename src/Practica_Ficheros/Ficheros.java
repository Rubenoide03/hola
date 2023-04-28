package Practica_Ficheros;//Porgrama que tenga todos los siguiente metodos: mostrar el tamaño de todos los directorios que se encuentran debajo de uno dado, pedir 2 directorios y copiar del segundo al primero todos los ficheros que no estén en el 1º y sí en el 2º o sean más recientes en el 2º que en el 1º. Pedir 2 directorios y mostrar cuáles son los ficheros/directorios diferentes (en fecha o que están en uno y no en otro), GEstionar papelera, etc.

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Ficheros {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Mostrar el tamaño de todos los directorios que se encuentran debajo de uno dado");
            System.out.println("2. Pedir 2 directorios y copiar del segundo al primero todos los ficheros que no estén en el 1º y sí en el 2º o sean más recientes en el 2º que en el 1º");
            System.out.println("3. Pedir 2 directorios y mostrar cuáles son los ficheros/directorios diferentes (en fecha o que están en uno y no en otro)");
            System.out.println("4. Gestionar papelera");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el directorio: ");
                    String ruta = sc.next();
                    File f = new File(ruta);
                    if (f.exists()) {
                        System.out.println("El tamaño del directorio es: " + f.length());

                    } else {
                        System.out.println("El directorio no existe");
                    }
                    break;
                case 2:
                    System.out.println("Introduce el directorio 1: ");
                    String ruta1 = sc.next();
                    System.out.println("Introduce el directorio 2: ");
                    String ruta2 = sc.next();
                    File f1 = new File(ruta1);
                    File f2 = new File(ruta2);
                    if (f1.exists() && f2.exists()) {
                        File[] ficheros = f2.listFiles();
                        for (int i = 0; i < ficheros.length; i++) {
                            if (ficheros[i].lastModified() > f1.lastModified()) {
                                System.out.println("El fichero " + ficheros[i].getName() + " es más reciente");
                            }
                        }
                    } else {
                        System.out.println("Alguno de los directorios no existe");
                    }
                    break;
                case 3:
                    System.out.println("Introduce el directorio 1: ");
                    String ruta3 = sc.next();
                    System.out.println("Introduce el directorio 2 : ");
                    String ruta4 = sc.next();
                    File f3 = new File(ruta3);
                    File f4 = new File(ruta4);
                    if (f3.exists() && f4.exists()) {
                        File[] ficheros1 = f3.listFiles();
                        File[] ficheros2 = f4.listFiles();
                        for (int i = 0; i < ficheros1.length; i++) {
                            File fichero2 = new File(f4.getAbsolutePath() + "\\" + ficheros1[i].getName());
                            if (fichero2.exists()) {
                                if (ficheros1[i].lastModified() != fichero2.lastModified()) {
                                    System.out.println("El fichero " + ficheros1[i].getName() + " es diferente");
                                }
                            } else {
                                System.out.println("El fichero " + ficheros1[i].getName() + " no existe en el directorio 2");
                            }
                        }
                        for (int i = 0; i < ficheros2.length; i++) {
                            File fichero1 = new File(f3.getAbsolutePath() + "\\" + ficheros2[i].getName());
                            if (!fichero1.exists()) {
                                System.out.println("El fichero " + ficheros2[i].getName() + " no existe en el directorio 1");
                            }
                        }
                    } else {
                        System.out.println("Alguno de los directorios no existe");
                    }
                    break;

                case 4:
                    System.out.println("Introduce el directorio: ");
                    String ruta5 = sc.next();
                    File f5 = new File(ruta5);
                    if (f5.exists()) {
                        File[] ficheros = f5.listFiles();
                        File papelera = new File("Papelera");
                        if (!papelera.exists()) {
                            papelera.mkdir();
                        }
                        for (int i = 0; i < ficheros.length; i++) {
                            if (ficheros[i].getName().endsWith(".txt") && ficheros[i].isFile()) {
                                System.out.println("El fichero " + ficheros[i].getName() + " es un fichero de texto");
                                System.out.println("¿Desea enviar este archivo a la papelera? (s/n)");
                                String respuesta = sc.next();
                                if (respuesta.equalsIgnoreCase("s")) {
                                    Path origenPath = ficheros[i].toPath();
                                    Path destinoPath = papelera.toPath().resolve(ficheros[i].getName());
                                    try {
                                        Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                                        System.out.println("Archivo " + ficheros[i].getName() + " enviado a la papelera");
                                    } catch (IOException e) {
                                        System.out.println("Error al enviar archivo a la papelera");
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("El directorio no existe");
                    }
                    break;


                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (opcion != 5);
    }
}



