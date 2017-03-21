package LineCount;

import LineCount.GUI.ProjectManager;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame projectManager = new JFrame("Project manager");
        projectManager.add(new ProjectManager());

        projectManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projectManager.setSize(900, 465);
        projectManager.setResizable(false);
        projectManager.setVisible(true);
    }
}
