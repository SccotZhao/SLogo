package commands;

import java.util.PriorityQueue;

import javafx.scene.layout.Pane;
import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class TELL extends ListContainingCommand{
	private PriorityQueue<Integer> turtleID;

	public TELL() {
		
		super();
		expectedNumParameters = Integer.MAX_VALUE;
		inputs = new LIST();
		inputs.addCommand(new LISTEND());
		inputs.expectedNumCommands = (double) inputs.subCommands.size();
		
	}


	@Override
	public boolean addCommandWithin(Command toAdd) {
		if (listOfCommands == null) {
			return true;
		}
		return listOfCommands.needsCommand();
	}
	
	
	@Override
	public void addCommand(Command toAdd) {
		if (listOfCommands == null) {
			listOfCommands = (LIST) toAdd;
			return;
		}
		if (listOfCommands.needsCommand()) {
			listOfCommands.addCommand(toAdd);
		}
		if (!listOfCommands.needsCommand()) {
			expectedNumParameters = parameters.size();
		}
	}
	
	@Override
	public boolean needsCommand() {
		if ( listOfCommands == null) {
			//System.out.println("should be triggering");
			return true;
		}
		if (parameters.size()+numCommandAsParam != expectedNumParameters || listOfCommands.needsCommand() || inputs.needsCommand()) {
			return true;
		}
		return (inputs.needsCommand() || listOfCommands.needsCommand());
	}
	
	@Override
	public void addParameter(Double d) {
		parameters.add(d);
	}

	@Override
	public void setReturnValue() {
		returnValue = 0.0;

		turtleID = new PriorityQueue<Integer>();
		
		for (int i=0;i<parameters.size();i++) {
			turtleID.add((int) parameters.get(i).intValue());
		}
		try {
			createTurtle(turtleID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendReturnToDependent();
	}

	@Override
	public double executeCommand() {
		
		
		return returnValue;


	}


	/**
	 * act up the turtle in the PriorityQueue
	 * @param turtleID
	 * @throws Exception 
	 */
	


	private void createTurtle(PriorityQueue<Integer> turtleID) throws Exception {
		for (Double d : parameters) {
			if (!tv.containsTurtle((int)d.intValue())) {
				tv.addTurtle((int) d.intValue());
			} 
			tv.getTurtle((int) d.intValue()).setActivity(true);
		}
		tv.setTurtlesInactive(parameters);
		/*for(int i = 0; i < turtleID.size(); i++){
			if(target.size() <= i){
				//create a new turtle of id = i
				Turtle turtle = new Turtle(i, null);
				for(int j = 0; j < i -target.size() + 2; j++){
					//add some null turtles between the size and index i
					target.add(null);
				}
				target.add(turtle);

			}
		}*/

	}
}
