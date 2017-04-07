/**
 * 
 */
package commands;

import java.util.Random;

import exceptions.ParameterNotEnoughException;

/**
 * @author Zhiyong
 *
 */
public class RANDOM extends Command{
	public RANDOM(){
		super();
		expectedNumParameters = 1;
	}

	/*
	 * the random number less than the paramter
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {

			Random random = new Random();
			returnValue = parameters.get(0) * random.nextDouble() ;

			sendReturnToDependent();
		}
	}

	@Override
	public double executeCommand() {

		return returnValue;
	}

}