package LineCount.FileOperations.Parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private Pattern commentPattern;
    private Pattern whitespacePattern = Pattern.compile("^\\s*?$");
    private String typeExtension;

    /**
     * Create a FileParser from a model
     * @param model to build FileParser from
     */
    public FileParser(FileParserModel model){
        this.commentPattern = Pattern.compile(model.commentRegex);
        this.typeExtension = model.typeExtension;
    }

    /**
     * Create a FileParser from regex pattern and extension string
     * @param commentPattern regex pattern matching comments in the file format
     * @param typeExtension format (extension) the FileParser can parse
     */
    public FileParser(Pattern commentPattern, String typeExtension){
        this.commentPattern = commentPattern;
        this.typeExtension = typeExtension;
    }

    /**
     * Create a Default FileParse which can't match comments, only whitespace and total lines
     */
    public FileParser(){
        this.typeExtension = "Default";
    }

    public Boolean testWhitespace(String line){
        Matcher matcher = this.whitespacePattern.matcher(line);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    /**
     * Test if a string is a comment in the specified file type
     * @param line String to test
     * @return True if string is a comment, false if it isn't, will always return false if no pattern is defined
     */
    public Boolean testComment(String line){
        if(commentPattern != null) {
            Matcher matcher = this.commentPattern.matcher(line);
            if (matcher.matches()) {
                return true;
            }
        }

        return false;

    }

    /**
     * Get the extension of the file type
     * @return String extension of the specified file type
     */
    public String getType(){
        return this.typeExtension;
    }

}
