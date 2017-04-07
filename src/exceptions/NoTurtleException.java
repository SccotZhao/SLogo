// This entire file is part of my masterpiece.
// Zhiyong Zhao
package exceptions;

import java.util.ResourceBundle;

public class NoTurtleException extends MyException{
	 public static final String DEFAULT_RESOURCE_PACKAGE = "resources/exception";
	 public static final String MESSAGE = "NoTurtleException";
		/* 
		 *return message from the properties files
		 *This will be passed to the PopUpException class
		 */
	@Override
	public String getMessage() {
		ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		String message = myResources.getString(MESSAGE);
		
		return message;
	}
}
