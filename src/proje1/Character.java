package proje1;
import java.util.*;


public class Character {
    private String name = new String();
    public Location characterLoc = new Location();
    public String characterLabel = new String();
    int startX;
    int startY;
    public Character(){
        
    }
    
    public Character(String cName){
        name = cName;
    }
    
    public Character(String cName, int xVal, int yVal){
        name = cName;
        characterLoc.setXValue(xVal);
        characterLoc.setYValue(yVal);
    }
    
    public void setStartPoint(int x, int y){
        startX = x;
        startY = y;
    }
    
    public void setLocation(int xVal, int yVal){
         characterLoc.setXValue(xVal);
         characterLoc.setYValue(yVal);
    }
    
    public Location getLocation(){
        return characterLoc;
    }
    
    public String getName(){
        return name;
    }
    
    public void setCharacterLabel(String label){
        characterLabel = label;
    }
    
    public String getCharacterLabel(){
        return characterLabel;
    }
    
    public void setName(String cName){
        name = cName;
    }
    
   
    
    public void shortestPath(int sx, int sy, int dx, int dy){
        
    }
}