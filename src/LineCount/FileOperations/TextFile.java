package LineCount.FileOperations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TextFile {
    private ArrayList<String> content = new ArrayList<>();
    private Charset charset;

    public TextFile(Charset charset){
        this.charset = charset;
    }

    public TextFile(){
        this.charset = Charset.forName("UTF-8");
    }

    public void saveFile (Path path) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(path, this.charset)){
            for (String line: content.toArray(new String[0])){
                writer.write(line+"\n");
            }
        }
    }

    public void addLine(String line){
        this.content.add(line);
    }
}
