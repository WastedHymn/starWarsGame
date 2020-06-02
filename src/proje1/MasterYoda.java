package proje1;


public class MasterYoda extends Character {
    private float Lives = 3;
   
    public MasterYoda(String cName, int xVal, int yVal) {
         super(cName, xVal, yVal);
    }
    
    public void damage(){
        Lives -= 0.5;
    }
    
    
    
    public void setName(String cName){
        super.setName(cName);
    }
    
    public String getName(){
        return super.getName();
    }
    
    public void moveUp(){
        characterLoc.setXValue((characterLoc.getXValue()-1));
    }
    
    public void moveDown(){
        characterLoc.setXValue((characterLoc.getXValue()+1));
    }
    
    public void moveRight(){
        characterLoc.setYValue((characterLoc.getYValue()+1));
    }
    public void moveLeft(){
        characterLoc.setYValue((characterLoc.getYValue()-1));
    }
}