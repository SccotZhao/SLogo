// This entire file is part of my masterpiece.
// Zhiyong Zhao
package exceptions;

import java.util.ResourceBundle;

/**
 * @author Zhiyong
 *
 */
public class ParameterNotEnoughException extends MyException{
	 public static final String DEFAULT_RESOURCE_PACKAGE = "resources/exception";
	 public static final String MESSAGE = "ParameterNotEnoughException";
		
	 /* 
		 *return message from the properties files
		 *If the parameter is not enough for the other command
		 *then there will be ParameterNotEnoughException PupUp
		 */

	@Override
	public String getMessage() {
		ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		String message = myResources.getString(MESSAGE);
		
		return message;
	}

}
