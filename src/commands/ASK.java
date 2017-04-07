/**
 * 
 */
package commands;

/**
 * @author Zhiyong
 *
 */
public class ASK extends ListContainingCommand{
	
	public ASK() {
		super();
		expectedNumParameters = 0;
		inputs = new LIST();
		inputs.addCommand(new LISTEND());
		inputs.expectedNumCommands = (double) inputs.subCommands.size();
	}


	@Override
	public double executeCommand() {


		return 0;
	}

}
