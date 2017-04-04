package LineCount.FileOperations.Utils;

import LineCount.FileOperations.Files.TextFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object for filtering out unwanted files in a list, stream etc.
 * Matches pathstring against a regex which can be generated from
 * a glob read from a gitignore file
 */
public class FileFilter{
    private final ArrayList<Pattern> filter = new ArrayList<>();

    private final Character ast = '*';
    private final Character folderChar = '/';
    private final Character[] escaped = {'.', '-', '+', '[', ']', '(', ')', '\\'};
    private final Pattern ignorePattern = Pattern.compile("^[\\s]*[#]?");

    /**
     * No arguments.
     * adds .git/* by default
     */
    public FileFilter(){
        // the .git folder is not normally covered by gitignore
        // so we'll add a pattern here
        addGlob(".git/*");
    }

    /**
     * Parse a .gitignore file adding every glob in it to the filter list
     * @param ignoreFile Path to the .gitignore
     * @throws IOException Since it reads from disk it'll
     * throw an error if the file doesn't exist, or can't be read from.
     */
    public void ParseIgnoreFile(Path ignoreFile) throws IOException{
        // Read the gitignore file
        TextFile gitIgnore = new TextFile(ignoreFile);

        // Iterate over the gitignore content
        for (String line: gitIgnore.getContent()){
            try{
                // Parse each glob converting into a regex pattern
                String pattern = parseGlob(line);
                System.out.println("Regex generated: " + pattern);
                // Compile & add to filter list
                filter.add(Pattern.compile(pattern));
            } catch (InvalidGlobError e){
                System.out.println("Comment found/empty line, skipping");
            }

        }
    }

    /**
     * Test a file against the added filters
     * @param file Path of the file to be tested
     * @return Boolean, true if file matches the filters, false if it doesn't
     */
    public Boolean testFile(Path file){
        // Test the path against every filter in the filter list
        for(Pattern pattern: filter){
            Matcher matcher = pattern.matcher(file.toString());
            if (matcher.matches()){
                // Return true if it's a match
                // which also stops the loop
                return true;
            }
        }
        return false;
    }

    /**
     * Parses a glob returning a regex string
     * @param glob String glob to be parsed
     * @return String regex string
     */
    private String parseGlob(String glob) throws InvalidGlobError {
        // If the glob is either empty or a comment trow a commentError
        Matcher whitespace = this.ignorePattern.matcher(glob);
        if (whitespace.matches()){
            throw new InvalidGlobError("Glob is either a comment or a empty line");
        }

        StringBuilder builder = new StringBuilder();
        // Start the expression
        // note that any root directory is ignored
        builder.append("^[\\s\\S]*");

        // Iterate over the entire string
        for(int i = 0; i < glob.length(); i++){
            // Fetching the character at the current position
            char c = glob.charAt(i);

            // Match against asterisk IE. we want everything to match
            if(c == this.ast){
                // Skip it if it's the first character
                // since the regex already starts with "ignore all"
                if(i != 0) builder.append("[\\s\\S]*");
            }
            else if (c == this.folderChar){
                // If it's a folder delimiter char add a backslash
                // Since backslash is used as escape character in both regex and java
                // four backslashes is needed two for java and two for regex
                builder.append("\\\\");
            }
            else if (Arrays.asList(this.escaped).contains(c)){
                // If the char is a special char in regex it needs to be escaped with a \
                builder.append("\\");
                builder.append(c);
            }
            else{
                builder.append(c);
            }

        }
        // Close the expression
        builder.append("$");

        return builder.toString();
    }

    /**
     * Add a glob to the filter list
     * @param glob String glob to be added (EX: 'out/*')
     * @throws InvalidGlobError if the globs is a comment or just whitespace
     */
    public void addGlob(String glob) throws InvalidGlobError {
        this.filter.add(Pattern.compile(parseGlob(glob)));
    }

}
