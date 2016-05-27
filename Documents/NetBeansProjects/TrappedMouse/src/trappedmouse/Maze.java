package trappedmouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

/**
 *
 * @author Mikael
 */
public class Maze {
    private int rows = 0, cols = 0;
    private char[][] store;
    private Cell currentCell, exitCell = new Cell(), entryCell = new
            Cell();
    private final char exitMarker = 'e', entryMarker = 'm', visited = '.';
    private final char passage = '0', wall = '1';
    private mazeStack mazeStack = new mazeStack();
    
    public Maze() {
        int row = 0, col = 0;

        mazeStack mazeRows = new mazeStack();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(isr);
        System.out.println("Enter a rectangular maze using the following "
                            + "characters:\nm - entry\ne - exit\n1 - wall\n0 - passage\n"
                            + "Enter one line at at time; end with Ctrl-z:");

        try {
            String str = buffer.readLine();
            while (str != null) {
                row++;
                cols = str.length();
                str = "1" + str + "1"; // put 1s in the borderline cells;
                mazeRows.push(str);
                if (str.indexOf(exitMarker) != -1) {
                    exitCell.x = row;
                    exitCell.y = str.indexOf(exitMarker);
                } else {
                }
                if (str.indexOf(entryMarker) != -1) {
                    entryCell.x = row;
                    entryCell.y = str.indexOf(entryMarker);
                }
                str = buffer.readLine();
            }
        } catch(IOException eof) {
        }
        rows = row;
        store = new char[rows+2][]; // create a 1D array of char arrays;
        store[0] = new char[cols+2]; // a borderline row;
        for ( ; !mazeRows.isEmpty(); row--)
            store[row] = ((String) mazeRows.pop()).toCharArray();
            store[rows+1] = new char[cols+2]; // another borderline row;
        for (col = 0; col <= cols+1; col++) {
            store[0][col] = wall; // fill the borderline rows with 1s;
            store[rows+1][col] = wall;
        }
    }
    
    private void display(PrintStream out) {
        for (int row = 0; row <= rows+1; row++)
            out.println(Arrays.toString(store[row]));
        out.println();
    }
    
    private void pushUnvisited(int row, int col) {
        if (store[row][col] == passage || store[row][col] == exitMarker)
            mazeStack.push(new Cell(row,col));
    }
    
    public void exitMaze(PrintStream out) {
        currentCell = entryCell;
        out.println();
        while (!currentCell.equals(exitCell)) {
            int row = currentCell.x;
            int col = currentCell.y;
            display(System.out); // print a snapshot;
            if (!currentCell.equals(entryCell))
                store[row][col] = visited;

            pushUnvisited(row-1,col);
            pushUnvisited(row+1,col);
            pushUnvisited(row,col-1);
            pushUnvisited(row,col+1);
            if (mazeStack.isEmpty()) {
                display(out);
                out.println("Failure");
                return;
            }
            else currentCell = (Cell) mazeStack.pop();
        }
        display(out);
        out.println("Success");
    }
}
