package Client.Controller;
import Client.View.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class responsible for View action listeners
 */
public class ViewController {

    private View theView;
    private ClientController client;

    public ViewController (View v,ClientController c)
    {
        theView = v;
        this.client = c;
        theView.addStringListener(new StringListener());
        theView.addUpperCaseListener(new UpperCaseListener());
        theView.addLowerCaseListener(new LowerCaseListener());
    }

    /**
     * Concat button listener sends choice 1 to the server
     */
    class StringListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            client.sendWords(1);
        }
    }

    /**
     * Upper case listener sends choice 2 to the server
     */
    class UpperCaseListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            client.sendWords(2);
        }
    }

    /**
     * Lower case listener sends choice 3 to the server
     */
    class LowerCaseListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            client.sendWords(3);
        }
    }
}
