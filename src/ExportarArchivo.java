import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExportarArchivo {

    public static void main(String[] args) {


    }

    public static void Exportar(int numFilas, int numCols, int tamPagina) {
        int TP = tamPagina; // Tamaño de Página
        int NF = numFilas; // Número de filas de la matriz
        int NC = numCols; // Número de columnas de la matriz
        int NR = (NF * NC * 3) / TP; // Número de referencias en el archivo - Falta calcular

        try {
            FileWriter archivo = new FileWriter("archivo_salida.txt");
            BufferedWriter buffer = new BufferedWriter(archivo);

            buffer.write("TP=" + TP + "\n");
            buffer.write("NF=" + NF + "\n");
            buffer.write("NC=" + NC + "\n");
            buffer.write("NR=" + NR + "\n");

            // Escribir las referencias en el archivo
            for (int i = 0; i < NR; i++) {
                int matriz = i / (NF * NC); // Calcular la matriz a la que pertenece la referencia
                int fila = (i / NC) % NF; // Calcular la fila de la referencia en la matriz
                int columna = i % NC; // Calcular la columna de la referencia en la matriz

                String referencia = "[" + ((matriz == 0) ? "A" : (matriz == 1) ? "B" : "C") + "-" + fila + "-" + columna + "]";
                int paginaVirtual = i / TP;
                int desplazamiento = (i % TP) * 4; // Suponiendo que cada elemento es de 4 bytes

                buffer.write(referencia + "," + paginaVirtual + "," + desplazamiento + "\n");
            }

            buffer.close();
            archivo.close();
            System.out.println("El archivo ha sido generado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo: " + e.getMessage());
        }
    }

}

