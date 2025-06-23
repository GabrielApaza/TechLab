package com.techlab.inicio;
import java.util.ArrayList;

public class Pedido {
    private static int contadorPedidos = 0;
    private int id;
    private Cliente cliente;
    private ArrayList<Producto> productos;

    public Pedido(Cliente cliente) {
        this.id = contadorPedidos++;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }
    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }
    public String toString() {
        return "Pedido #" + id + " - Cliente: " + cliente + " - Total: $" + calcularTotal();
    }
}
