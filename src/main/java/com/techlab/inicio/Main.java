package com.techlab.inicio;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = null;
    public static void main(String[] args) {
        /*
        int[] array = new int[3];


        try {
            System.out.println(array[5]);
        } catch (Exception e) {
            e.printStackTrace(); // imprime el error
            //System.out.println("Indice incorrecto");
        }

        System.out.println("fin del programa");*/


        try {
            pedirDato();
        } catch (InputMismatchException e) {
            System.out.println("X Error: tenes que poner un numero");
            e.getMessage();
        } finally {
            if (sc != null) {
                sc.close();
                System.out.println("Scanner cerrado correctamente");
            }
        }

    }

    public static void pedirDato() {
        sc = new Scanner(System.in);
        System.out.println("Ingrese su edad (solo numeros)");
        int edad = sc.nextInt();

        if (edad < 1) {
            throw new RuntimeException("Tenes que poner numeros positivos");
        } else {
            System.out.println("Correcto");
        }
    }
}