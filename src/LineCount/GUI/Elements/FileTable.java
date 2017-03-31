package LineCount.GUI.Elements;

import LineCount.FileOperations.Files.CodeFile;
import static LineCount.Utils.StringHelp.getPercentageString;

import javax.swing.*;
import java.awt.*;

/**
 * A table displaying data from an array of CodeFiles
 */
public class FileTable extends JPanel {
    private final Object[] columnNames = {
            "Filename",
            "Extension",
            "Path",
            "Code",
            "Comments",
            "Whitespace",
            "Total"
    };

    private final CodeFile[] files;
    private final int columns;

    private int total_code = 0;
    private int total_comments = 0;
    private int total_whitespace = 0;
    private int total_lines = 0;

    /**
     * Create a panel containing a table
     * @param files CodeFiles to generate table for
     */
    public FileTable(CodeFile[] files) throws ArrayIndexOutOfBoundsException{
        // Set values
        this.files = files;
        this.columns = columnNames.length;

        Init();
    }

    /**
     * Initialize the GUI
     */
    private void Init() {

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
            fileData[3] = getPercentageString(this.files[i].getCodeCount(), this.files[i].getLineCount());
            fileData[4] = getPercentageString(this.files[i].getCommentCount(), this.files[i].getLineCount());
            fileData[5] = getPercentageString(this.files[i].getWhiteSpace(), this.files[i].getLineCount());
            fileData[6] = this.files[i].getLineCount();
            data[i] = fileData;
        }
        // Create the total array
        Object[] total = new Object[columns];
        total[0] = "Total";
        total[1] = "N/A";
        total[2] = this.files[0].getRootDir();
        total[3] = getPercentageString(total_code, total_lines);
        total[4] = getPercentageString(total_comments, total_lines);
        total[5] = getPercentageString(total_whitespace, total_lines);
        total[6] = total_lines;
        // Append the total to the end of the data array
        data[this.files.length] = total;

        JTable overview = new JTable(data, this.columnNames){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //This disables table editing
            }
        };

        JScrollPane pane = new JScrollPane(overview);
        pane.setPreferredSize(new Dimension(690, 300));
        this.add(pane);

    }

}
