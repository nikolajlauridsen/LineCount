package LineCount.GUI;


import LineCount.FileOperations.CodeFile;
import LineCount.FileOperations.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ProjectPanel extends JPanel {
    private JLabel titleLabel = new JLabel();
    private JTextField folderPath = new JTextField();
    private JButton openChooser = new JButton();
    private JFileChooser folderChooser = new JFileChooser();
    private File projectDir;
    private Path[] files;
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
                CodeFile[] files = fileHandler.readFiles(selectedPaths);
                for (CodeFile file : files){
                    System.out.println(file.getPath());
                }

                // TODO: remove this debug feature
                int total_lines = 0;
                int total_comments = 0;
                int total_whitespace = 0;
                for (CodeFile file: files){
                    System.out.println("Path: " + file.getPath());
                    System.out.println("Filename: " + file.getFileName());
                    System.out.println("Extension: "  + file.getExtension());
                    System.out.println("Line count: " + file.getLineCount());
                    System.out.println("Comments count: " + file.getCommentCount());
                    System.out.println("Whitespace count: " + file.getWhiteSpace());
                    System.out.println("Lines minus comments & whitespace: " +
                            (file.getLineCount()-file.getCommentCount()-file.getWhiteSpace()));
                    System.out.println("\n");
                    total_lines += file.getLineCount();
                    total_comments += file.getCommentCount();
                    total_whitespace += file.getWhiteSpace();
                }

                System.out.println("Total lines: " + total_lines);
                System.out.println("Total comments: " + total_comments);
                System.out.println("Total whitespace: " + total_whitespace);
                System.out.println("Total lines minus comments & whitespace: " +
                        (total_lines - total_comments - total_whitespace));
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
        loadDir(folderPath.getText());
        Path projectPath =  Paths.get(folderPath.getText());
        for (Path path: this.files){
            if (path.toString().length() > 2) {
                filePicker.addPath(projectPath.relativize(path).toString());
            }
        }

    }

    private void loadDir(String path){
        ArrayList<Path> tmpFiles = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    tmpFiles.add(filePath);
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        this.files = tmpFiles.toArray(new Path[0]);
    }


}
