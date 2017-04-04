package LineCount.FileOperations.Parsing;

import LineCount.GUI.Windows.ErrorFrame;
import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * A container object for parsers, can read FileParsers from fileparsers.yml from the harddrive
 */
public class ParserChooser {
    private final FileParser[] parsers;


    /**
     * Takes path to the config file as a string
     * @param configPath String path to file
     */
    public ParserChooser(String configPath) throws Exception{
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
    private FileParser[] readParsers(String path) throws Exception {
        // Create temporary arraylist for storing the FileParsers
        ArrayList<FileParser> parsers = new ArrayList<>();
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
                    new ErrorFrame(e);
                }
            }

        return parsers.toArray(new FileParser[0]);
    }
}
