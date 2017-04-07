/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class DIFFERENCE extends Command{
	public DIFFERENCE(){
		super();
		expectedNumParameters = 2;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if(parameters.size() == expectedNumParameters){
			returnValue = parameters.get(0) - parameters.get(1);
		}
		sendReturnToDependent();
	}

	/* 
	 *calculate the difference of the parameters
	 */
	@Override
	public double executeCommand() {
		returnValue = (parameters.get(0) - parameters.get(1));
		return returnValue;
	}

}
