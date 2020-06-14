
package agameofmaze;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class creates a draft i.e a simple matrix with walls
 * in every row and column at regular intervals such that
 * there is an empty cell in between.
 */
class MazeDraft {//Start of class MazeDraft.

    private static final int MAZE_HEIGHT = 15;//Width of the maze. Must be odd (no. of rows of the maze). Must be less than 100.
    private static final int MAZE_WIDTH = 35;//Height of the maze. Must be odd (no. of columns of the maze). Must be less than 100.
    private static final int CELL_WIDTH =35;//Width of a cell of the maze.
    private static final int CELL_HEIGHT =35;//Height of a cell of the maze.

    private static Rectangle[][] maze = new Rectangle[MAZE_HEIGHT][MAZE_WIDTH];

    /**Getter for individual cells of the maze.**/
    public Rectangle getMaze(int i,int j){
        return maze[i][j];
    }

    /**Setter for individual cells of the maze.**/
    public void setMaze(Rectangle rect, int i, int j){
        maze[i][j]=rect;
    }

    /**Getter for MAZE_HEIGHT.**/
    public int getMazeHeight(){
        return MAZE_HEIGHT;
    }

    /**Getter for MAZE_WIDTH.**/
    public int getMazeWidth(){
        return MAZE_WIDTH;
    }

    /**Getter for CELL_WIDTH.**/
    public int getCellWidth(){
        return CELL_WIDTH;
    }

    /**Getter for CELL_HEIGHT.**/
    public int getCellHeight(){
        return CELL_HEIGHT;
    }

    /**This method creates the draft of the maze upon which Prim's algorithm is applied to generate a
     * random maze in maze class.**/
    public void draftMaze(){//Start of method draftMaze.
        int dx;
        int dy;

        dy = 0;
        for(int i = 0; i< MAZE_HEIGHT; i++){//Start of outer for loop.
            dx = 0;
            for(int j = 0; j< MAZE_WIDTH; j++){//Start of inner for loop.

                Rectangle rect = new Rectangle();

                rect.setWidth( getCellWidth() );
                rect.setHeight( getCellHeight() );
                rect.setX( 100+dx );
                rect.setY( 100+dy );

                if(j%2==0 || i%2==0){
                    rect.setFill(Color.FORESTGREEN);
                }
                else{
                    rect.setFill(Color.DARKBLUE);
                }

                setMaze(rect,i,j);
                dx += getCellHeight();

            }//End of inner for loop.
            dy += getCellWidth();

        }//End of outer for loop.

    }//End of method draftMaze.

}//End of class MazeDraft.
