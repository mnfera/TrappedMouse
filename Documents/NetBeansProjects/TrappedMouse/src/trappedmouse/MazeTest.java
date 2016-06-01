package trappedmouse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Carlos Dhyego 
 * @author Mikael
 * @author Walter
 */
public class MazeTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Maze maze = new Maze();
        
        int opc = 0;
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Enter option: "+" \n1 - enter your maze: "+ " \n2 - open a text file:");
        opc = entrada.nextInt();
        
        switch (opc) {
            case 1:
                maze.playMaze();
                maze.exitMaze();
                return;
            case 2:
                maze.playMaze2();
                maze.exitMaze();
                return;
            default:
                System.out.println("Option Error!");
        }
    }
}

