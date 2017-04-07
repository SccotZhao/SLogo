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

public class SETXY extends TurtleCommand {

	public SETXY() {
		super();
		expectedNumParameters = 2;
	}

	/* 
	 *set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = Math.sqrt(parameters.get(0) * parameters.get(0) +  parameters.get(1) * parameters.get(1));

			sendReturnToDependent();
		}
	}

	/* 
	 *set the location of the turtle
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		//the distance  that turtle turned
		returnValue = Math.sqrt(x *x + y * y);
		for (Turtle t : target) {
			t.setX(x);
			t.setY(y);
		}
		
		return returnValue;			
	}

}