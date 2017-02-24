package LineCount.GUI;

import LineCount.FileOperations.CodeFile;

import javax.swing.*;

class ProjectReport extends JFrame{

    private CodeFile[] files;

    ProjectReport(CodeFile[] _files){
        this.setTitle("Project Report");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 1000);
        this.setVisible(true);
        this.setResizable(false);
        this.files = _files;
        this.add(new ReportPanel());
    }

    class ReportPanel extends JPanel{

        JLabel title = new JLabel("Project report");
        TablePanel fileOverview;

        ReportPanel(){
            try {init();} catch (Exception e){
                e.printStackTrace();
            }
        }

        private void init() throws Exception{
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setAlignmentX(CENTER_ALIGNMENT);

            fileOverview = new TablePanel(files);

            this.add(title);
            this.add(fileOverview);
        }
    }

}
