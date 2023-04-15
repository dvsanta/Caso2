import java.util.Queue;

public class Aging implements Runnable {
    private int[] page_table;
    private Queue<Integer> page_queue;
    private int aging_rate;
    private boolean running = true;
    
    public Aging(int[] page_table, Queue<Integer> page_queue, int aging_rate) {
        this.page_table = page_table;
        this.page_queue = page_queue;
        this.aging_rate = aging_rate;
    }
    
    public void run() {
        while (running) {
            try {
                Thread.sleep(aging_rate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (page_queue) {
                for (Integer page : page_queue) {
                    page_table[page] >>= 1; // Aplicamos el corrimiento de bits
                }
            }
        }
    }

    public void stop() {
        running = false;
    }
}
