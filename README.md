Model

First, we added an IColor interface and implemented it with ColorImpl, to represent one pixel. We
then designed the ImageModel interface and implemented its methods in ImageImpl, which represents
the model for an image. The interface contains a few useful methods, such as getHeight(), getWidth()
, and most importantly, executeCommand(Command c), which accepts a macro command and calls
goCommand(ImageModel image), which lives in each command class. We thoroughly tested each of these
methods, and began to implement the Command interface, which contains function objects that execute
specific transformations on the ImageModel. We soon began to see the value in implementing Command
with an abstract class and then extending the abstract class with the specific command classes. As
we continued, we made sure to test our command classes before continuing.

GUI update: We added an abstract histogram model and three color-specific models which extend 
the abstract class. 

Controller

Next, we created a Controller interface and implemented it with ControllerImpl. We added a Map
images field to contain every image loaded to the program. Then we added a control() method to
handle CLI input and implemented a helper, private writeMessege(String str) to write to the command
line. control() handles bad user inputs using try/catch statements and writes the relevant exception
information to the appendable. We tested the class for number of input cases, including valid and
invalid user inputs. Program.java Program.java contains our main method, which instantiates a
controller, on which control() is called.
User Input
This program can load and save images, and apply a various operations to images. When the project
directory is open on the command line, an example of the accepted commands are given below.

GUI update: We added a new GUIController to receive a String command and call executeCommand().
This class also implements the Controller interface, similar to Controller Impl for the CLI. 

View
First, we wrote and implemented IGUIView. The view has a controller and current image, which
is to be processed. The view contains buttons which each have an action command, which is called
using setActionCommand(). The view passes the model to the NEW GUIController, which as said above,
will call back to the view. The new GUIController takes whatever action command corresponded to the 
button and calls the Command class on the image, mutating it.


#load file with path res/example.ppm and call it dog
$ load res/dog.ppm dog

#brighten dog by 10 increments and call it bright-dog
$ brighten 10 dog bright-dog

#darken dog by 10 increments and call it dark-dog
$ brighten -10 dog dark-tdog

#vertically flip dog and call it vertical-flip-dog
$ vertical-flip dog vertical-flip-dog

#horizontally flip dog and call it horizontal-flip-dog
$ horizontal-flip dog horizontal-flip-dog

#create a blue grayscale of dog and call it blue-dog
$ blue-grayscale dog blue-dog

#create a red grayscale of dog and call it red-dog
$ red-grayscale dog red-dog

#create a green grayscale of dog and call it green-dog
$ green-grayscale dog green-dog

#create a luma grayscale of dog and call it luma-dog
$ luma-grayscale dog luma-dog

#create a value grayscale of dog and call it value-dog
$ value-grayscale dog value-dog

#create a intensity grayscale of dog and call it intensity-dog
$ lumintensitya-grayscale dog intensity-dog

#all previously created images are able to be used
$ blue-grayscale dog blue-grayscale-vertical-flip-dog

#save blue-grayscale-vertical-flip-dog to specified path on computer
$ save User/Desktop/blue-grayscale-vertical-flip-dog.ppm blue-grayscale-vertical-flip-dog

Image Citation
Image was marked as free to use commercially and non-commerically.
https://www.kibrispdr.org/unduh-19/100x100-png.html
