package LineCount.FileOperations;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object representing a file containing source code
 * Retrievable values
 * String path ------- path to file
 * String fileName --- filename
 * String extension -- file extension as a string
 * String[] content -- file content as list of strings
 * String[] comments - code comments in the files
 * int commentCount -- amount of comments in the file
 * int lineCount  ---- total amount of lines in the file
 * int whiteSpace ---- total amount of whitespace in the file
 * all values can be retrieved with it's get function IE. CodeFile.getPath()
 */
public class CodeFile {

    // Python comments starts with # and doc strings starts/ends with """ TODO: handle doc strings
    private Pattern pythonPattern = Pattern.compile("^\\s*?#[\\s\\S]*$");
    // Java has a lot of comments, catch the line as comment if it begins with: //, /*, /**, *, or */
    private Pattern javaPattern = Pattern.compile("^\\s*?(//|/\\*[*]?|\\*[/]?)[\\s\\S]*?$");
    private Pattern whiteSpacePattern = Pattern.compile("^\\s*?$");

    private String path;
    private String fileName;
    private String extension;
    private String[] content;
    private String[] comments;
    private int commentCount;
    private int lineCount;
    private int whiteSpace;

    /**
     * Constructor which reads the content and assigns variables
     * @param path String path to file
     * @param content String[] file content as list of strings
     */
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

    /**
     * Read through the content of a file, takes extension to choose regex pattern
     * @param file String[] contents of the file as a string array
     * @param extension String file extension as a string (omit the ".")
     * @return String[] comments as a string array
     */
    private String[] readComments(String[] file, String extension){
        // Temporary list for adding the comments (needed since we don't know the amount of comments)
        ArrayList<String> commentList = new ArrayList<>();

        // Iterate over the content string
        for (String line : file) {
            Matcher matcher;                                // Declare matcher
            if (extension.equals("java")) {
                matcher = this.javaPattern.matcher(line);   // If it's a java file use java matcher
            } else {
                matcher = this.pythonPattern.matcher(line); // If it isn't just use python instead
            }

            if (matcher.matches()) {    // If the line matches a comment regex
                commentList.add(line);  // Add it to the comment list
            }
        }

        // Convert the temporary list to an array and return it
        return commentList.toArray(new String[0]);
    }

    /**
     * Count the amount of whitespace lines in the code file
     * @param content String[] content as a string array
     * @return int amount of lines of whitespace
     */
    private int countWhitespace(String[] content){
        int whitespace = 0;  // Whitespace counter

        for (String line: content){                                 // Iterate over the list of strings
            Matcher matcher = this.whiteSpacePattern.matcher(line); // create whitespace matcher
            if (matcher.matches()){                                 // If the string matches as whitespace
                whitespace++;                                       // Add one to whitespace counter
            }
        }
        // Return the whitespace counter
        return whitespace;
    }

    /**
     * Read the extension of a filepath
     * @param path String path to file
     * @return String file extension
     */
    private String readExtension(String path){
        // The rightmost segment of a string will always be it's extension if we assume it's extension is appended to
        // the file name IE. Main.java
        String[] extension = path.split("\\.");  // Split the path at every .
        if (extension.length > 0) {                 // If the string has been split
            return extension[extension.length - 1]; // Return the rightmost segment
        } else {
            // If no . is found assume it's a direct path IE. path = filename
            return path;
        }
    }

    /**
     * Read the filename off a path
     * @param path String path to file
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
     * @return String[] content of the file
     */
    public String[] getContent(){
        return this.content;
    }

    /**
     * @return String[] comments in the file
     */
    public String[] getComments(){
        return this.comments;
    }

    /**
     * @return String path to file
     */
    public String getPath(){
        return this.path;
    }

    /**
     * @return String name of file including extension
     */
    public String getFileName(){
        return this.fileName;
    }

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

    public int getCodeCount(){
        return this.lineCount - this.whiteSpace - this.commentCount;
    }

}
