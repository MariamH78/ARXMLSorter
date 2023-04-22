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
        /*Include assumptions in the readme (file is structurally correct (all tags are paired (or self-closing)),
        file always starts with xml declaration,
        if text exists outside tags then its location in the document is of no significance,
        no empty tags exist,
        tags are reasonably named (no tags named '/' for example),
        , only one "short-name" exists per container,
        no comments or multi-line comments in the file.
        /*
        What if container has another container inside of it?
        Try running with no args?
        
        */
        
        }
    
}
