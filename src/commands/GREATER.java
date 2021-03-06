/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class GREATER extends Command{
	public GREATER(){
		super();
		expectedNumParameters = 2;
	}
	
	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
				returnValue = (parameters.get(0) > parameters.get(1)) ? 1 : 0;
				
				sendReturnToDependent();
		}
	}
	

	/*
	 *check greater or not
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = (x > y)? 1 : 0;
		
		return returnValue;
	}

}