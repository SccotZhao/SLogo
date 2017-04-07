package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class PENCOLOR extends Command{
	public PENCOLOR(){
		super();
		expectedNumParameters = 0;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		//do nothing
		returnValue = target.get(target.size()-1).getPenColorIndex();
		sendReturnToDependent();
	}

	/* 
	 * get the pencolor of the turtle
	 */
	@Override
	public double executeCommand() {
		returnValue = target.get(target.size()-1).getPenColorIndex();
		return returnValue;
	}

}