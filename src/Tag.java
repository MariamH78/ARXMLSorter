package arxmlsorter;

import javafx.util.Pair;
import java.util.ArrayList;

/**
 *      
 * @author MariamH78
 */

public class Tag implements Comparable<Tag>{
    private final String name;
    private ArrayList<Pair<String, String>> params;
    private ArrayList<Tag> children = new ArrayList<>();
    private String content = null;
    
    public Tag(){
        this.name = "Dummy";
    }
    public Tag(String name, ArrayList<Pair<String, String>> params, String content){
        this.name = name;
        this.params = params;
        this.content = content;
    }
    public void addChild(Tag child){
        children.add(child);
    }
    public void addContent(String content){
        content = content.trim();
        
        if (content.length() == 0) return;
        
        if (this.content != null){
            this.content = this.content.concat(content);
        } else {
            this.content = content;
        }
    }
    public String getName(){
        return this.name;
    }
    public String getContent(){
        return this.content;
    }
    public ArrayList<Tag> getChildren(){
        return this.children;
    }
    public ArrayList<Pair<String, String>> getParams(){
        return this.params;
    }
    
    private String findShortName() {
        for(Tag child : this.children) {
            if(child.name.equals("SHORT-NAME")) {
                return child.getContent();
            }
        }
        return null;
    }
    
    @Override
    public int compareTo(Tag other){
        String first = this.findShortName();
        String second = other.findShortName();
        try {
            return first.compareTo(second);
        } catch (NullPointerException e){
            if (first == null)
                if (second == null)
                    return 0;
                else
                    return -1;
            else
                return 1;
        }
        
    }
} 

