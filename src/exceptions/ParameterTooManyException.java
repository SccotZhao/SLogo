package exceptions;

import java.util.ResourceBundle;

/**
 * @author Zhiyong
 *
 */
public class ParameterTooManyException  extends MyException{
	 public static final String DEFAULT_RESOURCE_PACKAGE = "resources/exception";
	 public static final String MESSAGE = "ParameterTooManyException";
	
		/* 
		 *return message from the properties files
		 */
	 @Override
	public String getMessage() {
		ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE);
		String message = myResources.getString(MESSAGE);
		
		return message;
	}

}