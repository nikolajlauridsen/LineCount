package LineCount.GUI.Windows;

import LineCount.FileOperations.Files.CodeFile;
import LineCount.FileOperations.Files.ReportFile;
import LineCount.FileOperations.Parsing.ParserChooser;
import LineCount.GUI.Elements.ErrorPanel;
import LineCount.GUI.Elements.FileTable;
import LineCount.Utils.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static LineCount.FileOperations.Utils.FileHelp.getCodeFiles;
import static LineCount.GUI.BoxHelp.*;

/**
 * The Project report frame, holds a JPanel displaying statistics about the code project
 */
class ProjectReport extends JFrame{

    private final CodeFile[] files;

    /**
     * Constructor for the ProjectReport Frame (aka window)
     * The constructor initiate the window and configures it as necessary,
     * and then creates and loads a ReportPanel object containing all the GUI elements.
     * @param files Path array of files to generate report upon
     * @param root The root directory the files originate from
     *             (Used to generate relative path)
     * @param parsers ParserChooser loaded with Parsers to match the files against
     */
    ProjectReport(Path[] files, Path root, ParserChooser parsers){
        this.setTitle("Project Report");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setResizable(false);
        // Read files from frame constructor since passing the arguments to ProjectReport
        // only to pass hem on to ProjectPanel seems backwards, also this way the frame is
        // hidden until everything is done processing
        this.files = getCodeFiles(files, root, parsers);

        try {
            this.add(new ReportPanel());
        } catch (ArrayIndexOutOfBoundsException e){
            this.add(new ErrorPanel("Please add one or more files."));
        }
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(Config.IMAGE_NAME)));
        // Hide the frame till the very end
        this.setVisible(true);
    }


    /**
     * JPanel containing all the GUI elements, and associated logic,
     * for the ProjectReport window
     */
    class ReportPanel extends JPanel{
        private final String[] saveOptions = {"Markdown", "Plaintext"};
        private JComboBox formatPicker;

        /**
         * Calls the initializer method in a try block
         * since errors might occur
         */
        ReportPanel() throws ArrayIndexOutOfBoundsException{
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            // --------- Create the GUI objects ---------

            // Big title so the user know's what it is
            JLabel title = new JLabel("Project report");
            title.setFont(new Font(title.getFont().getFontName(), Font.PLAIN, 16));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Show root dir of the project
            JLabel pathLabel = new JLabel();
            if (files.length > 0) {
                // Assuming all files are from the same root dir, so just get the path from the first
                pathLabel.setText("Project path: " + files[0].getRootDir());
            } else {
                pathLabel.setText("Please choose a file in the project manager");
            }

            // Show the amount of files
            JLabel fileCountLabel = new JLabel("Number of files: " + files.length);

            // Create a FileTable to display the files
            JLabel tableTitle = new JLabel("File overview");
            FileTable fileTable = new FileTable(files);
            fileTable.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Save button
            JButton save = new JButton("Save");
            save.addActionListener(new saveListener());
            save.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Create a drop down where the user can choose export format
            this.formatPicker = new JComboBox(saveOptions);
            // We don't want the comboBox to stretch, it looks hideous
            this.formatPicker.setMaximumSize( this.formatPicker.getPreferredSize() );

            // Button box holding both button and comboBox so they can be side by side
            Box buttonBox = Box.createHorizontalBox();
            buttonBox.add(save);
            buttonBox.add(padX(this.formatPicker, 20));

            // ------- Add objects to parent panel ------
            // and some padding as well to make it nice (RigidArea)
            Dimension smallPadding = new Dimension(0, 10);
            Dimension largePadding = new Dimension(0, 20);

            this.add(Box.createRigidArea(smallPadding));
            this.add(title);
            this.add(Box.createRigidArea(largePadding));
            this.add(leftJustify(pathLabel, 10));
            this.add(leftJustify(fileCountLabel, 10));
            this.add(Box.createRigidArea(largePadding));
            this.add(leftJustify(tableTitle, 5));
            this.add(fileTable);
            this.add(buttonBox);
            this.add(Box.createRigidArea(smallPadding));

        }

        /**
         * Listen to the save button
         */
        class saveListener implements ActionListener{
            public void actionPerformed(ActionEvent e){
                ReportFile reportFile = new ReportFile(files);
                try {
                    if (formatPicker.getSelectedIndex() == 0){
                        reportFile.saveMarkDownReport(Paths.get(files[0].getRootDir(), "ProjectReport.md"));
                    } else {
                        reportFile.saveTxtReport(Paths.get(files[0].getRootDir(), "ProjectReport.txt"));
                    }

                } catch (IOException exc){
                    // Display the error to the user
                    new ErrorFrame(exc);
                }
            }
        }
    }

}
