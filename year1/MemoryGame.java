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
import javax.swing.JOptionPane;
import javafx.scene.layout.BorderPane;
import javafx.animation.Animation;
import java.lang.reflect.InvocationTargetException;

public class MemoryGame extends Application {
    /** this stores the default number of rows in the board */
    private static int numRows = 12;
    /** this stores the default number of columns in the board */
    private static int numColumns = 12;
    /** this stores a boolean[][] that says if the button is flipped or not*/
    private boolean[][] flipped = new boolean[numRows][numColumns];
    
    private final Color[] predefinedColors = {
    Color.rgb(64, 163, 63),
    Color.rgb(245, 152, 29),
    Color.rgb(252, 88, 52),
    Color.rgb(27, 114, 222),
    Color.rgb(203, 0, 78),
    Color.rgb(137, 0, 160)
    };
    
    /** this stores buttons to be added to the game */
    private Button[][] buttons = null;
    
    Button button;
    
    public void defaultBoolean() {
    		for(int i = 0; i < flipped.length; i++) {
    			for(int j = 0; j < flipped[0].length; j++) {
    				flipped[i][j] = false;
    			}
    		}
    }
    
    public Color[] getPredefinedColors(){
        return predefinedColors;
    }
    
    public void setButtons(Button[][] buttons){
        this.buttons = buttons;
    }
    
    public Button[][] getButtons(){
        return this.buttons;
    }
    
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
            for (int i = 0; i < numColors; i ++){
                colors[i] = this.getPredefinedColors()[i];
            }
            
            /**
             * this loop setups the play area with the correct amount of buttons, colors and their actions
             * subgoal: initialize buttons, set fills, make background transparent, add to the gridpane, and set actions
             */
            for (int column = 0; column < intendedColumn; column ++){
                for (int row = 0; row < intendedRow; row++){
                    this.getButtons()[column][row] = new Button();
                    this.getButtons()[column][row].setPrefSize(50, 50);
                    
                    /** this stores the rectangle to be placed into buttons */
                    Rectangle rectangle = new Rectangle(50, 50);
                    rectangle.setArcHeight(15);
                    rectangle.setArcWidth(rectangle.getArcHeight());
                    rectangle.setFill(colors[(int)(Math.random() * numColors)]);
                    
                    this.getButtons()[column][row].setGraphic(rectangle);
                    this.getButtons()[column][row].setStyle("-fx-background-color: transparent");
                    
                    gridPane.add(this.getButtons()[column][row], column, row);
                    
                    this.getButtons()[column][row].setOnAction(e ->{
                    	/** this stores the clicked button instance */
                        Button b = (Button)e.getSource();
                        /** this stores the coordinate of the clicked button */
                        int[] coordinate = this.locate(b);   
                        
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
    		defaultBoolean();
    		primaryStage.setTitle("Memory Game");
        Scene scene = new Scene(this.setupPlayArea(12, 12, 3));
        primaryStage.setScene(scene);
        primaryStage.show();
        for(int i = 0 ; i < flipped.length; i++) {
        		for(int j = 0; j < flipped[0].length; j++) {
        			System.out.print(flipped[i][j] + " ");
        		}
        		System.out.println();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}