/**
 * 
 */
package commands;

/**
 * @author Zhiyong
 *
 */
import exceptions.ParameterNotEnoughException;

public class ISPENDOWN extends TurtleCommand {
	
	public ISPENDOWN() {
		super();
		expectedNumParameters = 0;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		
		returnValue = (target.get(target.size()-1).getPen() == true)? 1 : 0;
		sendReturnToDependent();
	}
	
	/* 
	 *put the pen down of the turtle
	 */
	@Override
	public double executeCommand() {
		returnValue = (target.get(target.size()-1).getPen() == true)? 1 : 0;
		return returnValue;
	}
	
}