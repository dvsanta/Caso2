import java.util.Random;
public class SumarMatricesAleatorias {

    public static int[][] sumarMatrices(int n, int m, int numPaginas) {
        int [][] matrizPaginas = new int[numPaginas][10];
        int[][] matriz1 = new int[n][m];
        int[][] matriz2 = new int[n][m];
        int[][] matrizSuma = new int[n][m];
        Random rand = new Random();

        // Llenar las dos matrices aleatoriamente
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matriz1[i][j] = rand.nextInt(10); // Números aleatorios entre 0 y 9
                matriz2[i][j] = rand.nextInt(10);
            }
        }

        // Sumar las dos matrices en la matriz de la suma
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrizSuma[i][j] = matriz1[i][j] + matriz2[i][j];
            }
        }

        return matrizSuma;
    }
}
