
package MarcoTeorico;

public class Ej2Hilos {
    public static void main(String[] args) {
        
        //Se obtiene la referencia del hilo principal y se muestra en consola
        Thread t = Thread.currentThread();
        System.out.println("Hilo actual: "+t);
        
        //Cambio del nombre del hilo principal
        //ya que se obtuvo control sobre el hilo
        t.setName("Armando");
        System.out.println("Cambio de nombre: "+t);
        
        
        try {
            for (int n = 5; n > 0; n--) {
                System.out.println(n);
                Thread.sleep(1000);     //Pausa de 1 segundo
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupción del hilo principal");
        }
    }
}
