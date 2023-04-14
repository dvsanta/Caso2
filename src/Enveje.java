public class Enveje extends Thread{
    private int[] page_ages;
    public int numeroFallos = 0;

    public Enveje(int[] page_ages){
        this.page_ages = page_ages;
    }

    public void run(){
        while (true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for (int i = 0; i < page_ages.length; i++) {
                page_ages[i] = page_ages[i] >> 1;
            }
        }
    }

    
    
}