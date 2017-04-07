/**
 * 
 */
package commands;

import turtles.Turtle;

/**
 * @author Zhiyong
 *
 */
public class ForwardBackHelper {
	
	/**
	 * This will help the forward and back command
	 * @param target
	 * @param returnValue
	 */
	public void getHelp(Turtle target, double returnValue){
		System.out.println("return value is " + returnValue);
		double degree = target.getHeading();
		double y = Math.sin(degree*Math.PI/180) * returnValue;
		double x = Math.cos(degree*Math.PI/180) * returnValue;

		target.setY(target.getY() - y);	
		target.setX(target.getX() - x);

	}

}
