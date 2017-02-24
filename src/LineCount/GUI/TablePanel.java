package LineCount.GUI;

import LineCount.FileOperations.CodeFile;

import javax.swing.*;

class TablePanel extends JPanel {
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

    TablePanel(CodeFile[] _files){
        this.files = _files;
        this.columns = columnNames.length;
        try { Init(); } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void Init() throws Exception{
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Object[][] data = new Object[this.files.length+1][];

        // Initialize totals, used for creating the final row
        int total_code = 0;
        int total_comments = 0;
        int total_whitespace = 0;
        int total_lines = 0;

        // Create row for each file
        for(int i = 0; i < this.files.length; i++){
            // Update totals
            total_code += this.files[i].getCodeCount();
            total_comments += this.files[i].getCommentCount();
            total_whitespace += this.files[i].getWhiteSpace();
            total_lines += this.files[i].getLineCount();

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
        total[2] = "Get base path";
        total[3] = total_code;
        total[4] = total_comments;
        total[5] = total_whitespace;
        total[6] = total_lines;
        // Append the total to the end of the data array
        data[this.files.length] = total;

        JTable overview = new JTable(data, this.columnNames);
        JScrollPane pane = new JScrollPane(overview);
        this.add(pane);

    }
}
