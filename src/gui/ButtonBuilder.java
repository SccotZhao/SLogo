package gui;

import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * creates a graphical button with the parameters given in the constructor
 * 
 * @author Jack Bloomfeld
 */
public class ButtonBuilder{
	private Button myButton;

	/**
	 * constructor for a ButtonBuilder that places buttons in specific rows and columns
	 * @param label
	 * @param row
	 * @param col
	 * @param buttonPress
	 */
	public ButtonBuilder(String label, int row, int col, EventHandler<ActionEvent> buttonPress) {
		myButton = new Button();
		GridPane.setConstraints(myButton, col, row);
		myButton.setText(label);
		myButton.setOnAction(buttonPress);
	}
	
	/**
	 * Constructor for a ButtonBuilder that doesn't need a row and column parameter
	 * @param label
	 * @param buttonPress
	 */
	public ButtonBuilder(String label, EventHandler<ActionEvent> buttonPress) {
		myButton = new Button();
		myButton.setText(label);
		myButton.setOnAction(buttonPress);
	}


	/**
	 * This method adds the buttons from the collection to the Pane that is passed in
	 * @param buttons
	 * @param myPane
	 */
	public static void addButtonsTo(Collection<ButtonBuilder> buttons, Pane myPane) {
		for (ButtonBuilder button : buttons) {
			myPane.getChildren().add(button.getButton());
		}
	}

	/**
	 * getter method for the private Button
	 * @return
	 */
	public Button getButton() {
		return myButton;
	}
}