package com.techlab.inicio;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void reducirStock(int cantidad) {
        this.stock -= cantidad;
    }

    public boolean hayStockDisponible(int cantidad) {
        return stock >= cantidad;
    }

    public String toString() {
        return "ID: " + id + " | " + nombre + " - $" + precio + " | Stock: " + stock;
    }
}
