/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class TAN extends Command{
	public TAN(){
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
			returnValue = (degree %  (Math.PI/2) == 0) ? 0 :Math.tan(degree);

			sendReturnToDependent();
		}
	}

	/* 
	 *do the tan of the parameters
	 */
	@Override
	public double executeCommand() {
		double degree = parameters.get(0);
		returnValue = (degree %  (Math.PI/2) == 0) ? 0 :Math.tan(degree);

		return returnValue;
	}

}
