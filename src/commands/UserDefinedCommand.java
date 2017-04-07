package commands;

import java.util.ArrayList;

public class UserDefinedCommand extends ListContainingCommand {
	ArrayList<Object> specificParameters;
	
	public UserDefinedCommand() {
		super();
		specificParameters = new ArrayList<Object>();
		inputs = new LIST();
		inputs.addCommand(new LISTEND());
	}
	
	public void setListOfCommands(LIST commandList) {
		listOfCommands = commandList;
		listOfCommands.variables = variables;
	}
	
	public void setExpectedNumParameter(Double d) {
		expectedNumParameters = d;
	}
	
	public boolean needsParameter() {
		return !(specificParameters.size() + numCommandAsParam >= expectedNumParameters);
	}
	
	public boolean needsCommand() {
		return needsParameter();
	}
	
	@Override
	public boolean addCommandWithin(Command toAdd) {
		return  !(toAdd instanceof VARIABLE);
	}
	
	@Override
	public void addCommand(Command toAdd) {
		toAdd.addVariableSet(variables.get(variables.size()-1));
		specificParameters.add(toAdd);
	}
	
	@Override
	public void addParameter(Double d) {
		specificParameters.add(d);
	}
	
	@Override
	public void setReturnValue() {
		for (Object o : specificParameters) {
			if (o instanceof VARIABLE) {
				VARIABLE v = (VARIABLE) o;
				v.setReturnValue();
				parameters.add(v.getReturnValue());
			} else {
				parameters.add((Double) o);
			}
		}
		for(Command c : listOfCommands.subCommands) {
			c.variables = variables;
		}
		listOfCommands.setReturnValue();
		returnValue = listOfCommands.getReturnValue();
	}
	
	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		listOfCommands.executeCommand();
		return returnValue;
	}

}
