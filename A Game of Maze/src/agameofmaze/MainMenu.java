package agameofmaze;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class manages the main menu
 * that is displayed when the game
 * is run.
 * **/

public class MainMenu {//Start of class MainMenu.

    public static void mainMenu(Stage stage){

        Scene mainMenu;
// **************************************************  MENU Code Starting **************************************************//
        //Setting the title.
        stage.setTitle("A Game Of Maze");

        //Creating buttons.
        Button play = new Button("PLAY GAME!");
        Button exit = new Button("EXIT");

        //Event handling for exit button.
        exit.setOnAction(e -> {
            System.out.println("Button clicked");
            System.exit(0);
        });

        //Event handling for play button.
        play.setOnAction(event -> {
            stage.setScene(Run.getScene());
            stage.show();
            stage.setFullScreen(true);
            Run.getThread().start();
        });

        //Adding buttons to HBox.
        HBox hbox = new HBox(play, exit);

        //Setting the spacing
        hbox.setSpacing(50);

        //Setting alignment for the HBox
        hbox.setAlignment(Pos.CENTER);

        //Creating a scene
        mainMenu = new Scene(hbox, 615, 440);

        //Specifying the background image's path.
        try {
            FileInputStream input = new FileInputStream("theMaze.jpg");
            Image image = new Image(input);
            //Creating the background image
            BackgroundImage backgroundimage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));
                Background background = new Background(backgroundimage);
                hbox.setBackground(background);

        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }

        stage.setScene(mainMenu);
        stage.show();
        stage.setFullScreen(true);

//**************************************************  MENU Code Ends  ************************************************************//


    }//End of method mainMenu.

}//End of class MainMenu.

