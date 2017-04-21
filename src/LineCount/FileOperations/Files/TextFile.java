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
    private final ArrayList<String> content = new ArrayList<>();
    private final Charset charset;

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
     * Initiate a TextFile from a file on the disk, the file will immediately try to
     * read the file on the disk into it's content.
     * Defaults to the UTF-8 encoding since it'll do for most use cases
     * If UTF-8 isn't viable, use the charset constructor and load manually
     * @param file Path to the file on disk to be read
     * @throws IOException Since it reads from drive it might throw IOException
     */
    public TextFile(Path file) throws IOException{
        this.charset = Charset.forName("UTF-8");
        this.load(file);
    }


    /**
     * Save the file to disk
     * @param path desired save location
     * @throws IOException if desired save location is invalid
     */
    public void save(Path path) throws IOException{
        // Use a try with resource to ensure the file isn't left hanging
        try(BufferedWriter writer = Files.newBufferedWriter(path, this.charset)){
            for (String line: this.getContent()){
                writer.write(line+"\n");
            }
        }
    }

    /**
     * Read the contents of a file into the TextFile content
     * @param file Path to the file
     * @throws IOException throws exception for non existent files, invalid files and so on
     */
    public void load(Path file) throws IOException{
        try (BufferedReader reader = Files.newBufferedReader(file, charset)){
            String line;
            while ((line = reader.readLine()) != null){
                this.content.add(line);
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
     * Return the content of the file as a string array
     * @return String[] file contents
     */
    public String[] getContent(){ return this.content.toArray(new String[0]);}
}
