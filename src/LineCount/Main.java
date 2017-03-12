package LineCount;

import LineCount.GUI.ProjectPanel;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame projectManager = new JFrame("Project manager");
        projectManager.add(new ProjectPanel());

        projectManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        projectManager.setSize(650, 300);
        projectManager.setResizable(false);
        projectManager.setVisible(true);
    }
}
