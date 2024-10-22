
package Practica;

public class MatricesConcurrentes {
    
    public static void main(String[] args) {
        
        int tam = 1000;
        double[][] matrizA = new double[tam][tam];
        double[][] matrizB = new double[tam][tam];
        double[][] resultadoM = new double[tam][tam];

        inicializarMatriz(matrizA);
        inicializarMatriz(matrizB);

        
        long tInicio = System.currentTimeMillis();
        multiplicarMatricesConcurrente(matrizA, matrizB, resultadoM, 4); // Usamos 4 hilos
        long tFinal = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución concurrente: " + (tFinal - tInicio) + " ms");
    }

    public static void inicializarMatriz(double[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = Math.random();
            }
        }
    }

    //Metodo actualizado para resolver producto de dos matrices con hilos
    public static void multiplicarMatricesConcurrente(double[][] matrixA, double[][] matrixB, double[][] resultadoM, int cantHilos) {
        int rows = matrixA.length;
        
        //Creamos un arreglo de hilos
        Thread[] threads = new Thread[cantHilos];
        
        //Luego calculamos la cantidad de filas que ejecutara cada hilo
        int filasPorHilo = rows / cantHilos;

        //Crea, inicializa y ejecuta los hilos requeridos
        for (int i = 0; i < cantHilos; i++) {
            
            //Inicio en la fila nro. n
            final int filaInicial = i * filasPorHilo;
            
            //El final sera el inicio de la siguiente tanda de filas.
            final int filaFinal = (i == cantHilos - 1) ? rows : (i + 1) * filasPorHilo;
            
            //Usamos el constructor de la clase que implementa Runnable y ejecutamos los hilos
            threads[i] = new Thread(new multiplicacionMatrices(matrixA, matrixB, resultadoM, filaInicial, filaFinal));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Espera a que todos los hilos terminen
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



class multiplicacionMatrices implements Runnable {
    private double[][] matrizA;
    private double[][] matrizB;
    private double[][] resultadoMHilos;
    private int filaInicial;
    private int filaFinal;

    //Metodo constructor
    public multiplicacionMatrices(double[][] matrizA, double[][] matrizB, double[][] resultadoMHilos, int filaInicial, int filaFinal) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.resultadoMHilos = resultadoMHilos;
        this.filaInicial = filaInicial;
        this.filaFinal = filaFinal;
    }

    @Override
    //Sobreescribimos run() a nuestra conveniencia. Aqui es donde ejecutamos la multiplicacion de matrices
    public void run() {
        int col = matrizB[0].length;       //Cantidad de columnas de la matriz B
        int cantComun = matrizA[0].length;  //Cant. columnas Matriz A = Cant. filas Matriz B

        //
        for (int i = filaInicial; i < filaFinal; i++) { //Aqui nuestros limites de filas seran los asignados a cada thread
            for (int j = 0; j < col; j++) {             //Mientras que se recorren las columnas de cada fila
                resultadoMHilos[i][j] = 0;
                for (int k = 0; k < cantComun; k++) {
                    resultadoMHilos[i][j] += matrizA[i][k] * matrizB[k][j];
                    //La operacion es la misma.
                }
            }
        }
    }
    
}
