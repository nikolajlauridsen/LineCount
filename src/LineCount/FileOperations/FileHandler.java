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

    public FileHandler(String charsetString){
        this.charset = Charset.forName(charsetString);
    }

    public CodeFile readFile(String pathString){
        // Create a temporary list of strings for the content
        ArrayList<String> contentList = new ArrayList<>();
        // Create a path object pointing to the file
        Path path = FileSystems.getDefault().getPath(pathString);

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
        return new CodeFile(pathString, contentList.toArray(new String[0]));
    }

    public CodeFile[] readFiles(String[] pathArray){
        CodeFile[] fileArray = new CodeFile[pathArray.length];

        for (int i = 0; i < pathArray.length; i++){
            try {
                CodeFile file = readFile(pathArray[i]);
                fileArray[i] = file;
            } catch (Exception e){
                System.out.println("An error has occurred: " + e.toString());
            }

        }

        return fileArray;
    }

}
