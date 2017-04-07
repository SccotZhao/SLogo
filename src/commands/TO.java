package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import turtles.Turtle;

public class TO extends ListContainingCommand {
	private String methodName;
	private HashMap<String, Integer> methodParametersMap;
	private HashMap<String, LIST> methodCommands;
	
	public TO() {
		super();
		expectedNumParameters = 0;
	}
	
	public void setMethodParamMap(Map<String, Integer> map) {
		methodParametersMap = (HashMap<String, Integer>) map;
	}
	
	public void setMethodCommands(Map<String, LIST> map) {
		methodCommands = (HashMap<String, LIST>) map;
	}
	
	
	public void setMethodName(String s) {
		methodName = s;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	@Override
	public void setReturnValue() {
		
		listOfCommands.setReturnValue();
		returnValue = 1;
		sendReturnToDependent();
	}
	
	
	
	
	@Override
	public boolean addCommandWithin(Command toAdd) {
		return true;
	}
	
	
	@Override
	public boolean needsCommand() {
		System.out.println("should def reach here");
		if (inputs != null) {
			System.out.println(methodName);
			methodParametersMap.put(methodName, inputs.subCommands.size());
		}
		System.out.println("should def reach here 2");
		if (inputs == null || listOfCommands == null) {
			//System.out.println("should be triggering");
			return true;
		}
		if (parameters.size()+numCommandAsParam != expectedNumParameters || listOfCommands.needsCommand() || inputs.needsCommand()) {
			return true;
		}
		System.out.println(inputs.needsCommand() + " or " + listOfCommands.needsCommand());
		return (inputs.needsCommand() || listOfCommands.needsCommand());
	}
	
	@Override
	public boolean needsParameter() {
		return listOfCommands.needsParameter();
	}
	
	@Override 
	public void addParameter(Double d) {
		listOfCommands.needsParameter();
	}
	
	@Override
	public void addCommand(Command toAdd) {
		System.out.println(toAdd.getClass().getSimpleName() + " is being added to TO");
		toAdd.addVariableSet(localVars);
		if (inputs == null) {
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
				methodCommands.put(methodName, listOfCommands) ;
			} else {
				//TODO: Throw new exception
			}
			return;
		}
		listOfCommands.addCommand(toAdd);
		methodCommands.put(methodName, listOfCommands) ;

	}

	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		return returnValue;
	}
	



}
