package trappedmouse;

/**
 *
 * @author Carlos Dhyego 
 * @author Mikael
 * @author Walter
 */
public class Cell {
    int x, y;
    
    public Cell() {
    }
    
    public Cell(int i, int j) {
        x = i; y = j;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals(Cell cell) {
        return x == cell.x && y == cell.y;
    }    
}
