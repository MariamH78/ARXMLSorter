package arxmlsorter;

/**
 *
 * @author MariamH78
 */
public class EmptyAutosarFileException extends RuntimeException {
    public EmptyAutosarFileException(String s){
        super("File \"" + s + "\" is an empty file.");
    }
}
