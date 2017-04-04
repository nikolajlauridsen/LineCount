package LineCount.GUI.Windows;

import LineCount.FileOperations.Parsing.ParserChooser;
import LineCount.FileOperations.Utils.FileFilter;
import LineCount.GUI.Elements.ErrorPanel;
import LineCount.GUI.Elements.FilePicker;
import LineCount.Utils.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static LineCount.FileOperations.Utils.FileHelp.walkDir;
import static LineCount.GUI.BoxHelp.padX;

/**
 * GUI window for choosing files to be counted and then creating a report window
 */
public class ProjectManager extends JFrame{

    /**
     * JFrame containing only a ProjectPanel
     */
    public ProjectManager(){
        this.setTitle("Project Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 465);
        this.setResizable(false);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(Config.IMAGE_NAME)));

        try{
            this.add(new ProjectPanel());
        } catch (Exception e){
            this.add(new ErrorPanel(e));
        }

        this.setVisible(true);
    }

    /**
     * Panel containing all elements in the project manager
     */
    class ProjectPanel extends JPanel {
        private final JLabel titleLabel = new JLabel();
        private final JTextField folderField = new JTextField();
        private final JButton openChooser = new JButton();
        private final JFileChooser folderChooser = new JFileChooser();
        private Path projectDir;
        private Path[] filePaths;
        private final FilePicker filePicker = new FilePicker();
        private ParserChooser parsers;

        /**
         * ProjectPanel constructor
         * @throws Exception if something goes wrong during initiation of the GUI
         */
        ProjectPanel() throws Exception{
            Init();

            try{
                this.parsers = new ParserChooser(Config.FILEPARSER_PATH);
            } catch (FileNotFoundException e){
                String errorString = Config.FILEPARSER_PATH + " not found, please ensure that it's available\n" +
                        "Without it, LineCount cannot read comments and all files will have 0 comments\n" +
                        "If you've lost the file and need to acquire a new one visit:\n" + Config.PROJECT_HUB;
                new ErrorFrame(errorString);
            }
        }

        /**
         * Initialize the GUI
         */
        private void Init() {
            // Set layout to GridBag and create constraints
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Create choose dir and generate button
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            openChooser.setText("Choose dir");
            openChooser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (actionEvent.getSource() == openChooser){
                        // returnVal is used to check if a folder is actually chosen
                        int returnVal = folderChooser.showOpenDialog(ProjectManager.ProjectPanel.this);

                        // If there indeed has been a selection
                        if(returnVal == JFileChooser.APPROVE_OPTION){
                            // Get the selected file
                            projectDir = Paths.get(folderChooser.getSelectedFile().getPath());
                            System.out.println(projectDir.toString());
                            // Set the folderfield text
                            folderField.setText(projectDir.toString());
                            // Fil the dir list now that projectDir has been updated
                            fillDirList();
                        }
                    }
                }
            });

            // And the button for opening the file chooser
            JButton generateButton = new JButton("Generate");
            generateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    Path[] selectedPaths = filePicker.getSelectedPaths(folderField.getText());
                    new ProjectReport(selectedPaths, projectDir, parsers);
                }
            });

            // Configure and add the title
            titleLabel.setText("Project Manager");
            titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), Font.PLAIN, 16));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(0, 10)));
            this.add(titleLabel);

            this.add(Box.createRigidArea(new Dimension(0, 5)));

            // Add file picker (configured when object is initialized)
            this.add(filePicker);

            this.add(Box.createRigidArea(new Dimension(0, 10)));

            // Then folderfield
            folderField.setText("Choose folder");
            folderField.setEditable(false);
            this.add(padX(folderField, 17));

            this.add(Box.createRigidArea(new Dimension(0, 10)));

            // Create panel for buttons
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.add(Box.createHorizontalGlue());
            panel.add(openChooser);
            panel.add(Box.createRigidArea(new Dimension(30, 0)));
            panel.add(generateButton);
            panel.add(Box.createHorizontalGlue());

            this.add(panel);
            this.add(Box.createRigidArea(new Dimension(0, 10)));

        }

        /**
         * Fill the dir list with files from the selected folder.
         * Clears both folder list and project list and then
         * refills the folder list with the new files
         */
        private void fillDirList(){
            filePicker.emptyLists();    // Empty the lists
            // Refresh the filePaths array  with the items from the selected folder
            // Create file filter
            Path ignoreFile = this.projectDir.resolve(".gitignore");
            FileFilter fileFilter = new FileFilter();
            try {
                fileFilter.ParseIgnoreFile(ignoreFile);
            } catch (IOException e){
                System.out.println("gitignore not found");
            }

            this.filePaths = walkDir(folderField.getText(), fileFilter);
            // Add each path to the filePicker if it's longer than 2 characters
            for (Path path: this.filePaths){
                if (path.toString().length() > 2) {
                    filePicker.addPath(projectDir.relativize(path).toString());
                }
            }

        }

    }

}
