/**
 * 
 */
package commands;

/**
 * @author Zhiyong
 *
 */
import exceptions.ParameterNotEnoughException;

public class YCOR extends TurtleCommand {

	public YCOR() {
		super();
		expectedNumParameters = 0;
	}


	/* 
	 *set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		//DO NOTHING
		returnValue = target.get(target.size()-1).getY();
		sendReturnToDependent();
	}

	/* 
	 * get the ycor of the turtle
	 */
	@Override
	public double executeCommand() {
		
		return returnValue;
	}

}