import java.io.*;
import java.util.*;

public class ComportamientoSistema {

    public static void main(String[] args) throws IOException {
        
        // Lectura de los parámetros desde el archivo de entrada
        Scanner scanner = new Scanner(new File("config.txt"));
        int num_filas = scanner.nextInt();
        int num_cols = scanner.nextInt();
        int size_of_integer = scanner.nextInt();
        int page_size = scanner.nextInt();
        int num_pages = scanner.nextInt();
        scanner.close();
        
        // Lectura de las referencias de página desde el archivo de entrada
        List<Integer> references = new ArrayList<>();
        scanner = new Scanner(new File("output.txt"));
        while (scanner.hasNext()) {
            references.add(scanner.nextInt());
        }
        scanner.close();
        
        // Simulación del comportamiento del sistema de paginación
        int num_page_faults = 0;
        int[] page_table = new int[num_pages];
        int[] page_ages = new int[num_pages];
        Arrays.fill(page_table, -1);
        for (int reference : references) {
            if (page_table[reference] == -1) {
                // Fallo de página
                num_page_faults++;
                int oldest_page = 0;
                for (int i = 1; i < num_pages; i++) {
                    if (page_ages[i] < page_ages[oldest_page]) {
                        oldest_page = i;
                    }
                }
                page_table[oldest_page] = reference;
                page_ages[oldest_page] = 0;
            }
            page_ages[reference]++;
        }
        
        // Escritura del número de fallas de página en el archivo de salida
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true));
        writer.println("Numero de fallas: " + num_page_faults);
        writer.close();
    }
}
