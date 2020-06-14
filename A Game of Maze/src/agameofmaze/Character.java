
package agameofmaze;

import javafx.scene.paint.Color;

/**
 * This class contains the common attributes and
 * behaviours of all the characters in the game.
 * It also has a method to detect collisions and
 * a method to covert coordinates in pixels to
 * the corresponding cells of the maze.
 */

abstract class Character {//Start of abstract class Character.

    protected boolean up = false;
    protected boolean down = false;
    protected boolean right = false;
    protected boolean left = false;

    /**This method sets the next location for the character so that it can move across the maze.**/
    public abstract void newLocation();

    /**This method moves the character across the maze in the relevant direction.**/
    public abstract void move();

    /**This method checks whether the cell, the character is moving into, can be traversed or not.
     * If the cell's color is FORESTGREEN (walls) then it cannot be traversed, otherwise it can be.**/
    public static boolean isTraversable(int a,int b){
        boolean traversable = true;

        if(Run.getMaze().getMaze(a,b).getFill()== Color.FORESTGREEN){
            traversable=false;
        }
        return traversable;
    }//End of method isTraversable.

    /**This method converts coordinates in pixels to the corresponding coordinates of the maze.**/
    public static int pixelToCell(int n){
        n = (n-100)/Run.getMaze().getCellHeight();
        return n;
    }//End of method pixelToCell.

}//End of abstract class Character.


