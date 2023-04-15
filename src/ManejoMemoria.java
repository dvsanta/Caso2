import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ManejoMemoria {
    private int numFilas; // Número de filas de la matriz
    private int numCols; // Número de columnas de la matriz
    private int tamEntero; // Tamaño de un entero en bytes
    private int tamPagina; // Tamaño de las páginas en bytes
    private int numPaginas; // Número de páginas
    private String res1 = "";

    public void leerArchivoConfiguracion(String rutaArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            numFilas = Integer.parseInt(br.readLine()); // Número de filas
            
            numCols = Integer.parseInt(br.readLine()); // Número de columnas

            tamEntero = Integer.parseInt(br.readLine()); // Tamaño de un entero en bytes

            tamPagina = Integer.parseInt(br.readLine()); // Tamaño de las paginas en bytes

            numPaginas = Integer.parseInt(br.readLine()); // Número de páginas
            
            br.close();
            res1 += "TP=" + tamPagina + "\n";
            res1 += "NF=" + numFilas + "\n";
            res1 += "NC=" + numCols + "\n";
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración");
            e.printStackTrace();
        }
    }

    public void generarReferenciasPagina() throws IOException {
        Integer tamTotalMatriz = numFilas * numCols * tamEntero;
        Integer numTotalPaginasNecesariasPorMatriz = tamTotalMatriz / tamPagina;
        float numTotalPaginasNecesariasPorMatriz2 = (float) (tamTotalMatriz) / (float) tamPagina;

        StringBuilder res2 = new StringBuilder();
        int numeroReferencias = 0;
        for (int i = 0; i < numTotalPaginasNecesariasPorMatriz2; i++) {
            int paginaMatA = i;
            int paginaMatB = paginaMatA + numTotalPaginasNecesariasPorMatriz;
            int paginaMatC = i + + (int) (2.0f * numTotalPaginasNecesariasPorMatriz2);
            for (int k = 0; k < numFilas; k++) {
                for (int j = 0; j < numCols; j++) {
                    int offSet = (k * numCols + j) * tamEntero % tamPagina;
                    res2.append("[A-").append(k).append("-").append(j).append("],").append(paginaMatA).append(",").append(offSet).append("\n");
                    res2.append("[B-").append(k).append("-").append(j).append("],").append(paginaMatB).append(",").append(offSet).append("\n");
                    res2.append("[C-").append(k).append("-").append(j).append("],").append(paginaMatC).append(",").append(offSet).append("\n");
                    numeroReferencias += 3;
                }
            }
        }
        res1 += "NR=" + numeroReferencias + "\n";
        String respuesta = res1 + res2;
        PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true));
        writer.println(respuesta);
        writer.close();
    }

public static void main(String[] args) throws IOException {
    ManejoMemoria mm = new ManejoMemoria();
    mm.leerArchivoConfiguracion("config.txt");
    mm.generarReferenciasPagina();
}
}