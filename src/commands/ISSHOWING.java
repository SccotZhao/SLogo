/**
 * 
 */
package commands;

/**
 * @author Zhiyong
 *
 */
import exceptions.ParameterNotEnoughException;

public class ISSHOWING extends TurtleCommand {
	
	public ISSHOWING() {
		super();
		expectedNumParameters = 0;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {

		returnValue = (target.get(target.size()-1).showTurtle() == true)? 1 : 0;
		sendReturnToDependent();
	}
	
	/*
	 * check whether he turtle is howing or not
	 */
	@Override
	public double executeCommand() {
		returnValue = (target.get(target.size()-1).showTurtle() == true)? 1 : 0;

		return returnValue;
	}
	
}