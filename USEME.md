`Below are all the script commands supported by our Image Processing application.

$ load path name
Loads the file at the given path, and names it according to the given name.

$ save path name
Saves the file of given name to the given path destination on user's computer.

$ brighten increment name new-name
Loads a new image, which is the old image (specified by given name), but with all pixels brightened
by the given incremement

$ vertical-flip name new-name
Loads a new image, which is the old image (specified by given name), but flipped veritcally.

$ horizontal-flip name new-name
Loads a new image, which is the old image (specified by given name), but flipped horizontally.

$ blue-grayscale name new-name
Loads a new image, which is the old image (specified by given name), but blue-grayscaled.
The red and green values for every pixel become what the blue value was for that pixel.

$ red-grayscale name new-name
Loads a new image, which is the old image (specified by given name), but red-grayscaled.
The blue and green values for every pixel become what the red value was for that pixel.

$ green-grayscale name new-name
Loads a new image, which is the old image (specified by given name), but green-grayscaled.
The red and blue values for every pixel become what the green value was for that pixel.

$ luma-grayscale name new-name
Loads a new image, which is the old image (specified by given name), but luma-grayscaled.
The R, G, and B value for each pixel becomes 0.2126r + 0.7152g + 0.0722b where r,g,b are the
original RGB values of that pixel.

$ value-grayscale name new-name
Loads a new image, which is the old image (specified by given name), but value-grayscaled.
The R, G, and B value for each pixel becomes the maximum of the three original RGB values for that
pixel.

$ intensity-grayscale name new-name
Loads a new image, which is the old image (specified by given name), but intensity-grayscaled.
The R, G, and B value for each pixel becomes the average of the three original RGB values for that
pixel.

$ grayscale name new-name
Loads a new image, which is the old image (specified by given name), but grayscaled.
The grayscale 2d matrix is multiplied by the 1d matrix of original rgb values to produce the new rgb
values for each pixel.

$ sepia name new-name
Loads a new image, which is the old image (specified by given name), but sepia'd.
The sepia 2d matrix is multiplied by the 1d matrix of original rgb values to produce the new rgb
values for each pixel.

$ blur name new-name
Loads a new image, which is the old image (specified by given name), but with a gaussian blur
applied.
The gaussian blur kernel is applied to each color channel of each pixel of the image, producing a
new int value for each r,g,b value of each pixel in the image.

$ sharpen name new-name
Loads a new image, which is the old image (specified by given name), but with sharpen applied.
The shparen kernel is applied to each color channel of each pixel of the image, producing a new int
value for each r,g,b value of each pixel in the image.

$ -file path
$ -file res/examplescript.txt
Loads commands (separted by new lines) from a given .txt file specified by the provided path
destination.
Executes these commands in the order in which they appear in the provided .txt file.

On any given image, load must be called before any other command.

To use the GUI, run the jar file using java -jar jar.jar command. The top third of the screen
shows the histograms of RGB values. The middle third shows the filter buttons and load and save 
buttons. And the bottom third shows the current image being processed. 
