/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class MINUS extends Command{
	public MINUS(){
		super();
		expectedNumParameters = 1;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = -(parameters.get(0));

			sendReturnToDependent();
		}
	}

	/* 
	 * minus of the parameter
	 */
	@Override
	public double executeCommand() {

		double x = parameters.get(0);
		returnValue = -x ;

		return returnValue;
	}

}