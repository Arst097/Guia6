package ProductorConsumidor;

public class ProdConsum {
    public static void main(String[] args){
        
        Buffer bufferMonitor = new Buffer(5);       //Capacidad para 5 elementos
        
        //Hilos Productores
        for (int i=0; i<3; i++){                    //Creara 3 hilos productores
            new Thread(() -> {

                try{
                    for(int j=1; j<=10; j++){
                        bufferMonitor.producir(j);
                        System.out.println(Thread.currentThread().getName() + " produjo: " + j);
                        
                        Thread.sleep(100);
                    }
                    
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                
            }).start();
        }
        
        
        //Hilos Consumidores
        for (int i=0; i<3; i++){                    //Creara 3 hilos consumidores
            new Thread(() -> {

                try{
                    for(int j=1; j<=10; j++){
                        bufferMonitor.consumir();
                        System.out.println(Thread.currentThread().getName() + " consumio: " + j);
                        
                        Thread.sleep(150);
                    }
                    
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}





class Buffer{
    private final int[] buffer;         //Creamos el buffer
    
    private int capacidad;              //Capacidad del Buffer
    private int nroElementos = 0;       //Elementos contenidos
    private int in = 0;                 //A que lugar entra
    private int out = 0;                //De que lugar sale
    
    //Construcctor
    public Buffer(int capacidad){
        this.capacidad = capacidad;
        buffer = new int[capacidad];
    }
    
    //Procedimiento 1
    public synchronized void producir (int nuevoElemento)throws InterruptedException {
        
        while (nroElementos == capacidad) {
            wait();                         //A dormir (pasa a la cola del monitor)
        }
        
        buffer[in] = nuevoElemento;             //Se agrega el nuevo elemento al buffer
        in = (in + 1) % capacidad;              //Se actualiza al siguiente espacio disponible
        nroElementos++;
        
        notifyAll();                        //Notifica a otros procesos de la finalización
    }
    
    //Procedimiento 2
    public synchronized int consumir() throws InterruptedException{
        
        while(nroElementos == 0){
            wait();
        }
        
        int elementoEscogido = buffer[out];
        out = (out + 1) % capacidad;
        nroElementos--;
        
        notifyAll();
        
        return elementoEscogido;
        
    }
}