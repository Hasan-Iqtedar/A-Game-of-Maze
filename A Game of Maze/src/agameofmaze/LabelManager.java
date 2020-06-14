
package agameofmaze;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class manages the ending lines which
 * are displayed through a label. If the player
 * wins 'You Won' is displayed, otherwise
 * 'Game Over' is displayed.
 * **/
class LabelManager {//Start of class LabelManager.

    private static Label label = new Label();

    /**Getter for label object.**/
    public static Label getLabel(){
        return label;
    }

    /**Setter for label object.**/
    public static void setLabel(String text,int x){

        label.setText(text);
        label.setTranslateX(x);
        label.setTranslateY(200);
        label.setTextFill(Color.BURLYWOOD);
        label.setFont(new Font(120));
    }//End of method setLabel.

}//End of class LabelManager.
