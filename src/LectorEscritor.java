import java.util.List;
import java.util.Queue;

public class LectorEscritor implements Runnable {
    private int[] page_table;
    private Queue<Integer> page_queue;
    private List<Integer> references;
    private int num_page_faults;
    private int num_pages;
    private int numFilas;
    private int numCols;
    private int tamPagina;
    private int currentIndex;

    public LectorEscritor(int[] page_table, Queue<Integer> page_queue, List<Integer> references, 
                          int num_pages, int numFilas, int numCols, int tamPagina) {
        this.page_table = page_table;
        this.page_queue = page_queue;
        this.references = references;
        this.num_page_faults = 0;
        this.num_pages = num_pages;
        this.numFilas = numFilas;
        this.numCols = numCols;
        this.tamPagina = tamPagina;
        this.currentIndex = 0;
    }

    public void run() {
        while (currentIndex < references.size()) {
            int reference;
            synchronized (references) {
                if (currentIndex >= references.size()) {
                    break;
                }
                reference = references.get(currentIndex);
                currentIndex++;
            }
            synchronized (page_table) {
                if (page_table[reference] == -1) {
                    num_page_faults++;
                    if (page_queue.size() >= num_pages) {
                        int oldest_page = page_queue.poll();
                        page_table[oldest_page] = -1;
                    } 
                    page_queue.add(reference);
                    page_table[reference] = 1;
                }
            }
        }
        System.out.println("Referencias: " + references.size() + " Fallos de página: " + num_page_faults);

    }

    public int getNumPageFaults() {
        return num_page_faults;
    }
}
