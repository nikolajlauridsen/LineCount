package LineCount.FileOperations.Utils;

import LineCount.FileOperations.Files.CodeFile;
import LineCount.FileOperations.Parsing.ParserChooser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public interface FileOps {

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

    static Path[] walkDir(String basePath){
        ArrayList<Path> tmpFiles = new ArrayList<>();

        try(Stream<Path> paths = Files.walk(Paths.get(basePath))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    tmpFiles.add(filePath);
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }

        return tmpFiles.toArray(new Path[0]);
    }

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
