
package MarcoTeorico;

public class Ej1Hilos extends Thread {
    
    public Ej1Hilos(String str){
        super(str);                 //Nombra el hilo a traves del constructor thread(String name)
                                    //El constructor es de la clase de la que hereda, Thread.
    }
    
    public void run(){
        for (int i=0; i<5; i++){
            System.out.println("Este es el thread: "+getName());
        }
    }
    
    public static void main(String[] args) {
        Ej1Hilos miThread = new Ej1Hilos("Hilo de Prueba");
        
        miThread.start();     //Aqui se llama indirectamente al metodo run() con .start()
                              //por la logica de la clase Thread
    }
}

