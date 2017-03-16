package LineCount.FileOperations.Parsing;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParserChooser {
    FileParser[] parsers;


    /**
     * Takes path to the config file as a string
     * @param configPath String path to file
     */
    public ParserChooser(String configPath){
        this.parsers = readParsers(configPath);

    }

    /**
     * Return a parser based on a file extension
     * @param extension Extension of the file to be parsed
     * @return FileParser with the correct comment pattern
     */
    public FileParser getParser(String extension) {
        for (FileParser parser : this.parsers) {
            if (parser.getType().equals(extension)) {
                return parser;
            }
        }

        // If no fileparser is found return a default
        return new FileParser();
    }

    /**
     * Read FileParsers from a yaml file on the harddrive
     * @param path String path to the config file
     * @return Array of FileParsers defined in the yaml file
     */
    private FileParser[] readParsers(String path) {
        // Create temporary arraylist for storing the FileParsers
        ArrayList<FileParser> parsers = new ArrayList<>();
        try {
            YamlReader reader = new YamlReader(new FileReader(path));
            while (true) {
                try {
                    FileParserModel model = reader.read(FileParserModel.class);
                    if (model == null) {
                        // If model returns as null the file is over
                        break;
                    }

                    System.out.println(model.typeExtension);
                    System.out.println(model.commentRegex);
                    parsers.add(new FileParser(model));
                } catch (YamlReader.YamlReaderException e){
                    System.out.println("Invalid yaml file");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsers.toArray(new FileParser[0]);
    }
}
