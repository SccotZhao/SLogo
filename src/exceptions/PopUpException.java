// This entire file is part of my masterpiece.
// Zhiyong Zhao
package exceptions;
 
/**
 * @author Zhiyong
 *
 */
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUpException{
	public static final double SIZE =400;
	public static final String ERROR= "Errors";
	private Scene scene;
	private String message;
	
	/**
	 * @param exception
	 * Get the message for exception, which will be pup up
	 */
	public PopUpException(String exception){
       message = exception;
	}
	
	/**
	 * @return
	 * return he scene if other program needs the scene
	 */
	public Scene getScene(){
		return scene;
	}
	
	/**
	 * show up the exception from the pop up screen
	 */
	public void showMessage(){
		Pane root = new Pane();
		// create a place to see the shapes
		scene = new Scene(root, SIZE, SIZE);
		
		Text splash = new Text();
		//show the exception to the user
		splash = new Text(10,50,message);
		splash.setFont(Font.font(25));
		splash.setFill(Color.DARKVIOLET);
		root.getChildren().addAll(splash);
		Stage s = new Stage();
		s.setScene(scene);
		s.setTitle(ERROR);
		s.show();
	}
}