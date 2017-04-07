package turtles;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import turtles.Turtle;

/**
 * This class keeps track of all Turtle objects and adds them or changes them when necessary
 * @author Jack
 *
 */
public class TurtleViewer{

	private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
	private ArrayList<Turtle> activeTurtles = new ArrayList<Turtle>();
	public static final double DEFAULT_SIZE = 700;
	private String currentImage = "images/slogo1.jpg";
	private String inactiveImage = "images/slogo1in.jpg";
	private Pane myRoot;
	private boolean cleared = false;

	public static final double DEFAULT_X_POS = 0;
	public static final double DEFAULT_Y_POS = 0;
	public static final double DEFAULT_ANGLE = 0;

	/**
	 * this constructor places the first Turtle in the Pane that is passed in, and stores the Pane and the Turtle
	 * @param myRootIn
	 */
	public TurtleViewer(Pane myRootIn){
		myRoot = myRootIn;
		Turtle baseTurtle = new Turtle(0, myRoot);
		Image image2 = new Image(currentImage);
		ImageView myTurtleImage = new ImageView(image2);
		myTurtleImage.setFitWidth(50);
		myTurtleImage.setFitHeight(50);
		myTurtleImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				baseTurtle.setActivity(!baseTurtle.getActivity());
				if(!baseTurtle.getActivity()){
					activeTurtles.remove(baseTurtle);
					myTurtleImage.setImage(new Image(inactiveImage));
				}
				else{
					activeTurtles.add(baseTurtle);
					myTurtleImage.setImage(new Image(currentImage));
				}
			}
		});
		myRoot.getChildren().add(myTurtleImage);
		baseTurtle.setTurtleImage(myTurtleImage);
		turtles.add(baseTurtle);
		activeTurtles.add(baseTurtle);
	}

	/**
	 * returns the ArrayList of Turtles
	 * @return
	 */
	public ArrayList<Turtle> getTurtleList(){
		return turtles;
	}
	
	/**
	 * returns the ArrayList of only active Turtles
	 * @return
	 */
	public ArrayList<Turtle> getActiveList(){
		return activeTurtles;
	}

	/**
	 * clears all visuals from the simulation
	 */
	public void clear(){
		myRoot.getChildren().clear();
		turtles.clear();
		activeTurtles.clear();
	}

	/**
	 * adds a Turtle to the simulation and the appropriate ArrayLists
	 * @param ID
	 */
	public void addTurtle(int ID){
		Turtle temp = new Turtle(ID, myRoot);
		Image tempImage = new Image(currentImage);
		ImageView myTurtleImage=new ImageView(tempImage);
		myTurtleImage.setFitWidth(50);
		myTurtleImage.setFitHeight(50);
		myTurtleImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				temp.setActivity(!temp.getActivity());
				if(!temp.getActivity()){
					activeTurtles.remove(temp);
					myTurtleImage.setImage(new Image(inactiveImage));
				}
				else{
					activeTurtles.add(temp);
					myTurtleImage.setImage(new Image(currentImage));
				}
			}
		});
		myRoot.getChildren().add(myTurtleImage);
		temp.setTurtleImage(myTurtleImage);
		turtles.add(temp);
		activeTurtles.add(temp);
	}
	
	/**
	 * returns the Turtle with the given ID, if it exists
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public Turtle getTurtle(int ID) throws Exception {
		for (Turtle t : turtles) {
			if (t.getID() == ID) {
				return t;
			}
		}
		throw new Exception("ID not found");
	}
	
	/**
	 * returns whether or not the Turtle with the given ID exists
	 * @param ID
	 * @return
	 */
	public boolean containsTurtle(int ID) {
		for (Turtle t : turtles) {
			if (t.getID() == ID) {
				return true;
			}
		}
		return false;
	}

	/**
	 * changes the image of the Turtles
	 * @param imageIn
	 */
	public void setImage(String imageIn){
		currentImage = imageIn;
		String[] splitTemp = imageIn.split("\\.");
		inactiveImage = splitTemp[0] + "in." + splitTemp[1];
		for(Turtle t: turtles){
			if(t.getActivity()){
				t.setImage(new Image(currentImage));
			}
		}
	}
	
	/**
	 * returns the Pane that the turtles are on
	 * @return
	 */
	public Pane getTurtlePane(){
		return myRoot;
	}
	
	/**
	 * sets the appropriate turtles to be inactive
	 * @param active
	 */
	public void setTurtlesInactive(List<Double> active) {
		for (Turtle t : turtles) {
			t.setActivity(true);
			t.setImage(new Image(currentImage));
			if (!active.contains(new Double(t.getID()))) {
				t.setActivity(false);
				activeTurtles.remove(t);
				t.setImage(new Image(inactiveImage));
			}
		}
	}
	
	/**
	 * returns the boolean that keeps track of if the scene was cleared
	 * @return
	 */
	public Boolean getCleared(){
		return cleared;
	}
	
	/**
	 * sets the cleared boolean
	 * @param in
	 */
	public void setCleared(Boolean in){
		cleared = in;
	}

}