
package MarcoTeorico;

public class Ej3Hilo {
    public static void main(String[] args) {
        
        //Se buscara visualizar como 2 thread se ejecutan concurrentemente
        
        //Renombramos el hilo principal para distinguirlo mejor
        Thread k = Thread.currentThread();
        k.setName("Hilo Principal");
        System.out.println("Hilo Padre: " + k);
        
        //Aqui se utiliza el metodo constructor de la clase NewThread
        
        new NewThread();
        
        //En esa clase se crea e inicializa un segundo thread implementando Runnable
        //(Ver clase NewThread...)

        
        try {
            
            //Despues de cada ejecución del hilo hijo, se ejecuta el hilo principal
            //a traves del siguiente for.
            
            for (int i = 5; i > 0; i--) {
                System.out.println("Hilo Padre: "+i);
                Thread.sleep(1000);
                
                //(.sleep se ejecuta sobre el hilo que se le pida,
                //en Ej3Hilo es el principal, y en NewThread es el hilo secundario o t
                //pues es t.start() que llama al metodo run() en esa clase)
                
            }
            //El hilo principal se ejecutara cada segundo. Despues de cada ejecución, el hilo
            //hijo se ejecutara, y asi sucesivamente hasta que ambos hilos terminen sus loops.
            
        } catch (InterruptedException e) {
            System.out.println("Interrupcion del hilo padre");
        }
        System.out.println("Salida del hilo padre.");
    }
}


class NewThread implements Runnable {
    
    Thread t;
    
    NewThread(){    //Constructor de la clase
        
        //Inicializacion del Thread t
        //"this" es el parametro Runnable de t, luego el nombre del hilo
        t = new Thread(this, "Hilo Secundario");
        
        //Aqui se visualiza el # del hilo, nombre, prioridad y grupo
        System.out.println("Hilo hijo: " + t);
        
        //Aqui se ejecuta el metodo run() de la clase que implemento Runnable
        t.start();
        
    }
    
    public void run(){  //Este es el metodo llamado al ejecutar 
                        //.start() en los hilos de esta clase
        
                        
        try {
            //Se muestra la ejecución del hilo cada medio segundo
            for (int i = 5; i > 0; i--) {
                System.out.println("Hilo hijo: "+i);
                Thread.sleep(500);
            }
            //Despues de cada ejecución sale de la clase NewThread
            //y sigue la ejecucion de la clase Ej3Hilo (Volver a clase Ej3Hilo... )
            
        } catch (Exception e) {
            System.out.println("Interrupción del hilo hijo.");
        }
        //Aparece el siguiente mensaje al terminar el for.
        System.out.println("Salida del hilo hijo.");
        
    }
}

