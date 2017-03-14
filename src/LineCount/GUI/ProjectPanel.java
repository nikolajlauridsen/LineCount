package LineCount.GUI;


import LineCount.FileOperations.Utils.FileFilter;
import LineCount.FileOperations.Utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectPanel extends JPanel {
    private JLabel titleLabel = new JLabel();
    private JTextField folderField = new JTextField();
    private JButton openChooser = new JButton();
    private JFileChooser folderChooser = new JFileChooser();
    private Path projectDir;
    private Path[] filePaths;
    private FilePicker filePicker = new FilePicker();
    private FileHandler fileHandler = new FileHandler();

    public ProjectPanel(){
        try{
            Init();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Initialize the GUI
     * @throws Exception
     */
    private void Init() throws Exception{
        // Set layout to GridBag and create constraints
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Configure and add the title
        titleLabel.setText("Project Manager");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(titleLabel, c);

        // Then folderfield
        c.gridwidth = 1;
        folderField.setText("Choose folder");
        folderField.setEditable(false);
        folderField.setColumns(35);
        c.gridx = 0;
        c.gridy = 1;
        this.add(folderField, c);


        // Then the actual file chooser
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        openChooser.setText("Choose dir");
        openChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == openChooser){
                    // returnVal is used to check if a folder is actually chosen
                    int returnVal = folderChooser.showOpenDialog(ProjectPanel.this);

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
                new ProjectReport(selectedPaths, projectDir);
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        this.add(openChooser, c);

        // Add file picker (configured when object is initialized)
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        this.add(filePicker, c);

        // Same goes for the generate button
        c.gridy = 3;
        c.gridwidth = 2;
        this.add(generateButton, c);

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
        Path ignorefile = this.projectDir.resolve(".gitignore");
        FileFilter ignoreFiles = new FileFilter();
        try {
            ignoreFiles.ParseIgnoreFile(ignorefile);
        } catch (IOException e){
            System.out.println("gitignore not found");
        }

        this.filePaths = fileHandler.walkDir(folderField.getText(), ignoreFiles);
        // Add each path to the filePicker if it's longer than 2 characters
        for (Path path: this.filePaths){
            if (path.toString().length() > 2) {
                filePicker.addPath(projectDir.relativize(path).toString());
            }
        }

    }

}
