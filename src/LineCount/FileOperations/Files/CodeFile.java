package LineCount.FileOperations.Files;


import LineCount.FileOperations.Parsing.FileParser;
import LineCount.FileOperations.Parsing.ParserChooser;
import LineCount.GUI.Windows.ErrorFrame;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Object representing a file containing source code, and statistics about said code
 */
public class CodeFile extends TextFile{
    private final FileParser parser;

    private final Path absPath;
    private final Path root;

    private int commentCount = 0;
    private int lineCount;
    private int whiteSpace = 0;

    /**
     * Constructor which reads the content and assigns variables
     * @param absPath absolute path to the file
     * @param root path to codeproject root folder
     * @param parsers ParserChooser containing parsers
     */
    public CodeFile(Path absPath, Path root, ParserChooser parsers){
        // Path info
        this.absPath = absPath;
        this.root = root;
        this.parser = parsers.getParser(this.getExtension());

        // Read content
        try{
            load(absPath);
        } catch (IOException e){
            new ErrorFrame(e);
        }

        // Fill out commentCount, lineCount and whiteSpace
        this.analyzeContent();

    }

    /**
     * Count the amount of comments and whitespaces in the file.
     */
    private void analyzeContent(){
        String[] content = getContent();
        this.lineCount = content.length;

        for(String line: content){
            // Test whether each line is a comment, if it isn't test whether it's whitespace
            // updates commentCount and whitespace
            if (this.parser.testComment(line)){
                this.commentCount++;
            } else if(this.parser.testWhitespace(line)){
                this.whiteSpace++;
            }
        }
    }


    /**
     * Read the extension of a filepath
     * @param path String absPath to file
     * @return String file extension
     */
    private String readExtension(String path){
        // The rightmost segment of a string will always be it's extension if we assume it's extension is appended to
        // the file name IE. Main.java
        String[] extension = path.split("\\.");  // Split the path at every .
        if (extension.length > 0) {                 // If the string has been split
            return extension[extension.length - 1]; // Return the rightmost segment
        } else {
            // If no . is found assume it's a direct absPath IE. absPath = filename
            return path;
        }
    }


    /**
     * Read the filename off a absPath
     * @param path String absPath to file
     * @return String filename including extension
     */
    private String readFilename(String path){
        // TODO: Make os agnostic, Windoge uses \ while unix uses /
        String[] deconstructedPath = path.split("\\\\");
        if (deconstructedPath.length > 0){
            return deconstructedPath[deconstructedPath.length-1];
        } else {
            return "No filename found";
        }
    }


    /**
     * Get the absolute path to the file
     * @return String absPath to file
     */
    public String getAbsPath(){
        return this.absPath.toString();
    }


    /**
     * Get the relative path of the file from the root directiory
     * @return String relative path to the file
     */
    public String getRelPath(){
        return root.relativize(this.absPath).toString();
    }


    /**
     * Get the root directory of the project the file belongs to
     * @return Path to root directory as a string
     */
    public String getRootDir(){
        return this.root.toString();
    }


    /**
     * @return String name of file including extension
     */
    public String getFileName(){
        return readFilename(this.absPath.toString());
    }


    /**
     * Return the extension of the file as a string (without the .)
     * @return String extension of the file
     */
    public String getExtension(){
        return readExtension(this.absPath.toString());
    }


    /**
     * @return int amount of lines
     */
    public int getLineCount(){
        return this.lineCount;
    }


    /**
     * @return int amount of comments
     */
    public int getCommentCount(){
        return this.commentCount;
    }


    /**
     * @return int amount of whitespace
     */
    public int getWhiteSpace(){
        return this.whiteSpace;
    }


    /**
     * @return int # of lines of code
     */
    public int getCodeCount(){
        return this.lineCount - this.whiteSpace - this.commentCount;
    }

}
