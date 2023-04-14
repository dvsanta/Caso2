public class Edad {
    public int[] page_ages;

    public Edad(int[] page_ages) {
        this.page_ages = page_ages;
    }

    public synchronized void sumarEdad(int indice) {
        page_ages[indice] = page_ages[indice] + 1;
    }

    public synchronized int darPaginaMasVieja() {
        int indice = 0;
        for (int i = 0; i < page_ages.length; i++) {
            if (page_ages[i] < page_ages[indice]) {
                indice = i;
            }
        }

        return indice;
    }

    public synchronized void reiniciarEdad(int indice) {
        page_ages[indice] = 0;
    }
        
    }