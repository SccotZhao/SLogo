Hamsa Pillai (hsp8), Zhiyong Zhao (zz45), Jack Bloomfeld (jfb25), Salo Abraham (sea26)

Modifications to the APIs

External Backend:
The external backend has not changed from the plan. The interactions between the backend and frontend are as such: the backend will send exceptions to the frontend to display. Additionally, the frontend will get information from the backend Turtle class to update the Turtle images.

External Frontend:
We plan on transitioning some parts of the Turtle class to the backend, like the getX(), getY(), getHeading(), setX(), setY(), and setHeading() methods.
Those methods will be called by a new TurtleFrontEnd class that uses those variables to create an animation that displays on the window, and therefore they
will now be external frontend methods. The other methods inside of Turtle like makeAnimation(), move(), and clearLines() will remain in the TurtleFrontEnd
class and they will not be part of the external API. 

Internal Backend:
For this, our original idea is to implement all methods in some class. This is fine in some sense and we need to use condition statement to check all the commands.
But this will lead to bad code and it does not comply with the open-closed principle. In the end, we implemented an abstract class, called command. The 
basic command extends the command. In the command class, we implemented the checking methods and the ready-to implement method. For the turtle command, it
also implement the basic command but we need to change something in the return value implementation. We add some new class, like CSHomeHelper, which will
contribute to the implementing the CS and HOME class. There is only one public method. 

Internal Frontend:
We had a Turtle class called TurtleViewer which has various methods, we called a TurtleViewer object, clearing to get a new Turtle, update the Observable and setting 
the objects X and Y coordinates in the root.  This is different from what we had planned, firstly, there is no
stamp method or setPen method in the front end, rather, it is called from the back end, just like show turtle.
However, we kept what we had thought about menuBar and languages so that people can click a different language. .Some of the major changes that have been done are the following:
there is a Scene setup that was not written about in the previous plan and a GUI which call the GridBuilder, Console Builder,
Button Builder and PopUpExtension. The GridBuilder calls to set the BackgroundColor, getGrid and buildGrid for the Grids. These affected a lot of the code since these are 
the basis in which the GUI and the user interface are built upon in our project. Some of the foreseeable changes in the future are some changes to the methods, however no major API changes
will probably be implemented. If there is still time left, the saving and loading files will be some of the methods that will be implemented.