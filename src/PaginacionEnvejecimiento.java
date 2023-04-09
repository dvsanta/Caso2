import java.util.*;

public class PaginacionEnvejecimiento {
    
    // Estructuras de datos
    private int[][] tablaPaginas; // Tabla de páginas
    private LinkedList<Integer> marcosPagina; // Lista de marcos de página
    private LinkedList<Integer> paginasEnvejecidas; // Lista de páginas envejecidas
    private int edadMaxima; // Edad máxima para la política de reemplazo de páginas
    
    // Constructor
    public PaginacionEnvejecimiento(int tamMarco, int numPaginas, int edadMaxima) {
        tablaPaginas = new int[numPaginas][2]; // 2 columnas: número de página y edad
        marcosPagina = new LinkedList<Integer>(); // Inicialmente vacía
        for (int i = 0; i < tamMarco; i++) {
            marcosPagina.add(i); // Añadir los marcos de página disponibles
        }
        paginasEnvejecidas = new LinkedList<Integer>(); // Inicialmente vacía
        this.edadMaxima = edadMaxima;
    }
    
    // Método para asignar un marco de página a una página
    public int asignarMarco(int numPagina) {
        int marco;
        if (marcosPagina.size() > 0) {
            marco = marcosPagina.removeFirst(); // Tomar el primer marco de página disponible
        } else {
            marco = reemplazarPagina(); // Reemplazar una página envejecida
        }
        tablaPaginas[marco][0] = numPagina; // Asignar el número de página al marco
        tablaPaginas[marco][1] = 0; // Reiniciar la edad de la página
        return marco;
    }
    
    // Método para liberar un marco de página
    public void liberarMarco(int marco) {
        marcosPagina.add(marco); // Agregar el marco de página liberado a la lista de disponibles
        tablaPaginas[marco][0] = -1; // Desasignar el número de página del marco
    }
    
  // Método para envejecer las páginas en la tabla de páginas
public void envejecerPaginas() {
    for (int i = 0; i < tablaPaginas.length; i++) {
        if (tablaPaginas[i][0] != -1) { // Si el marco está asignado a una página
            tablaPaginas[i][1] = tablaPaginas[i][1] >> 1; // Desplazar la edad de la página a la derecha
            if (tablaPaginas[i][2] == 1) { // Si la página ha sido referenciada
                tablaPaginas[i][2] = 0; // Reiniciar el bit de referencia
                tablaPaginas[i][1] |= 0x80000000; // Establecer el bit más significativo de la edad
            }
        }
    }
}



// Método para reemplazar una página envejecida
public int reemplazarPagina() {
    int numPagina = -1;
    while (numPagina == -1) {
        if (paginasEnvejecidas.size() > 0) { // Si hay páginas envejecidas disponibles
            numPagina = paginasEnvejecidas.removeFirst(); // Tomar la primera página envejecida
            // Buscar el marco de página correspondiente a la página a reemplazar
            for (int i = 0; i < tablaPaginas.length; i++) {
                if (tablaPaginas[i][0] == numPagina) {
                    // Liberar el marco de página y asignarlo a la nueva página
                    liberarMarco(i);
                    break;
                }
            }
        } else { // Si no hay páginas envejecidas disponibles
            envejecerPaginas(); // Envejecer todas las páginas
        }
    }
    return asignarMarco(numPagina); // Asignar el marco de página a la nueva página
}

// Método para sumar dos matrices
public int[][] sumarMatrices(int numFilas, int numCols) {
    int[][] matrizA = new int[numFilas][numCols];
    int[][] matrizB = new int[numFilas][numCols];
    int[][] matrizC = new int[numFilas][numCols];
    // Inicializar las matrices con valores aleatorios
    Random rand = new Random();
    for (int i = 0; i < numFilas; i++) {
        for (int j = 0; j < numCols; j++) {
            matrizA[i][j] = rand.nextInt(10);
            matrizB[i][j] = rand.nextInt(10);
        }
    }
    // Sumar las matrices
    for (int i = 0; i < numFilas; i++) {
        for (int j = 0; j < numCols; j++) {
            matrizC[i][j] = matrizA[i][j] + matrizB[i][j];
        }
    }
    return matrizC;
}

// Método main para probar el programa
public static void main(String[] args) {
    // Parámetros de prueba
    int tamMarco = 4;
    int numPaginas = 16;
    int edadMaxima = 5;
    int numFilas1 = 100;
    int numCols1 = 100;
    int numFilas2 = 200;
    int numCols2 = 200;
    // Crear el objeto de paginación con envejecimiento
    PaginacionEnvejecimiento paginacion = new PaginacionEnvejecimiento(tamMarco, numPaginas, edadMaxima);
    // Sumar dos matrices con diferentes tamaños y verificar el tiempo de ejecución
    long startTime = System.currentTimeMillis();
    int[][] matrizC1 = paginacion.sumarMatrices(numFilas1, numCols1);
    int[][] matrizC2 = paginacion.sumarMatrices(numFilas2, numCols2);
    long endTime = System.currentTimeMillis();
    System.out.println("Tiempo de ejecución para matriz de " + numFilas1 + "x" + numCols1 + ": " + (endTime - startTime) + "ms");
    startTime = System.currentTimeMillis();
    System.out.println("Tiempo de ejecución para matriz de " + numFilas2 + "x" + numCols2 + ": " + (endTime - startTime) + "ms");
}
}

