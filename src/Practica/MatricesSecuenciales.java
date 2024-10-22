
package Practica;

public class MatricesSecuenciales {
    
    public static void main(String[] args) {
        
        int tam = 1000; // Tamaño de las matrices
        
        double[][] matrizA = new double[tam][tam];
        double[][] matrizB = new double[tam][tam];
        //Al ser matricez cuadradas, su matriz resultante tiene el mismo tamaño
        double[][] resultMatrix = new double[tam][tam];

        //Inicializar matrices con valores aleatorios
        inicializarMatriz(matrizA);
        inicializarMatriz(matrizB);

        //Usamos currentTimeMillis para calcular el tiempo de ejecucion
        //del metodo solucion.
        long tInicio = System.currentTimeMillis();
        
        //Producto de matrices secuencial
        multiplicarMatricesSecuencial(matrizA, matrizB, resultMatrix);
        
        long tFinal = System.currentTimeMillis();
        
        //Se muestra el tiempo que tardo en ejecutarse.
        System.out.println("Tiempo de ejecucion secuencial: " + (tFinal - tInicio) + " ms");
    }

    
    //Metodo que inicializa las matrices
    public static void inicializarMatriz(double[][] matriz) {
        
        //Recorre cada matriz por fila y columna asignando numeros random
        for (int i = 0; i < matriz.length; i++) {               //Recorre fila
                for (int j = 0; j < matriz[0].length; j++) {    //Recorre columna
                matriz[i][j] = Math.random();
            }
        }
    }

    //Metodo que resuelve el producto de las dos matrices
    public static void multiplicarMatricesSecuencial(double[][] matrizA, double[][] matrizB, double[][] resultadoM) {
        
        //La matriz resultante tendra la cantidad de filas de la matriz A y la cantidad de columnas de la matriz B.
        
        int rows = matrizA.length;          //Cantidad de filas de la matriz A
        int cols = matrizB[0].length;       //Cantidad de columnas de la matriz B
        int cantComun = matrizA[0].length;  //Cant. columnas Matriz A = Cant. filas Matriz B

        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultadoM[i][j] = 0;                   //Se recorre la matriz resultado inicializandola en ceros
                for (int k = 0; k < cantComun; k++) {                   //Luego hacemos la multiplicacion de las matrices
                    resultadoM[i][j] += matrizA[i][k] * matrizB[k][j];  //usando como pivotes i y j en cada matriz.
                }
            }
        }
    }
    
}


