import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;
import gui.GUIBuilder;
import gui.SplashScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Main method that compiles the project. It displays the splash screen and then the simulation.
 * @author Jack
 *
 */
public class Main extends Application {
	
	public static final String RESOURCE_LOCATION = "resources/gui";
	private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LOCATION);
	public static final int SCENE_WIDTH = 1400;
	public static final int SCENE_HEIGHT = 700;
	
	@Override
	public void start(Stage s) {
		showSplash(s);
	}
	
	/**
	 * This method shows the splash screen
	 * @param s
	 */
	private void showSplash(Stage s){
		s.setTitle(myResources.getString("StageTitle"));
		s.setScene(setupSplash(s));
		s.show();
	}
	
	/**
	 * This method runs the simulation
	 * @param s
	 * @param language
	 */
	private void runGame(Stage s, String language){
		GUIBuilder builder = new GUIBuilder(language);
		GridPane myRoot = buildGridPane();
		builder.buildGUI(myRoot);
		
		Scene scene = new Scene(myRoot, SCENE_WIDTH, SCENE_HEIGHT);
		
		File cssFile = new File("src/resources/format.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
		
		s.setTitle(myResources.getString("StageTitle"));
        s.setScene(scene);
        s.show();
	}
	
	/**
	 * this method creates the gridpane that the simulation is built on
	 * @return
	 */
	public GridPane buildGridPane(){
		GridPane myRoot = new GridPane();
		myRoot.setPadding(new Insets(10,10,10,10));
		myRoot.setHgap(20);
		myRoot.setVgap(5);
		return myRoot;
	}
	
	/**
	 * this method sets up the scene for the splash screen
	 * @param s
	 * @return
	 */
	private Scene setupSplash (Stage s) {
		EventHandler<ActionEvent> Chinese = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "Chinese");
			}
		};
		EventHandler<ActionEvent> English = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "English");
			}
		};
		EventHandler<ActionEvent> French = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "French");
			}
		};
		EventHandler<ActionEvent> German = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "German");
			}
		};
		EventHandler<ActionEvent> Italian = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "Italian");
			}
		};
		EventHandler<ActionEvent> Portuguese = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "Portuguese");
			}
		};
		EventHandler<ActionEvent> Russian = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "Russian");
			}
		};
		EventHandler<ActionEvent> Spanish = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				runGame(s, "Spanish");
			}
		};
		ArrayList<EventHandler<ActionEvent>> events = new ArrayList<EventHandler<ActionEvent>>();
		events.add(Chinese);
		events.add(English);
		events.add(French);
		events.add(German);
		events.add(Italian);
		events.add(Portuguese);
		events.add(Russian);
		events.add(Spanish);
		SplashScreen splash = new SplashScreen(events, SCENE_WIDTH, SCENE_HEIGHT);
		Scene ret = splash.setupSplash(s);
		return ret;
	}
	
	public static void main(String args[]){
		launch(args);
	}
	
}