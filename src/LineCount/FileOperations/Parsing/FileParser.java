package LineCount.FileOperations.Parsing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private Pattern commentPattern;
    private Pattern whitespacePattern = Pattern.compile("^\\s*?$");
    private String typeExtension;

    public FileParser(FileParserModel model){
        this.commentPattern = Pattern.compile(model.commentRegex);
        this.typeExtension = model.typeExtension;
    }

    public FileParser(Pattern commentPattern, String typeExtension){
        this.commentPattern = commentPattern;
        this.typeExtension = typeExtension;
    }

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

    public Boolean testComment(String line){
        if(commentPattern != null) {
            Matcher matcher = this.commentPattern.matcher(line);
            if (matcher.matches()) {
                return true;
            }
        }

        return false;

    }

    public String getType(){
        return this.typeExtension;
    }

}
