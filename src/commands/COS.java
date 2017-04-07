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
public class COS extends Command{
	public COS(){
		super();
		expectedNumParameters = 1;
	}
	
	/*set the return value 
	 *
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			double degree = parameters.get(0);
			returnValue = Math.cos(degree);
			sendReturnToDependent();
		}
	}

	/* 
	 *calculate the cos of the parameter
	 */
	@Override
	public double executeCommand() {
		double degree = parameters.get(0);
		returnValue = Math.cos(degree);

		return returnValue;
	}

}