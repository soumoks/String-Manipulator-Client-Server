package Client.Controller;
import Client.View.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Controller class responsible for socket communication
 */
public class ClientController {
    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private View theView;
    private Boolean isConnected = false;

    /**
     * Constructor
     * @param serverName
     * @param portNumber
     * @param theView
     */
    public ClientController(String serverName, int portNumber, View theView) {
        this.theView = theView;
        try {
            aSocket = new Socket(serverName, portNumber);
            System.out.println("Connected to server..");
            isConnected = true;
            // Socket input Stream
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));

            // Socket output Stream
            socketOut = new PrintWriter(aSocket.getOutputStream(), true);
        } catch (IOException e) {
            theView.setResult("Not able to connect to server!");
            System.out.println("Not able to connect to server");
        }
    }

    /**
     * Method to be called from ViewController on button press.
     * Client data is of the format: choice + "," + firstString + "," + secondString
     * @return
     */
    public void sendWords(int choice){

        String clientMessage = choice + "," + theView.getFirstString() + "," + theView.getSecString();

        //Send words to server only if connected
        if(isConnected){
            if (clientMessage != null && !clientMessage.isEmpty() && (!theView.getFirstString().isEmpty() || !theView.getSecString().isEmpty())) {
                System.out.println("Sending words: " + theView.getFirstString() + "," + theView.getSecString());
                socketOut.println(clientMessage);
                socketOut.flush();
            }
            else {
                theView.displayErrorMessage("Please enter words!");
            }
        }
        else{
            theView.displayErrorMessage("Not able to connect to server!");
        }
    }

    /**
     * Constantly listening for server response
     */
    public void getServerResponse(){
        while(isConnected){
            String response = "";
            try {
                response = socketIn.readLine();
                System.out.println("Server response is: " + response);
            }catch(SocketException e){
                System.out.println("Socket exception!");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if(response != null){
                theView.setResult(response);
            }
        }
    }
}
