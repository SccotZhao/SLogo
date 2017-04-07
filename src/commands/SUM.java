/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class SUM extends Command{
	public SUM(){
		super();
		expectedNumParameters = 2;
	}
	
	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
				returnValue = (parameters.get(0) + parameters.get(1));
				
				sendReturnToDependent();
		}
	}

	/* 
	 *do the sum
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		//parameters.add(1, x + y);
		returnValue = x + y;
		System.out.println("The sum is :" + returnValue);
		return returnValue;
	}

}