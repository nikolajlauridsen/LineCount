package LineCount.GUI;


import LineCount.FileOperations.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectPanel extends JPanel {
    private JLabel titleLabel = new JLabel();
    private JTextField folderPath = new JTextField();
    private JButton openChooser = new JButton();
    private JFileChooser folderChooser = new JFileChooser();
    private File projectDir;
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

    private void Init() throws Exception{
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        titleLabel.setText("Project Manager");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(titleLabel, c);
        c.gridwidth = 1;

        folderPath.setText("Choose folder");
        folderPath.setColumns(35);
        c.gridx = 0;
        c.gridy = 1;
        this.add(folderPath, c);


        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        openChooser.setText("Choose dir");
        openChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == openChooser){
                    int returnVal = folderChooser.showOpenDialog(ProjectPanel.this);

                    if(returnVal == JFileChooser.APPROVE_OPTION){
                        projectDir = folderChooser.getSelectedFile();
                        System.out.println(projectDir.toString());
                        folderPath.setText(projectDir.getPath());
                        fillDirList();
                    }
                }
            }
        });

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Path[] selectedPaths = filePicker.getSelectedPaths(folderPath.getText());
                Path root = Paths.get(projectDir.getPath());
                new ProjectReport(selectedPaths, root);
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        this.add(openChooser, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        this.add(filePicker, c);

        c.gridy = 3;
        c.gridwidth = 2;
        this.add(generateButton, c);

    }

    private void fillDirList(){
        filePicker.emptyLists();
        this.filePaths = fileHandler.walkDir(folderPath.getText());
        Path projectPath =  Paths.get(folderPath.getText());
        for (Path path: this.filePaths){
            if (path.toString().length() > 2) {
                filePicker.addPath(projectPath.relativize(path).toString());
            }
        }

    }

}
