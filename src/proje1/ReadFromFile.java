
package proje1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ReadFromFile {
    
     public void textOku(int matrix[][], ArrayList<Character> enemies) throws FileNotFoundException, IOException{
       FileInputStream fstream = new FileInputStream("Harita.txt");
       BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
       String str;
       
       char chr;
       char chr2;
       int index, i = 0, b = 0;
       while ((str = br.readLine()) != null) {
           int xVal=0, yVal=0;
           String enemyName = new String();
           if(str.startsWith("Karakter")){
               index = str.indexOf(",");
               //Char at index
               chr = str.charAt(9);
               chr2 = str.charAt(index+6);
               
               //DarthVader
               if(chr == 'D'){
                   DarthVader temp = new DarthVader("DarthVader");
                   temp.setCharacterLabel("DarthV");
                   if(chr2 == 'A'){
                     temp.setLocation(5, 0);//5,0
                     temp.setStartPoint(5, 0);
                     temp.setLocation(5, 0);
                    enemies.add(temp);
                   }else if(chr2 == 'B'){
                      temp.setLocation(0, 4);//0,4
                      temp.setStartPoint(0, 4);
                    enemies.add(temp);
                   }else if(chr2 == 'C'){
                      temp.setLocation(0, 12);//0,12
                      temp.setStartPoint(0, 12);
                      temp.setLocation(0, 12);
                    enemies.add(temp);
                   }else if(chr2 == 'D'){
                      temp.setLocation(5, 13);//5,13
                      temp.setStartPoint(5, 13);
                      temp.setLocation(5, 13);
                    enemies.add(temp);
                   }else if(chr2 == 'E'){
                      temp.setLocation(10, 4);//10,4
                      temp.setStartPoint(10, 4);
                      temp.setLocation(10, 4);
                    enemies.add(temp);  
                   }
                //KyloRen   
               }else if(chr == 'K'){
                   KyloRen temp = new KyloRen("KyloRen");
                   temp.setCharacterLabel("Kylo");
                   if(chr2 == 'A'){
                      temp.setLocation(5,0);
                      temp.setStartPoint(5, 0);
                      temp.setLocation(5, 0);
                     enemies.add(temp);
                   }else if(chr2 == 'B'){
                      temp.setLocation(0,4);
                      temp.setStartPoint(0, 4);
                      temp.setLocation(0, 4);
                     enemies.add(temp); 
                   }else if(chr2 == 'C'){
                      temp.setLocation(0,12);
                      temp.setStartPoint(0, 12);
                      temp.setLocation(0, 12);
                     enemies.add(temp);
                   }else if(chr2 == 'D'){
                      temp.setLocation(5,13);
                      temp.setStartPoint(5, 13);
                      temp.setLocation(5, 13);
                     enemies.add(temp);  
                   }else if(chr2 == 'E'){
                      temp.setLocation(10,4);
                      temp.setStartPoint(10, 4);
                      temp.setLocation(10, 4);
                     enemies.add(temp);
                   }
                //Stormtrooper   
               }else if(chr == 'S'){
                   Stormtrooper temp = new Stormtrooper("Stormtrooper");
                   temp.setCharacterLabel("Storm");
                   if(chr2 == 'A'){
                     temp.setLocation(5,0);
                     temp.setStartPoint(5, 0);
                     temp.setLocation(5, 0);
                     enemies.add(temp);
                   }else if(chr2 == 'B'){
                     temp.setLocation(0,4);
                     temp.setStartPoint(0, 4);
                     temp.setLocation(0, 4);
                     enemies.add(temp);  
                   }else if(chr2 == 'C'){
                     temp.setLocation(0,12);
                     temp.setStartPoint(0, 12);
                     temp.setLocation(0, 12);
                     enemies.add(temp);  
                   }else if(chr2 == 'D'){
                     temp.setLocation(5,13);
                     temp.setStartPoint(5, 13);
                     temp.setLocation(5, 13);
                     enemies.add(temp); 
                   }else if(chr2 == 'E'){
                     temp.setLocation(10,4);
                     temp.setStartPoint(10, 4);
                     temp.setLocation(10, 4);
                     enemies.add(temp); 
                   }
               }
              
  
           }else{
               char[] matrix2 =str.toCharArray();
               for(int j=0; j<28; j = j+2){
                   char c = matrix2[j];
                   matrix[i][b] = java.lang.Character.getNumericValue(c);
                   b++;
               }
               i++;
               b=0;
           }
       }
        //Close file
        fstream.close();
    }
 
     public void onlyReadMap(int[][] matrix, int[][] cost, int[][] visited) throws FileNotFoundException, IOException{
        FileInputStream fstream = new FileInputStream("Harita.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String str;
        int index, i = 0, b = 0;
        
        while ((str = br.readLine()) != null) {
            if (str.startsWith("Karakter")) {
                //Do nothing
            } else {
                char[] matrix2 = str.toCharArray();
                
                for (int j = 0; j < 28; j = j + 2) {
                    char c = matrix2[j];
                    if (c == '0') {
                        cost[i][b] = -1;
                    }
                    if (c == '1') {
                        cost[i][b] = 99;
                    }
                    matrix[i][b] = java.lang.Character.getNumericValue(c);
                    visited[i][b] = 0;
                    
                    b++;
                }
                i++;
                b = 0;
            }
        }
         //Close file
        fstream.close();
     }
}