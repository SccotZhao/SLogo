/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;
import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class HOME extends TurtleCommand{
	
	public HOME(){
		super();
		expectedNumParameters = 0;
	}
	
	/* 
	 *set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		//DO NOTHING
		returnValue = new CSHomeHelper().getRetVal(target.get(target.size()-1));
		sendReturnToDependent();
	}
	
	/* 
	 * using the CSHomeHelper to do the home command
	 */
	@Override
	public double executeCommand() {
		CSHomeHelper c = new CSHomeHelper();
		for (Turtle t : target) {
			c.getHelp(t);
		}
		
		return returnValue;
	}

}
