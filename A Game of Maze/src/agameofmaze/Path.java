
package agameofmaze;


import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.Random;

/**
 * This class enables the monster to
 * roam the maze randomly using random-
 * mouse algorithm. It inherits the Enemy
 * class so that it can use its methods
 * isTraversable and pixelToCell.
 * **/
class Path extends Enemy{//Start of class Path.

    private static Random random = new Random(System.currentTimeMillis());
    private static int count = 0;//To keep the count of directions which can be taken at any time.
    private static final int TRAVEL = Run.getMaze().getCellHeight();//To move the monster one cell
    //in the chosen direction.

    /**This method checks the surrounding of the monster at any instant
     * and sets the relevant direction variables true which can be travelled to.**/
    private static void lookForPath(){

        count = 0;
        Run.getEnemy().left  = false;
        Run.getEnemy().down  = false;
        Run.getEnemy().right = false;
        Run.getEnemy().up    = false;

        //Checking leftwards.
        if(isTraversable( pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x- TRAVEL) ) &&
                !(isTravelled( pixelToCell(Run.getEnemy().y) ,pixelToCell(Run.getEnemy().x- TRAVEL) )) )
        {
            Run.getEnemy().left = true;
            count++;
        }//End of if checking leftwards.


        //Checking downwards.
        if(isTraversable( pixelToCell(Run.getEnemy().y+ TRAVEL),pixelToCell(Run.getEnemy().x) ) &&
                !(isTravelled(pixelToCell(Run.getEnemy().y+ TRAVEL),pixelToCell(Run.getEnemy().x))) )
        {
            Run.getEnemy().down = true;
            count++;
        }//End of if checking downwards.


        //Checking rightwards.
        if(isTraversable( pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x+ TRAVEL) ) &&
                !(isTravelled(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x+ TRAVEL))) )
        {
            Run.getEnemy().right = true;
            count++;
        }//End of if checking rightwards.


        //Checking upwards.
        if(isTraversable( pixelToCell(Run.getEnemy().y- TRAVEL),pixelToCell(Run.getEnemy().x) ) &&
                !(isTravelled(pixelToCell(Run.getEnemy().y- TRAVEL),pixelToCell(Run.getEnemy().x))) )
        {
            Run.getEnemy().up = true;
            count++;
        }//End of if checking upwards.



    }//End of method lookForPath.


    /**This method selects the direction for the monster to move to. If
     * only one direction is true then the monster follows that. If more
     * than one is true then, it selects one randomly.**/
    public static void selectRandomDirection(){

        lookForPath();

        if(count == 0){
            deadEnd();
        }


        else if(count == 1){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(Run.getEnemy().getMonster());

            //When left is true.
            if(Run.getEnemy().left){
                Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                Run.getEnemy().x -= TRAVEL;
                translate.setByX(-TRAVEL);
                translate.setDuration(Duration.millis(500));
                translate.play();
            }
            //When down is true.
            else if(Run.getEnemy().down){
                Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                Run.getEnemy().y += TRAVEL;
                translate.setByY(TRAVEL);
                translate.setDuration(Duration.millis(500));
                translate.play();
            }
            //When right is true.
            else if(Run.getEnemy().right){
                Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                Run.getEnemy().x += TRAVEL;
                translate.setByX(TRAVEL);
                translate.setDuration(Duration.millis(500));
                translate.play();
            }
            //When up is true.
            else {
                Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                Run.getEnemy().y -= TRAVEL;
                translate.setByY(-TRAVEL);
                translate.setDuration(Duration.millis(500));
                translate.play();
            }

        }//End of if clause.


        else if(count == 2){
            TranslateTransition translate = new TranslateTransition();
            translate.setNode(Run.getEnemy().getMonster());

            int i = random.nextInt(2);
            //When left-down are true.
            if(Run.getEnemy().left && Run.getEnemy().down){

                switch(i){
                    case 0:{//move left.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x -= TRAVEL;
                        translate.setByX(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//move down.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y += TRAVEL;
                        translate.setByY(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of if when left-down are true.



            //When down-right are true.
            else if(Run.getEnemy().down && Run.getEnemy().right){

                switch(i){
                    case 0:{//move down.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y += TRAVEL;
                        translate.setByY(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//move right.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x += TRAVEL;
                        translate.setByX(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when down-right are true.


            //When up-left are true.
            else if(Run.getEnemy().up && Run.getEnemy().left){

                switch(i){
                    case 0:{//move up.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y -= TRAVEL;
                        translate.setByY(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//move left.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x -= TRAVEL;
                        translate.setByX(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when right-up are true.



            //When right-up are true.
            else if(Run.getEnemy().right && Run.getEnemy().up){

                switch(i){
                    case 0:{//move right.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x += TRAVEL;
                        translate.setByX(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//move up.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y -= TRAVEL;
                        translate.setByY(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when right-up are true.


            //When left-right are true.
            else if(Run.getEnemy().left && Run.getEnemy().right){
                switch(i){
                    case 0:{//move left.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x -= TRAVEL;
                        translate.setByX(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//move right.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x += TRAVEL;
                        translate.setByX(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when right-up are true.


            //When up-down are true.
            else {

                switch(i) {
                    case 0: {//move up.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y -= TRAVEL;
                        translate.setByY(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1: {//move down.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y += 35;
                        translate.setByY(35);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when right-up are true.


        }//End of if clause for count == 2.


        //count == 3.
        else {

            TranslateTransition translate = new TranslateTransition();
            translate.setNode(Run.getEnemy().getMonster());

            int i = random.nextInt(3);

            //When left-down-right are true.
            if(Run.getEnemy().left && Run.getEnemy().down && Run.getEnemy().right){

                switch(i){
                    case 0:{//Move left
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x -= TRAVEL;
                        translate.setByX(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//Move down.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y += TRAVEL;
                        translate.setByY(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 2:{//Move right.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x += TRAVEL;
                        translate.setByX(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of if when left-down-right are true.


            //When down-right-up are true.
            else if(Run.getEnemy().down && Run.getEnemy().right && Run.getEnemy().up){

                switch(i){
                    case 0:{//Move down.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y += TRAVEL;
                        translate.setByY(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//Move right.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x += TRAVEL;
                        translate.setByX(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 2:{//Move up.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y -= TRAVEL;
                        translate.setByY(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when down-right-up are true.

            //When left-right-up are true.
            else if(Run.getEnemy().left && Run.getEnemy().right && Run.getEnemy().up){

                switch(i) {
                    case 0:{//Move left.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x -= TRAVEL;
                        translate.setByX(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1: {//Move right.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x += TRAVEL;
                        translate.setByX(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 2: {//Move up.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y -= TRAVEL;
                        translate.setByY(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when down-right-up are true.

            //When left-down-up are true.
            else {

                switch(i){
                    case 0:{//Move left.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().x -= TRAVEL;
                        translate.setByX(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 1:{//Move down.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y += TRAVEL;
                        translate.setByY(TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                    case 2: {//Move up.
                        Run.getMaze().getMaze(pixelToCell(Run.getEnemy().y),pixelToCell(Run.getEnemy().x)).setFill(Color.TRANSPARENT);
                        Run.getEnemy().y -= TRAVEL;
                        translate.setByY(-TRAVEL);
                        translate.setDuration(Duration.millis(500));
                        translate.play();
                        break;
                    }
                }//End of switch.
            }//End of else if when left-down-up are true.

        }//End of if clause for count == 3.

    }//End of method selectRandomDirection.


    /**This method checks whether any cell has already been travelled
     * or not for backtracking. If yes, a true is returned, otherwise
     * a false is returned.**/
    private static boolean isTravelled(int a, int b){

        boolean ans = false;

        if( Run.getMaze().getMaze(a,b).getFill() == Color.TRANSPARENT ){
            ans = true;
        }

        return ans;

    }//End of method isTravelled.


    /**When a dead end is reached, this method erases the backtracking
     * so that the monster can move back to where it came from.**/
    private static void deadEnd(){

        for(int i = 0; i<Run.getMaze().getMazeHeight(); i++){
            for(int j = 0; j<Run.getMaze().getMazeWidth(); j++){

                if(Run.getMaze().getMaze(i,j).getFill()==Color.TRANSPARENT){
                    Run.getMaze().getMaze(i,j).setFill(Color.BLACK);
                }

            }//End of inner for loop.
        }//End of outer for loop.

    }//End of method deadEnd.


}//End of class Path.
