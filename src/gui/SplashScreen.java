package gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is used to create the splash screen
 * @author Jack
 *
 */
public class SplashScreen {
	private ResourceBundle myResources = ResourceBundle.getBundle("resources/gui");
	private ArrayList<EventHandler<ActionEvent>> events;
	private double width;
	private double height;

	/**
	 * this constructor method is passed in parameters for the screen, and a list of buttons that will appear on the screen
	 * @param eventsIn
	 * @param widthIn
	 * @param heightIn
	 */
	public SplashScreen(ArrayList<EventHandler<ActionEvent>> eventsIn, double widthIn, double heightIn) {
		events = eventsIn;
		width = widthIn;
		height = heightIn;
	}

	/**
	 * this method sets up the screen with visuals and the buttons
	 * @param s
	 * @return
	 */
	public Scene setupSplash(Stage s) {
		VBox root = new VBox(10);
		root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		root.setAlignment(Pos.CENTER);
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(myResources.getString("Logo")));
		ImageView logo = new ImageView(image);
		root.getChildren().add(logo);
		createButtons(root);
		Scene myScene = new Scene(root, width, height);
		return myScene;
	}

	private void createButtons(VBox myRoot) {
		String[] buttonLabels = { "ChineseLabel", "EnglishLabel", "FrenchLabel", "GermanLabel", "ItalianLabel",
				"PortugueseLabel", "RussianLabel", "SpanishLabel" };
		Collection<ButtonBuilder> buttons = new ArrayList<ButtonBuilder>();
		for (int i = 0; i < buttonLabels.length; i++) {
			ButtonBuilder newButton = new ButtonBuilder(myResources.getString(buttonLabels[i]), events.get(i));
			buttons.add(newButton);
		}
		ButtonBuilder.addButtonsTo(buttons, myRoot);
	}
}