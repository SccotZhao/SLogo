/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;
import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class SETPENSIZE extends Command {
	
	public SETPENSIZE(){
		super();
		expectedNumParameters = 1;
	}
	
	/* 
	 *set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = parameters.get(0);
			sendReturnToDependent();
		}
	}
	
	/*
	 * set the size of the pen
	 */
	@Override
	public double executeCommand() {
		returnValue = parameters.get(0);
		for (Turtle t : target) {
			t.setPenSize((int)Math.round(returnValue));
		}
			
		return returnValue;
	}

}
