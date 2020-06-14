
package agameofmaze;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class manages the main character of the game
 * i.e the player. It inherits Character class that
 * contains common attributes and behaviours of all
 * the characters in the game.
 */

class Player extends Character {//Start of Player class.

    private int x=101+34;//Setting initial coordinates to the
    private int y=101+34;//upper-left corner of the maze.
    private int changeInX = 0;//Change in the position w.r.t x coordinate.
    private int changeInY = 0;//Change in the position w.r.t y coordinate.

    private static Image image;
    private static ImageView hero;//The main character of the game.

    /**Getter for hero object.**/
    public ImageView getHero(){
        return hero;
    }

    /**Constructor to initialize the player character.**/
    public Player(){

        try {
            image = new Image(new FileInputStream("Hero32.png"));
        }
        catch (FileNotFoundException e ){
            e.printStackTrace();
        }

        hero = new ImageView(image);
        hero.setLayoutX(x);
        hero.setLayoutY(y);
    }

    /**This method sets the new location (in accordance with the key input) only if the
     * the new location can be traversed.**/
    public void newLocation(){

        Run.getPlayer().changeInX=0;
        Run.getPlayer().changeInY=0;

        if(up){
            Run.getPlayer().changeInY = -2;
        }

        if(down){
            Run.getPlayer().changeInY = 2;
        }

        if(right){
            Run.getPlayer().changeInX =2;
        }

        if(left){
            Run.getPlayer().changeInX = -2;
        }

    }//End of method.

    /**This method moves the character to the new location (if possible) in accordance
     * with the key input.**/
    public void move(){

        if(//Start of if for collision detection.
                isTraversable(pixelToCell(y+ Run.getPlayer().changeInY), pixelToCell(x+ Run.getPlayer().changeInX) ) &&
                isTraversable(pixelToCell(y+ Run.getPlayer().changeInY), pixelToCell(x+30+ Run.getPlayer().changeInX) )&&
                isTraversable(pixelToCell(y+30+ Run.getPlayer().changeInY), pixelToCell(x+ Run.getPlayer().changeInX) )&&
                isTraversable(pixelToCell(y+30+ Run.getPlayer().changeInY),pixelToCell(x+30+ Run.getPlayer().changeInX) )
        )//End of if for collision detection.

        {
            checkEndConditions();

            x = x + Run.getPlayer().changeInX;
            y = y + Run.getPlayer().changeInY;
        }
        hero.setLayoutX(x);
        hero.setLayoutY(y);
    }//End method.

    /**This method checks whether the player and the monster collide or not. If they do, then it removes
     * the player and the game is over. It also checks if the player has reached the end. If so, the
     * game is won.**/
    private void checkEndConditions(){

        Rectangle enemy = new Rectangle(Run.getEnemy().x,Run.getEnemy().y,32,32);
        Rectangle player = new Rectangle(this.x,this.y,32,32);
        Rectangle end = new Rectangle( (int)Run.getEndCell().getX(),(int)Run.getEndCell().getY(),32,32 );

        if( player.intersects(enemy) ){
            Run.getGroup().getChildren().remove(hero);
            Run.setRunning(false);
            LabelManager.setLabel("Game Over!",400);
        }

        if(player.intersects(end)){
            LabelManager.setLabel("You Won!",450);
            Run.setRunning(false);
            hero.setDisable(true);
        }

    }//End of method checkEndConditions.


}//End of class Player.
