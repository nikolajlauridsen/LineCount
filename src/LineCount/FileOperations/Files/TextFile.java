package LineCount.FileOperations.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * An object representing a generic text file
 */
public class TextFile {
    private ArrayList<String> content = new ArrayList<>();
    private Charset charset;

    /**
     * Create a texfile object with a specific charset
     * @param charset desired charset
     */
    public TextFile(Charset charset){
        this.charset = charset;
    }

    /**
     * Create a UTF-8 encoded text file
     */
    public TextFile(){
        this.charset = Charset.forName("UTF-8");
    }

    /**
     * Save the file to disk
     * @param path desired save location
     * @throws IOException if desired save location is invalid
     */
    public void saveFile (Path path) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(path, this.charset)){
            for (String line: content.toArray(new String[0])){
                writer.write(line+"\n");
            }
        }
    }

    /**
     * Add a line to the text file
     * @param line String to add
     */
    public void addLine(String line){
        this.content.add(line);
    }

    /**
     * Read the contents of a file into the TextFile content
     * @param file Path to the file
     * @throws IOException throws exception for non existent files, invalid files and so on
     */
    public void read(Path file) throws IOException{
        try (BufferedReader reader = Files.newBufferedReader(file, charset)){
            String line = null;
            while ((line = reader.readLine()) != null){
                this.content.add(line);
            }
        }
    }

    /**
     * Return the content of the file as a string array
     * @return String[] file contents
     */
    public String[] getContent(){ return this.content.toArray(new String[0]);}
}
