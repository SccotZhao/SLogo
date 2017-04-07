/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class SIN extends Command{
	public SIN(){
		super();
		expectedNumParameters = 1;
	}

	/* 
	 *set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = Math.sin(parameters.get(0));

			sendReturnToDependent();
		}
	}

	/* 
	 * do the sin in math
	 */
	@Override
	public double executeCommand() {

		double degree = parameters.get(0);
		returnValue = Math.sin(degree);
		return returnValue;
	}

}