package LineCount.FileOperations.Utils;

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


}
