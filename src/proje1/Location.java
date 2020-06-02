package proje1;


public class Location {
    private int x, y;
   
    public Location(){
        
    }
    
    public Location(int xValue, int yValue){
        x = xValue;
        y = yValue;
    }
    
    public int getXValue(){
        return x;
    }
    
    public int getYValue(){
        return y;
    }

    public void setXValue(int xVal){
        x = xVal;
    }
    
    public void setYValue(int yVal){
        y = yVal;
    }
}

