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
    private String respuesta;
    private String res1 = "";
    private String res2 = "";
    private Integer numeroReferencias = 0;

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
    /** VERSION 1,0
    public void generarReferenciasPagina() throws IOException
    {
        Integer tamTotalMatriz = numFilas * numCols * tamEntero;
        Integer numTotalPaginasNecesariasPorMatriz = (tamTotalMatriz) / tamPagina;
        float numTotalPaginasNecesariasPorMatriz2 = (float) (tamTotalMatriz) / (float) tamPagina;
        System.out.println(tamTotalMatriz + "/" + tamPagina + "=" + numTotalPaginasNecesariasPorMatriz2);
        System.out.println("Numero de paginas necesarias por matriz: " + numTotalPaginasNecesariasPorMatriz2);
        Integer numFilasPorPagina = tamPagina /(numFilas * tamEntero) ;
        System.out.println("Numero de filas por pagina: " + numFilasPorPagina);

        for (int i = 0; i < numTotalPaginasNecesariasPorMatriz2; i++)
        {
            for (int k = 0; k < numFilas; k++)
            {
                if (numFilasPorPagina < k)
                {
                    continue;
                }
                else
                {
                    for (int j = 0; j < numCols; j++)
                    {
                        Integer paginaMatA = i;
                        Integer paginaMatB = i + numTotalPaginasNecesariasPorMatriz;
                        Integer paginaMatC = i + 2 * numTotalPaginasNecesariasPorMatriz;

                        Integer offSetA = (k * numCols + j) * tamEntero % tamPagina;
                        Integer offSetB = (k * numCols + j) * tamEntero % tamPagina;
                        Integer offSetC = (k * numCols + j) * tamEntero % tamPagina;

                        res2 += "[A-" + k + "-" + j + "]," + paginaMatA + "," + offSetA + "\n";
                        res2 += "[B-" + k + "-" + j + "]," + paginaMatB + "," + offSetB + "\n";
                        res2 += "[C-" + k + "-" + j + "]," + paginaMatC + "," + offSetC + "\n";
                        numeroReferencias = numeroReferencias + 3;
                    }
                }
            }
        }
       res1 += "NR=" + numeroReferencias + "\n";
       respuesta = res1 + res2; 
       PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true));
       writer.println(respuesta);
       writer.close();
    }*/

    /**VERSION 2,0 - este es el que funciona
    public void generarReferenciasPagina() throws IOException
    {
        Integer tamTotalMatriz = numFilas * numCols * tamEntero;
        Integer numTotalPaginasNecesariasPorMatriz = (tamTotalMatriz) / tamPagina;
        float numTotalPaginasNecesariasPorMatriz2 = (float) (tamTotalMatriz) / (float) tamPagina;
        System.out.println(tamTotalMatriz + "/" + tamPagina + "=" + numTotalPaginasNecesariasPorMatriz2);
        System.out.println("Numero de paginas necesarias por matriz: " + numTotalPaginasNecesariasPorMatriz2);
        Integer numFilasPorPagina = tamPagina /(numFilas * tamEntero) ;
        System.out.println("Numero de filas por pagina: " + numFilasPorPagina);

        for (int i = 0; i < numTotalPaginasNecesariasPorMatriz2; i++)
        {
            for (int k = 0; k < numFilas; k++)
            {
                for (int j = 0; j < numCols; j++)
                {
                    Integer paginaMatA = i;
                    Integer paginaMatB = i + numTotalPaginasNecesariasPorMatriz;
                    Integer paginaMatC = i + (int) (2.0f * numTotalPaginasNecesariasPorMatriz2);

                    Integer offSetA = (k * numCols + j) * tamEntero % tamPagina;
                    Integer offSetB = (k * numCols + j) * tamEntero % tamPagina;
                    Integer offSetC = (k * numCols + j) * tamEntero % tamPagina;

                    res2 += "[A-" + k + "-" + j + "]," + paginaMatA + "," + offSetA + "\n";
                    res2 += "[B-" + k + "-" + j + "]," + paginaMatB + "," + offSetB + "\n";
                    res2 += "[C-" + k + "-" + j + "]," + paginaMatC + "," + offSetC + "\n";
                    numeroReferencias = numeroReferencias + 3;
                }
            }
        }
       res1 += "NR=" + numeroReferencias + "\n";
       respuesta = res1 + res2; 
       PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true));
       writer.println(respuesta);
       writer.close();
    }*/
    /** VERSION 3,0
    public void generarReferenciasPagina() throws IOException
    {
        Integer tamTotalMatriz = numFilas * numCols * tamEntero;
        Integer numTotalPaginasNecesariasPorMatriz = (tamTotalMatriz) / tamPagina;
        float numTotalPaginasNecesariasPorMatriz2 = (float) (tamTotalMatriz) / (float) tamPagina;
        System.out.println(tamTotalMatriz + "/" + tamPagina + "=" + numTotalPaginasNecesariasPorMatriz2);
        System.out.println("Numero de paginas necesarias por matriz: " + numTotalPaginasNecesariasPorMatriz2);
        Integer numFilasPorPagina = tamPagina /(numFilas * tamEntero) ;
        System.out.println("Numero de filas por pagina: " + numFilasPorPagina);
        Integer paginaMatA = 0;
        Integer paginaMatB = 0;
        Integer paginaMatC = 0;
            for (int k = 0; k < numFilas; k++)
            {
                for (int j = 0; j < numCols; j++)
                {
                    for (int i = 0; i < numTotalPaginasNecesariasPorMatriz2; i++)
                    {
                        paginaMatA = i;
                        paginaMatB = i + numTotalPaginasNecesariasPorMatriz;
                        paginaMatC = i + (int) (2.0f * numTotalPaginasNecesariasPorMatriz2);
                    }

                    Integer offSetA = (k * numCols + j) * tamEntero % tamPagina;
                    Integer offSetB = (k * numCols + j) * tamEntero % tamPagina;
                    Integer offSetC = (k * numCols + j) * tamEntero % tamPagina;

                    res2 += "[A-" + k + "-" + j + "]," + paginaMatA + "," + offSetA + "\n";
                    res2 += "[B-" + k + "-" + j + "]," + paginaMatB + "," + offSetB + "\n";
                    res2 += "[C-" + k + "-" + j + "]," + paginaMatC + "," + offSetC + "\n";
                    numeroReferencias = numeroReferencias + 3;
                }
                }
       res1 += "NR=" + numeroReferencias + "\n";
       respuesta = res1 + res2; 
       PrintWriter writer = new PrintWriter(new FileWriter("output.txt", true));
       writer.println(respuesta);
       writer.close();
    }
*/

public void generarReferenciasPagina() throws IOException {
    Integer tamTotalMatriz = numFilas * numCols * tamEntero;
    Integer numTotalPaginasNecesariasPorMatriz = tamTotalMatriz / tamPagina;
    System.out.println(tamTotalMatriz + "/" + tamPagina + "=" + numTotalPaginasNecesariasPorMatriz);
    System.out.println("Numero de paginas necesarias por matriz: " + numTotalPaginasNecesariasPorMatriz);
    Integer numFilasPorPagina = tamPagina / (numFilas * tamEntero);
    System.out.println("Numero de filas por pagina: " + numFilasPorPagina);

    StringBuilder res2 = new StringBuilder();
    int numeroReferencias = 0;
    for (int i = 0; i < numTotalPaginasNecesariasPorMatriz; i++) {
        int paginaMatA = i;
        int paginaMatB = paginaMatA + numTotalPaginasNecesariasPorMatriz;
        int paginaMatC = paginaMatB + numTotalPaginasNecesariasPorMatriz;
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