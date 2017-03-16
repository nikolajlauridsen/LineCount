package LineCount.GUI;

import LineCount.FileOperations.Files.CodeFile;
import LineCount.FileOperations.Files.ReportFile;
import LineCount.FileOperations.Parsing.ParserChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static LineCount.FileOperations.Utils.FileOps.getCodeFiles;

/**
 * The Project report frame, holds a jpanel displaying statistics about the code project
 */
class ProjectReport extends JFrame{

    private CodeFile[] files;

    /**
     *
     * @param _files Path array of paths for project files
     * @param root Path object of the root directory of the project
     */
    ProjectReport(Path[] _files, Path root, ParserChooser parsers){
        this.setTitle("Project Report");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setVisible(true);
        this.setResizable(false);

        this.files = getCodeFiles(_files, root, parsers);

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

            JButton saveMD = new JButton("Save .md");

            saveMD.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ReportFile reportFile = new ReportFile(files);
                    try {
                        reportFile.saveMarkDownReport(Paths.get(files[0].getRootDir(), "ProjectReport.md"));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });

            JButton saveTXT = new JButton("Save .txt");

            saveTXT.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ReportFile reportFile = new ReportFile(files);
                    try{
                        reportFile.saveTxtReport(Paths.get(files[0].getRootDir(), "ProjectReport.txt"));
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });

            this.add(Box.createRigidArea(new Dimension(0, 5)));
            this.add(title);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(leftJustify(pathLabel, 10));
            this.add(leftJustify(fileCountLabel, 10));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(leftJustify(tableTitle, 5));
            this.add(fileOverview);
            this.add(saveMD);
            this.add(saveTXT);
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
