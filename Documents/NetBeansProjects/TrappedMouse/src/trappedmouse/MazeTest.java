package trappedmouse;

import java.io.FileNotFoundException;

/**
 *
 * @author Mikael
 */
public class MazeTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Maze2 maze = new Maze2();
        
        maze.playMaze2();
        maze.exitMaze();
    }
}
