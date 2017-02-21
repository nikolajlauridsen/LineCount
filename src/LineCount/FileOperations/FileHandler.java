package LineCount.FileOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileHandler {

    private Charset charset;

    /**
     * FileHandler for reading files off the hard drive.
     * @param charsetString String charset to read in (commonly "UTF-8")
     */
    public FileHandler(String charsetString){
        this.charset = Charset.forName(charsetString);
    }

    /**
     * Defaults to UTF-8 encoding
     */
    public FileHandler(){
        this.charset = Charset.forName("UTF-8");
    }

    /**
     * Read a single file off the hard drive
     * @param path String path to desired file
     * @return CodeFile codefile object containing the file contents, statistics and more
     */
    public CodeFile readFile(Path path){
        // Create a temporary list of strings for the content
        ArrayList<String> contentList = new ArrayList<>();

        // Try creating a reader, catch IO exception
        try (BufferedReader reader = Files.newBufferedReader(path, this.charset)){
            // Current line of  file as a string (will be null if EOF)
            String line = null;
            while ((line = reader.readLine()) != null){
                // Add the line to the content list
                contentList.add(line);
            }
        } catch (IOException e){
            System.err.format("IOException: %s%n", e);
        }
        // Convert the list to a string array
        return new CodeFile(path.toString(), contentList.toArray(new String[0]));
    }

    /**
     * Read multiple files off the hard drive in one go.
     * @param pathArray String[] array of paths to files as strings
     * @return CodeFile[] array of codefile objects containing the file contents, statistics and more
     */
    public CodeFile[] readFiles(Path[] pathArray){
        CodeFile[] fileArray = new CodeFile[pathArray.length];

        for (int i = 0; i < pathArray.length; i++){
            try {
                CodeFile file = readFile(pathArray[i]);
                fileArray[i] = file;
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return fileArray;
    }

}
