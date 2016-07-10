package trappedmouse;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos Dhyego 
 * @author Mikael
 * @author Walter
 */
public class Maze extends JPanel {
    private Cell currentCell,
                 exitCell = new Cell(),
                 entryCell= new Cell();
    private final char exitMarker = 'e',
                       entryMarker = 'm',
                       visited = '.',
                       passage = '0',
                       wall = '1';
    private Stack<Cell> mazeStack = new Stack<>();
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
        Stack<String> mazeRows = new Stack<>();
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
        Stack<String> mazeRows = new Stack<>();
        
        while (scan.hasNextLine()){
            String line = scan.nextLine();
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
    
    private void pushUnvisited (int row, int col) {
        if (maze[row][col] == passage || maze[row][col] == exitMarker)
            mazeStack.push(new Cell(row,col));
    }
    
    private void showMaze (){
        Panel c = new Panel();
        JLabel jlabel = new JLabel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 2;//ocupa 1 coluna
        constraints.gridheight = 1;//ocupa 1 fila
        URL mouseURL = getClass().getResource("resources/mouseicon.png");
        URL exitURL = getClass().getResource("resources/mouseicon.png");
        URL visitedURL = getClass().getResource("resources/mouseicon.png");
        URL passageURL = getClass().getResource("resources/mouseicon.png");
        URL wallURL = getClass().getResource("resources/mouseicon.png");
        Icon image1 = new ImageIcon(mouseURL);
        Icon image2 = new ImageIcon(exitURL);
        Icon image3 = new ImageIcon(visitedURL);
        Icon image4 = new ImageIcon(passageURL);
        Icon image5 = new ImageIcon(wallURL);
        JButton boton1 = new JButton(image1);
        JButton boton2 = new JButton(image2);
        JButton boton3 = new JButton(image3);
        JButton boton4 = new JButton(image4);
        JButton boton5 = new JButton(image5);
        for(int row = 1; row <= rows; row++){
            for(int col = 1; col <= cols; col++){
                System.out.printf("%s", maze[row][col]);
                    constraints.gridx = col;
                    constraints.gridy = row;
                    layout.addLayoutComponent(boton1, constraints);                
            }
            System.out.println("");
        }
        jlabel.setLayout(layout);
        JFrame ventanaPrincipal = new JFrame("Ejemplo Jframe");
        ventanaPrincipal.setBounds(0, 0, 800, 600);
        ventanaPrincipal.setContentPane(jlabel);
        ventanaPrincipal.setVisible(true);        
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void exitMaze () {
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
                showMaze();
                System.out.println("Caminho não encontrado");
                return;
            }
            else currentCell = (Cell) mazeStack.pop();
            showMaze();
        }
        showMaze();
        System.out.println("Caminho encontrado com sucesso!");
    }
}

