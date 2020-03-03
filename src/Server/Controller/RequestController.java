package Server.Controller;
import Server.Model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Server side request Controller. This class implements runnable
 * as a separate instance of this class is created per client.
 * This class also constructs the Model.
 * This class is responsible for taking the requests and data sent over by the client
 */
public class RequestController implements Runnable {
    private Socket aSocket;
    private PrintWriter aSocketOut;
    private BufferedReader aSocketIn;
    private Model theModel;

    /**
     * Constructor
     * @param socket
     * @throws IOException
     */
    public RequestController(Socket socket) throws IOException {
        this.aSocket = socket;
        theModel = new Model();
        aSocketOut = new PrintWriter(aSocket.getOutputStream(),true);
        aSocketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
    }

    /**
     * Gets client data and performs relevant actions
     */
    @Override
    public void run() {
        try {
            getClientData();
        } catch (IOException e) {
            //e.printStackTrace();
            //Close socket connections if this exception occurs
            System.out.println("Closing client connection..");
            aSocketOut.close();
            try {
                aSocketIn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                aSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Client data is of the format: choice + "," + firstString + "," + secondString
     * @throws IOException
     */
    public void getClientData() throws IOException {
        while(true){
            String clientData = "";
            clientData = aSocketIn.readLine();
            if(clientData != null){
                String [] temp = clientData.split(",");
                //Call switchBoard with choice and the 2 words passed by the client
                if(temp.length != 0){
                    if(temp.length == 3){
                        switchBoard(Integer.parseInt(temp[0]),temp[1],temp[2]);
                    }
                    /*
                    The server methods should work with just 1 word as well.
                     */
                    else if(temp.length == 2){
                        switchBoard(Integer.parseInt(temp[0]),temp[1],"");
                    }
                }
            }
        }
    }

    /**
     * Switch Board calls the required method within model
     * @param choice
     * @param firstString
     * @param secString
     */
    public void switchBoard(int choice,String firstString, String secString){
        switch (choice){
            //Simple concatination
            case 1:
                sendServerResponse(theModel.concatenate(firstString,secString));
                break;
            //Concatination and to Upper case
            case 2:
                sendServerResponse(theModel.toUpperCase(firstString,secString));
                break;
            //Concatination and to lower case
            case 3:
                sendServerResponse(theModel.tolowerCase(firstString,secString));
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    /**
     * Helper method for sending serverResponse
     * @param serverResponse
     */
    public void sendServerResponse(String serverResponse){
        System.out.println("Sending server response: " + serverResponse);
        aSocketOut.println(serverResponse);
        aSocketOut.flush();
    }
}
