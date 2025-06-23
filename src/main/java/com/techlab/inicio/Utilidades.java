package com.techlab.inicio;

import java.util.Scanner;

public class Utilidades {
    public static int leerEntero(Scanner sc, String mensaje) {
        System.out.println(mensaje);
        while (!sc.hasNextInt()) {
            System.out.println("Debe ingresar un numero entero. Intente de nuevo");
            sc.nextLine();
        }
        return sc.nextInt();
    }
}
