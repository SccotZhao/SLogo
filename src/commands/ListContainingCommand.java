package commands;

import java.util.HashMap;
import java.util.List;

import exceptions.ParameterNotEnoughException;
import exceptions.PopUpException;
import turtles.Turtle;

public abstract class ListContainingCommand extends Command{
	protected LIST listOfCommands;
	protected LIST inputs;
	protected HashMap<String, Double> localVars;
	//private ArrayList<Double> parameters;
	
	public ListContainingCommand(){
		super();
		listOfCommands = null;
		inputs = null;
		localVars = new HashMap<String, Double>();
	}
	
	@Override
	public void setReturnValue() {
		
		listOfCommands.setReturnValue();
		returnValue = listOfCommands.getReturnValue();
		sendReturnToDependent();
	}
	
	@Override
	public void setTurtle(List<Turtle> turtlesToAdd) {
		super.setTurtle(turtlesToAdd);
		if (inputs != null && listOfCommands != null) {
			inputs.setTurtle(target);
			listOfCommands.setTurtle(target);
		}
		
	}
	
	public boolean addCommandWithin(Command toAdd) {
		return (inputs == null || listOfCommands == null || listOfCommands.needsCommand() || inputs.needsCommand());
	}
	
	public void incrementNumCommAsParam() {
		numCommandAsParam++;
	}
	
	@Override
	public boolean needsCommand() {
		if (inputs == null || listOfCommands == null) {
			//System.out.println("should be triggering");
			return true;
		}
		if (parameters.size()+numCommandAsParam != expectedNumParameters || listOfCommands.needsCommand() || inputs.needsCommand()) {
			return true;
		}
		return (inputs.needsCommand() || listOfCommands.needsCommand());
	}
	
	@Override
	public boolean needsParameter() {
		if (parameters != null && listOfCommands != null && inputs!=null) {
			return (parameters.size() +numCommandAsParam != expectedNumParameters || listOfCommands.needsParameter() || inputs.needsParameter());

		}
		return true;
	}
	
	@Override 
	public void addParameter(Double d) {
		if (parameters.size() + numCommandAsParam != expectedNumParameters) {
			//System.out.println(this.getClass().getSimpleName() + " has " + (parameters.size()+numCommandAsParam) + " existing parameters and needs this many: " + expectedNumParameters);
			//System.out.println("Parameter is added in the right place " + d);
			parameters.add(d);
		} else {
			if (inputs.needsParameter()) {
				inputs.addParameter(d);
			} else {
				listOfCommands.addParameter(d);
			}
			
		}
	}
	
	@Override
	public void addCommand(Command toAdd) {
		toAdd.addVariableSet(localVars);
		System.out.println(toAdd.variables.size() + " is how many maps " + toAdd.getClass().getSimpleName() + " has");
		
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
		listOfCommands.addCommand(toAdd);
		
	}
	
}