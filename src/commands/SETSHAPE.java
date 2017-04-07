/**
 * 
 */
package commands;

import exceptions.ParameterNotEnoughException;
import javafx.scene.image.Image;
import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class SETSHAPE extends TurtleCommand {
	public static final String IMAGE_NAME = "images/slogo";
	public static final String IMAGE_TYPE = ".jpg";

	public SETSHAPE(){
		super();
		
		expectedNumParameters = 1;
	}

	/* 
	 * set the return value
	 */
	@Override
	public void setReturnValue() throws ParameterNotEnoughException {
		if (parameters.size() == expectedNumParameters) {
			returnValue = parameters.get(0);

			sendReturnToDependent();
		}
	}

	/* 
	 * choose a shape of the turtle
	 */
	@Override
	public double executeCommand() {
		returnValue = parameters.get(0);
		//combine the information of the image name as a String
		for (Turtle t : target) {
			t.setImage(new Image(IMAGE_NAME + (Math.round(returnValue) + IMAGE_TYPE)));
			t.setImageIndex((int) Math.round(returnValue));
		}
		

		return returnValue;
	}

}
