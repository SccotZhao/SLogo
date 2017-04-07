/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;
import exceptions.PopUpException;
import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class HIDETURTLE extends TurtleCommand{
	
	public HIDETURTLE(){
		super();
		expectedNumParameters = 0;
	}
	
	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		//DO NOTHING
		returnValue = 0;
		sendReturnToDependent();
	}
	

	/* 
	 * hide the turtle in the target list
	 */
	@Override
	public double executeCommand() {

		showTurtle = false;
		returnValue = 0;
		for (Turtle t : target) {
			t.setShow(false);
		}
		
		
		return 0;
	}

}
