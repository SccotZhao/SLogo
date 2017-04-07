package commands;

import java.util.ArrayList;
import java.util.List;

import turtles.Turtle;

public abstract class LongCommand extends Command {
	 protected ArrayList<Command> subCommands;
	 protected Double expectedNumCommands;
	

	public LongCommand() {
		super();
		subCommands = new ArrayList<Command>();
		expectedNumCommands = Double.MAX_VALUE;
	}
	
	
	
	
	// COULD BE AUTOFAIL STATEMENT HERE???
	@Override
	public boolean needsParameter() {
		for (int i=0;i<subCommands.size();i++) {
			if (subCommands.get(i).needsParameter()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void addParameter(Double d) {
		////System.out.println("param is being added to subcommand");
		int index = 0;
		for (int i=subCommands.size()-1;i>-1;i-=1) {
			if (subCommands.get(i).needsParameter()) {
				index = i;
			}
		}
		subCommands.get(index).addParameter(d);
	}
	
	@Override 
	public boolean needsCommand() {
		return !(subCommands.size() == expectedNumCommands);
	}
	
	@Override
	public void addCommand(Command toAdd) {
		//System.out.println("override does not occur");
		
		int index = -1;
		for (int i=subCommands.size()-1;i>-1;i-=1) {
			if (subCommands.get(i).needsCommand()) {
				//System.out.println(subCommands.get(i).getClass().getSimpleName() + " needs a command");
				index = i;
			}
		}
		//System.out.println(toAdd.getClass().getSimpleName() + " was added to position " + index + " within LIST");
		if (index!=-1) {
			if (subCommands.get(index) instanceof ListContainingCommand) {
				ListContainingCommand lcc = (ListContainingCommand) subCommands.get(index);
				//System.out.println("this def needs to occur");
				if (lcc.addCommandWithin(toAdd)) {
					//System.out.println("needed command within");
					lcc.addCommand(toAdd);
				} else {
					toAdd.setDependent(lcc);
					subCommands.add(index, toAdd);
					
				}
			} else {
				if (subCommands.get(index) instanceof LongCommand) {
					subCommands.get(index).addCommand(toAdd);
				} else {
					toAdd.setDependent(subCommands.get(index));
					subCommands.add(index, toAdd);
				}
			}
			
		} else {
			subCommands.add(toAdd);
		}
	}




	public void setTurtle(List<Turtle> turtlesToAdd) {
		// TODO Auto-generated method stub
		super.setTurtle(turtlesToAdd);
		for (Command c : subCommands) {
			c.setTurtle(target);
		}
	}
	
	
	
	
}
