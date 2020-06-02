package proje1;

import java.util.LinkedList;
import java.util.Queue;


public class DarthVader extends Character {
    //Shortest Path Variables
    int row = 11;
    int column = 14;
    int enx;
    int eny;
    int[] arrx = new int[100];
    int[] arry = new int[100];
    int[][] visited = new int[row][column];
    int[][] cost = new int[row][column];
    int[][] matrix = new int[row][column];
    int move_count = 0;
    int nodes_left_in_layer = 1;
    int nodes_in_next_layer = 0;
    int counter = 0;
    boolean reached_end = false;
    
    Queue<Integer> rq = new LinkedList<Integer>();
    Queue<Integer> cq = new LinkedList<Integer>();
    public int a;
    
    
    public DarthVader(){
        
    }
    
    public DarthVader(String cName) {
        super(cName);
    }
    
    public DarthVader(String cName, int xVal, int yVal) {
        super(cName, xVal, yVal);
    }
    
    public void setName(String cName){
        super.setName(cName);
    }
    
    public String getName(){
        return super.getName();
    }
    
    public int getPathX(int i){
        return arrx[i];
    }
    public int getPathY(int i){
        return arry[i];
    }
    
    public int getCounter(){
        return counter;
    }
    
    @Override
    public void shortestPath(int sx, int sy, int dx, int dy){
            for(int p=0; p<counter;p++){
                arrx[p] = 0;
                arry[p] = 0;
            }
            for(int p=0;p<row;p++){
                for(int q=0;q<column;q++){
                    visited[p][q] = 0;
                    cost[p][q] = 99;
                }
            }
            
            rq.clear();
            cq.clear();
            move_count = 0;
            nodes_left_in_layer = 1;    
            nodes_in_next_layer = 0;
            counter = 0;
            fillMatrix();
            solve(sx,sy,dx,dy);
            findPath(dx, dy);
            
           
    }
    public int solve(int sr, int sc, int drow, int dcolumn) {
        rq.add(sr);
        cq.add(sc);
        visited[sr][sc] = 1;
        cost[sr][sc] = move_count;
        while (rq.size() > 0) {
            int r = rq.remove();
            int c = cq.remove();
            if (r == drow && c == dcolumn) {
                reached_end = true;
                break;
            }
            explore_childs(r, c);
            nodes_left_in_layer--;
            if (nodes_left_in_layer == 0) {
                nodes_left_in_layer = nodes_in_next_layer;
                nodes_in_next_layer = 0;
                move_count++;
            }

        }
        if (reached_end) {
            return move_count;
        }
        return -1;
    }
    public void explore_childs(int r, int c) {

        //Go Right
        if (isValid(r, c+1) && isSafe(r ,c+1)) {
            rq.add(r);
            cq.add(c + 1);
            visited[r][c+1] = 1;
            nodes_in_next_layer++;
            cost[r][c+1] = move_count;
        }
        //Go left
        if (isValid(r, c-1) && isSafe(r ,c-1)) {
            rq.add(r);
            cq.add(c - 1);
            visited[r][c-1] = 1;
            nodes_in_next_layer++;
            cost[r][c-1] = move_count;
        }
        //Go up
        if (isValid(r-1, c) && isSafe(r-1 ,c)) {
            rq.add(r-1);
            cq.add(c);
            visited[r-1][c] = 1;
            nodes_in_next_layer++;
            cost[r-1][c] = move_count;
        }
        //Go Down
        if (isValid(r+1, c) && isSafe(r+1 ,c)) {
            rq.add(r+1);
            cq.add(c);
            visited[r+1][c] = 1;
            nodes_in_next_layer++;
            cost[r+1][c] = move_count;
        }
    }
    public boolean isSafe(int x, int y) {
        if ( visited[x][y] != 1) {
            return true;
        }
        return false;
    }
    public boolean isValid(int x, int y) {
        if (x < 11 && x >= 0 && y >= 0 && y < 14) {
            return true;
        }
        return false;
    }
    public int findPath(int r, int c){
        int min = cost[r][c];
        if(cost[r][c] == 0)
            return 1;
        //right
        if(isValid(r, c+1)){
            if(cost[r][c + 1] < min){
                min = cost[r][c + 1];
                enx = r;
                eny = c+1;
                arrx[counter] = enx;
                arry[counter] = eny;
            }
        }
        //left
        if(isValid(r, c-1)){
            if(cost[r][c - 1] < min){
                min = cost[r][c - 1];
                enx = r;
                eny = c-1;
                arrx[counter] = enx;
                arry[counter] = eny;
            }
        }
        //down
        if(isValid(r+1, c)){
            if(cost[r+1][c] < min){
                min = cost[r+1][c];
                enx = r+1;
                eny = c;
                arrx[counter] = enx;
                arry[counter] = eny;
            }
        }
        //up
        if(isValid(r-1, c)){
            if(cost[r-1][c] < min){
                min = cost[r-1][c];
                enx = r-1;
                eny = c;
                arrx[counter] = enx;
                arry[counter] = eny;
            }
        }
                counter++;
                findPath(enx, eny);
                return 0;
    }
    public void fillMatrix(){
        for(int i =0; i<row; i++){
            for(int j=0; j<column; j++){
                matrix[i][j] = 1;
                cost[i][j] = 99;
            }
        }
    }
}