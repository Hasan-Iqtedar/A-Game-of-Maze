package agameofmaze;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class manages the enemy i.e the
 * monster. It inherits the character
 * class that contains common attributes and
 * behaviours of all the characters in the game.
 */

class Enemy extends Character implements Runnable {//Start of class enemy.

    private static Image image;
    private static ImageView monster;

    protected int x;
    protected int y;


    /**Constructor to initialize the enemy character.**/
    public Enemy(){

        try{
            image = new Image(new FileInputStream("ghost32.png"));
        }
        catch(FileNotFoundException exception){
            exception.printStackTrace();
        }

        //Setting the initial coordinates of the monster to the lower right corner of the maze.
        x = 101+(Run.getMaze().getCellWidth() *(Run.getMaze().getMazeWidth()-2) );
        y = 135+(Run.getMaze().getCellHeight()*(Run.getMaze().getMazeHeight()-3 ) );

        monster = new ImageView(image);
        monster.setLayoutX(x);
        monster.setLayoutY(y);

    }

    /**Getter for monster object.**/
    public ImageView getMonster(){
        return monster;
    }

    /**This method calls the selectRandomDirection of class Path to
     * select the new direction.**/
    public void newLocation(){
        Path.selectRandomDirection();
    }//End of method newLocation.


    /**This method calls the newLocation method to move to the new
     * location.**/
    public void move(){
        newLocation();
        }

    /**This method overrides the run method and calls the move method
     * to move the monster continuously in a background thread.**/
    public void run(){

        Runnable r = new Runnable() {
            @Override
            public void run() {
                move();
            }
        };

        while(Run.isRunning()){

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }

            Platform.runLater(r);
        }//End of while loop.

    }//End of method run.


}//End of class Enemy

