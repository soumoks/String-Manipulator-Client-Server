package Client;
import Client.Controller.ViewController;
import Client.View.View;
import Client.Controller.ClientController;

/**
 * Main application class on client
 */
public class ClientApp {
    public static void main(String [] args){
        View theView = new View();
        ClientController theController = new ClientController("localhost",9090,theView);
        ViewController viewController = new ViewController(theView,theController);
        theView.setVisible(true);
        theController.getServerResponse();
    }
}
