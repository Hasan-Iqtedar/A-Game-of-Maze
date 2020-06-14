
package agameofmaze;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 *This class runs the whole game. It contains the start method
 * of Application and the main method. Static objects of class
 * other classes are made to incorporate their functionality.
 */
public class Run extends Application {//Start of class Run.

    private static final double SCENE_WIDTH = 800, SCENE_HEIGHT = 440;

    private static Maze maze;
    private static Player player;
    private static Enemy enemy;
    private static Group group;
    private static Rectangle endCell;
    private static Thread thread;
    private static Scene scene;

    private static boolean running;//To keep the monster moving till the end.

    /**This method returns the state (true or false)
     * of the variable running.**/
    static boolean isRunning(){
        return running;
    }

    /**Getter for scene object.**/
    public static Scene getScene(){
        return scene;
    }

    /**Getter for maze object.**/
    public static Maze getMaze(){
        return maze;
    }

    /**Getter for player object.**/
    public static Player getPlayer(){
        return player;
    }

    /**Getter for enemy object.**/
    public static Enemy getEnemy(){
        return enemy;
    }

    /**Getter for group object.**/
    public static Group getGroup(){
        return group;
    }

    /**Getter for endCell object.**/
    public static Rectangle getEndCell(){
        return endCell;
    }

    /**Getter for thread object.**/
    public static Thread getThread(){
        return thread;
    }

    /**Setter for running variable.**/
    public static void setRunning(boolean r){
        running = r;
    }

    @Override
    public void start(Stage stage) throws Exception {//Start of method Start.

        running = true;

        maze   = new Maze();
        player = new Player();
        enemy  = new Enemy();
        group  = new Group();

        //Adding all the maze contents to the scene.
        for (int i = 0; i < maze.getMazeHeight(); i++) {//Start of outer for loop.
            for (int j = 0; j < maze.getMazeWidth(); j++) {//Start of inner for loop.

                if (maze.getMaze(i, j).getFill() == Color.FORESTGREEN)
                    maze.getMaze(i, j).setStroke(Color.DARKBLUE);

                group.getChildren().add(maze.getMaze(i, j));
            }//End of inner for loop,
        }//End of outer for loop.

        //Setting the End point.
        endCell = new Rectangle(100+((maze.getMazeWidth()-2)*maze.getCellWidth()),100+((maze.getMazeHeight()-2)*maze.getCellHeight()),maze.getCellWidth(),maze.getCellHeight());
        endCell.setFill(Color.MAROON);

        //Adding rest of the nodes.
        group.getChildren().addAll(endCell,player.getHero(),enemy.getMonster(),LabelManager.getLabel());
        scene = new Scene(group, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);

        //Thread for moving the monster continuously.
        thread = new Thread(enemy);
        thread.setDaemon(true);

        KeyManager.manageKeyInputs();//Calling method manageKeyInputs to manage the inputs by the user.

        MainMenu.mainMenu(stage);
        player.getHero().requestFocus();

        //Moving the player in accordance with key inputs.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.newLocation();
                player.move();
            }
        };
        timer.start();

    }//End of method start.

    public static void main (String[]args){//Start of method main.
        launch(args);
    }//End of method main

}//End of class Run.
