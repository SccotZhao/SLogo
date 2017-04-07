package commands;

import java.util.ResourceBundle;

import exceptions.ParameterNotEnoughException;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Zhiyong
 *
 */
public class SETBACKGROUND extends Command{
	
	public static final String RESOURCE_BUNDLE = "index/backgroundcolor";
	public static final double OPACITY = 0.5;
	
	public SETBACKGROUND(){
		super();
		expectedNumParameters = 1;
		
	}
	
//	@Override
//	public void setReturnValue() throws ParameterNotEnoughException {
//		if (parameters.size() == expectedNumParameters) {
//			returnValue = parameters.get(0);
//			sendReturnToDependent();
//		}
//	}

	@Override
	public double executeCommand() {
			//ResourceBundle resources = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			
			//TODO:add a method in the grid to set the background
			returnValue = parameters.get(0);
			//set the color of the grid to be the color at index = returnValue
//			ResourceBundle resources = ResourceBundle.getBundle(RESOURCE_BUNDLE);
//			Background b = new Background(new BackgroundFill((Paint)(Color.web(resources.getString(Math.round(returnValue)+""))), null, null));
//			
//			grid.getGrid().setBackground(b);
//			grid.getGrid().setOpacity(OPACITY);
			
		return returnValue;
	}

}
