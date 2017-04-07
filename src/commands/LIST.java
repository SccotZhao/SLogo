package commands;

import java.util.ArrayList;

import exceptions.ParameterNotEnoughException;

public class LIST extends LongCommand{
	
	public LIST() {
		super();
	}
	
	@Override
	public void addCommand(Command toAdd) {
		if (toAdd instanceof LISTEND) {
			boolean addWithin = false;
			for (int i=0;i<subCommands.size();i++) {
				Command c = subCommands.get(i);
				if (c instanceof LIST) {
					LIST l = (LIST) c;
					if (l.needsCommand()) {
						addWithin = true;
						l.addCommand(toAdd);
					}
				}
				if (c instanceof ListContainingCommand) {
					ListContainingCommand lcc = (ListContainingCommand) c;
					if (lcc.needsCommand()) {
						addWithin = true;
						lcc.addCommand(toAdd);
					}
				}
			}
			if (!addWithin) {
				expectedNumCommands =  subCommands.size() * 1.0; //outer list is done
			}
		} else {
			addCommandWithinList(toAdd);
		}
	}
	
	
	private void addCommandWithinList(Command toAdd) {
		int commandIndex = -1;
		for (int i=subCommands.size()-1;i>=0;i-=1) {
			if (subCommands.get(i).needsCommand()) {
				commandIndex = i;
			}
		}
		if (commandIndex!=-1) {
			if (subCommands.get(commandIndex) instanceof ListContainingCommand) {
				ListContainingCommand lcc = (ListContainingCommand) subCommands.get(commandIndex);
				if (lcc.addCommandWithin(toAdd)) {
					lcc.addCommand(toAdd);
				} else {
					toAdd.setDependent(lcc);
					subCommands.add(commandIndex, toAdd);
					
				}
			} else {
				if (subCommands.get(commandIndex) instanceof LongCommand ) {
					subCommands.get(commandIndex).addCommand(toAdd);
				} else {
					if (subCommands.get(commandIndex) instanceof MAKE && toAdd instanceof VARIABLE) {
						//System.out.println("SHOULD BE TRIGGERING 2 times!");
						subCommands.get(commandIndex).addCommand(toAdd);
					} else {
						toAdd.setDependent(subCommands.get(commandIndex));
						subCommands.add(commandIndex, toAdd);
					}
				}
			}
			
		} else {
			subCommands.add(toAdd);
		}
	}
	
	@Override
	public void setReturnValue() {
		for (int i=0;i<subCommands.size();i++) {
			try {
				System.out.println(i);
				subCommands.get(i).setReturnValue();
				System.out.println(i);
			} catch (ParameterNotEnoughException e) {
				// TODO Auto-generated catch block
				//System.out.println("WTFFFF IS GOING ON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		if (subCommands.size()>0) {
			returnValue = subCommands.get(subCommands.size()-1).getReturnValue();

		} else {
			returnValue = 0;
		}
	}
	
	
	@Override
	public double executeCommand() {
		// TODO Auto-generated method stub
		
		for (int i=0;i<subCommands.size();i++) {
			Double d = subCommands.get(i).executeCommand();
		}
		return returnValue;
	}

}
