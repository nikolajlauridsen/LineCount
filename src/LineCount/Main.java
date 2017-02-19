package LineCount;

import LineCount.FileOperations.CodeFile;
import LineCount.FileOperations.FileHandler;

public class Main {

    public static void main(String[] args) {
        // Create file handler object
        FileHandler fileHandler = new FileHandler();
        // Filename string
        String[] fileNames = new String[3];
        fileNames[0] = "Testfiles\\CodeFile.java";
        fileNames[1] = "Testfiles\\FileHandler.java";
        fileNames[2] = "Testfiles\\Main.java";
        // fileNames[3] = "Testfiles\\test2.py";
        // fileNames[4] = "Testfiles\\test3.py";
        // String array for file content
        CodeFile[] files;
        // Read the file
        files = fileHandler.readFiles(fileNames);

        int total_lines = 0;
        int total_comments = 0;
        int total_whitespace = 0;
        for (CodeFile file: files){
            System.out.println("Path: " + file.getPath());
            System.out.println("Filename: " + file.getFileName());
            System.out.println("Extension: "  + file.getExtension());
            System.out.println("Line count: " + file.getLineCount());
            System.out.println("Comments count: " + file.getCommentCount());
            System.out.println("Whitespace count: " + file.getWhiteSpace());
            System.out.println("Lines minus comments & whitespace: " +
                    (file.getLineCount()-file.getCommentCount()-file.getWhiteSpace()));
            System.out.println("\n");
            total_lines += file.getLineCount();
            total_comments += file.getCommentCount();
            total_whitespace += file.getWhiteSpace();
        }

        System.out.println("Total lines: " + total_lines);
        System.out.println("Total comments: " + total_comments);
        System.out.println("Total whitespace: " + total_whitespace);
        System.out.println("Total lines minus comments & whitespace: " +
                (total_lines - total_comments - total_whitespace));
    }
}
