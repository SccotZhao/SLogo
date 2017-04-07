package commands;

import exceptions.ParameterNotEnoughException;
import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class SHAPE extends Command{

	public SHAPE(){
		super();
		expectedNumParameters = 0;

		
	}

	/* 
	 *set the return value 
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {

		returnValue = target.get(target.size()-1).getImageIndex();
		sendReturnToDependent();
		
	}

	/*
	 *get the shape of the turtle
	 */
	@Override
	public double executeCommand() {
		returnValue = target.get(target.size()-1).getImageIndex();
		return returnValue;
	}

}