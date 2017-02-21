package LineCount;

import LineCount.GUI.ProjectPanel;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame projectManager = new JFrame("Project manager");
        projectManager.add(new ProjectPanel());

        projectManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        projectManager.setSize(720, 300);
        projectManager.setVisible(true);
    }
}
