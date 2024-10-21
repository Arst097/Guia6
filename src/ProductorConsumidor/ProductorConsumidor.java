import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductorConsumidor {

    // Cola compartida entre productor y consumidor
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    private static int LIMITE = 10; // Límite de iteraciones

    public static void main(String[] args) {
        // Crear hilos de productor y consumidor
        Thread productor = new Thread(new Productor());
        Thread consumidor = new Thread(new Consumidor());

        // Iniciar los hilos
        productor.start();
        consumidor.start();
    }

    // Clase del Productor
    static class Productor implements Runnable {
        Random random = new Random();

        @Override
        public void run() {
            try {
                for (int i = 0; i < LIMITE; i++) {  // Iterar hasta el límite
                    int numero = random.nextInt(100) + 1;  // Generar número aleatorio entre 1 y 100
                    System.out.println((i+1)+". Productor generó: " + numero);
                    queue.put(numero);  // Colocar el número en la cola
                    Thread.sleep(500);  // Simula el tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Clase del Consumidor
    static class Consumidor implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < LIMITE; i++) {  // Iterar hasta el límite
                    int numero = queue.take();  // Leer número de la cola
                    int resultado = numero * 2;  // Multiplicar por 2
                    System.out.println("Consumidor leyó: " + numero + ", y su doble es: " + resultado);
                    Thread.sleep(500);  // Simula el tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}