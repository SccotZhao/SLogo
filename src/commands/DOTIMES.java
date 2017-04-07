package commands;

public class DOTIMES extends FOR {

	
	public DOTIMES() {
		super();
		expectedNumParameters = 1;
		
	}
	
	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		double end = parameters.get(0);
		
		for (double i=1;i<=end;i++) {
			localVars.put(var.getName(), i);
			listOfCommands.setReturnValue();
			listOfCommands.executeCommand();
		}
		return returnValue;
	}
		
}


