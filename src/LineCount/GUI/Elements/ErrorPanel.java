package LineCount.GUI.Elements;

import javax.swing.*;

import java.awt.*;

import static LineCount.GUI.BoxHelp.centerJustify;
import static LineCount.Utils.StringHelp.errorToString;

/**
 * Panel for displaying errors
 */
public class ErrorPanel extends JPanel {

    /**
     * Display a custom message and no StackTrace().
     * @param message Error message to display to the user.
     */
    public ErrorPanel(String message){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(centerJustify(new JLabel(message)));
        this.add(Box.createVerticalGlue());
    }

    /**
     * Display error in a textfield
     * @param exception Exception to display
     */
    public ErrorPanel(Exception exception){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        JTextArea textArea = new JTextArea();
        textArea.setText(errorToString(exception));
        textArea.setEditable(false);

        JScrollPane scrollView = new JScrollPane(textArea);
        Dimension areaSize = new Dimension(700, 250);
        scrollView.setPreferredSize(areaSize);
        scrollView.setMaximumSize(areaSize);

        this.add(new JLabel("An unknown error occurred"));
        this.add(scrollView);
    }
}
