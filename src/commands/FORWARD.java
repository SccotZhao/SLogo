package commands;

import turtles.Turtle;

public class FORWARD extends TurtleCommand {

	public FORWARD() {
		super();
		expectedNumParameters = 1;
	}
	

	/*
	 * using the  ForwardBackHelper to do the forward
	 */
	@Override
	public double executeCommand() {
		returnValue = parameters.get(0);
		ForwardBackHelper f = new ForwardBackHelper();

		for (Turtle t : target) {
			System.out.println("t is moving");
			f.getHelp(t, -returnValue);
			System.out.println("t finished moving");
		}
		
		return returnValue;
	}


}