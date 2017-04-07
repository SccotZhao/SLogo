/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class LESS extends Command{
	public LESS(){
		super();
		expectedNumParameters = 2;
	}
	
	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
				returnValue = (parameters.get(0) < parameters.get(1))? 1 : 0;
				//System.out.println("less set ret to be this: " + returnValue);
				sendReturnToDependent();
		}
	}

	/*
	 * check whether parameter 0  is less than the parameter 1
	 */
	@Override
	public double executeCommand() {

		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = (x < y)? 1 : 0;
		
		return returnValue;
	}

}
