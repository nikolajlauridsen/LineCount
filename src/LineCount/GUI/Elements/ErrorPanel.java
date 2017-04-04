package LineCount.GUI.Elements;

import LineCount.Utils.Config;

import javax.swing.*;

import java.awt.*;
import java.util.StringTokenizer;

import static LineCount.GUI.BoxHelp.centerJustify;
import static LineCount.Utils.StringHelp.errorToString;

/**
 * Panel for displaying errors
 */
public class ErrorPanel extends JPanel {

    /**
     * Display a custom message and no StackTrace().
     * Is compatible with the newline character \n
     * @param message Error message to display to the user.
     */
    public ErrorPanel(String message){
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createVerticalGlue());

        if(message.contains("\n")){
            StringTokenizer lineSpitter = new StringTokenizer(message, "\n", false);

            while (lineSpitter.hasMoreTokens()){
                JLabel label = new JLabel(lineSpitter.nextToken());
                label.setAlignmentX(CENTER_ALIGNMENT);
                this.add(label);
            }
        } else {
            JLabel label = new JLabel(message);
            label.setAlignmentX(CENTER_ALIGNMENT);
            this.add(centerJustify(label));
        }

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
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        JScrollPane scrollView = new JScrollPane(textArea);

        Dimension padding = new Dimension(0, 10);
        this.add(Box.createRigidArea(padding));
        this.add(centerJustify(new JLabel("An unknown error occurred")));
        this.add(Box.createRigidArea(padding));
        this.add(centerJustify(scrollView));
        this.add(Box.createRigidArea(padding));

        String authorContact = "Please send the above error message to: " + Config.AUTHOR_EMAIL;
        String gitContact = "Or post an issue to: " + Config.PROJECT_HUB;
        this.add(centerJustify(new JLabel(authorContact)));
        this.add(centerJustify(new JLabel(gitContact)));
        this.add(Box.createRigidArea(padding));
    }
}
