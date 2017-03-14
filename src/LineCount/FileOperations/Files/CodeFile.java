package LineCount.FileOperations.Files;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object representing a file containing source code, and statistics about said code
 */
public class CodeFile {

    // Python comments starts with # and doc strings starts/ends with """ TODO: handle doc strings
    private Pattern pythonPattern = Pattern.compile("^\\s*?#[\\s\\S]*$");
    // Java has a lot of comments, catch the line as comment if it begins with: //, /*, /**, *, or */
    private Pattern javaPattern = Pattern.compile("^\\s*?(//|/\\*[*]?|\\*[/]?)[\\s\\S]*?$");
    private Pattern whiteSpacePattern = Pattern.compile("^\\s*?$");

    private String absPath;
    private Path root;
    private Path relPath;
    private String fileName;
    private String extension;
    private String[] content;
    private int commentCount = 0;
    private int lineCount;
    private int whiteSpace = 0;

    /**
     * Constructor which reads the content and assigns variables
     * @param absPath String absPath to file
     * @param content String[] file content as list of strings
     */
    public CodeFile(String absPath, String[] content, Path root){
        // TODO: switch strings to path objects internally, too lazy atm.
        // Path info
        this.absPath = absPath;
        this.root = root;
        this.relPath = root.relativize(Paths.get(this.absPath));
        this.fileName = readFilename(this.absPath);
        this.extension = readExtension(this.absPath);
        // content and statistics
        this.content = content;
        this.lineCount = this.content.length;
        this.analyzeContent();
    }

    /**
     * Test whether a string matches the comment regex and increment comment count if it does
     * @param line String to be tested
     * @return Bool whether line is a comment
     */
    private Boolean testComment(String line){
        Matcher matcher;
        if(extension.equals("java")){
            matcher = this.javaPattern.matcher(line);
        } else {
            matcher = this.pythonPattern.matcher(line);
        }

        if (matcher.matches()) {
            this.commentCount++;
            return true;
        } else{
            return false;
        }
    }

    /**
     * Test whether a string matches the whitespace regex and increment whitespace if it does
     * @param line String to be tested
     * @return Bool whether the line is whitespace
     */
    private Boolean testWhitespace(String line){
        Matcher matcher = this.whiteSpacePattern.matcher(line);
        if(matcher.matches()){
            this.whiteSpace++;
            return true;
        } else{
            return false;
        }
    }

    /**
     * Count the amount of comments and whitespaces in the file.
     */
    private void analyzeContent(){
        for(String line: content){
            // Test whether each line is a comment, if it isn't test whether it's whitespace
            // updates commentCount and whitespace
            if (!testComment(line)){
                testWhitespace(line);
            }
        }
    }

    /**
     * Read the extension of a filepath
     * @param path String absPath to file
     * @return String file extension
     */
    private String readExtension(String path){
        // The rightmost segment of a string will always be it's extension if we assume it's extension is appended to
        // the file name IE. Main.java
        String[] extension = path.split("\\.");  // Split the path at every .
        if (extension.length > 0) {                 // If the string has been split
            return extension[extension.length - 1]; // Return the rightmost segment
        } else {
            // If no . is found assume it's a direct absPath IE. absPath = filename
            return path;
        }
    }

    /**
     * Read the filename off a absPath
     * @param path String absPath to file
     * @return String filename including extension
     */
    private String readFilename(String path){
        // TODO: Make os agnostic, Windoge uses \ while unix uses /
        String[] deconstructedPath = path.split("\\\\");
        if (deconstructedPath.length > 0){
            return deconstructedPath[deconstructedPath.length-1];
        } else {
            return "No filename found";
        }
    }

    /**
     * Get the content of the file as a list of strings, each string is a single line
     * @return String[] content of the file
     */
    public String[] getContent(){
        return this.content;
    }

    /**
     * Get the absolute path to the file
     * @return String absPath to file
     */
    public String getAbsPath(){
        return this.absPath;
    }

    /**
     * Get the relative path of the file from the root directiory
     * @return String relative path to the file
     */
    public String getRelPath(){
        return this.relPath.toString();
    }

    public String getRootDir(){
        return this.root.toString();
    }

    /**
     * @return String name of file including extension
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * Return the extension of the file as a string (without the .)
     * @return String extension of the file
     */
    public String getExtension(){
        return this.extension;
    }

    /**
     * @return int amount of lines
     */
    public int getLineCount(){
        return this.lineCount;
    }

    /**
     * @return int acmount of comments
     */
    public int getCommentCount(){
        return this.commentCount;
    }

    /**
     * @return int amount of whitespace
     */
    public int getWhiteSpace(){
        return this.whiteSpace;
    }

    /**
     *
     * @return int # of lines of code
     */
    public int getCodeCount(){
        return this.lineCount - this.whiteSpace - this.commentCount;
    }

}
