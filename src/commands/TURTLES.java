package commands;

import exceptions.ParameterNotEnoughException;

public class TURTLES extends TurtleCommand{
	public TURTLES(){
		super();
		expectedNumParameters = 0;
	}

	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		
		returnValue = target.size();
		sendReturnToDependent();
	}
	@Override
	public double executeCommand() {
		returnValue = target.size();
		System.out.println("The sise of the target is :" + returnValue);
		return returnValue;
	}
}