package LineCount.FileOperations.Utils;

import LineCount.FileOperations.Files.CodeFile;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;
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
    static CodeFile[] getCodeFiles(Path[] pathArray, Path root){
        CodeFile[] fileArray = new CodeFile[pathArray.length];

        for (int i = 0; i < pathArray.length; i++){
            try {
                CodeFile file = new CodeFile(pathArray[i], root);
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

    /**
     * Read file parses from a yaml file
     * @param path String path to file
     */
    static FileParser[] readParsers(String path) {
        // Create
        ArrayList<FileParser> parsers = new ArrayList<>();
        try {
            YamlReader reader = new YamlReader(new FileReader(path));
            while (true){
                FileParserModel model = reader.read(FileParserModel.class);
                if (model == null){
                    break;
                }

                System.out.println(model.typeExtension);
                System.out.println(model.commentRegex);
                parsers.add(new FileParser(model));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return parsers.toArray(new FileParser[0]);
    }

}
