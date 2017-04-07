# slogo
Names of the people in the project: Salo Abraham Bandel, Jack Bloomfeld, Hamsa Pillai and Zhiyong Zhao 

In this project, we developed an interated development environment that helps users write SLogo programs. In this program, we split the projects
into the backend and frontend. Hamsa and Zhiyong were in charge of the backend of the simulation. Hamsa was in charge mostly of the Long, variable, control structures
and User-Defined Commands, meanwhile Zhiyong was in charge of the Boolean operations and the Math Operations.
Salo and Jack were in charge of the frontend. Jack was in charge of the GridBuider, SplashScreen and the Buttons, meanwhile,
Salo was in charge of the MenuBuilder, lines and the Turtle. Between both they implemented the GUI builder.


In the front end, the user can have a good splash screen, through which the users can choose the programming language that they use. They can change the 
behavior of the turtle through the mouse clicking and other buttons. The users can choose shapes for the turtles by clicking on the menue bar. We provide a 
console and a history command. If the user want to run a hstory command, he/she only needs to click the button from the history command button.

Between the frontend and the backend, we implemented the parser which is responsible to check the grammmars and make class from the user codes. Here we 
provide a lot of exception checks. If some kinds of exception happens, the corresponding PopUpscreesn will show up and the user will know  what the errors 
are.


In the backend, we implement the classes forall the command in separate class. WE implemented an abstract Command class and a TurtleCommand which are
used to fix the API of all the fields and methods that are necessary for the commands. We implemented a LongCommand which is used to implement the
IFELSE, REPEAT, etc. The user can change the turles by the TELL command. The usercan also change the pen of the turtle and the color of the pens.
What's more, the user can create more turtle through the TELL command. In the beginning the user can experience the basic command. For advanced users,
they  can use To command to make new functions and do more complicated and interesting behavior of the turtle.

The resource files used for the project are found in various folders. The languages folder for the different languages that can be used for
the SLOGO project can be found inside the resources package. In addition, the other resources used are the GUI properties used to have the parameters
for the various GUI elements used, the format file which has a basic CSS implementation, the exception properties that has the different errors that 
can occur in the project. In the index folder, there are two other properties files for the background and pencolor properties.

Some of the files used for the project are found in the data folder in the project. However, by just input a command one can also test the project.

Some information about the program is that it is available to copy and paste different types of commands that one wants to do. In addition, different 
keys can be used to Reset, see the documentation, observe the about and check out the ReadMe.