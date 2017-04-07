/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class POW extends Command{
	public POW(){
		super();
		expectedNumParameters = 2;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = Math.pow(parameters.get(0) , parameters.get(1));

			sendReturnToDependent();
		}
	}

	/*
	 * do the power of the parameters
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = Math.pow(x, y);		
		return returnValue;
	}

}