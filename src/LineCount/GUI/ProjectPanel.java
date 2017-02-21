package LineCount.GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ProjectPanel extends JPanel {
    JLabel titleLabel = new JLabel();
    JTextField folderPath = new JTextField();
    JButton openChooser = new JButton();
    JFileChooser fileChooser = new JFileChooser();
    File projectDir;
    JList<String> dirList = new JList();

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
                    }
                }
            }
        });
        c.gridx = 1;
        c.gridy = 1;
        this.add(openChooser, c);

    }

}
