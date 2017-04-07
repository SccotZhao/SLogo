package turtles;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import lines.Lines;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Turtle object that contains coordinates and variables, and displays and image on the Pane
 * @author Salo
 *
 */
public class Turtle{

	private double xPos;
	private double yPos;
	private double previousxPos;
	private double previousyPos;
	private double prevprevxPos;
	private double prevprevyPos;
	private double heading;
	private double previousHeading;
	private double prevprevHeading;
	private boolean showing;
	private boolean penDown;
	private int myImageIndex;
	private int myPenColorIndex;
	private int myID;
	private List<Lines> myLines; 
	private Pane myRoot;
	private Animation myAnimation;
	private double width=1000;
	private double height=430;
	public static final double DEFAULT_X_POS = 0;
	public static final double DEFAULT_Y_POS = 0;
	public static final double DEFAULT_ANGLE = 0;
	private int myPenSize;
	private ImageView turtleImage;
	private boolean isActive;
	
	/**
	 * This method instantiates the turtle and gives its original characteristics
	 * @param id
	 * @param Pane 
	 */
	public Turtle(int id, Pane myRootIn) {
		myRoot = myRootIn;
		xPos = DEFAULT_X_POS;
		yPos = DEFAULT_Y_POS;
		previousxPos = DEFAULT_X_POS;
		previousyPos = DEFAULT_Y_POS;
		heading = DEFAULT_ANGLE;
		myLines= new ArrayList<Lines>();
		showing = true;
		penDown=true;
		myImageIndex=0;
		myPenColorIndex=0;
		myID=id;
		myPenSize = 4;
		isActive = true;
	}
	/**
	 * This method sees if the turtle is active or not
	 */
	public boolean getActivity(){
		return isActive;
	}

	/**
	 * This method sets whether a turtle is active
	 * @param b
	 */
	public void setActivity(boolean b){
		isActive = b;
	}

	/**
	 * this method sets a new image for the Turtle
	 * @param imageIn
	 */
	public void setTurtleImage(ImageView imageIn) {
		turtleImage = imageIn;
	}
	
	/**
	 * This method sets the new x position of the turtle depending on where it is and if its active or not
	 * @param X coordinate
	 */
	public void setX(double newX) {
		if(isActive){
			prevprevxPos = previousxPos;
			previousxPos=xPos;	
			xPos = checkBounds(previousxPos,newX,width);
			if(xPos!=previousxPos){
				myAnimation = moveAnimation();
				myAnimation.play();
			}
			updatePen(myPenColorIndex, myPenSize);
		}

	}
	
	/**
	 * This method sets the new y position of the turtle depending on where it is and if its active or not
	 * @param Ycoordinate
	 */
	public void setY(double newY) {
		if(isActive){
			prevprevyPos=previousyPos;
			previousyPos = yPos;
			yPos = checkBounds(previousyPos,newY,height);
			if(yPos!=previousyPos){
				myAnimation = moveAnimation();
				myAnimation.play();
			}
		}
	}

	private double checkBounds(double previous, double current, double parameter) {
		if(current>=parameter){
			current=current % parameter;
			return current;
		}
		else if(current<0){
			current=parameter-Math.abs(current % parameter);
			return current;
		}
		return current;
	}

	private void updatePen(int colorIndex, int penSize){
		if (penDown){
			Lines current = new Lines(previousxPos, previousyPos + 25, xPos, yPos + 25);
			current.setColorIndex(colorIndex);
			current.setThickness(penSize);
			myLines.add(current);
			myRoot.getChildren().add(current.getLine());
		}
	}

	private Animation moveAnimation(){
		Path path = new Path();
		path.getElements().addAll(new MoveTo(previousxPos + 25, previousyPos + 25), new LineTo(xPos+25, yPos+25));
		PathTransition pt = new PathTransition(Duration.seconds(2), path, turtleImage);
		return new SequentialTransition(turtleImage, pt);
	}

	private Animation rotateAnimation(){
		RotateTransition rt = new RotateTransition(Duration.seconds(1));
		rt.setToAngle(heading);
		return new SequentialTransition(turtleImage, rt);
	}

	/**
	 * returns the x position
	 * @return
	 */
	public double getX() {
		return this.xPos;
	}

	/**
	 * returns the y position
	 * @return
	 */
	public double getY() {
		return this.yPos;
	}

	/**
	 * returns the previous x position
	 * @return
	 */
	public double getPreviousX() {
		return this.previousxPos;
	}

	/**
	 * returns the previous y position
	 * @return
	 */
	public double getPreviousY() {
		return this.previousyPos;
	}

	/**
	 * returns the angle
	 * @return
	 */
	public double getHeading() {
		return this.heading;
	}

	/**
	 * sets the angle
	 * @param newHeading
	 */
	public void setHeading(double newHeading) {
		if(isActive){
			prevprevHeading=previousHeading;
			previousHeading = heading;
			heading = newHeading;

			myAnimation = rotateAnimation();
			myAnimation.play();
		}
	}

	/**
	 * returns whether the turtle is showing
	 * @return
	 */
	public boolean showTurtle(){
		return showing;
	}

	/**
	 * sets whether the turtle is showing
	 * @param b
	 */
	public void setShow(boolean b){
		showing = b;
	}

	/**
	 * sets whether the pen is down
	 * @param b
	 */
	public void setPenDown(boolean b){
		penDown = b ;
	}

	/**
	 * returns whether the pen is down
	 * @return
	 */
	public boolean getPen(){
		return penDown;
	}

	/**
	 * sets the turtle to the previous position
	 */
	public void setprev(){
		setX(previousxPos);
		setY(previousyPos);
		setHeading(previousHeading);
		clearprevlines();
	}
	
	/**
	 * clears the previously made line
	 */
	public void clearprevlines(){
		myRoot.getChildren().remove(myRoot.getChildren().size()-1);
		myLines.remove(myLines.size()-1);
	}

	/**
	 * sets a new image for the turtle
	 * @param imageIn
	 */
	public void setImage(Image imageIn){
		turtleImage.setImage(imageIn);
	}

	/**
	 * sets a new image index
	 * @param index
	 * @return
	 */
	public int setImageIndex(int index){
		myImageIndex=index;		
		return myImageIndex;
	}

	/**
	 * returns the image index
	 * @return
	 */
	public int getImageIndex(){
		return myImageIndex;
	}

	/**
	 * returns the ID of the turtle
	 * @return
	 */
	public int getID(){
		return myID;
	}

	/**
	 * sets a new pen color
	 * @param index
	 * @return
	 */
	public int setPenColorIndex(int index){
		myPenColorIndex=index;
		return myPenColorIndex;
	}
	
	/**
	 * returns the pen color
	 * @return
	 */
	public int getPenColorIndex(){
		return myPenColorIndex;
	}

	/**
	 * sets a new pen size
	 * @param pixel
	 * @return
	 */
	public int setPenSize(int pixel){
		myPenSize = pixel;
		return myPenSize;
	}
}