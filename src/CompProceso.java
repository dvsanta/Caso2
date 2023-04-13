import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompProceso extends Thread {
    private String filename;
    private int pageSize;
    private int numRows;
    private int numCols;
    private int numReferences;

    public CompProceso(String filename) {
        this.filename = filename;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumReferences() {
        return numReferences;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                if (lineNum == 0) {
                    String[] header = line.split("\\s+");
                    pageSize = Integer.parseInt(header[0]);
                    numRows = Integer.parseInt(header[1]);
                    numCols = Integer.parseInt(header[2]);
                    numReferences = Integer.parseInt(header[3]);
                } else {
                    while (lineNum <= numReferences) {
                        String[] reference = line.split("\\s+");
                        int row = Integer.parseInt(reference[0]);
                        int col = Integer.parseInt(reference[1]);
                        lineNum++;
                    }
                }
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
