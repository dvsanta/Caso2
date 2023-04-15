import java.io.*;
import java.util.*;

public class ComportamientoSistema {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Lectura de los parámetros desde el archivo de entrada
        int numFilas = 0;
        int numCols = 0;
        List<Integer> references = new ArrayList<>();
        String archivo = "output.txt";
        Integer num_pages = 4;
        int tamPagina = 0;
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            linea = br.readLine();
            String[] lineaSizePag = linea.split("=");
            tamPagina = Integer.parseInt(lineaSizePag[1]);
            System.out.println(tamPagina);
            linea = br.readLine();
            String[] lineaNumFilas = linea.split("=");
            numFilas = Integer.parseInt(lineaNumFilas[1]);
            System.out.println(numFilas);
            linea = br.readLine();
            String[] lineaNumCols = linea.split("=");
            numCols = Integer.parseInt(lineaNumCols[1]);
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
        int num_page_faults = 0; // Compartido con Aging
        int[] page_table = new int[(numFilas * numCols * 3) / num_pages]; // Compartido con Aging
        Arrays.fill(page_table, -1);
        Queue<Integer> page_queue = new LinkedList<>(); // Compartido con Aging

        // Creación de los objetos de las clases LectorEscritor y Aging
        LectorEscritor lectorEscritor = new LectorEscritor(page_table, page_queue, references, num_pages, numFilas, numCols, tamPagina);
        Aging aging = new Aging(page_table, page_queue, num_pages);

        // Creación de los threads
        Thread t1 = new Thread(lectorEscritor);
        Thread t2 = new Thread(aging);

        // Arranque de los threads
        t1.start();
        t2.start();

        // Espera a que los threads terminen
        t1.join();
        t2.join();

        // Obtención del número de fallos de página
        num_page_faults = lectorEscritor.getNumPageFaults();

        // Escritura de los resultados en el archivo de salida
        System.out.println("Número de fallos de página: " + num_page_faults);
    }
}
