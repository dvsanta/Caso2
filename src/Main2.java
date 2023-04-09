import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {

    private static int numFilas;
    private static int numColumnas;
    private static int tamEntero;
    private static int tamPagina;
    private static int numMarcosPagina;

    public static void main(String[] args) {
        try {
            File configFile = new File("config.txt");
            Scanner scanner = new Scanner(configFile);

            numFilas = Integer.parseInt(scanner.nextLine());
            numColumnas = Integer.parseInt(scanner.nextLine());
            tamEntero = Integer.parseInt(scanner.nextLine());
            tamPagina = Integer.parseInt(scanner.nextLine());
            numMarcosPagina = Integer.parseInt(scanner.nextLine());

            scanner.close();

            int numElementosPagina = (tamPagina * 1024) / tamEntero;
            int numPaginasPorMatriz = (int) Math.ceil((double) (numFilas * numColumnas * tamEntero) / (tamPagina * 1024));

            System.out.println("Número de elementos por página: " + numElementosPagina);
            System.out.println("Número de páginas por matriz: " + numPaginasPorMatriz);

            int numPaginasTotales = numPaginasPorMatriz * 2; // Suponemos que hay dos matrices
            int numMarcosLibres = numMarcosPagina;

            for (int i = 0; i < numPaginasTotales; i++) {
                if (numMarcosLibres > 0) {
                    System.out.println("La página " + i + " está en el marco de página " + (numMarcosPagina - numMarcosLibres));
                    numMarcosLibres--;
                } else {
                    System.out.println("La página " + i + " está en memoria secundaria");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo de configuración no encontrado.");
            e.printStackTrace();
        }
    }
}
