package threadPooledServer;

/**
 * Maakt een nieuwe multithreaded server aan.
 */
public class ThreadPooledServerRunner {
    public static ThreadPooledServer  server = new ThreadPooledServer();
    public static boolean isRunning;

    public static void main(String[] args) throws Exception {
        new Thread(server).start();
        isRunning=true;
    }
}