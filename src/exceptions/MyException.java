// This entire file is part of my masterpiece.
// Zhiyong Zhao
package exceptions;

/**
 * @author Zhiyong
 *
 */
public abstract class MyException extends Exception {
	
	/**
	 * This is the default constructor for the exception handling
	 * It will use the constructor of the constructor in Exception
	 */
	public MyException(){
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 * This will return the message of the corresponding exception
	 * of the subclasses
	 */
	public abstract String getMessage();

}
