package arxmlsorter;

/**
 *
 * @author MariamH78
 */
public class NotValidAutosarFileException extends Exception {
    public NotValidAutosarFileException(String s){
        super("File \"" + s + "\" doesn't end in the supported .arxml extention.");
    }
}
