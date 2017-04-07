package commands;

import exceptions.ParameterNotEnoughException;

public class ID extends TurtleCommand{
	public ID(){
		super();
		expectedNumParameters = 0;
	}

	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		//do nothing
		returnValue = tv.getActiveList().get(tv.getActiveList().size()-1).getID();
		sendReturnToDependent();
	}

	@Override
	public double executeCommand() {
		return returnValue;
	}

}
