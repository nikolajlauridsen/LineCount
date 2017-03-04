package LineCount.FileOperations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import static LineCount.Utils.MdHelp.*;

public class Report {

    private CodeFile[] files;

    private String title = "Project Report";
    private String tableTitle = "File Overview";
    private String nFilesString = "Number of Files: ";

    private int total_code = 0;
    private int total_comments = 0;
    private int total_whitespace = 0;
    private int total_lines  = 0;

    /**
     * A report object representing a report to be saved to the harddrive
     * @param _files an array of codefiles to generate the report on.
     */
    public Report(CodeFile[] _files){
        this.files = _files;

        for (CodeFile file : this.files){
            total_code += file.getCodeCount();
            total_comments += file.getCommentCount();
            total_whitespace += file.getWhiteSpace();
            total_lines += file.getLineCount();
        }
    }

    /**
     * Save the report in the markdown format
     * @param path desired path for the resulting file, should have a .md extension
     * @throws IOException
     */
    public void saveMarkDownReport(Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");

        nFilesString += files.length;
        String projectFolder = "\nProject folder: " + files[0].getRootDir();

        String[] breakdownTitles = new String[4];
        breakdownTitles[0] = "Code";
        breakdownTitles[1] = "Comments";
        breakdownTitles[2] = "Whitespace";
        breakdownTitles[3] = "Total";

        String[] breakdownList = new String[4];
        breakdownList[0] = Integer.toString(total_code);
        breakdownList[1] = Integer.toString(total_comments);
        breakdownList[2] = Integer.toString(total_whitespace);
        breakdownList[3] = Integer.toString(total_lines);

        Object[][] breakdown = new Object[1][];
        breakdown[0] = breakdownList;
        // the fileBreakdown for the table needs to be one longer than files
        // due to the total column which is appended to the end
        String[] columnNames = {
                "Filename",
                "Extension",
                "Path",
                "Code",
                "Comments",
                "Whitespace",
                "Total"
        };
        Object[][] fileBreakdown = new Object[this.files.length+1][];

        // TODO: It might be sensible to create a function creating these column/rows arrays
        for (int i = 0; i < this.files.length; i++){
            String[] row = new String[columnNames.length];
            row[0] = this.files[i].getFileName();
            row[1] = this.files[i].getExtension();
            row[2] = this.files[i].getRelPath();
            row[3] = Integer.toString(this.files[i].getCodeCount());
            row[4] = Integer.toString(this.files[i].getCommentCount());
            row[5] = Integer.toString(this.files[i].getWhiteSpace());
            row[6] = Integer.toString(this.files[i].getLineCount());
            fileBreakdown[i] = row;

        }
        String[] totalRow = new String[columnNames.length];
        totalRow[0] = "Total";
        totalRow[1] = "N/A";
        totalRow[2] = this.files[0].getRootDir();
        totalRow[3] = Integer.toString(total_code);
        totalRow[4] = Integer.toString(total_comments);
        totalRow[5] = Integer.toString(total_whitespace);
        totalRow[6] = Integer.toString(total_lines);
        fileBreakdown[this.files.length] = totalRow;


        try(BufferedWriter writer = Files.newBufferedWriter(path, charset)){
            writer.write(h1(title));
            writer.write("\n" + h2("Project Overview"));
            writer.write(projectFolder);
            writer.write("\n\n");
            writer.write(nFilesString);
            writer.write("\n\n" + h3("Code breakdown"));
            writer.write("\n" + generateTable(breakdown, breakdownTitles));
            writer.write("\n\n");
            writer.write(h3("File overview"));
            writer.write("\n");
            writer.write(generateTable(fileBreakdown, columnNames));
        }
    }


}
