/**
 * 
 */
package commands;

import turtles.Turtle;

public class LEFT extends TurtleCommand {

	public LEFT() {
		super();
		expectedNumParameters = 1;
	}

	/*turn left of the turtle for some degree
	 * 
	 */
	@Override
	public double executeCommand() {
		returnValue = parameters.get(0);
		for (Turtle t : target) {
			t.setHeading(t.getHeading() - returnValue);
			t.setX(t.getX());
			t.setY(t.getY());
		}
		
		
		return returnValue;
	}

}