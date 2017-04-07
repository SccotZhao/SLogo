package gui;

import java.util.ResourceBundle;

import commands.Parser;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import gui.MenuBuilder;
import turtles.TurtleViewer;

/**
 * This class uses the individual builders to compile the entire GUI
 * @author Jack
 *
 */
public class GUIBuilder {
	public static final String RESOURCE_LOCATION =  "resources/gui";
	private ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_LOCATION);
	private String myLanguage;
	
	/**
	 * This constructor is passed the language the simulation will run in
	 * @param languageIn
	 */
	public GUIBuilder(String languageIn){
		myLanguage = languageIn;
	}

	/**
	 * This method is passed a Pane in which it builds the simulation GUI
	 * @param myRoot
	 */
	public void buildGUI(Pane myRoot){
		Pane turtleRoot = new Pane();
		turtleRoot.setPrefHeight(500);
		turtleRoot.setPrefWidth(1050);
		GridPane.setConstraints(turtleRoot, 0, 1);
		
		TurtleViewer tv = new TurtleViewer(turtleRoot);
		myRoot.getChildren().add(turtleRoot);
		
		Parser myParser = new Parser(tv);
		myParser.changeLanguage(myLanguage);
		
		ConsoleBuilder b = new ConsoleBuilder(myResources, myParser, tv);
		b.buildConsole(myRoot);

		MenuBuilder m=new MenuBuilder(tv, turtleRoot);
		m.buildMenu(myRoot);
	}

}
