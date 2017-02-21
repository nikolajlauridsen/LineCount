package LineCount.GUI;


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
    private JFileChooser fileChooser = new JFileChooser();
    private File projectDir;
    private JList<String> dirList = new JList<>();
    private JScrollPane dirListScroller = new JScrollPane(dirList);
    private Path[] files;
    private ProjectSelector fileSelector = new ProjectSelector();

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


        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        openChooser.setText("Choose dir");
        openChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == openChooser){
                    int returnVal = fileChooser.showOpenDialog(ProjectPanel.this);

                    if(returnVal == JFileChooser.APPROVE_OPTION){
                        projectDir = fileChooser.getSelectedFile();
                        System.out.println(projectDir.toString());
                        folderPath.setText(projectDir.getPath());
                        fillDirList();
                    }
                }
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        this.add(openChooser, c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        this.add(fileSelector, c);

    }

    private void fillDirList(){
        loadDir(folderPath.getText());
        for (Path path: this.files){
            if (path.toString().length() > 2) {
                fileSelector.addPath(path.toString());
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
