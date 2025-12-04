public class singleThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread is running!");
    }

    public static void main(String[] args) {
        singleThread obj = new singleThread();
        Thread thread1 = new Thread(obj, "Thread1");
        thread1.start();


        System.out.println("Main thread finished.");
    }
}
