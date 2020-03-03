package Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class.
 * This class is responsible for initializing the server.
 * The Server runs on localhost on port 9090
 */
public class Server {
    private Socket aSocket;
    private ServerSocket serverSocket;
    private ExecutorService pool;

    /**
     * Constructor
     */
    public Server() {
        try {
            // Server socket accepts the port as a parameter
            serverSocket = new ServerSocket(9090);
            System.out.println("Server is running!");
            pool = Executors.newFixedThreadPool(20);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the server and initiates a new RequestController Object
     */
    public void runServer() {
        try {
            while (true) {
                aSocket = serverSocket.accept();
                System.out.println("Accepted connection..");
                RequestController requestController = new RequestController(aSocket);
                pool.execute(requestController);
            }

        }catch (SocketException e) {
            System.out.println("Socket exception!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
