package arxmlsorter;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.PrintStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.regex.*;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Stack;
import javafx.util.Pair;

/**
 * @author MariamH78
 */

public class ARXMLParser {
    private final String fileName;
    private String fileText;
    private final String XMLDeclaration;
    
    //Both of these will be used to parse the file.
    private final Matcher regexMatcher;
    private int previousMatchEnds;
    
    //This dummy node represents the root, first parent of any and all tags.
    private final Tag dummyRootNode = new Tag();
    private final Stack<Tag> nodes;
    
    
    public ARXMLParser(String fileName) throws IOException, NotValidAutosarFileException, EmptyAutosarFileException {
        
        if (!fileName.endsWith(".arxml")) throw new NotValidAutosarFileException(fileName); 
        this.fileName = fileName;
        
        this.fileText = Files.readString(Paths.get(fileName));
        this.reformatFileText();
        if (this.fileText.isBlank()) throw new EmptyAutosarFileException(fileName);
        
        this.nodes = new Stack();
        this.nodes.push(this.dummyRootNode);
        
        //Preparing document's matcher, which matches anything between < > brackets.
        this.regexMatcher = Pattern.compile("(?<=<).+?(?=>)").matcher(this.fileText);
        this.regexMatcher.find();
        this.XMLDeclaration = regexMatcher.group();
        this.previousMatchEnds = this.regexMatcher.end() + 1;
        
    }
    
    private void reformatFileText(){
        //Function to reformat the file to be easier to parse later, removes newlines and extra spaces.
        this.fileText = this.fileText.replaceAll("\\t|\\r\\n|\\r|\\n", "");
        this.fileText = this.fileText.replaceAll(" +", " ");
        this.fileText = this.fileText.replaceAll(" = | =|= ", "=");
        this.fileText = this.fileText.replaceAll(" >", ">");
        this.fileText = this.fileText.replaceAll("< ", "<");
    }
    
    private ArrayList<Pair<String,String>> getParams(String text){
        //This function extracts the list of parameters of a tag from the returned match between the <> brackets.
        ArrayList<Pair<String,String>> returnedList = new ArrayList<>();
        ArrayList <String> params = new ArrayList<>();
        Matcher matcher = Pattern.compile("(?<= ).*?(?= |$|/)").matcher(text);
        while(matcher.find()) {
            params.add(matcher.group()); 
        }
        
        for (String param : params){
            String keyAndValue[] = param.split("=");
            returnedList.add(new Pair(keyAndValue[0], keyAndValue[1]));
        }
        return returnedList;
    }
    
    private Tag initChild(String tagName, String found, boolean hasParam){
            ArrayList<Pair<String,String>> paramList = null;
            if (hasParam) paramList = getParams(found);
            return new Tag(tagName,
                        paramList,
                        null);
        
    }
    public void parse(){
        Tag child;
        String tagName;
        
        while (this.regexMatcher.find()){
           
            String found = this.regexMatcher.group();
            
            //The goal of this condition is to check for text between the previous found tag and the current found tag.
            if (this.regexMatcher.start() > previousMatchEnds)
                this.nodes.peek().addContent(this.fileText.substring(previousMatchEnds, this.regexMatcher.start() - 1));
            
            boolean hasParams = false;
            if(found.contains(" ")) hasParams = true;
            
            
            if (found.endsWith("/")){           //Self-closing tag
                
                if (!hasParams) tagName = found.substring(0, found.length()- 2);
                else tagName = found.substring(0, found.indexOf(' '));
                
                child = this.initChild(tagName, found, hasParams);
                this.nodes.peek().addChild(child);
                
            }  else if (found.startsWith("/")){ //Ending tag
                this.nodes.pop();
                
            } else {                                  //Opening tag
                
                if (!hasParams) tagName = found;
                else tagName = found.substring(0, found.indexOf(' '));
                
                child = this.initChild(tagName, found, hasParams);
                this.nodes.peek().addChild(child);
                this.nodes.push(child);
                
            }
            previousMatchEnds = this.regexMatcher.end() + 1;
        }
    }
    
    private void sort(ArrayList<Tag> children){
        //Function that recursively sorts file by SHORT-NAME
        for (Tag child: children){
            Collections.sort(child.getChildren());
            sort(child.getChildren());
        }
    }
    public void sort(){
        //Public interface for sort function that takes no arguments.
        sort(this.dummyRootNode.getChildren());
    }
    
    private void print(ArrayList<Tag> children, String tabs){
        //Function that recursively prints the formatted output
        if (children == null) return;
        for (Tag child: children){
            System.out.print(tabs + "<" + child.getName());
            if (child.getParams() != null)
                for (Pair<String, String> param : child.getParams()){
                    System.out.print(" " + param.getKey() + "=" + param.getValue());
                }
            System.out.println(">");
            
            if (child.getContent() != null){
                System.out.println(tabs + "\t"+ child.getContent());
            }
            
            print(child.getChildren(), tabs+"\t");
            System.out.println(tabs + "</" + child.getName() + ">");
        }
    }
    public void print(){
        //Public interface for print function that takes no arguments.
        System.out.println("<" + this.XMLDeclaration + ">");
        print(this.dummyRootNode.getChildren(), "");
    }
    
    
    public void printToFile() throws FileNotFoundException, IOException{
        //Redirects output to required file instead of stdout.
        PrintStream stdout = System.out;
        String newName = this.fileName.substring(0, this.fileName.lastIndexOf(".arxml")) + "_mod.arxml";
        PrintStream stream = new PrintStream(newName);
        System.setOut(stream);
        this.print();
        System.setOut(stdout);
    }

}
