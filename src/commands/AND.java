/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;
import exceptions.PopUpException;

/**
 * @author Zhiyong
 *
 */
public class AND extends Command{
	public AND(){
		super();
		expectedNumParameters = 2;
	}

	/* 
	 * @see commands.Command#setReturnValue()
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			double x = parameters.get(0);
			double y = parameters.get(1);
			returnValue = (x != 0 && y != 0)? 1 : 0;
			sendReturnToDependent();
		}
	}

	/* 
	 * if x and y are not 0, the result is zero 
	 */
	@Override
	public double executeCommand() {
		double x = parameters.get(0);
		double y = parameters.get(1);
		returnValue = (x != 0 && y != 0)? 1 : 0;
		return returnValue;
	}

}