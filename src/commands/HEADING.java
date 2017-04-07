/**
 * 
 */
package commands;

public class HEADING extends TurtleCommand {
	
	public HEADING() {
		super();
		expectedNumParameters = 0;
	}
	
	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() {
		returnValue = target.get(target.size()-1).getHeading();
		sendReturnToDependent();
	}

	/* 
	 * change the heading of the turtle
	 */
	@Override
	public double executeCommand() {
		returnValue = target.get(target.size()-1).getHeading();
		return returnValue;
	}
	
}