
package proje1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
//import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Map {
    private Button[][] arrayBtn = new Button[11][14];
    private int[][] matrix = new int[11][14];
    private Font font = new Font("sansserif", Font.PLAIN, 12);
    private Character player;
    private String playerLabel = new String();
    private ArrayList<Character> enemies = new ArrayList<Character>();
    private int a = 0, b=0, c=0, d=0;
    int characterCounter = 0;    
    
    public boolean isValid(int x, int y){
        if(x>=0 && x<11 && y<14 && y>=0){
            return true;
        }
        return false;
    }
    
    public void endGame(){
        System.exit(0);
    }
    
    class KeyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        int key = e.getKeyCode();
        int x,y;
        Location t = new Location();
        t = player.getLocation();
        x = t.getXValue();
        y = t.getYValue();
        
        if(key == KeyEvent.VK_UP){
            
            if(a%2 == 0){
                if(player.getCharacterLabel().matches("Yoda") && (matrix[x-1][y] != 0) && isValid((x-1), y)){
                    ((MasterYoda)player).moveUp();
                    movePlayerUp(x, y);
  
                }else if(player.getCharacterLabel().matches("Luke") && (matrix[x-1][y] != 0) && isValid((x-1), y)){
                    ((LukeSkywalker)player).moveUp();  
                    movePlayerUp(x, y);
                }
              
                clearShortestPath();
                findShortestPath();
                moveEnemy();
                findShortestPath();
                drawShortestPath();
            }
            
            a++;
        }
        if(key == KeyEvent.VK_DOWN){
            if(b%2 == 0){
                
                 clearShortestPath();
                if(player.getCharacterLabel().matches("Yoda") && (matrix[x+1][y] != 0) && isValid((x+1), y)){
                    ((MasterYoda)player).moveDown();
                     movePlayerDown(x, y);
                }else if(player.getCharacterLabel().matches("Luke") && (matrix[x+1][y] != 0) && isValid((x+1), y)){
                    
                    ((LukeSkywalker)player).moveDown();
                    movePlayerDown(x, y);
                }
               
                clearShortestPath();
                findShortestPath();
                moveEnemy();
                findShortestPath();
                drawShortestPath();
                
            }
            b++;
        }
        if(key == KeyEvent.VK_RIGHT){
            if(c%2 == 0){
               
                
                if(player.getCharacterLabel().matches("Yoda") && (matrix[x][y+1] != 0) && isValid(x, (y+1))){
                   
                    ((MasterYoda)player).moveRight();
                    if((((MasterYoda)player).getLocation().getXValue() == 9) && (((MasterYoda)player).getLocation().getYValue() == 13) ){
                        System.out.println("YOU WON!");
                        endGame();
                    }
                    movePlayerRight(x, y);
                }else if(player.getCharacterLabel().matches("Luke") && (matrix[x][y+1] != 0) && isValid(x, (y+1))){
                  
                    ((LukeSkywalker)player).moveRight();
                    if((((LukeSkywalker)player).getLocation().getXValue() == 9) && (((LukeSkywalker)player).getLocation().getYValue() == 13) ){
                        System.out.println("YOU WON!");
                        endGame();
                    }
                    movePlayerRight(x, y);
                }
                clearShortestPath();
                findShortestPath();
                moveEnemy();
                findShortestPath();
                drawShortestPath();
            }
            c++;
        }
        if(key == KeyEvent.VK_LEFT){
            if(d%2 == 0){
            
                
                if(player.getCharacterLabel().matches("Yoda") && (matrix[x][y-1] != 0) && isValid(x, (y-1))){
                    ((MasterYoda)player).moveLeft();
                    movePlayerLeft(x, y);
                }else if(player.getCharacterLabel().matches("Luke") && (matrix[x][y-1] != 0) && isValid(x, (y-1))){
                    
                    ((LukeSkywalker)player).moveLeft();
                    movePlayerLeft(x, y);
                }
                clearShortestPath();
                findShortestPath();
                moveEnemy();
                findShortestPath();
                drawShortestPath();
            }
            d++;
        }
        //Allow the event to be redispatched
        return false;
    }
}
    
    public void createMap() throws FileNotFoundException, IOException{
        
        //Hijack the keyboard manager
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher( new KeyDispatcher() );
        
        Scanner input = new Scanner(System.in);
        String letter = new String();
        ReadFromFile fileReader = new ReadFromFile();

        //Read data
        fileReader.textOku(matrix, enemies);
        //Print map
        System.out.println("Map from harita.txt");
        System.out.println(Arrays.deepToString(matrix));
        //Print enemy infos
        for(Character enemyN : enemies){
            System.out.println("Enemy name: " + enemyN.getName());
            Location temp = enemyN.getLocation();
            System.out.println("Enemy location: " + temp.getXValue() + "," + temp.getYValue() );   
        } 
        
        //Creating labyrinth
            JFrame frame1 = new JFrame();
            
            JPanel border = new JPanel();
            border.setLayout(new BorderLayout(100,100));
            
            JPanel buttonpanel = new JPanel();
            buttonpanel.setLayout(new GridLayout(11,14));
            
            //Button Coloring
            for(int i = 0; i<11; i++){
                for(int j = 0; j<14; j++){
                    Button temp = new Button();
                    if(matrix[i][j] == 0){
                        temp.setLabel("0");
                        temp.setBackground(Color.white);
                    }else if(matrix[i][j] == 1){
                        temp.setBackground(Color.cyan);
                    }
                    if(i==5 && j == 6){
                        temp.setBackground(Color.yellow);
                    }
                    buttonpanel.add(temp);
                    arrayBtn[i][j] = temp;
                }
            }
        //Choosing hero
        System.out.println("Choose a Hero!");
        System.out.println("1-Luke Skywalker");
        System.out.println("2-Master Yoda");
        System.out.print("Enter character name:");
        letter = input.nextLine();
        if(letter.startsWith("Luke") || letter.startsWith("luke")){
            player = new LukeSkywalker("Luke Skywalker", 5, 6);
            player.setCharacterLabel("Luke"); 
        }
        if(letter.startsWith("Master") || letter.startsWith("master")){
            player = new MasterYoda("Master Yoda", 5, 6);
            player.setCharacterLabel("Yoda"); 
        }
      while(!(letter.startsWith("Luke") || letter.startsWith("luke")) && !(letter.startsWith("Master") || letter.startsWith("master"))){
            System.out.print("Enter character name:");
            letter = input.nextLine();
             if(letter.startsWith("Luke") || letter.startsWith("luke")){
            player = new LukeSkywalker("Luke Skywalker", 5, 6);
            player.setCharacterLabel("Luke");
            }
        if(letter.startsWith("Master") || letter.startsWith("master")){
            player = new MasterYoda("Master Yoda", 5, 6);
            player.setCharacterLabel("Yoda"); 
        }
      }
                Button t = new Button();
                t = arrayBtn[5][6];
                t.setLabel(player.getCharacterLabel());
                t.setFont(font);
                arrayBtn[5][6] = t;
                
                Border emtyBorder = BorderFactory.createEmptyBorder(20,20,20,20);
                border.setBackground(Color.DARK_GRAY);
                border.setBorder(emtyBorder);
                border.add(buttonpanel, BorderLayout.CENTER);
               
            frame1.setSize(1000,1000);
            frame1.setTitle("Star Wars");
            frame1.setResizable(false);
            frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame1.setContentPane(border);
            frame1.setBounds(230,100,600,600);
            frame1.setVisible(true);
            spawnEnemy();
            findShortestPath();
            drawShortestPath();
            
    }
    
    public void clearShortestPath(){
       
        for(Character enemyN : enemies){
            if(enemyN.getCharacterLabel().equalsIgnoreCase("Storm")){
                int length = ((Stormtrooper)enemyN).getCounter();
                for(int i=0;i<length;i++){
                    Button temp = new Button();
                    int x = ((Stormtrooper)enemyN).getPathX(i);
                    int y = ((Stormtrooper)enemyN).getPathY(i);
                    temp = arrayBtn[x][y];
                    temp.setBackground(Color.CYAN);       
                    arrayBtn[x][y] = temp;
                }
            }
            if(enemyN.getCharacterLabel().equalsIgnoreCase("DarthV")){
                int length = ((DarthVader)enemyN).getCounter();
                for(int i=0;i<length;i++){
                    Button temp = new Button();
                    int x = ((DarthVader)enemyN).getPathX(i);
                    int y = ((DarthVader)enemyN).getPathY(i);
                    temp = arrayBtn[x][y];
                    if(matrix[x][y] == 0){
                        temp.setBackground(Color.white);       
                    }else{
                        temp.setBackground(Color.CYAN);       
                    }
                    arrayBtn[x][y] = temp;
                }
            }
            if(enemyN.getCharacterLabel().equalsIgnoreCase("Kylo")){
                int length = ((KyloRen)enemyN).getCounter();
                for(int i=0;i<length;i++){
                    Button temp = new Button();
                    int x = ((KyloRen)enemyN).getPathX(i);
                    int y = ((KyloRen)enemyN).getPathY(i);
                    temp = arrayBtn[x][y];
                    temp.setBackground(Color.CYAN);       
                    arrayBtn[x][y] = temp;
                }
            }    
        }
    }
    
    public void moveEnemy(){
         for(Character enemyN : enemies){
             int x, y, x2, y2;
             Button temp;
             if(enemyN.getCharacterLabel().equalsIgnoreCase("Storm")){
                 x2 = ((Stormtrooper)enemyN).getLocation().getXValue();
                 y2 = ((Stormtrooper)enemyN).getLocation().getYValue();
                 temp = arrayBtn[x2][y2];
                 temp.setLabel("");
                 x = ((Stormtrooper)enemyN).getPathX(((Stormtrooper)enemyN).getCounter()-1);
                 y = ((Stormtrooper)enemyN).getPathY(((Stormtrooper)enemyN).getCounter()-1);
                 ((Stormtrooper)enemyN).setLocation(x, y);
                 temp = arrayBtn[x][y];
                 temp.setLabel(((Stormtrooper)enemyN).getCharacterLabel());
                 arrayBtn[x][y] = temp;
             }
             if(enemyN.getCharacterLabel().equalsIgnoreCase("DarthV")){
                 x2 = ((DarthVader)enemyN).getLocation().getXValue();
                 y2 = ((DarthVader)enemyN).getLocation().getYValue();
                 if(matrix[x2][y2] == 0){
                     temp = arrayBtn[x2][y2];
                     temp.setLabel("0");
                     arrayBtn[x2][y2] = temp;
                 }else{
                     temp = arrayBtn[x2][y2];
                     temp.setLabel("");
                     arrayBtn[x2][y2] = temp;
                 }
                 x = ((DarthVader)enemyN).getPathX(((DarthVader)enemyN).getCounter()-1);
                 y = ((DarthVader)enemyN).getPathY(((DarthVader)enemyN).getCounter()-1);
                 ((DarthVader)enemyN).setLocation(x, y);
                 temp = arrayBtn[x][y];
                 temp.setLabel(((DarthVader)enemyN).getCharacterLabel());
                 arrayBtn[x][y] = temp;
             }
             if(enemyN.getCharacterLabel().equalsIgnoreCase("Kylo")){
                 boolean valid = false;
                 
                 x = ((KyloRen)enemyN).getPathX(((KyloRen)enemyN).getCounter()-2);
                 y = ((KyloRen)enemyN).getPathY(((KyloRen)enemyN).getCounter()-2); 
                 if(isValid(x, y)){
                     for(int i=0;i<((KyloRen)enemyN).getCounter();i++){
                     if(((KyloRen)enemyN).getPathX(i)== x && ((KyloRen)enemyN).getPathY(i) == y )
                         valid = true;
                 }
                 }
                 
                 if(valid){
                 x2 = ((KyloRen)enemyN).getLocation().getXValue();
                 y2 = ((KyloRen)enemyN).getLocation().getYValue();
                 temp = arrayBtn[x2][y2];
                 temp.setLabel("");
                 ((KyloRen)enemyN).setLocation(x, y);
                 temp = arrayBtn[x][y];
                 temp.setLabel(((KyloRen)enemyN).getCharacterLabel());
                 arrayBtn[x][y] = temp;
                 }
                 
             }
            
              characterCounter++;
         }
         characterCounter = 0;
    }
    
    public void findShortestPath(){

        for(Character enemyN : enemies){
            if(enemyN.getCharacterLabel().equalsIgnoreCase("Storm")){
                int sx,sy,dx,dy;
                sx = enemyN.getLocation().getXValue();
                sy = enemyN.getLocation().getYValue(); 
                dx = player.getLocation().getXValue();
                dy = player.getLocation().getYValue();
                ((Stormtrooper)enemyN).shortestPath(sx, sy, dx, dy);
            }
            if(enemyN.getCharacterLabel().equalsIgnoreCase("DarthV")){
                int sx,sy,dx,dy;
                sx = enemyN.getLocation().getXValue();
                sy = enemyN.getLocation().getYValue(); 
                dx = player.getLocation().getXValue();
                dy = player.getLocation().getYValue();

                ((DarthVader)enemyN).shortestPath(sx, sy, dx, dy);                    
            }
            if(enemyN.getCharacterLabel().equalsIgnoreCase("Kylo")){
                int sx,sy,dx,dy;
                sx = enemyN.getLocation().getXValue();
                sy = enemyN.getLocation().getYValue(); 
                dx = player.getLocation().getXValue();
                dy = player.getLocation().getYValue();
                
                ((KyloRen)enemyN).shortestPath(sx, sy, dx, dy);
               
            }    
        }
        
    }
    
    public void drawShortestPath(){
        for(Character enemyN : enemies){
            if(enemyN.getCharacterLabel().equalsIgnoreCase("Storm")){
                int length = ((Stormtrooper)enemyN).getCounter();
                for(int i=0;i<length;i++){
                        
                        Button temp = new Button();
                    int x = ((Stormtrooper)enemyN).getPathX(i);
                    int y = ((Stormtrooper)enemyN).getPathY(i);
                    temp = arrayBtn[x][y];
                    temp.setBackground(Color.red);       
                    arrayBtn[x][y] = temp;
                    
                }
                
            }
            if(enemyN.getCharacterLabel().equalsIgnoreCase("DarthV")){
                int length = ((DarthVader)enemyN).getCounter();
                for(int i=0;i<length;i++){
                   
                        Button temp = new Button();
                    int x = ((DarthVader)enemyN).getPathX(i);
                    int y = ((DarthVader)enemyN).getPathY(i);
                    temp = arrayBtn[x][y];
                    temp.setBackground(Color.MAGENTA);       
                    arrayBtn[x][y] = temp;
                   
                }
                    
            }
            if(enemyN.getCharacterLabel().equalsIgnoreCase("Kylo")){
                int length = ((KyloRen)enemyN).getCounter();
                for(int i=0;i<length;i++){
                    Button temp = new Button();
                    int x = ((KyloRen)enemyN).getPathX(i); 
                    int y = ((KyloRen)enemyN).getPathY(i);
                    temp = arrayBtn[x][y];
                    temp.setBackground(Color.ORANGE);       
                    arrayBtn[x][y] = temp;
                }
            }    
        }
    }
    
    public void spawnEnemy(){
         for(Character enemyN : enemies){
             System.out.println(enemyN.getName());
            Location temp = enemyN.getLocation();
            int x = temp.getXValue();
            int y = temp.getYValue();
            Button button = new Button();
            button = arrayBtn[x][y];
            button.setFont(font);
            if(null != enemyN.getName())
                button.setLabel(enemyN.getCharacterLabel());
            arrayBtn[x][y] = button;
        }
    }
    
    private void movePlayerUp(int x, int y) {
       Button temp = new Button();
       //Delete "P" from label
       temp = arrayBtn[x][y];
       temp.setLabel("");
       arrayBtn[x][y] = temp;
       //Write "P" to label
       temp = arrayBtn[x-1][y];
       temp.setFont(font);
       temp.setLabel(player.getCharacterLabel());
       arrayBtn[x-1][y] = temp;
       
    }
    
    private void movePlayerDown(int x, int y) {
       Button temp = new Button();
       //Delete "P" from label
       temp = arrayBtn[x][y];
       temp.setLabel("");       
       arrayBtn[x][y] = temp;
       //Write "P" to label
       temp = arrayBtn[x+1][y];
       temp.setFont(font);
       temp.setLabel(player.getCharacterLabel());
       arrayBtn[x+1][y] = temp;
    }
    
    private void movePlayerRight(int x, int y) {
       Button temp = new Button();
       //Delete "P" from label
       temp = arrayBtn[x][y];
       temp.setLabel("");
       arrayBtn[x][y] = temp;
       //Write "P" to label
       temp = arrayBtn[x][y+1];
       temp.setFont(font);
       temp.setLabel(player.getCharacterLabel());
       arrayBtn[x][y+1] = temp;
    }
    private void movePlayerLeft(int x, int y) {
       Button temp = new Button();
       //Delete "P" from label
       temp = arrayBtn[x][y];
       temp.setLabel("");
       arrayBtn[x][y] = temp;
       //Write "P" to label
       temp = arrayBtn[x][y-1];
       temp.setFont(font);
       temp.setLabel(player.getCharacterLabel());
       arrayBtn[x][y-1] = temp;
    }
}