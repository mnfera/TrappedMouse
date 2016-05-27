package trappedmouse;

/**
 *
 * @author Mikael
 */
public class Cell {
    int x, y;
    
    public Cell() {
    }
    
    public Cell(int i, int j) {
        x = i; y = j;
    }
    
    public boolean equals(Cell cell) {
        return x == cell.x && y == cell.y;
    }    
}
