/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class PRODUCT extends Command{
	public PRODUCT(){
		super();
		expectedNumParameters = 2;
	}

	/* set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = (parameters.get(0) * parameters.get(1));

			sendReturnToDependent();
		}
	}

	/*
	 * do the product of the parameter
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = x * y;
		System.out.println("The producr is : " + returnValue);
		return returnValue;
	}

}