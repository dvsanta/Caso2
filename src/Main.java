public class Main {
    public static void main(String[] args) {
        int[] Configuraciones = ConfigReader.ConfigReader();
        int numFilas = Configuraciones[0];
        int numCols = Configuraciones[1];
        int tamEntero = Configuraciones[2];
        int tamPagina = Configuraciones[3];
        int numPaginas = Configuraciones[4];

        int[][] matrizResultante = SumarMatricesAleatorias.sumarMatrices(numFilas, numCols);
        leerMatriz(matrizResultante);
    }


    public static void leerMatriz(int[][] matriz) {
            System.out.println("Valores de la matriz:");
            for (int fila = 0; fila < matriz.length; fila++) {
                for (int columna = 0; columna < matriz[fila].length; columna++) {
                    int valor = matriz[fila][columna];
                    System.out.print(valor + " ");
                }
                System.out.println(); // Agregar un salto de línea después de cada fila
            }
        }
    }
