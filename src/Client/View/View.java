package Client.View;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Client side view class
 */
public class View extends JFrame {
    private JTextField firstString = new JTextField(10);
    private JLabel addLabel = new JLabel ("+");
    private JLabel resultLabel = new JLabel("Result:");
    private JTextField secondString = new JTextField(10);
    private JButton concatButton = new JButton ("Concatenate");
    private JButton toUpperCase = new JButton("Uppercase");
    private JButton toLowerCase = new JButton("Lowercase");
    private JTextField result = new JTextField(20);

    /**
     * Constructor
     */
    public View(){
        JPanel calcPanel = new JPanel ();
        this.setSize(350, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcPanel.add(firstString);
        calcPanel.add(addLabel);
        calcPanel.add(secondString);
        calcPanel.add(concatButton);
        calcPanel.add(toUpperCase);
        calcPanel.add(toLowerCase);
        calcPanel.add(resultLabel);
        calcPanel.add(result);

        this.add(calcPanel);
    }

    /**
     * Getters and setters
     * @return
     */
    public String getFirstString ()
    {
        return firstString.getText();
    }
    public String getSecString ()
    {
        return secondString.getText();
    }
    public void setResult (String res)
    {
        result.setText(res);
    }

    /**
     * Helper methods
     * @param listenForConcatButton
     */
    public void addStringListener (ActionListener listenForConcatButton)
    {
        concatButton.addActionListener(listenForConcatButton);
    }

    public void addUpperCaseListener(ActionListener listenForUpperCase){
        toUpperCase.addActionListener(listenForUpperCase);
    }

    public void addLowerCaseListener(ActionListener listenForLowerCase){
        toLowerCase.addActionListener(listenForLowerCase);
    }

    public void displayErrorMessage (String errorMessage)
    {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}
