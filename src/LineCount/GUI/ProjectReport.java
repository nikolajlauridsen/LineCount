package LineCount.GUI;

import LineCount.FileOperations.CodeFile;

import javax.swing.*;

public class ProjectReport extends JFrame{

    private CodeFile[] files;
    private TablePanel table;

    ProjectReport(CodeFile[] _files){
        this.setTitle("Project Report");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 1000);
        this.setVisible(true);
        this.setResizable(false);
        this.files = _files;
        table = new TablePanel(this.files);
        this.add(table);
    }

}
