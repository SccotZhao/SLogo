package commands;

import java.util.List;

import exceptions.ParameterNotEnoughException;
import turtles.Turtle;

public class IFELSE extends ListContainingCommand{

	private LIST elseListOfCommands;
	
	public IFELSE() {
		super();
		expectedNumParameters = 1;
		inputs = new LIST();
		inputs.addCommand(new LISTEND());
		inputs.expectedNumCommands = (double) inputs.subCommands.size();
	}
	

	@Override
	public void setReturnValue() {
		returnValue = 0.0;
		if (parameters.get(parameters.size()-1) != 0) {
			listOfCommands.setReturnValue();
			returnValue = listOfCommands.getReturnValue();
		} else {
			elseListOfCommands.setReturnValue();
			returnValue = elseListOfCommands.getReturnValue();
		}
		sendReturnToDependent();
	}
	
	@Override
	public void setTurtle(List<Turtle> turtlesToAdd) {
		super.setTurtle(turtlesToAdd);
		inputs.setTurtle(target);
		
		listOfCommands.setTurtle(target);
		elseListOfCommands.setTurtle(target);
	}
	
	@Override
	public boolean addCommandWithin(Command toAdd) {
		if (numCommandAsParam + parameters.size()== expectedNumParameters) {
			
			return listOfCommands == null || listOfCommands.needsCommand() || elseListOfCommands == null || elseListOfCommands.needsCommand();
		}
		return false;
	}
	
	public void incrementNumCommAsParam() {
		numCommandAsParam++;
	}
	
	@Override
	public boolean needsCommand() {
		if (inputs == null || listOfCommands == null || elseListOfCommands == null) {
			//System.out.println("should be triggering");
			return true;
		}
		if (parameters.size()+numCommandAsParam != expectedNumParameters || listOfCommands.needsCommand() || inputs.needsCommand()) {
			return true;
		}
		return (inputs.needsCommand() || listOfCommands.needsCommand() || elseListOfCommands.needsCommand());
	}
	
	@Override
	public boolean needsParameter() {
		if (parameters != null && listOfCommands != null && inputs!=null && elseListOfCommands != null) {
			return (parameters.size() +numCommandAsParam != expectedNumParameters || listOfCommands.needsParameter() || inputs.needsParameter() || elseListOfCommands.needsParameter());

		}
		return true;
	}
	
	@Override 
	public void addParameter(Double d) {
		//System.out.println("This is the parameter being added to IFELSE: " + d);
		if (parameters.size() + numCommandAsParam != expectedNumParameters) {
			parameters.add(d);
		} else {
			if (inputs.needsParameter()) {
				inputs.addParameter(d);
			} else {
				if (listOfCommands != null && listOfCommands.needsParameter()) {
					listOfCommands.addParameter(d);
				} else {
					elseListOfCommands.addParameter(d);
				}
			}
			
		}
	}
	
	
	@Override
	public void addCommand(Command toAdd) {
		//System.out.println("this is the command being added to IFELSE: " + toAdd.getClass().getSimpleName());
		if (inputs == null) {
			////System.out.println("should not be null");
			if (toAdd instanceof LIST) {
				inputs  = (LIST) toAdd;
				
			} else {
				//TODO: throw new exception
				
			}
			return;
		} 
		if (inputs.needsCommand()) {
			////System.out.println("should not be funny");
			inputs.addCommand(toAdd);
			return;
		}
		if (listOfCommands == null) {
			////System.out.println("this was done");
			if (toAdd instanceof LIST) {
				////System.out.println("triggered");
				listOfCommands = (LIST) toAdd;
			} else {
				//TODO: Throw new exception
			}
			return;
		}
		if (listOfCommands.needsCommand()) {
			listOfCommands.addCommand(toAdd);
			return;
		}
		if (elseListOfCommands == null) {
			if (toAdd instanceof LIST) {
				//System.out.println("should make list here");
				elseListOfCommands = (LIST) toAdd;
			} else {
				//TODO: Throw exception
			}
			return;
		}
		elseListOfCommands.addCommand(toAdd);
		
	}
	
	
	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		if (parameters.get(parameters.size()-1) != 0) {
			listOfCommands.executeCommand();
		} else {
			elseListOfCommands.executeCommand();
		}
		return returnValue;
	}

}
