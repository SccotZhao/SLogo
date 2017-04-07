package commands;

public class FOR extends ListContainingCommand {

	VARIABLE var;
	
	public FOR() {
		super();
		expectedNumParameters = 3;
		
	}
	
	
	@Override 
	public void addParameter(Double d) {
		System.out.println((parameters.size() + numCommandAsParam) + " "  + expectedNumParameters); 
		if (parameters.size() + numCommandAsParam != expectedNumParameters) {
			//System.out.println(this.getClass().getSimpleName() + " has " + (parameters.size()+numCommandAsParam) + " existing parameters and needs this many: " + expectedNumParameters);
			//System.out.println("Parameter is added in the right place " + d);
			parameters.add(d);
		} else {
			listOfCommands.addParameter(d);
		}
	}
	
	
	
	@Override
	public boolean needsParameter() {
		if (parameters == null || (parameters.size() + numCommandAsParam != expectedNumParameters)) {
			return true;
		}
		if (inputs != null && listOfCommands!= null) {
			return inputs.needsParameter() || listOfCommands.needsParameter();
		}
		return true;
	}
	
	@Override
	public boolean addCommandWithin(Command toAdd) {
		if (inputs == null || inputs.needsCommand()) {
			return true;
		}
		if (parameters.size()+numCommandAsParam != expectedNumParameters) {
			return false;
		}
		return listOfCommands == null || listOfCommands.needsCommand();
	}
	
	@Override
	public void setReturnValue() {
		var = (VARIABLE) inputs.subCommands.get(0);
		inputs.setReturnValue();
		listOfCommands.setReturnValue();
		returnValue = listOfCommands.getReturnValue();
	}
	
	
	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		double start = parameters.get(0);
		double end = parameters.get(1);
		double incr = parameters.get(2);
		for (double i=start;i<=end;i+=incr) {
			localVars.put(var.getName(), i);
			listOfCommands.setReturnValue();
			listOfCommands.executeCommand();
		}
		
		return returnValue;
	}

}
