package arxmlsorter;

import java.io.IOException;

/**
 *
 * @author MariamH78
 */
public class ARXMLSorter {

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args){
        for (String arg : args){
            try {
                ARXMLParser parser = new ARXMLParser(arg);
                parser.parse();
                parser.sort();
                parser.printToFile();
            } catch (EmptyAutosarFileException | NotValidAutosarFileException | IOException e){
                System.out.println(e +" Ignored file.");
            }
        }
    } 
}
