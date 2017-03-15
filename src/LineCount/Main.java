package LineCount;

import LineCount.FileOperations.Utils.FileOps;
import LineCount.GUI.ProjectPanel;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        FileOps.readParsers("fileparsers.yml");
        JFrame projectManager = new JFrame("Project manager");
        projectManager.add(new ProjectPanel());

        projectManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projectManager.setSize(650, 300);
        projectManager.setResizable(false);
        projectManager.setVisible(true);
    }
}
