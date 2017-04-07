/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class QUOTIENT extends Command{
	public QUOTIENT(){
		super();
		expectedNumParameters = 2;
	}

	/* 
	 *set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = (parameters.get(1) ==0 ) ? 0 : parameters.get(0)/parameters.get(1) ;

			sendReturnToDependent();
		}
	}

	/* 
	 *do the quotient of parameters
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = (y==0)? 0 : x / y;		
		return returnValue;
	}

}