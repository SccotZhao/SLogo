/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class  OR extends Command{
	public OR(){
		super();
		expectedNumParameters = 2;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = (parameters.get(0) != 0 || parameters.get(1) != 0) ? 1 : 0;

			sendReturnToDependent();
		}
	}

	/*
	 *check the logical or
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = (x != 0 || y != 0)? 1 : 0;

		return returnValue;
	}

}