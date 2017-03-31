package LineCount.FileOperations.Utils;

import LineCount.FileOperations.Files.CodeFile;
import LineCount.FileOperations.Parsing.ParserChooser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public interface FileHelp {


    /**
     * Read multiple files off the hard drive in one go.
     * @param pathArray String[] array of paths to files as strings
     * @return CodeFile[] array of codefile objects containing the file contents, statistics and more
     */
    static CodeFile[] getCodeFiles(Path[] pathArray, Path root, ParserChooser parsers){
        CodeFile[] fileArray = new CodeFile[pathArray.length];
        for (int i = 0; i < pathArray.length; i++){
            try {
                CodeFile file = new CodeFile(pathArray[i], root, parsers);
                fileArray[i] = file;
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        return fileArray;
    }


    /**
     * Walk a directory, top down, filtering out undesired files with a FileFilter
     * @param basePath String path to folder to walk
     * @param fileFilter FileFilter to filter out files with
     * @return Path[] array of paths in the folder not filtered out
     */
    static Path[] walkDir(String basePath, FileFilter fileFilter){
        ArrayList<Path> tmpFiles = new ArrayList<>();

        try(Stream<Path> paths = Files.walk(Paths.get(basePath))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath) && !fileFilter.testFile(filePath)) {
                    tmpFiles.add(filePath);
                } else {
                    System.out.println("File filtered out" + filePath.toString());
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        return tmpFiles.toArray(new Path[0]);
    }

}
