package LineCount.GUI;

import LineCount.FileOperations.CodeFile;

import javax.swing.*;
import java.awt.*;

class ProjectReport extends JFrame{

    private CodeFile[] files;

    ProjectReport(CodeFile[] _files){
        this.setTitle("Project Report");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.files = _files;
        this.add(new ReportPanel());
    }

    class ReportPanel extends JPanel{
        FileTable fileOverview;

        ReportPanel(){
            try {init();} catch (Exception e){
                e.printStackTrace();
            }
        }

        private void init() throws Exception{
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            fileOverview = new FileTable(files);
            JLabel title = new JLabel("Project report");

            JLabel pathLabel = new JLabel();
            if (files.length > 0) {
                pathLabel.setText("Project path: " + files[0].getRootDir());
            } else {
                pathLabel.setText("Please choose a file in the project manager");
            }

            JLabel fileCountLabel = new JLabel("Number of files: " + files.length);

            JLabel tableTitle = new JLabel("File overview");
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            fileOverview.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(Box.createRigidArea(new Dimension(0, 5)));
            this.add(title);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(leftJustify(pathLabel, 10));
            this.add(leftJustify(fileCountLabel, 10));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(leftJustify(tableTitle, 5));
            this.add(fileOverview);
        }

        private Component leftJustify(Component panel){
            Box box = Box.createHorizontalBox();
            box.add(panel);
            box.add(Box.createHorizontalGlue());
            return box;
        }

        private Component leftJustify(Component panel, int padding){
            Box box = Box.createHorizontalBox();
            box.add(Box.createRigidArea(new Dimension(padding, 0)));
            box.add(panel);
            box.add(Box.createHorizontalGlue());
            return box;
        }
    }

}
