package LineCount.GUI;

import LineCount.FileOperations.Files.CodeFile;

import javax.swing.*;
import java.awt.*;

class FileTable extends JPanel {
    private Object[] columnNames = {
            "Filename",
            "Extension",
            "Path",
            "Code",
            "Comments",
            "Whitespace",
            "Total"
    };
    private CodeFile[] files;
    private int columns;
    private JLabel tableHeading = new JLabel("File overview");

    private int total_code = 0;
    private int total_comments = 0;
    private int total_whitespace = 0;
    private int total_lines = 0;

    FileTable(CodeFile[] _files){
        this.files = _files;
        this.columns = columnNames.length;
        try { Init(); } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Init() throws Exception{

        Object[][] data = new Object[this.files.length+1][];

        // Create row for each file
        for(int i = 0; i < this.files.length; i++){
            // Update totals
            this.total_code += this.files[i].getCodeCount();
            this.total_comments += this.files[i].getCommentCount();
            this.total_whitespace += this.files[i].getWhiteSpace();
            this.total_lines += this.files[i].getLineCount();

            // Create and add the row
            Object[] fileData = new Object[columns];
            fileData[0] = this.files[i].getFileName();
            fileData[1] = this.files[i].getExtension();
            fileData[2] = this.files[i].getRelPath();
            fileData[3] = this.files[i].getCodeCount();
            fileData[4] = this.files[i].getCommentCount();
            fileData[5] = this.files[i].getWhiteSpace();
            fileData[6] = this.files[i].getLineCount();
            data[i] = fileData;
        }
        // Create the total array
        Object[] total = new Object[columns];
        total[0] = "Total";
        total[1] = "N/A";
        total[2] = this.files[0].getRootDir();
        total[3] = total_code;
        total[4] = total_comments;
        total[5] = total_whitespace;
        total[6] = total_lines;
        // Append the total to the end of the data array
        data[this.files.length] = total;

        JTable overview = new JTable(data, this.columnNames);
        JScrollPane pane = new JScrollPane(overview);
        pane.setMaximumSize(new Dimension(700, 300));
        pane.setPreferredSize(new Dimension(690, 200));
        this.add(pane);

    }

    void getTotals(){

    }
}
