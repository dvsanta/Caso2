import java.io.*;
import java.util.*;

public class ComportamientoProceso {

    public static void main(String[] args) throws IOException {
        
        // Lectura de los parámetros desde el archivo de entrada
        Scanner scanner = new Scanner(new File("config.txt"));
        int num_filas = scanner.nextInt();
        int num_cols = scanner.nextInt();
        int size_of_integer = scanner.nextInt();
        int page_size = scanner.nextInt();
        int num_pages = scanner.nextInt();
        scanner.close();
        
        // Generación de las referencias de página
        List<Integer> references = new ArrayList<>();
        for (int i = 0; i < num_filas; i++) {
            for (int j = 0; j < num_cols; j++) {
                int page_number = (i * num_cols + j) * size_of_integer / page_size;
                references.add(page_number);
            }
        }
        
        // Escritura de las referencias de página en el archivo de salida
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));
        for (int reference : references) {
            writer.println(reference);
        }
        writer.close();
    }
}
