package LineCount.FileOperations.Files;

import LineCount.Utils.MdHelp;
import LineCount.Utils.TxtHelp;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static LineCount.Utils.StringHelp.getPercentageString;

/**
 * A project report file, can be save as either .txt file or .md
 */
public class ReportFile {

    private CodeFile[] files;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private  String timeStamp;

    private String title = "Saved Project Report";
    private String overviewTitle = "Project Overview";
    private String tallyTableTitle = "Code Tally";
    private String fileTableTitle = "File Overview";
    private String nFilesString = "Number of Files: ";
    private String projectFolder;
    private String[][] tallyRows;
    private String[][] filesRows;
    private String[] tallyTitles = {
            "Code",
            "Comments",
            "Whitespace",
            "Total"
    };
    private String[] filesColumns = {
            "Filename",
            "Extension",
            "Path",
            "Code",
            "Comments",
            "Whitespace",
            "Total"
    };


    /**
     * A report object representing a report to be saved to the harddrive
     * @param _files an array of codefiles to generate the report on.
     */
    public ReportFile(CodeFile[] _files){
        this.files = _files;

        // Calculate totals for the entire project
        int total_code = 0;
        int total_comments = 0;
        int total_whitespace = 0;
        int total_lines = 0;
        for (CodeFile file : this.files){
            total_code += file.getCodeCount();
            total_comments += file.getCommentCount();
            total_whitespace += file.getWhiteSpace();
            total_lines += file.getLineCount();
        }

        // Generate strings displaying project folder and amount of files
        this.timeStamp = "Report saved: " + dateFormat.format(new Date().getTime());
        this.projectFolder = "Project folder: " + files[0].getRootDir();
        nFilesString += files.length;

        // Create row for single rowed project overview table
        this.tallyRows = new String[1][];
        String[] breakdownList = new String[4];
        breakdownList[0] = getPercentageString(total_code, total_lines);
        breakdownList[1] = getPercentageString(total_comments, total_lines);
        breakdownList[2] = getPercentageString(total_whitespace, total_lines);
        breakdownList[3] = Integer.toString(total_lines);
        this.tallyRows[0] = breakdownList;

        // Create files overview table rows
        // the filesRows for the table needs to be one longer than files
        // due to the total column which is appended to the end
        this.filesRows = new String[this.files.length+1][];

        // Create the rows for the table and add them to the filesRows
        for (int i = 0; i < this.files.length; i++){
            String[] row = new String[filesColumns.length];
            row[0] = this.files[i].getFileName();
            row[1] = this.files[i].getExtension();
            row[2] = this.files[i].getRelPath();
            row[3] = getPercentageString(this.files[i].getCodeCount(), this.files[i].getLineCount());
            row[4] = getPercentageString(this.files[i].getCommentCount(), this.files[i].getLineCount());
            row[5] = getPercentageString(this.files[i].getWhiteSpace(), this.files[i].getLineCount());
            row[6] = Integer.toString(this.files[i].getLineCount());
            this.filesRows[i] = row;

        }
        // Create the final row showing the totals for the project
        // This is a redundancy but it's nice for verifying totals
        String[] totalRow = new String[filesColumns.length];
        totalRow[0] = "Total";
        totalRow[1] = "N/A";
        totalRow[2] = this.files[0].getRootDir();
        totalRow[3] = getPercentageString(total_code, total_lines);
        totalRow[4] = getPercentageString(total_comments, total_lines);
        totalRow[5] = getPercentageString(total_whitespace, total_lines);
        totalRow[6] = Integer.toString(total_lines);
        this.filesRows[this.files.length] = totalRow;
    }

    /**
     * Save the report in the markdown format
     * @param path desired path for the resulting file, should have a .md extension
     * @throws IOException
     */
    public void saveMarkDownReport(Path path) throws IOException {
        // Create a generic TextFile object
        TextFile mdReport = new TextFile();

        // Add report items to the TextFile object
        mdReport.addLine(MdHelp.h1(this.title));
        mdReport.addLine(MdHelp.h2(overviewTitle));
        mdReport.addLine(this.timeStamp);
        mdReport.addLine("\n");
        mdReport.addLine(this.projectFolder);
        mdReport.addLine("\n");
        mdReport.addLine(this.nFilesString);
        mdReport.addLine("\n");
        mdReport.addLine(MdHelp.h3(this.tallyTableTitle));
        for(String line: MdHelp.generateTable(this.tallyRows, this.tallyTitles)) mdReport.addLine(line);
        mdReport.addLine("\n");
        mdReport.addLine(MdHelp.h2(this.fileTableTitle));
        for(String line: MdHelp.generateTable(this.filesRows, this.filesColumns)) mdReport.addLine(line);

        // Save it
        mdReport.save(path);
    }

    /**
     * Save the report as a plaintext file
     * @param path desire path for the resulting file, should have a .txt exension
     * @throws IOException
     */
    public void saveTxtReport(Path path) throws IOException{
        // Create a generic text file
        TextFile txtReport = new TextFile();

        // Add its contents
        txtReport.addLine(this.title);
        txtReport.addLine("");
        txtReport.addLine(this.timeStamp);
        txtReport.addLine(this.projectFolder);
        txtReport.addLine(this.nFilesString);
        txtReport.addLine("\n");
        txtReport.addLine(this.tallyTableTitle);
        for(String line: TxtHelp.generateTable(this.tallyRows, this.tallyTitles)) txtReport.addLine(line);
        txtReport.addLine("\n");
        txtReport.addLine(this.fileTableTitle);
        for(String line: TxtHelp.generateTable(this.filesRows, this.filesColumns)) txtReport.addLine(line);

        // Save it
        txtReport.save(path);
    }


}
