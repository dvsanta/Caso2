import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    public static void main(String[] args) {

    }

        public static int[] ConfigReader() {
        String filename = "src/config.txt"; // nombre del archivo de entrada
        int numFilas = 0, numCols = 0, tamEntero = 0, tamPagina = 0, numPaginas = 0;
        int[] DatosConfiguracion = new int[0];
        try {
            DatosConfiguracion = new int[5];
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            DatosConfiguracion[0] = Integer.parseInt(reader.readLine());
            DatosConfiguracion[1] = Integer.parseInt(reader.readLine());
            DatosConfiguracion[2] = Integer.parseInt(reader.readLine());
            DatosConfiguracion[3] = Integer.parseInt(reader.readLine());
            DatosConfiguracion[4] = Integer.parseInt(reader.readLine());
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // aquí se pueden utilizar las variables leídas del archivo
        return (DatosConfiguracion);
        }
    }
