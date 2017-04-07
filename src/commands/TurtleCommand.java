package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import turtles.Turtle;
import turtles.TurtleViewer;

public abstract class TurtleCommand extends Command {

	protected boolean penDown;
	protected boolean showTurtle;
	
	public TurtleCommand() {
		parameters = new ArrayList<Double>();
		penDown = true;
		showTurtle = true;
		
	//	turtles = TurtleViewer.getTurtleList();
		
	}
	
	
	
	
/*	public Turtle getTurtle() {
		Turtle ret = new Turtle();
		TurtleViewer.getTurtle(turtleTarget);
		return ret;
	} */
	
	
	public abstract double executeCommand();
	
	
	public void setTurtleX() {
		//TODO
	}
	
	public  void setTurtleY() {
		//TODO
	}
	
	public  void setDegree() {
		//TODO
	}
	
	public double getNumParameters(){
		return expectedNumParameters;
	}
	
}
