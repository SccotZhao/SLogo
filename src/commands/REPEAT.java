package commands;

public class REPEAT extends ListContainingCommand {
	VARIABLE repc;
	
	public REPEAT() {
		super();
		expectedNumParameters = 1;
		inputs = new LIST();
		repc = new VARIABLE();
		repc.setName("repcount");
		for (int i=0;i<variables.size();i++) {
			repc.addVariableSet(variables.get(i));
		}
		repc.addVariableSet(localVars);
		repc.setReturnValue();
		inputs.addCommand(new LISTEND());
		inputs.expectedNumCommands = (double) inputs.subCommands.size();
	}
	
	
	@Override
	public boolean addCommandWithin(Command toAdd) {
		if (numCommandAsParam + parameters.size()== expectedNumParameters) {
			//System.out.println("repeat actually gets a value");
			return listOfCommands == null || listOfCommands.needsCommand();
		}
		return false;
		
	}
	
	
	
	
	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		Double d = 0.0;
		for (int i=1;i<=parameters.get(0);i++) {
			localVars.put(repc.getName(), (double) i);
			listOfCommands.setReturnValue();
			d = listOfCommands.executeCommand();
		}
		return d;
	}

	
	
	

}
