package Server;
import Server.Controller.Server;

/**
 * Main application class on server
 */
public class ServerApp {
    public static void main(String [] args){
        Server theServer = new Server();
        theServer.runServer();
    }
}
