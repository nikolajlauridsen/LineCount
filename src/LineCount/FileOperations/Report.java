package LineCount.FileOperations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Report {

    private CodeFile[] files;
    private Path root;

    private String title = "Project Report";
    private String tableTitle = "File Overview";
    private String nFilesString = "Number of Files: ";

    private int total_code = 0;
    private int total_comments = 0;
    private int total_whitespace = 0;
    private int total_lines  = 0;

    public Report(CodeFile[] _files){
        this.files = _files;

        for (CodeFile file : this.files){
            total_code += file.getCodeCount();
            total_comments += file.getCommentCount();
            total_whitespace += file.getWhiteSpace();
            total_lines += file.getLineCount();
        }
    }

    public void saveMarkDownReport(Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");

        nFilesString += files.length;
        String projectFolder = "\nProject folder: " + files[0].getRootDir();

        String[] breakdownList = new String[4];
        breakdownList[0] = "Code: " + total_code;
        breakdownList[1] = "Comments: " + total_comments;
        breakdownList[2] = "Whitespace: " + total_whitespace;
        breakdownList[3] = "Total: " + total_lines;

        try(BufferedWriter writer = Files.newBufferedWriter(path, charset)){
            writer.write(h1(title));
            writer.write("\n" + h2("Project Overview"));
            writer.write(projectFolder);
            writer.write("\n\n");
            writer.write(nFilesString);
            writer.write("\n\n" + h3("Code breakdown"));
            writer.write("\n" + generateList(breakdownList));
        }
    }

    private String h1(String string){
        return "# " + string;
    }

    private String h2(String string){
        return "## " + string;
    }

    private String h3(String string){
        return "### " + string;
    }

    private String generateList(String[] items){
        String stringList = "";
        for(String item: items){
            stringList += "* " + item + "\n";
        }

        return stringList;
    }

}
