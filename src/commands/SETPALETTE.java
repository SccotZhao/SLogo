/**
 * 
 */
package commands;

/**
 * @author Zhiyong
 *
 */
public class SETPALETTE extends Command {
	
	public SETPALETTE(){
		super();
		expectedNumParameters = 4;
	}
	
	@Override
	public double executeCommand() {
			
			returnValue = parameters.get(0);
			
		return returnValue;
	}

}
