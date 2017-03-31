package LineCount.FileOperations.Utils;

/**
 * A comment error thrown by FileFilter if the glob to parse
 * is either just empty whitespace or a glob comment (as used in .gitignore)
 */
public class CommentError extends RuntimeException {
    public CommentError(String message){
        super(message);
    }
}
