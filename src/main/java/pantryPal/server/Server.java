package pantryPal.server;
import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.*;


public class Server {
    // initialize server port and hostname
    // private static final int SERVER_PORT = 8100;
    // private static final String SERVER_HOSTNAME = "localhost";
    // ./ngrok http 8100  --authtoken 2Z9QcTokIzfLVfmOLrOz5fMiSgo_4wK6J7Yqhxbzz8BcmnrQt

    private static final String NGROK_SUBDOMAIN = "https://554b-128-54-10-106.ngrok-free.app/";
    private static final int NGROK_PORT = 8100;

    public static String getDomain() {
        return NGROK_SUBDOMAIN;
    }
    public static void main(String[] args) throws IOException {
        // create a thread pool to handle requests
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        // create a server
        // HttpServer server = HttpServer.create(
        //     new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
        //     0
        // );

        HttpServer server = HttpServer.create(
            new InetSocketAddress("0.0.0.0", NGROK_PORT),
            0
        );

        server.createContext("/", new Handler()); // Http context
        server.setExecutor(threadPoolExecutor);
        server.start();

        System.out.println("Server started on port " + NGROK_PORT);
    }
}