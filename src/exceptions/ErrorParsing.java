package exceptions;

import java.util.ResourceBundle;

public class ErrorParsing extends MyException{
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/exception";
	public static final String MESSAGE = "ErrorParsing";
	
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
