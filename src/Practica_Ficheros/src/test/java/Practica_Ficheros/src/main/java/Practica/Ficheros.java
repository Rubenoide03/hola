package Practica;

import java.util.Scanner;


public class Ficheros {



    public static void main(String[] args) {
        Ficheros_Metodos f = new Ficheros_Metodos();
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
                    f.mostrarTamanoDirectorio();
                    break;
                case 2:
                    f.copiarFicheros();
                    break;
                case 3:
                    f.mostrarFicherosDiferentes();
                    break;
                case 4:
                    f.gestionarPapelera();
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



