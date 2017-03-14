package LineCount.FileOperations;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFilter extends TextFile{
    private ArrayList<Pattern> filter = new ArrayList<>();

    private final Character ast = '*';
    private final Character folderChar = '/';
    private final Character[] escaped = {'.', '-', '+'};

    public FileFilter(){
        // the .git folder is not normally covered by gitignore so we'll add a pattern here
        filter.add(Pattern.compile("^[\\s\\S]*\\.git\\\\[\\s\\S]*$"));
    }

    public void ParseIgnoreFile(Path ignoreFile) throws IOException{
        read(ignoreFile);

        for (String line: this.getContent()){
            String pattern = parseGlob(line);
            System.out.println("Regex generated: " + pattern);
            filter.add(Pattern.compile(pattern));
        }
    }

    public Boolean testFile(Path file){
        for(Pattern pattern: filter){
            Matcher matcher = pattern.matcher(file.toString());
            if (matcher.matches()){
                return true;
            }
        }
        return false;
    }

    private String parseGlob(String glob){
        StringBuilder builder = new StringBuilder();
        // Add the beginning
        builder.append("^[\\s\\S]*");
        for(int i = 0; i < glob.length(); i++){
            char c = glob.charAt(i);
            if(c == this.ast){
                if(i != 0) builder.append("[\\s\\S]*");
            } else if (c == folderChar){
                builder.append("\\\\");
            } else if (Arrays.asList(this.escaped).contains(c)){
                builder.append("\\");
                builder.append(c);
            } else{
                builder.append(c);
            }

        }
        builder.append("$");

        return builder.toString();
    }

    public void addGlob(String glob){
        filter.add(Pattern.compile(parseGlob(glob)));
    }
}
