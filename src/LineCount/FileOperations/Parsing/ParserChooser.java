package LineCount.FileOperations.Parsing;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParserChooser {
    FileParser[] parsers;


    public ParserChooser(String configPath){
        this.parsers = readParsers(configPath);

    }

    public FileParser getParser(String extension) {
        for (FileParser parser : this.parsers) {
            if (parser.getType().equals(extension)) {
                return parser;
            }
        }

        // If no fileparser is found return a default
        return new FileParser();
    }

    private FileParser[] readParsers(String path) {
        // Create
        ArrayList<FileParser> parsers = new ArrayList<>();
        try {
            YamlReader reader = new YamlReader(new FileReader(path));
            while (true) {
                try {
                    FileParserModel model = reader.read(FileParserModel.class);
                    if (model == null) {
                        break;
                    }

                    System.out.println(model.typeExtension);
                    System.out.println(model.commentRegex);
                    parsers.add(new FileParser(model));
                } catch (YamlReader.YamlReaderException e){
                    System.out.println("Invalid yaml file");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsers.toArray(new FileParser[0]);
    }
}
