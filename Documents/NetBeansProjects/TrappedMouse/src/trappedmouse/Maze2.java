package trappedmouse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Mikael
 */
public class Maze2 {
    private Cell currentCell,
                 exitCell = new Cell(),
                 entryCell= new Cell();
    private final char exitMarker = 'e',
                       entryMarker = 'm',
                       visited = '.',
                       passage = '0',
                       wall = '1';
    private mazeStack<Cell> mazeStack = new mazeStack<>();
    private char[][] maze;
    private int rows = 0,
                cols = 0;
    private String dirFile = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "maze.txt";
    private File f = new File(dirFile);
    
    
    public void playMaze (){
        int row = 0,
            col = 0;
        System.out.println("Enter a rectangular maze using the following "
                            + "characters:\nm - entry\ne - exit\n1 - wall\n0 - passage\n"
                            + "Enter one line at at time; For END use exit:");
        
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        mazeStack<String> mazeRows = new mazeStack<>();
        while (!line.contentEquals("exit")){
            row++;
            cols = line.length();
            line = "1" + line + "1"; // put 1s in the borderline cells;
            mazeRows.push(line);
            if(line.indexOf(exitMarker) >= 0){
                exitCell.x = row;
                exitCell.y = line.indexOf(exitMarker);
            }
            if(line.indexOf(entryMarker) >= 0){
                entryCell.x = row;
                entryCell.y = line.indexOf(entryMarker);
            }
            line = scan.nextLine();
        }
        rows = row;
        maze = new char[rows+2][]; // create a 1D array of char arrays;
        maze[0] = new char[cols+2]; // a borderline row;
        for ( ; !mazeRows.isEmpty(); row--)
            maze[row] = ((String) mazeRows.pop()).toCharArray();
            maze[rows+1] = new char[cols+2]; // another borderline row;
        for (col = 0; col <= cols+1; col++) {
            maze[0][col] = wall; // fill the borderline rows with 1s;
            maze[rows+1][col] = wall;
        }
    }
    
    public void playMaze2 () throws FileNotFoundException{
        int row = 0,
            col = 0;
        
        Scanner scan = new Scanner(f);
        
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            row++;
            cols = line.length();
            line = "1" + line + "1"; // put 1s in the borderline cells;
            mazeStack.push(line);
            if(line.indexOf(exitMarker) >= 0){
                exitCell.x = row;
                exitCell.y = line.indexOf(exitMarker);
            }
            if(line.indexOf(entryMarker) >= 0){
                entryCell.x = row;
                entryCell.y = line.indexOf(entryMarker);
            }
        }
        rows = row;
        maze = new char[rows+2][]; // create a 1D array of char arrays;
        maze[0] = new char[cols+2]; // a borderline row;
        for ( ; !mazeStack.isEmpty(); row--)
            maze[row] = ((String) mazeStack.pop()).toCharArray();
            maze[rows+1] = new char[cols+2]; // another borderline row;
        for (col = 0; col <= cols+1; col++) {
            maze[0][col] = wall; // fill the borderline rows with 1s;
            maze[rows+1][col] = wall;
        }
    }
    
    private void pushUnvisited (int row, int col) {
        if (maze[row][col] == passage || maze[row][col] == exitMarker)
            mazeStack.push(new Cell(row,col));
    }
    
    private String showMaze (){
        for(int row = 1; row <= rows; row++){
            for(int col = 1; col <= cols; col++){
                System.out.printf("%s", maze[row][col]);                
            }
            System.out.println("");
        }                    
    }
    
    public void exitMaze (){
        currentCell = entryCell;
        while (!currentCell.equals(exitCell)){
            int row = currentCell.x;
            int col = currentCell.y;
            if (!currentCell.equals(entryCell))
                maze[row][col] = visited;
            
            
            pushUnvisited(row-1,col); //cima
            pushUnvisited(row+1,col); //baixo
            pushUnvisited(row,col-1); //esquerda
            pushUnvisited(row,col+1); //direita
            if (mazeStack.isEmpty()) {
                showMaze();//sout(this)
                System.out.println("Caminho não encontrado");
                return;
            }
            else currentCell = (Cell) mazeStack.pop();
            
            System.out.println(this);
           
        }
        showMaze();
        System.out.println("Caminho encontrado com sucesso!");
    }  

    @Override
    public String toString() {
        return showMaze();
    }
    
    
}


