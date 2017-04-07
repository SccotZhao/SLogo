/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class ATAN extends Command{
	public ATAN(){
		super();
		expectedNumParameters = 1;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			double degree = parameters.get(0);
			returnValue = Math.atan(degree);
			sendReturnToDependent();
		}
	}

	/* 
	 * calculate the actangent of the parameter
	 */
	@Override
	public double executeCommand() {

		double degree = parameters.get(0);
		returnValue = Math.atan(degree);
		return returnValue;
	}

}