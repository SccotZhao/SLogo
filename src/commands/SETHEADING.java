/**
 * 
 */
package commands;

/**
 * @author Zhiyong
 *
 */
import exceptions.ParameterNotEnoughException;
import turtles.Turtle;

public class SETHEADING extends TurtleCommand {

	public SETHEADING() {
		super();
		expectedNumParameters = 1;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = parameters.get(0);

			sendReturnToDependent();
		}
	}


	/* 
	 * change the heading of the turtle
	 */
	@Override
	public double executeCommand() {
		returnValue = parameters.get(0);
		for (Turtle t : target) {
			t.setHeading(returnValue);
		}
		return returnValue;


	}

}