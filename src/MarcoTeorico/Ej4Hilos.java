
package MarcoTeorico;

class HiloExtendThread extends Thread {

    HiloExtendThread() {
        super("Hilo secundario");
        System.out.println("Hilo hijo: " + this);
        start();
    }

    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Hilo hijo: "+i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupción del hilo hijo.");
        }
        System.out.println("Salida del hilo hijo");
    }
}


class ExtendThread {
    public static void main(String[] args) {
        
        Thread k = Thread.currentThread();
        k.setName("Hilo Principal");
        System.out.println("Hilo padre: " + k);
        
        new HiloExtendThread();
        
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Hilo padre: "+i);
                Thread.sleep(1000);                
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupcion del hilo padre");
        }
        System.out.println("Salida del hilo padre.");
    }
}
