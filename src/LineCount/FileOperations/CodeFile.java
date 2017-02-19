package LineCount.FileOperations;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeFile {

    private Pattern pythonPattern = Pattern.compile("^\\s*?#[\\s\\S]*$");
    private Pattern javaPattern = Pattern.compile("^\\s*?//[\\s\\S]*$");
    private Pattern whiteSpacePattern = Pattern.compile("^\\s*?$");

    private String path;
    private String fileName;
    private String extension;
    private String[] content;
    private String[] comments;
    private int commentCount;
    private int lineCount;
    private int whiteSpace;

    CodeFile(String path, String[] content){
        this.path = path;
        this.fileName = readFilename(this.path);
        this.extension = readExtension(this.path);
        this.content = content;
        this.comments = readComments(this.content, this.extension);
        this.lineCount = this.content.length;
        this.commentCount = this.comments.length;
        this.whiteSpace = countWhitespace(this.content);
    }

    private String[] readComments(String[] file, String extension){
        ArrayList<String> commentList = new ArrayList<>();

        for (String line : file) {
            Matcher matcher;
            if (extension.equals("java")) {
                matcher = this.javaPattern.matcher(line);
            } else {
                matcher = this.pythonPattern.matcher(line);
            }
            boolean match = matcher.matches();
            if (match) {
                commentList.add(line);
            }
        }

        return commentList.toArray(new String[0]);
    }

    private int countWhitespace(String[] content){
        int whitespace = 0;

        for (String line: content){
            Matcher matcher = this.whiteSpacePattern.matcher(line);
            if (matcher.matches()){
                whitespace++;
            }
        }
        return whitespace;
    }

    private String readExtension(String path){
        String[] extension = path.split("\\.");
        if (extension.length > 0) {
            return extension[extension.length - 1];
        } else {
            // If no \ is found assume it's a direct path IE. path = filename
            return path;
        }
    }

    private String readFilename(String path){
        // TODO: Make os agnostic
        String[] deconstructedPath = path.split("\\\\");
        if (deconstructedPath.length > 0){
            return deconstructedPath[deconstructedPath.length-1];
        } else {
            return "No filename found";
        }
    }

    public String[] getContent(){
        return this.content;
    }

    public String[] getComments(){
        return this.comments;
    }

    public String getPath(){
        return this.path;
    }

    public String getFileName(){
        return this.fileName;
    }

    public String getExtension(){
        return this.extension;
    }

    public int getLineCount(){
        return this.lineCount;
    }

    public int getCommentCount(){
        return this.commentCount;
    }

    public int getWhiteSpace(){
        return this.whiteSpace;
    }

}
