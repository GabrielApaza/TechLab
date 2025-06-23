package com.techlab.inicio;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static boolean superUsuario = false;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Pedido> pedidos = new ArrayList<>();


    public static void main(String[] args) {
        inicializarProductos();
        menu();

    }
    private static void inicializarProductos() {
        productos.add(new Producto(1, "Cafe", 2000, 10));
        productos.add(new Producto(2, "Tostadas", 600, 15));
        productos.add(new Producto(3, "Jugo", 400, 20));
    }

    private static void menu() {
        int opcion;
        do {
            System.out.println();
            System.out.println("=".repeat(35));
            System.out.println("--- MENÚ PRINCIPAL - TECHLAB ---");
            System.out.println("=".repeat(35));
            System.out.println("1. Ver productos");
            System.out.println("2. Crear pedido");
            System.out.println("3. Ver pedidos");
            System.out.println("4. Super Usuario");
            if (superUsuario) {
                System.out.println("5. Agregar nuevo producto ");
            }
            if (superUsuario) {
                System.out.println("6. Cerrar sesión de super usuario");
            }
            System.out.println("0. Salir");
            opcion = Utilidades.leerEntero(sc, "Ingrese opción: ");
            switch (opcion) {
                case 1 -> mostrarProductos();
                case 2 -> crearPedido();
                case 3 -> mostrarPedidos();
                case 4 -> superUsuario();
                case 5 -> {if (superUsuario) {agregarProducto();}}
                case 6 -> {superUsuario = false;}
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción invalida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarProductos() {
        mostrarEncabezado("MENÚ PRINCIPAL - TECHLAB");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    private static void crearPedido() {
        sc.nextLine();
        System.out.println("Nombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.println("Email del cliente: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nombre, email);
        Pedido pedido = new Pedido(cliente);

        boolean seguir = true;
        while (seguir) {
            mostrarProductos();
            int id = Utilidades.leerEntero(sc, "Ingrese el ID del producto a agregar (0 para terminar): ");
            if (id == 0) break;

            Producto seleccionado = buscarProductoPorId(id);
            if (seleccionado != null) {
                int cantidad = Utilidades.leerEntero(sc, "Ingrese la cantidad deseada: ");
                if (cantidad <= 0) {
                    System.out.println("Cantidad inválida.");
                    continue;
                }
                if (seleccionado.hayStockDisponible(cantidad)) {
                    for (int i = 0; i < cantidad; i++) {
                        pedido.agregarProducto(seleccionado);
                    }
                    seleccionado.reducirStock(cantidad);
                    System.out.println("Producto agregado correctamente");
                } else {
                    System.out.println("Stock insuficiente. Solo quedan " + seleccionado.getStock() + " unidades.");
                }
            } else {
                System.out.println("Producto no encontrado. Intente con otro ID");
            }
        }
        try {
            if (pedido.calcularTotal() == 0) {
                throw new PedidoException("No se puede crear un pedido vacio");
            } else {
                pedidos.add(pedido);
                System.out.println("Pedido creado con exito");
            }
        } catch (PedidoException e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
        }
    }

    private static Producto buscarProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private static void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos aún.");
            return;
        }
        mostrarEncabezado("MENÚ PRINCIPAL - TECHLAB");

        for (Pedido p : pedidos) {
            System.out.println(p);
        }

    }

    private static void mostrarEncabezado(String titulo) {
        System.out.println();
        System.out.println("=".repeat(35));
        System.out.println("--- " + titulo + " ---");
        System.out.println("=".repeat(35));
    }

    private static void superUsuario() {
        sc.nextLine();
        System.out.println("Ingrese su usuario: ");
        String usuario = sc.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        if (usuario.equals("gabriel") && contrasena.equals("gabriel")) {
            superUsuario = true;
            System.out.println("Se activo el modo super usuario");
        } else {
            System.out.println("Usuario o contraseña incorrecta");
        }
    }

    private static void agregarProducto() {
        sc.nextLine();
        System.out.println("Nombre del producto nuevo: ");
        String nombre = sc.nextLine();

        int precio = Utilidades.leerEntero(sc, "Precio del producto: ");
        int stock = Utilidades.leerEntero(sc, "Stock inicial del producto: ");
        int nuevoId = productos.size() + 1;

        productos.add(new Producto(nuevoId, nombre, precio, stock));
        System.out.println("Producto agregado correctamente");
    }




}