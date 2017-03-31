package LineCount.GUI.Elements;

import javax.swing.*;

/**
 * Panel for displaying errors
 */
public class ErrorPanel extends JPanel {

    /**
     * Display a custom message and no traceback.
     * @param message Error message to display to the user.
     */
    public ErrorPanel(String message){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(new JLabel(message));
        this.add(Box.createVerticalGlue());
    }
}
