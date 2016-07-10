package processadordecores;

/**
 *
 * @author Carlos Dhyego 
 * @author Mikael
 * @author Walter
 * 
 */
public class ColorTime {
    public String color;
    public int time;
    
    public ColorTime (String color, int time) {
        this.color = color;
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }   
    
    
}
