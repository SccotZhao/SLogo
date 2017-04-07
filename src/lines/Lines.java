package lines;
import java.awt.geom.Point2D;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Lines {
	
	public static final String RESOURCE_BUNDLE_PEN_COLOR = "index/pencolor";
	
	private double initXpos;
	private double initYpos;
	private double FinalXpos;
	private double FinalYpos;
	private int myColorIndex;
	private int myThickness;
	private int myAngle;
	private Line line;
	
	public Lines(){
		
	}
	
	public Lines(double xPrev, double yPrev, double xNow, double yNow){
		line = new Line(xPrev, yPrev, xNow, yNow);
	}

	public double getLength(){
		return Point2D.distance(initXpos,initYpos,FinalXpos,FinalYpos);
	}
	public double getAngle(){
		return myAngle;
	}
	public int getColor() {
		return myColorIndex;
	}

	public int getThickness() {
		return myThickness;
	}

	public void setColorIndex(int colorIndex) {
		myColorIndex = colorIndex;
		//TODO: Zhiyong, set the pen color and connect this with lines
		ResourceBundle resources = ResourceBundle.getBundle(RESOURCE_BUNDLE_PEN_COLOR);
		line.setStroke(Color.web(resources.getString(colorIndex+"")));
	}

	/**
	 * Sets the thickness of the line
	 */
	public void setThickness(int thickness) {
		myThickness = thickness;
		line.setStrokeWidth(myThickness);
	}
	//TODO:Zhiyong, add the Line
	public Line getLine(){
		return line;
	}
}
