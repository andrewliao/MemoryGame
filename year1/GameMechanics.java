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


public class GameMechanics extends Application {

	/** this stores the default number of rows in the board */
	public static int numRows = 12;
	/** this stores the default number of columns in the board */
	public static int numColumns = 12;

	Button button;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Title of the window");
		
		
		GridPane layout = new GridPane();
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 12; j++) {
				layout.add(new Button("Row " + i +" and Column " + j), i, j);
			}
		}
		
		Scene scene = new Scene(layout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}