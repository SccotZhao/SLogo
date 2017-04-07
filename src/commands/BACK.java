package commands;

import turtles.Turtle;

public class BACK extends TurtleCommand {
	
	public BACK() {
		super();
		expectedNumParameters = 1;
	}
	
	/* using the helper class to do the back command
	 * 
	 */
	@Override
	public double executeCommand() {
	
		returnValue = parameters.get(0);
		ForwardBackHelper f = new ForwardBackHelper();
		for (Turtle t : target) {
			f.getHelp(t, returnValue);
		}
		return returnValue;
	}
	
}