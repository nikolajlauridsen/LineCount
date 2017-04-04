package LineCount.GUI.Windows;

import LineCount.GUI.Elements.ErrorPanel;

import javax.swing.*;

// TODO: Add custom icon

/**
 * Frame for displaying an error, creates a JFrame with an ErrorPanel
 */
public class ErrorFrame extends JFrame {

    /**
     * Create a frame displaying a custom message
     * Is compatible with the new line sign \n
     * @param message Message to display
     */
    public ErrorFrame(String message){
        this.setTitle("An error occurred");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new ErrorPanel(message));
        this.setSize(500, 150);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Create an ErrorFrame for an unkown error
     * Displays stacktrace and an email to send it to
     * as well as a github repository link
     * @param error Exception to display stacktrace from
     */
    public ErrorFrame(Exception error){
        this.setTitle("And unknown error occurred");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(new ErrorPanel(error));
        this.setSize(700, 500);
        this.setResizable(false);
        this.setVisible(true);
    }
}
