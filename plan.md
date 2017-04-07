Introduction
===

This program is creating an integrated development environment that allows the user to input a variety of commands and see the results visually on a grid.
The design goal is to have the most efficient interactions between the front end and back end of the project by designing an API that only makes the necessary 
interactions public. The front end will have an external API that includes get methods from the menus so that the backend can update the necessary components 
when a menu option is clicked. The other components to the external API will be get and set methods for the turtle properties like location and angle. It will
send a command object to the backend so that the backend can parse the command and send the correct commands to the front end in response. The external API from
the backend is just the method to parse the input command, and then it will access the frontend API to carry out the commands. The closed components of the project
are displaying all of the components of the GUI onto the stage in the front end, and interpreting all of the console inputs in the backend. 


Backend will send frontend the x and y distance to move in small time frame, and the number of times to do that move (because animation update updates every few milliseconds). 
  		
Design Overview
===
The overall program has a basic split into backend and frontend. The backend handles parsing commands and figures out how much the turtle should move every in every frame. The 
frontend takes this information and updates the position of the turtle image on the display, along with other things (such as pen lines). Within the backend, there is a further
split into 4 different classes. A short-command parser, a long-command parser, a command engine, and a math engine. The command engine will be a superclass
that is extended by different subclasses that represent each command. The short-command parser will have a public method that allows the frontend to send a command. The short-command parser will interact with the long-command parser only when it receives
a long command, which it then sends to the long-command parser. The two parsers interact with the command engine by instantiating a subclass based
off the command they receive. The parsers will add these new objects to a queue that then calculates information back to the frontend. Lastly, in the backend, any exceptions found in parsing or executing a command will be sent to the frontend as well. Public calls
to the math engine might be made to return the result of math expressions. In the frontend, there is a split into the display, the menu, the commandline,
the grid, the exception-handler, and the turtle. The exception-handler will contain a public method that will allow the backend to send it any exceptions. The turtle will have setter methods
that the backend can use to update the turtle according to the commands given. The grid and the display class will use this information (via public getter methods) to
update the graphics in the display. The menubar will mostly change the display settings, but if a user changes the language, that will make a call
to a public method in the backend that changes resource files.

In summary, the basic APIs will be 1. Backend external: receive and parse commands. Change resource files. 2. Backend internal: create new CommandObjects and figure out information to send to frontend.
3. Frontend external: receive exceptions from the backend and display them to the user. Update turtle's position based off input from backend. 4. Frontend internal: update display based off new information held inside turtle class. Change display based off menu options. 

Here is an image of our backend design breakdown:
![backend](https://coursework.cs.duke.edu/CompSci308_2017Spring/slogo_team01/raw/615711bb5be5ca898d53d5cee62c518183a2dafb/slogo_backend.jpg)

User Interface
===

There will be 3 main ways for the user to interact with our program: the menu bar, the variables/executed commands boxes, and the console. 
The menu bar will consist of a series of buttons at the top left of the GUI that allow the user to change parameters of the simulation including language,
colors, turtle image, and open a help page. On the right side of the screen there will be boxes that contain the values of turtle variables like x and y
location, as well as a list of previously executed commands. The information will be displayed for the users knowledge, but the information will also be
interactive so that the user can set a new value for any of the turtle variable, or the user can click a previous command to run the same command again.
The console will be at the bottom of the screen and allow the user to type in any of the valid commands to run. 

The erroneous situations will be reported to the user through the console window (where commands are inputted) as shown in git bash. In addition, the console would
give out the different set of commands available for the person to input. The different erroneous situations that would be reported
are if the input would make the turtle be out of bounds, if the data is empty, if the input data does not correspond to an actual instruction for the project and
if the turtle is already centered.


API Details
===

External Backend API:
This will be comprised of very few methods: parseCommand() and changeLanguage(). These are the only 2 methods that the frontend will need to call in the backend. 
The parseCommand() in the parser class is necessary to be able to actually allow commands to be read in and executed. Without this public method, the display in the frontend won't change since no command will ever be executed; every command needs to be parsed in order to be executed. 
This method will be used in the frontend whenever a command is to be executed (whether that be from the command line or from a command history dropdown). The String representing the command(s) will be passed in as an argument and the parser will parse the commands and call 
methods from the Internal Backend API. The extension for this method is that different commands may be added in the future, so it will need to be able to handle that. 
The changeLanguage() in the parser class will be called from the frontend whenever the user presses a button to change the language. This will essentially change the resource files that the backend uses, so even if the user
changes the language they write their commands in, it will still work since the backend will know what file to use to translate the command. It could easily be extended to add more language files. 
The two parser classes are necessary separate classes because their behavior is very distinctive from the other classes in the backend. They specifically convert Strings into Command objects. The long command parser deals with for loops and if statements
which are different from the normal directional commands. The Command subclasses are necessary because this is easily extensible to add more commands in the future. Also, it is easier to create these objects from strings than to just have many methods representing each command.
The math engine is a necessary separate class since Command subclasses may need math operations, and the math engine will contain subclasses that return the necessary answer.

External Frontend API: 
This API will contain a many methods in the Turtle class including: getX(), getY(), getAngle(), move(), setAngle(), setPen(), etc.
These methods will allow the back end to interpret a command and then call the appropriate methods so that the front end carries out those commands.
For example, if the command in the console is to move forward, the back end will intepret it, use the getX() and getY() when necessary, and then call
the move() method to have the Turtle in the front end move to the appropriate location. 
Additional methods for this API include a sendException() method in the InputException class so that the backend can appropriately express what causes
an exception from the console command. It will be able to describe why the command is invalid and why it caused an exception. 
The Grid class will also have some methods in this API like getBounds(). The getBounds() method will return the boundaries of the grid so that the backend
can use it to assess whether a command will move the Turtle out of bounds or not. 

Internal Frontend API:
This will be compromised by various methods. Firstly, there will be a a penUp() method in the turtle class and the Stamp() which will be boolean methods. This won't affect the backend. Since the method called to set the pen and stamp would be called in the setpen() and setStamp().
Another method necessary is showTurtle(). This will be in the Turtle class but will be called in the Display class. This will also be a boolean. Some other methods that are going to be used are in the menu package. 
Firstly, There is a menubar which will be public. Colors, which will have sliders that call upon the saturation, the hue and the brightness to change the color of the grid. Lastly, there also is a language button in which one person can click the different language 
one wants to use.


Internal Backend API:
For the internal backend API, there are a lot of methods for the basic maths commands and a lot of other commands. For example, there are the basic add, subtract,
quotient, module, remainder, abs, and a lot of calculations about the angle of rotation. There are arcsin, arccos, arctan and the angle itself. There will be some 
logic commands to deal with, like and, or, xor, not and comparison relation <, > =, etc. And there are a lot of methods to calculate the locations of the turtle and 
rotations of the turtle. The backend also need to consider definition of variables and constants and other comments commands. The methods will need to interact will the
command engine and the parser to calculate the result of different expressions and return the results needed in the frontend to set the location of the turtle.


API Example Code
===

Zhiyong(zz45) user code example:

1. rt 60
The command will transfer this code to the backend, the parser class will parse this. There is no error, the command will use getRoation() method and then the 
information will transfer back to the input exception. Since there is no exception, the turtle class will getRotation(). The grid class will transfer the 
information to the display class. Then in the Main class will handle the display.

2. rot  60
The command will transfer this code to the backend, the parser class will parse this. There is an error since this command is not defined in the command pool. 
There will be exception which will be transfered directly to the input exception in the fronend. Then some information about the exception will pop up. Then the 
user will act according to the information showed in the popuo screen.

Jack (jfb25) API code example:

1. cs
This clear screen command will first be sent to the backend and the parser class will interpret it. It will then call the clearScreen() command on the Display
class, and the setHome() command on the Turtle class. The clearScreen() command will remove any images that have been placed on the screen so far, such as 
pen lines or stamps, and the setHome() command will set the Turtle X and Y private integer/coordinates to the appropriate values, usually 0,0.

2. pu
This command is first interpreted by the parser class in the backend. It then calls the penUp() method of the Turtle class which changes the private boolean
Pen to be equal to false if it is currently equal to true. This boolean is referenced during the move() methods so that the code knows whether or not to
draw a line along the movement of the turtle.

Salo (sea26) API code example:
1. Color change in the screen
This color button will be found in the display class, the color will be changed by a set of sliders to change the saturation, hue and brightness of the screen 
or turtle. Since the display class is the only class that would use this, the method for color would be private. The button which will have a paintbrush image will
be clicked and then the sliders would appear for someone to change it. 

2. Stamp
This will crate a stamp at the end of each step made by the turtle which will be interpreted by the parser in the backend. The Stamp method in the turtle class 
will either be a true or false (boolean). This will then pass to the display method where the stamp will be put in the new X and Y of the turtle. 

Hamsa (hsp8) API code example:
1. Changing language for the program
The button to change languages will trigger a call to a public method in the backend, in the parser class, that changes the resource file that the program uses. 

2. User enters an invalid command
The command line will make a call to the public parse method in the small-command parser class in the backend. This class will detect an error in the command and will make a call to the Exception Handler
class in the frontend which will call a method in the display class that shows an error box and tells the user an invalid command occurred. 



Design Considerations 
===

We decided to split this project into two separate parts: the frontend user interface design and the backend logic and maths design, and each part will be 
finished mainly by two students. 

For the frontend, we need a few classes which will implement their corresponding interface. For the control part, we decided 
to split it into two parts: the command class which stores the user input through the console, the menubar class which is responsible for the selection of color,
language, etc. This split will make the codes more modular which will contribute to the developing in the future. Use a separate display class to show the result of
the turtle. The turtle class will handle the returning information from the backend. The turtle will  handle the location of the pen. The Grid class will hold 
information of the grids, like WIDTH, HEIGHTs,etc. Before turtle and grid fetching information, there will be a Input Exception class which will show exception information.
If there is any exception from the backend, information about the exception will pop up and there will be no connection between the information and the turtle or the 
grid class in this case.


For the backend, we need a parser class which will parse information. And the parses command will transfer to the command engine.  We decided to separate the command code 
and the maths calculations.  For the parser class, we will need a lot of methods to parse the String as a long command code. In the command engine class, we need to implement 
some public methods which will transfer information from the frontend back and forth.

I think we also need to consider the class to store all history commands or some other advanced features, like autocomplete. We may also need to consider whether we need to
implement the feature that there can be multiple windows that can run different codes simultaneously. We need to specify some default stamp and the size of the window for displaying.

We also need to consider whether we give the user oppurtunity that one can change the size of the window and then the display will also change proportionally. I mean the box and
the button will change proportionally.


Team Responsibilities
===

Hamsa: Will work on parsing in the backend primarily. Will also work on Exception handling in the backend. 

Jack: Will work on the graphical interface in the frontend and taking in user input.

Salo : Will work on the Menu package and help with the nonvisual interface of the frontend.


Zhiyong: Will work on the maths and the command in the backend.