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
public class CS extends TurtleCommand{
	
	public CS(){
		super();
		expectedNumParameters = 0;
	}
	
	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		returnValue = new CSHomeHelper().getRetVal(target.get(target.size()-1));
		sendReturnToDependent();
	}
	
	/* 
	 *clear all the lines
	 */
	@Override
	public double executeCommand() {
		CSHomeHelper c = new CSHomeHelper();
		for (Turtle t : target) {
			c.getHelp(t);
			t.clearprevlines();
		}
		
		
		
		return returnValue;
	}

}
