/**
 * MazeSolver gives the solution to a maze in the form of ordered pairs.
 */
import java.util.Scanner;
import java.io.*;
public class MazeSolver {
    public static int startX = 0;
    public static int startY = 0;
    public static int maxRow = 0;
    public static int maxCol = 0;
    public static int [][] maze = new int[1000][1000];
    public static int [][] explored = new int [1000][1000];
    public static Stack coordinateX = new Stack();
    public static Stack coordinateY = new Stack();
    /**
     * Scans the maze into a 2D array. 
     * Converts the maze symbols to integers.
     * 1 is start and pathways, 2 is a wall, 3 is the end. 
     * Also builds a wall around the maze in the array (prevents out of bounds).
     * Then uses the solve method to compute and print a solution. 
     */
    public static void main (String [] args) {
        try {
            Scanner scan = new Scanner(new BufferedReader(new FileReader("maze.txt")));
            int y = 0;
            int x = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (x = 0; x < line.length(); x++) {
                    char symbol = line.charAt(x);
                    if (symbol == '@') {
                        maze[x+1][y+1] = 1;
                        startX = x + 1;
                        startY = y + 1;
                    }
                    else if (symbol == '.') {
                        maze[x+1][y+1] = 1;
                    }
                    else if (symbol == '#') {
                        maze[x+1][y+1] = 2;
                    }
                    else if (symbol == '$') {
                        maze[x+1][y+1] = 3;
                    }
                }
                maxCol = line.length();
                maxRow++; 
                y++;
            }
            for (int leftCol = 0; leftCol < maxCol + 1; leftCol++) {
                maze[0][leftCol] = 2;
            }
            for (int topRow = 0; topRow < maxRow + 1; topRow++) {
                maze [topRow][0] = 2;
            }
            if (solve(startX, startY)) {
                while (coordinateX.peek() != null && coordinateY.peek() != null) {
                    System.out.println((coordinateY.pop() - 1) + "," + (coordinateX.pop() - 1));
                }
            } 
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Solves the maze given the starting ordered pair.
     * @param x The current x coordinate 
     * @param y The current y coordinate
     * @return returns true when the maze is solvable from a given pair of coordinates, false if the path results in a dead end.
     */
    public static boolean solve(int x, int y) {
        explored [x][y] = 1;
        if (maze[x][y] == 3) {
            coordinateX.push(x);
            coordinateY.push(y);
            return true;
        }
        
        if (maze[x+1][y] != 2 && maze[x+1][y] != 0 && explored [x+1][y] != 1) {
            if (solve(x+1,y) == true) {
                coordinateX.push(x);
                coordinateY.push(y);
                return true;
            }
        }
        
        if (maze[x][y+1] !=2 && maze[x][y+1] !=0 && explored [x][y+1]!= 1) {
            if(solve(x,y+1) == true) {
                coordinateX.push(x);
                coordinateY.push(y);
                return true;
            }
        }

        if(maze[x-1][y] != 2 && maze[x-1][y] != 0 && explored[x-1][y] != 1) {
            if(solve(x-1,y) == true) {
                coordinateX.push(x);
                coordinateY.push(y);
                return true;
            }
        }

        if (maze [x][y-1] != 2 && maze[x][y-1] != 0 && explored [x][y-1] != 1) {
            if (solve(x,y-1) == true) {
                coordinateX.push(x);
                coordinateY.push(y);
                return true;
            }
        }
        return false;
    }
}