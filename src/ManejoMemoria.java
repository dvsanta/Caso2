import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ManejoMemoria {
    private int numFilas;
    private int numCols;
    private int tamEntero;
    private int tamPagina;
    private int numPaginas;
    private String respuesta;

    public void leerArchivoConfiguracion(String rutaArchivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            numFilas = Integer.parseInt(br.readLine());
            respuesta += "Número de filas: " + numFilas + "\n";
            numCols = Integer.parseInt(br.readLine());
            respuesta += "Número de columnas: " + numCols + "\n";
            tamEntero = Integer.parseInt(br.readLine());
            respuesta += "Tamaño de un entero: " + tamEntero + "\n";
            tamPagina = Integer.parseInt(br.readLine());
            respuesta += "Tamaño de una página: " + tamPagina + "\n";
            numPaginas = Integer.parseInt(br.readLine());
            respuesta += "Número de páginas: " + numPaginas + "\n";
            br.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración");
            e.printStackTrace();
        }
    }

    public void generarReferenciasPagina() throws IOException {
        int[][] mat1 = new int[numFilas][numCols];
        int[][] mat2 = new int[numFilas][numCols];
        int[][] mat3 = new int[numFilas][numCols];
        int tamMatriz = numFilas * numCols * tamEntero;
        int tamPaginaMatriz = tamPagina * tamPagina/tamMatriz;
        System.out.println("Tamaño de una página de matriz: " + tamPaginaMatriz);
        respuesta += "Tamaño de una página de matriz: " + tamPaginaMatriz + "\n";
        int numPaginasMatriz = (int) Math.ceil((double) tamMatriz/tamPaginaMatriz);
        int tamFila = numCols * tamEntero;
        System.out.println("Tamaño de una fila: " + tamFila);
        float numFilasPorPagina = tamPaginaMatriz/tamFila;
        System.out.println("Número de filas por página: " + numFilasPorPagina);
        int numeroPaginasNecesariasPorMatriz = Math.round(numFilas / numFilasPorPagina);
        System.out.println("Número de páginas necesarias por matriz: " + numeroPaginasNecesariasPorMatriz);

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numCols; j++) {
                int paginaMat1 = i;
                int offsetMat1 = (i*numCols+j) * tamEntero / tamPaginaMatriz;
                respuesta += "Referencia de página para mat1[" + i + "][" + j + "]: " + paginaMat1  + ", " + offsetMat1 + "\n";

                int paginaMat2 = i + 1;
                int offsetMat2 = (i*numCols+j) * tamEntero / tamPaginaMatriz;
                respuesta += "Referencia de página para mat2[" + i + "][" + j + "]: " + paginaMat2  + ", " + offsetMat2 + "\n";

                int paginaMat3 = i + 2 * 1;
                int offsetMat3 = (i*numCols+j) * tamEntero / tamPaginaMatriz;
                respuesta += "Referencia de página para mat3[" + i + "][" + j + "]: " + paginaMat3  + ", " + offsetMat3 + "\n";
            }
        }

        int tamPaginaSuma = tamPagina - tamPaginaMatriz;
        int numPaginasSuma = (int) Math.ceil((double) tamEntero/tamPaginaSuma);
        for (int k = 0; k < numPaginas; k++) {
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numCols; j++) {
                    int paginaSuma = k * numPaginasSuma + (i*numCols+j) * tamEntero / tamPaginaSuma;
                    int offsetSuma = (i*numCols+j) * tamEntero % tamPaginaSuma;
                    respuesta += "Referencia de página para suma[" + i + "][" + j + "], página " + paginaSuma + ": " + offsetSuma + "\n";
            }
        }
    }
    // Escritura del número de fallas de página en el archivo de salida
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