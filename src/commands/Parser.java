package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.lang.reflect.Constructor;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import exceptions.EmptyParserException;
import exceptions.MyException;
import exceptions.NotMatchException;
import exceptions.ParameterNotEnoughException;
import exceptions.PopUpException;
import turtles.TurtleViewer;


/*
 * NEED TO CONVERT TO PARSE LINE BY LINE!!!!!!
 *
 */

public class Parser {
	 private List<Entry<String, Pattern>> mySymbols;
	 public CommandEngine engine;
	 private TurtleViewer myTurtleViewer;
	 private ArrayList<String> userDefinedCommands;
	 private HashMap<String, Integer> methodParametersMap;
	 private HashMap<String, LIST> methodCommands;
	 
	 public static final String DEFAULT_LANGUAGE_BUNDLE = "English";
	 public static final String DEFAULT_SYNTAX_BUNDLE = "Syntax";
	 public static final String RESOURCE_BUNDLE_URL = "resources/languages/";
	 public static final String WHITESPACE = "\\s+";
	 public static final String COMM = "commands.COMMAND";

	    public Parser(TurtleViewer turtleIn) {
	    	mySymbols = new ArrayList<>();
	        engine = new CommandEngine();
	        myTurtleViewer = turtleIn;
	        engine.setTurtleViewer(myTurtleViewer);
	        methodParametersMap = new HashMap<String, Integer>();
	        methodCommands = new HashMap<String, LIST>();
	        
	        engine.setUserDefinedMaps(methodParametersMap, methodCommands);
	    	addPatterns(RESOURCE_BUNDLE_URL+DEFAULT_LANGUAGE_BUNDLE);
	    	addPatterns(RESOURCE_BUNDLE_URL+DEFAULT_SYNTAX_BUNDLE);
	    	userDefinedCommands = new ArrayList<String>();
	        
	    }
	    
	    
	    
	    public List<Double> parse(String s) throws Exception {
	    	engine.reset();
	    	String[] newLineSplit = s.split("\n");
	    	StringBuilder newCommandsEntry = new StringBuilder();
	    	for (String comm : newLineSplit ) {
	    		if (!comm.startsWith("#")) {
	    			newCommandsEntry.append(comm + " ");
	    		}
	    	}
	    	
	    	String convertedNewCommands = newCommandsEntry.toString(); 
	    	System.out.println(convertedNewCommands);
	    	String[] tokens = convertedNewCommands.split(WHITESPACE);
	    	//System.out.println(Arrays.toString(tokens));
	    	if (tokens.length == 0) {
	    		System.out.println("shouldn't be coming from here!!");
	    		MyException p = new EmptyParserException();
	    		PopUpException pop = new PopUpException(p.getMessage());
	    		pop.showMessage();
	    	} else {

	    		for (int i=0;i<tokens.length;i++) {
	    			System.out.println(tokens[i] + " is the current thing");
    				
	    			String symbol = getSymbol(tokens[i]);

	    			if (checkIfValid(symbol)) {
	    				if (symbol.equals("Constant")) {
	    					//System.out.println("it's working??");
	    					engine.addParameter(Double.valueOf(tokens[i]));
	    				} else {
	    					
	    					String className = "commands." + symbol.toUpperCase();
	    					if (className.equals(COMM)) {
	    						MyException p = new NotMatchException();
	    						PopUpException pop = new PopUpException(p.getMessage());
	    						pop.showMessage();
	    						return null;
	    					}
	    					Class<?> clazz = Class.forName(className);
	    					//System.out.println(className);
	    					System.out.println(className);

	    					Object o = makeClass(clazz);
	    					Command toAdd = (Command) o;
	    					if (toAdd instanceof VARIABLE) {
	    						VARIABLE v = (VARIABLE) toAdd;
	    						v.setName(tokens[i].substring(1, tokens[i].length()));
	    						engine.addCommand(v);
	    						continue;
	    					} 
	    					if (toAdd instanceof TO) {
	    						System.out.println("should be triggered");
	    						TO t = (TO) toAdd;
	    						t.setMethodName(tokens[i+1]);
	    						userDefinedCommands.add(tokens[i+1]);
	    						i++;
	    					}
    						System.out.println("should be triggered 2");

	    					engine.addCommand(toAdd);
    						System.out.println("should be triggered 3");


	    				}
	    			}
	    			
	    		}
	    	}
	    	
	    	engine.initializeForExecution();
			List<Double> rVals = engine.executeCommands();
	    	return rVals;
	    	
	    }
	    
	    public boolean commandsLeftToExecute() {
	    	return !(engine.commandExecuteIndex >= (engine.commandQueue.size()-1));
	    }
	    
	    public void executeNextCommand() {
	    	Double retval = engine.executeNextCommand();
	    }
	    
	    
	    public Object makeClass (Class<?> clazz) {

	        try {
	            // the more robust way
	            Constructor<?> ctor = clazz.getDeclaredConstructor();
	            Object o = ctor.newInstance();
	            return o;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    private boolean checkIfValid(String s) {
	    	return !(s.equals("COMMAND") || s.equals("NO MATCH"));
	    }
	    
	    /*public void parse(File f) {
	    	
	    }*/

	public void changeLanguage(String newLanguage) {
		mySymbols.clear();
		addPatterns(RESOURCE_BUNDLE_URL + newLanguage);
		addPatterns(RESOURCE_BUNDLE_URL + DEFAULT_SYNTAX_BUNDLE);
	}

	// adds the given resource file to this language's recognized types
	public void addPatterns (String syntax) {
		ResourceBundle resources = ResourceBundle.getBundle(syntax);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key,
					// THIS IS THE IMPORTANT LINE
					Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}

	// returns the language's type associated with the given text if one exists 
	public String getSymbol (String text) {
		final String ERROR = "NO MATCH";
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}

		MyException p =  new NotMatchException();
		PopUpException pop = new PopUpException(p.getMessage());
		pop.showMessage();

		return ERROR;
	}

	// returns true if the given text matches the given regular expression pattern
	private boolean match (String text, Pattern regex) {
		// THIS IS THE KEY LINE
		return regex.matcher(text).matches();
	}

}