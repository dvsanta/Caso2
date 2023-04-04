import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    public static void main(String[] args) {
        String filename = "src/config.txt"; // nombre del archivo de entrada
        int numFilas = 0, numCols = 0, tamEntero = 0, tamPagina = 0, numPaginas = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            numFilas = Integer.parseInt(reader.readLine());
            numCols = Integer.parseInt(reader.readLine());
            tamEntero = Integer.parseInt(reader.readLine());
            tamPagina = Integer.parseInt(reader.readLine());
            numPaginas = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // aquí se pueden utilizar las variables leídas del archivo
        System.out.println("Número de filas: " + numFilas);
        System.out.println("Número de columnas: " + numCols);
        System.out.println("Tamaño de Entero: " + tamEntero);
        System.out.println("Tamaño de página: " + tamPagina);
        System.out.println("Número de páginas: " + numPaginas);
    }
}