import java.io.*;
import java.util.*;

public class ComportamientoSistema {

    public static void main(String[] args) throws IOException {

        //Lectura de los parámetros desde el archivo de entrada
        List<Integer> references = new ArrayList<>();
        String archivo = "output.txt";
        Integer num_pages = 20000;
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            linea = br.readLine();
            String[] lineaSizePag = linea.split("=");
            int tamPagina = Integer.parseInt(lineaSizePag[1]);
            System.out.println(tamPagina);
            linea = br.readLine();
            String[] lineaNumFilas = linea.split("=");
            int numFilas = Integer.parseInt(lineaNumFilas[1]);
            System.out.println(numFilas);
            linea = br.readLine();
            String[] lineaNumCols = linea.split("=");
            int numCols = Integer.parseInt(lineaNumCols[1]);
            System.out.println(numCols);
            linea = br.readLine();
            String[] lineaNumRef = linea.split("=");
            int numeroReferencias = Integer.parseInt(lineaNumRef[1]);
            System.out.println(numeroReferencias);
            while ((linea = br.readLine()) != null) {
                String[] referencia = linea.split(",");
                int ref = Integer.parseInt(referencia[1]);
                references.add(ref);
            }
            br.close();
            fr.close();
            System.out.println(references.size());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + archivo);
            e.printStackTrace();
        }
        
        
        
        // Simulación del comportamiento del sistema de paginación
        int num_page_faults = 0;
        int[] page_table = new int[num_pages];
        Queue<Integer> page_queue = new LinkedList<>();
        Arrays.fill(page_table, -1);
        for (int reference : references) {
            if (page_table[reference] == -1) {
                // Fallo de página //
                num_page_faults++;
                if (page_queue.size() >= num_pages) {
                    int oldest_page = page_queue.poll();
                    page_table[oldest_page] = -1;
                }
                page_queue.add(reference);
                page_table[reference] = 1; // Marca la página como presente en memoria
            }
        }
        
        // Escritura de los resultados en el archivo de salida
        System.out.println("Número de fallos de página: " + num_page_faults);
    }
}