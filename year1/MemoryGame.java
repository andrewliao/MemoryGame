package year1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javafx.scene.layout.BorderPane;
import javafx.animation.Animation;
import java.lang.reflect.InvocationTargetException;

public class MemoryGame extends Application {
    /** this stores the default number of rows in the board */
    private static int numRows = 4;
    /** this stores the default number of columns in the board */
    private static int numColumns = 4;
    /** thie stores a boolean[][] that checks if button is paired up and found */
    private boolean[][] pairedUp = new boolean[numRows][numColumns];
    /** this stores the index of the color arrays and how many times it was used*/
    private int[][] colorIndex = new int[8][1];
    /** this stores the hidden colors of the buttons */
    private Color[][] buttonColor = new Color[4][4];
    /** coordinate of button to be compared to */
    private int[] coordinateTocheck;
    /** count of buttons flipped */
    private int numberOfButtonFlipped = 0;
    /** this stores buttons to be added to the game */
    private Button[][] buttons;
    /** This is the default color when it is not flipped yet */
    private final Color lightGrayColor = Color.rgb(225, 225, 225);
    private boolean turnEnded = false;
    private boolean firstTurn = true;

    /** This stores the predifinedColors we can use */
    private final Color[] predefinedColors = {
            Color.rgb(64, 163, 63),
            Color.rgb(245, 152, 29),
            Color.rgb(252, 88, 52),
            Color.rgb(27, 114, 222),
            Color.rgb(203, 0, 78),
            Color.rgb(137, 0, 160),
            Color.rgb(114, 85, 73),
            Color.rgb(66, 66, 66),
            Color.rgb(58, 71, 78)
    };



 
    
    /** this sets up the default boolean[][] indicating whether or not something is pairedUp */
    public void defaultPairedUp() {
    		for(int i = 0; i < pairedUp.length; i++) {
    			for(int j = 0; j < pairedUp[0].length; j++) {
    				pairedUp[i][j] = false;
    			}
    		}
    }
    

    /** setter method of buttons flipped */
    public void setNumberOfButtonFlipped(int number){
        this.numberOfButtonFlipped = number;
    }

    /** getter method of buttons that are flipped */
    public int getNumberOfButtonFlipped(){
        return this.numberOfButtonFlipped;
    }

    /** this method returns the array of the predefinedColors */
    public Color[] getPredefinedColors(){
        return predefinedColors;
    }

    /** setter method of coordinate of button to be checked */
    public void setCoordinateToCheck(int[] coordinate){
        this.coordinateTocheck = coordinate;
    }

    /** getter method of coordinate of button to be checked */
    public int[] getCoordinateToCheck(){
        return this.coordinateTocheck;
    }

    /** setter method of array of buttons */
    public void setButtons(Button[][] buttons){
        this.buttons = buttons;
    }

    /** getter method of array of buttons */
    public Button[][] getButtons(){
        return this.buttons;
    }
    
    /** getter method of light gray color */
    public Color getLightGrayColor() {
    		return this.lightGrayColor;
    }

    /** locates the coordinate of the button */
    public int[] locate(Button b){
        /** this stores all buttons in the play area */
        Button[][] checkButtons = this.getButtons();

        /** this stores the row index of clicked button */
        Integer rowCheck = null;
        /** this b stores the column index of clicked button */
        Integer columnCheck = null;
        /**
         * this loop iterates all buttons and finds the one matches the one user clicks
         * subgoal: tries to find the matched button in one column
         */
        for (int column = 0; column < checkButtons.length; column ++){
            for (int row = 0; row < checkButtons[0].length; row ++ ){
                if (checkButtons[column][row] == b) {
                    columnCheck = column;
                    rowCheck = row;
                }
            }
        }
        /** this stores coordinate info of the clicked button */
        int[] coordinate = { columnCheck, rowCheck };
        return coordinate;
    }

    /** this compares to buttons to see if they have the same color */
    public boolean compare(int[] coordinate){
        return buttonColor[coordinate[0]][coordinate[1]] == ((Rectangle)(this.getButtons()[this.getCoordinateToCheck()[0]][this.getCoordinateToCheck()[1]].getGraphic())).getFill();
    }

    /** this method creates a grid pane and sets up each of the buttons on action method */
    public GridPane setupPlayArea(int intendedColumn, int intendedRow, int numColors){
        /** this stores the grid pane where all buttons are placed on */
        GridPane gridPane = new GridPane();
        try{
            this.setButtons(new Button[intendedColumn][intendedRow]);

            /** this stores the list of colors with the number the user intends to have */
            Color[] colors = new Color[numColors];

            /**
             * this loop extracts colors from the predefined colors with the correct amount
             * subgoal: retrieve the predefined colors
             */
            for (int i = 0; i < numColors; i++){
                colors[i] = this.getPredefinedColors()[i];
            }

            /**
             * this loop setups the play area with the correct amount of buttons, colors and their actions
             * subgoal: initialize buttons, set fills, make background transparent, add to the gridpane, and set actions
             */
            for (int column = 0; column < intendedColumn; column ++){
                for (int row = 0; row < intendedRow; row++){
                		

                    //filling all the buttons to be the default color of
                    this.getButtons()[column][row] = new Button();
                    this.getButtons()[column][row].setPrefSize(100, 100);

                    /** this stores the rectangle to be placed into buttons */
                    Rectangle rectangle = new Rectangle(100, 100);
                    rectangle.setArcHeight(15);
                    rectangle.setArcWidth(rectangle.getArcHeight());
                    boolean colorFound = false;
                    while(!colorFound) {
                        int indexOfColor = (int)(Math.random() * colorIndex.length);
                        if(colorIndex[indexOfColor][0] < 2) {
                            rectangle.setFill(lightGrayColor);
                            colorIndex[indexOfColor][0]++;
                            buttonColor[row][column] = colors[indexOfColor];
                            colorFound = true;
                        }

                    }


                    this.getButtons()[column][row].setGraphic(rectangle);
                    this.getButtons()[column][row].setStyle("-fx-background-color: transparent");

                    gridPane.add(this.getButtons()[column][row], column, row);

                    
                    
                    
                    
                    /** creating the button on action */
                    this.getButtons()[column][row].setOnAction(e ->{

                        /** this stores the clicked button instance */
                        Button b = (Button)e.getSource();
                        /** this stores the coordinate of the clicked button */
                        int[] coordinate = this.locate(b);
                        if(pairedUp[coordinate[0]][coordinate[1]]) {
                        		return;
                        }
                  
                       
                       /** this is to add coordinates of button when there is only one clicked */
                        if (turnEnded || firstTurn){
                         	turnEnded = false;
                         	if (firstTurn) {
                         	firstTurn = false;
                         	}
                            this.setNumberOfButtonFlipped(this.getNumberOfButtonFlipped() + 1);
                            ((Rectangle)b.getGraphic()).setFill(buttonColor[coordinate[0]][coordinate[1]]); //flip first button
                            this.setCoordinateToCheck(coordinate); //record the coordinate of first button
                        }
                        else if(this.getNumberOfButtonFlipped() % 2 != 0 ){

                        		/** this is when two buttons are clicked */
                        	//if match -> permanently flip them
                            if (this.compare(coordinate)){
                            		pairedUp[coordinate[0]][coordinate[1]] = true;
                            		pairedUp[getCoordinateToCheck()[0]][getCoordinateToCheck()[1]] = true;
                                ((Rectangle)b.getGraphic()).setFill(buttonColor[coordinate[0]][coordinate[1]]);
                                turnEnded = true;
                                this.setNumberOfButtonFlipped(this.getNumberOfButtonFlipped() + 1);

                            }
                         //if no match -> wait and then flip back
                            
                            else{
                                this.setNumberOfButtonFlipped(this.getNumberOfButtonFlipped() + 1);

                            	   Timer timer = new Timer();
                            	   //setting the second button and showing its color
                            	   ((Rectangle)b.getGraphic()).setFill(buttonColor[coordinate[0]][coordinate[1]]);
                            	   
                            	   timer.schedule(new TimerTask() {
                          
                            		   @Override
                            		   public void run() {
                            			   //flipping second button to gray color
                            			   ((Rectangle)b.getGraphic()).setFill(getLightGrayColor());
                            			   
                            			   
      
                            			   //find the first button, gets its rectangle and resets its color back to light gray
                            			  ((Rectangle)getButtons()[getCoordinateToCheck()[0]][getCoordinateToCheck()[1]].getGraphic()).setFill(getLightGrayColor());                          			  
                                      
                            			   turnEnded = true;
                            		   }
                            		
                            		   
                            	   }, (long)(0.5 * 1000));
                                   
                                                   
                    //for first turn, the value will be 0
                    //IN ORDER FOR YOU TO START YOUR FIRST TURN, IT WILL BE 0, OR EVEN
                    //each turn, on the first flip, the value will be odd
                    //each turn, on the second flip, the value will be even
                    
                    //AFTER 2 SECONDS, THEN CAN YOU FLIP
                    //
                    
                            }
                        }

                    });
                }
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Too many colors! Make them less than 7");
        }

        return gridPane;
    }


    @Override
    public void start(Stage primaryStage) {

    		defaultPairedUp();
    		primaryStage.setTitle("Memory Game");
        primaryStage.setTitle("Memory Game");
        Scene scene = new Scene(this.setupPlayArea(4, 4, 8));
        primaryStage.setScene(scene);
        primaryStage.show();
 
    }

    public static void main(String[] args) {
        launch(args);
    }
}