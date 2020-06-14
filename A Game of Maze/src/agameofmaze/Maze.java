
package agameofmaze;


import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the class in which the maze is created by applying
 * Prim's algorithm on the maze draft created by the class MazeDraft.
 */
class Maze extends MazeDraft {//Start of class Maze.

    private final int SIZE = ( getMazeHeight() * getMazeWidth())/3;//size of the cellRecord array.

    private int x;//x-coordinate of a cell in the matrix.
    private int y;//y-coordinate of a cell in the matrix.

    private int noOfVisitedCells=0;//Number of cells that have been visited.

    private int [] cellRecord = new int [SIZE /*580*/];//cellRecord will store the coordinates of visited cells
    //as a single number for record. E.g 3,5 will be stored
    //as 35. and for y-coordinate >=10 -ve values are stored.

    /**Constructor-When the object of class Maze is created, a complete maze
     * will be accessible by that object initialized by this constructor.**/
    public Maze(){

        mazeFormation();

    }//End of constructor.

    /**This method applies prim's algorithm to the maze draft to create a maze.**/
    private void mazeFormation(){

        draftMaze();//Calling draftMaze to make a matrix of cells and walls.
        Random random = new Random();//Creating a random class object to get random values.

        int propagation = random.nextInt(4);//To determine randomly whether to up, down, left or right if possible.

        //x and y are the coordinates of a random cell from the matrix.
        x = random.nextInt( getMazeHeight()/2 );
        y = random.nextInt(getMazeWidth()/2 );

        x = (2*x)+1;//Making sure we get odd coordinates as only
        y = (2*y)+1;//odd ones have cells, even ones have walls.

        if( y<10 ){
            cellRecord[0]= (x*10)+y;
        }
        else{
            cellRecord[0]= (-1*( (x*100)+y ) );
        }

        noOfVisitedCells++;

//******************************************Propagation to form the maze************************************************
        for(int p = 1; p < ( ( (getMazeHeight()/2)+1)*( (getMazeWidth()/2)+1 ) ) ; p++) {
            //Start of for loop for propagation.
            propagation = 0;
            /*
             * 0 for right.
             * 1 for upwards.
             * 2 for left.
             * 3 for downwards.
             */

            switch (propagation) {//Start of switch.

                case 0://Propagate towards right.
                    if (y < getMazeWidth() - 2) {

                        if (getMaze(x, y + 2).getFill() == Color.DARKBLUE) {

                            getMaze(x, y + 1).setFill(Color.BLACK);
                            getMaze(x, y + 2).setFill(Color.BLACK);

                            y = y + 2;

                            if (y < 10) {
                                cellRecord[noOfVisitedCells] = (x * 10) + y;
                            } else {
                                cellRecord[noOfVisitedCells] = -1 * ((x * 100) + y);
                            }
                            noOfVisitedCells++;
                            break;
                        }
                    }

                case 1://Propagate upwards.
                    if (x > 1) {

                        if (getMaze(x - 2, y).getFill() == Color.DARKBLUE) {

                            getMaze(x - 1, y).setFill(Color.BLACK);
                            getMaze(x - 2, y).setFill(Color.BLACK);

                            x = x - 2;

                            if (y < 10) {
                                cellRecord[noOfVisitedCells] = (x * 10) + y;
                            } else {
                                cellRecord[noOfVisitedCells] = -1 * ((x * 100) + y);
                            }
                            noOfVisitedCells++;
                            break;
                        }
                    }

                case 2://Propagate towards left.
                    if (y > 1) {

                        if (getMaze(x, y - 2).getFill() == Color.DARKBLUE) {

                            getMaze(x, y - 1).setFill(Color.BLACK);
                            getMaze(x, y - 2).setFill(Color.BLACK);

                            y = y - 2;

                            if (y < 10) {
                                cellRecord[noOfVisitedCells] = (x * 10) + y;
                            } else {
                                cellRecord[noOfVisitedCells] = -1 * ((x * 100) + y);
                            }
                            noOfVisitedCells++;
                            break;
                        }
                    }


                case 3://Propagate downwards.
                    if (x < getMazeHeight() - 2) {

                        if (getMaze(x + 2, y).getFill() == Color.DARKBLUE) {

                            getMaze(x + 1, y).setFill(Color.BLACK);
                            getMaze(x + 2, y).setFill(Color.BLACK);

                            x = x + 2;

                            if (y < 10) {
                                cellRecord[noOfVisitedCells] = (x * 10) + y;
                            } else {
                                cellRecord[noOfVisitedCells] = -1 * ((x * 100) + y);
                            }
                            noOfVisitedCells++;
                            break;
                        }
                    }

            }//End of switch.

            setNewCoordinates();

        }//End of for loop for propagation.



    }//End of method mazeFormation.

    /**This method checks whether a cell has any unvisited neighbors or not. If
     * yes, true is returned, otherwise false.**/
    private boolean checkNeighbor(int cell){

        int xPart = getCoordinates(cell)[0];
        int yPart = getCoordinates(cell)[1];

        //Checking the neighbors.
        if( yPart < getMazeWidth()-2 ){
            //Checking towards right.
            if( getMaze(xPart, yPart+2).getFill() == Color.DARKBLUE ){
                return true;
            }
        }

        if( xPart >1 ){
            //Checking upwards.
            if( getMaze(xPart-2, yPart).getFill() == Color.DARKBLUE ){
                return true;
            }
        }

        if( yPart >1 ){
            //Checking towards left.
            if( getMaze(xPart,yPart-2).getFill() == Color.DARKBLUE ){
                return true;
            }
        }

        if( xPart < getMazeHeight()-2 ){
            //Checking downwards.
            if( getMaze(xPart+2, yPart).getFill() == Color.DARKBLUE ){
                return true;
            }
        }

        return false;

    }//End of method checkNeighbor.

    /**This method counts the number of digits of the number passed.**/
    private int countNumOfDigits(int num){
        int count = 0;
        if(num<0){
            num = -1*(num);//Making it +ve;
        }

        do{
            num = num/10;
            count++;
        }while(num>0);

        return count;
    }
    //End of countNumOfDigits.

    /**This method sets next coordinates of the cell to which the program
     * moves to form the maze.**/
    private void setNewCoordinates(){

        Random random = new Random();

        ArrayList<Integer> availableCells= new ArrayList<Integer>();

        for(int i=0;i<noOfVisitedCells;i++){

            if (checkNeighbor(cellRecord[i]) == true) {
                availableCells.add(cellRecord[i]);
            }

        }//End of for loop.
        if (availableCells.size() > 0) {
            int nextCoordinates = availableCells.get(random.nextInt(availableCells.size()));
            x = getCoordinates(nextCoordinates)[0];
            y = getCoordinates(nextCoordinates)[1];
        }

    }//End of setNewCoordinates.

    /**This method returns the coordinates of any cell in an array.**/
    private int[] getCoordinates(int cell){

        int xPart = 0;//x-coordinate of the cell.
        int yPart = 0;//y-coordinate of the cell.
        int digits = countNumOfDigits(cell);//Number of digits of the cell number.

        if (digits == 2) {
            xPart = cell / 10;
            yPart = cell % 10;
        }

        if (digits == 3) {

            if (cell > 0) {//If only the last digit is of y-coordinate then nextCell will be +ve.
                xPart = cell / 10;
                yPart = cell % 10;
            } else {//If y-coordinate has 2 digits then nextCell will be -ve.
                if (cell < 0) {
                    cell = -1 * (cell);//Making it +ve;
                }
                xPart = cell / 100;
                yPart = cell % 100;
            }
        }

        if (digits == 4) {
            if (cell < 0) {
                cell = -1 * (cell);//Making it +ve;
            }
            xPart = cell / 100;
            yPart = cell % 100;
        }

        return new int[] {xPart,yPart};

    }

}//End of class Maze.