import java.io.*;
import java.util.*;

public class ComportamientoSistema {

    public static void main(String[] args) throws IOException {

        //Lectura de los parámetros desde el archivo de entrada
        List<Integer> references = new ArrayList<>();
        String archivo = "output.txt";
        Integer num_pages = 2000;
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
        int[] page_ages = new int[num_pages];
        Arrays.fill(page_table, -1);
        for (int reference : references) {
            if (page_table[reference] == -1) {
                // Fallo de página  //
                num_page_faults++;
                int oldest_page = 0;
                for (int i = 1; i < num_pages; i++) {
                    if (page_ages[i] < page_ages[oldest_page]) {
                        oldest_page = i;
                    } // Fallo de página // 
                }
                page_table[oldest_page] = reference;
                page_ages[oldest_page] = 0; //Actualiza la edad de la página
            }
            page_ages[reference]++;
        }
        
        // Escritura de los resultados en el archivo de salida
        System.out.println("Número de fallos de página: " + num_page_faults);
    }
}
