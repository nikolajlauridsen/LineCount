package LineCount.GUI.Windows;

import LineCount.FileOperations.Files.CodeFile;
import LineCount.FileOperations.Files.ReportFile;
import LineCount.FileOperations.Parsing.ParserChooser;
import LineCount.GUI.Elements.FileTable;
import LineCount.Utils.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static LineCount.FileOperations.Utils.FileHelp.getCodeFiles;
import static LineCount.GUI.BoxHelp.*;

/**
 * The Project report frame, holds a jpanel displaying statistics about the code project
 */
class ProjectReport extends JFrame{

    private CodeFile[] files;

    /**
     * @param files Path array of paths for project files
     * @param root Path object of the root directory of the project
     */
    ProjectReport(Path[] files, Path root, ParserChooser parsers){
        this.setTitle("Project Report");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.files = getCodeFiles(files, root, parsers);

        this.add(new ReportPanel());
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(Config.IMAGE_NAME)));
    }


    class ReportPanel extends JPanel{
        FileTable fileOverview;
        private String[] saveOptions = {"Markdown", "Plaintext"};
        private JComboBox saveChooser;

        ReportPanel(){
            try {init();} catch (Exception e){
                e.printStackTrace();
            }
        }

        private void init() throws Exception{
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            this.saveChooser = new JComboBox(saveOptions);
            this.saveChooser.setMaximumSize( this.saveChooser.getPreferredSize() );
            fileOverview = new FileTable(files);
            JLabel title = new JLabel("Project report");
            title.setFont(new Font(title.getFont().getFontName(), Font.PLAIN, 16));

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

            JButton save = new JButton("Save");

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ReportFile reportFile = new ReportFile(files);
                    try {
                        if (saveChooser.getSelectedIndex() == 0){
                            reportFile.saveMarkDownReport(Paths.get(files[0].getRootDir(), "ProjectReport.md"));
                        } else {
                            reportFile.saveTxtReport(Paths.get(files[0].getRootDir(), "ProjectReport.txt"));
                        }

                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });

            // Set alignment on the buttons
            save.setAlignmentX(Component.CENTER_ALIGNMENT);
            Box buttonBox = Box.createHorizontalBox();
            buttonBox.add(save);
            buttonBox.add(padX(this.saveChooser, 20));

            this.add(Box.createRigidArea(new Dimension(0, 10)));
            this.add(title);
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(leftJustify(pathLabel, 10));
            this.add(leftJustify(fileCountLabel, 10));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(leftJustify(tableTitle, 5));
            this.add(fileOverview);
            this.add(buttonBox);
            this.add(Box.createRigidArea(new Dimension(0, 10)));
        }

    }

}
