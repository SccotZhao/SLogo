package gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import commands.Parser;
import exceptions.EmptyParserException;
import exceptions.ErrorParsing;
import exceptions.MyException;
import exceptions.NoTurtleException;
import exceptions.PopUpException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import turtles.Turtle;
import turtles.TurtleViewer;

/**
 * This class builds the Console for input, the buttons that influence the console, and the tabs that contain the existing
 * turtle variables, the previous commands, the existing variables defined, and the existing functions defined
 * @author Jack
 *
 */
public class ConsoleBuilder {
	private TabPane myTab = new TabPane();
	private TextArea console = new TextArea();
	private ObservableList<Button> pcommands = FXCollections.observableArrayList();
	private ObservableList<TextArea> turtleList = FXCollections.observableArrayList();
	private ObservableList<TextArea> variables = FXCollections.observableArrayList();
	private ObservableList<TextArea> outputs = FXCollections.observableArrayList();
	private ListView<Button> previousCommandList = new ListView<Button>();
	private ListView<TextArea> turtleVariables = new ListView<TextArea>();
	private ListView<TextArea> variablesList = new ListView<TextArea>();
	private ListView<TextArea> outputList = new ListView<TextArea>();
	private ResourceBundle myResources;
	private Parser myParser;
	private TurtleViewer tv;
	private int currentID = 0;

	/**
	 * This constructor method takes in a Parser object to send commands to, and a TurtleViewer object to interact with Turtles
	 * @param myResourcesIn
	 * @param parserIn
	 * @param tvIn
	 */
	public ConsoleBuilder(ResourceBundle myResourcesIn, Parser parserIn, TurtleViewer tvIn){
		tv = tvIn;
		myResources = myResourcesIn;
		myParser = parserIn;

		formatConsole();
		formatObservableLists();
		formatTabPane();
	}
	/**
	 * This method puts the console, tabs, and buttons into the given root
	 * @param myRoot
	 */
	public void buildConsole(Pane myRoot){
		GridPane.setConstraints(console, 0, 3);
		GridPane.setRowSpan(console, 2);
		GridPane.setConstraints(myTab, 1, 1);
		GridPane.setRowSpan(myTab, 2);
		GridPane.setColumnSpan(myTab, 2);
		myRoot.getChildren().addAll(console, myTab);
		createButtons(myRoot, 3);
	}
	private void createButtons(Pane myRoot, int row) {
		String[] buttonLabels = { "ExecuteButtonLabel", "ClearButtonLabel", "UndoButtonLabel", "AddTurtleLabel"};
		EventHandler<ActionEvent> execute = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				addPreviousCommand(console.getText());
				try {
					addOutput(myParser.parse(console.getText()));
				} catch (Exception e1) {
					MyException p =  new EmptyParserException();
					PopUpException pop = new PopUpException(p.getMessage());
					pop.showMessage();
				}
				try {
					updateVariables();
				} catch (Exception e2) {
					MyException p =  new NoTurtleException();
					PopUpException pop = new PopUpException(p.getMessage());
					pop.showMessage();
				}
				console.clear();
			}
		};
		EventHandler<ActionEvent> clear = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				console.clear();
			}
		};
		EventHandler<ActionEvent> undo = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					for(Turtle t: tv.getTurtleList()){
						t.setprev();
						t.clearprevlines();
					}
				} catch (Exception e1) {
					MyException p =  new NoTurtleException();
					PopUpException pop = new PopUpException(p.getMessage());
					pop.showMessage();
				}
				console.clear();
			}
		};
		EventHandler<ActionEvent> addTurtle = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(tv.getCleared()){
					turtleList.clear();
					currentID = 0;
					tv.setCleared(false);
				}
				currentID++;
				TextArea turtleVars = createNewTurtleTextArea("Turtle" + currentID, 0, 0, 0, true);
				turtleList.add(turtleVars);
				tv.addTurtle(currentID);
			}
		};

		ArrayList<EventHandler<ActionEvent>> events = new ArrayList<EventHandler<ActionEvent>>();
		events.add(execute);
		events.add(clear);
		events.add(undo);
		events.add(addTurtle);
		Collection<ButtonBuilder> buttons = new ArrayList<ButtonBuilder>();
		ButtonBuilder newButton = new ButtonBuilder(myResources.getString(buttonLabels[0]), row, 1, events.get(0));
		buttons.add(newButton);
		newButton = new ButtonBuilder(myResources.getString(buttonLabels[1]), row, 2, events.get(1));
		buttons.add(newButton);
		newButton = new ButtonBuilder(myResources.getString(buttonLabels[2]), row+1, 1, events.get(2));
		buttons.add(newButton);
		newButton = new ButtonBuilder(myResources.getString(buttonLabels[3]), row+1, 2, events.get(3));
		buttons.add(newButton);
		ButtonBuilder.addButtonsTo(buttons, myRoot);
	}

	private void addPreviousCommand(String previousText){
		Button pcommandButton = new Button(previousText);
		pcommandButton.setOnAction(e -> {
			try {
				myParser.parse(previousText);
			} catch (Exception e1) {
				MyException p =  new ErrorParsing();
				PopUpException pop = new PopUpException(p.getMessage());
				pop.showMessage();
			}
		});
		pcommands.add(pcommandButton);
	}
	
	private void addOutput(java.util.List<Double> list){
		TextArea output = new TextArea();
		output.setEditable(false);
		output.setText(list.toString());
		outputs.add(output);
	}

	private void updateVariables() throws Exception{
		if(tv.getCleared()){
			turtleList.clear();
			currentID = 0;
			tv.setCleared(false);
		}
		if(tv.getTurtleList().size() > turtleList.size()){
			for(int i = tv.getTurtleList().size() - turtleList.size(); i > 0; i--){
				TextArea turtleVars = createNewTurtleTextArea("Turtle" + tv.getTurtleList().get(tv.getTurtleList().size()-i).getID(), 0, 0, 0, true);
				turtleList.add(turtleVars);
			}
		}
		for(int i = 0; i < tv.getTurtleList().size(); i++){
			Turtle temp = tv.getTurtleList().get(i);
			String name = "Turtle" + temp.getID();
			double xloc = temp.getX();
			double yloc = temp.getY();
			double angle = temp.getHeading() % 360;
			boolean active = temp.getActivity();
			turtleList.get(i).setText(String.format("%s\nX: %f \nY: %f \nAngle: %f\nActive: %B",name, xloc, yloc, angle, active));
		}
	}

	private TextArea createNewTurtleTextArea(String name, double xloc, double yloc, double angle, boolean active){
		TextArea variables = new TextArea();
		variables.setEditable(false);
		variables.setMaxWidth(280);
		variables.setMaxHeight(120);
		variables.setText(String.format(name + "\nX: %f \nY: %f \nAngle: %f\nActive: %B", xloc, yloc, angle, active));
		return variables;
	}

	private void formatConsole(){
		console.setPrefColumnCount(50);
		console.setPrefRowCount(5);
		console.setMinHeight(150);
		console.setMaxHeight(150);
	}

	private void formatTabPane(){
		myTab.setMinWidth(300);
		myTab.setMaxWidth(300);

		TextArea turtleVariablesText = createNewTurtleTextArea("Turtle" + currentID, 0, 0, 0, true);
		turtleList.add(turtleVariablesText);

		Tab pcTab = new Tab();
		pcTab.setText("Prev Cmnds");
		pcTab.setContent(previousCommandList);
		pcTab.setClosable(false);
		myTab.getTabs().add(pcTab);

		Tab turtleTab = new Tab();
		turtleTab.setText("Turtles");
		turtleTab.setContent(turtleVariables);
		turtleTab.setClosable(false);
		myTab.getTabs().add(turtleTab);

		Tab variablesTab = new Tab();
		variablesTab.setText("Vars");
		variablesTab.setContent(variablesList);
		variablesTab.setClosable(false);
		myTab.getTabs().add(variablesTab);

		Tab outputTab = new Tab();
		outputTab.setText("Outputs");
		outputTab.setContent(outputList);
		outputTab.setClosable(false);
		myTab.getTabs().add(outputTab);
	}
	
	private void formatObservableLists(){
		previousCommandList.setItems(pcommands);
		previousCommandList.setOrientation(Orientation.VERTICAL);
		previousCommandList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		turtleVariables.setItems(turtleList);
		turtleVariables.setOrientation(Orientation.VERTICAL);
		turtleVariables.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		variablesList.setItems(variables);
		variablesList.setOrientation(Orientation.VERTICAL);
		variablesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		outputList.setItems(outputs);
		outputList.setOrientation(Orientation.VERTICAL);
		outputList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

}