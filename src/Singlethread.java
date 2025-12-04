import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Singlethread implements Runnable {

    // Use a constant for the file name, assuming you want to read 'book2s.txt'
    private static final String FILE_NAME = "books2.txt";

    @Override
    public void run() {
        // Use try-with-resources for automatic closing of the stream
        try (
                // FIX 1: Use ClassLoader to read the file from the classpath
                InputStream is = Singlethread.class.getClassLoader().getResourceAsStream(FILE_NAME);
                BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            if (is == null) {
                // Throw an exception if the file cannot be found in the classpath
                throw new RuntimeException("Error: Could not find resource file: " + FILE_NAME + ". Ensure it is in the src folder.");
            }

            String line;
            while ((line = br.readLine()) != null) {
                // Reading the file line by line
            }
            System.out.println("Thread1 finished reading " + FILE_NAME);

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + FILE_NAME, e);
        }
    }

    public static void main(String[] args) {
        Singlethread obj = new Singlethread();
        long startTime = System.nanoTime();
        Thread thread1 = new Thread(obj, "Thread1");

        thread1.start();

        // FIX 2: Use join() to wait for the worker thread to finish before measuring time
        try {
            thread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting for join.", e);
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime)/1_000_000;
        System.out.println("Time taken: " + durationMs + " ms");

        System.out.println("Main thread finished.");
    }
}