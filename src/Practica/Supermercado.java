package Practica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Lina <3

// Clase que representa un producto
class Producto {
    private final String nombre;
    private final double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

// Clase que representa un cliente
class Cliente implements Runnable {
    private final String nombre;
    private final List<Producto> carrito;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.carrito = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        carrito.add(producto);
    }

    public List<Producto> getCarrito() {
        return carrito;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        // Simular el proceso de cobro
        Cajera.cobrar(this);
    }
}

// Clase que representa la cajera
class Cajera {
    public static synchronized void cobrar(Cliente cliente) {
        double total = 0;
        System.out.println("Procesando compra para: " + cliente.getNombre());
        for (Producto producto : cliente.getCarrito()) {
            System.out.println("Escaneando: " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
            total += producto.getPrecio();
            try {
                Thread.sleep(500); // Simula el tiempo de escaneo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total a pagar por " + cliente.getNombre() + ": $" + total);
        System.out.println("-------------------------------------------------");
    }
}

// Clase principal que simula el supermercado
public class Supermercado {
    public static void main(String[] args) {
        // Crear algunos productos
        Producto producto1 = new Producto("Leche", 1.50);
        Producto producto2 = new Producto("Pan", 0.80);
        Producto producto3 = new Producto("Huevos", 2.00);
        Producto producto4 = new Producto("Manzanas", 3.00);

        // Crea clientes y agrega productos a sus carritos
        Cliente cliente1 = new Cliente("Lina");
        cliente1.agregarProducto(producto1);
        cliente1.agregarProducto(producto2);

        Cliente cliente2 = new Cliente("Sara");
        cliente2.agregarProducto(producto3);
        cliente2.agregarProducto(producto4);
        cliente2.agregarProducto(producto1);

        // Crea hilos para cada cliente
        Thread hiloCliente1 = new Thread(cliente1);
        Thread hiloCliente2 = new Thread(cliente2);

        // Inicia el proceso de cobro
        hiloCliente1.start();
        hiloCliente2.start();
    }
}
        
        
        
   
 