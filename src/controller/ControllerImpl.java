package controller;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import model.ImageImpl;
import model.ImageModel;
import model.ImageUtil;
import model.color.IColor;
import model.commands.Brighten;
import model.commands.Command;
import model.commands.FlipHorizontal;
import model.commands.FlipVertical;
import model.commands.colortransformations.Grayscale;
import model.commands.colortransformations.Sepia;
import model.commands.filters.Blur;
import model.commands.filters.Sharpen;
import model.commands.grayscale.BlueGrayScale;
import model.commands.grayscale.GreenGrayScale;
import model.commands.grayscale.IntensityGrayScale;
import model.commands.grayscale.LumaGrayScale;
import model.commands.grayscale.RedGrayScale;
import model.commands.grayscale.ValueGrayScale;

/**
 * The controller implementation.
 */
public class ControllerImpl implements Controller {

  ImageUtil u = new ImageUtil();
  private Readable readable;
  private Appendable appendable;

  // load-mask res/face.png face upper-half
  // new MaskImageImpl(arr,;
  // mask face fm
  //
  /**
   * Generates a new Controller Impl.
   *
   * @param readable   the input with commands to the controller.
   * @param appendable the output that is shown back to the user.
   */
  public ControllerImpl(Readable readable, Appendable appendable) {
    // check for null arguments
    if (readable == null || appendable == null) {
      throw new IllegalArgumentException("Controller constructor received null argument(s)");
    }
    this.readable = readable;
    this.appendable = appendable;
  }


  /**
   * The main method that relinquishes control of the application to the controller.
   *
   * @throws IllegalStateException if the controller is unable to transmit output
   */
  public void control() throws IllegalStateException, IOException {
    Scanner sc = new Scanner(readable);
    ImageModel image;
    String name;

    writeMessage("Type instruction:\n");
    while (sc.hasNext()) { //continue until the user quits
      String userInstruction = sc.next(); //take an instruction name
      switch (userInstruction) {
        case "load":
          try {
            String path = sc.next();
            if (path.endsWith(".ppm".toLowerCase())) { // dealing with a .ppm file
              image = new ImageImpl(path);
            } else { // dealing with a different type of image file (.png / .jpeg, etc)
              IColor[][] arr = u.readFile(path); // create the color array from given file path
              image = new ImageImpl(arr); // creates the image from color array
            }
            name = sc.next(); // store the name of the file
            u.addImage(name, image);
            writeMessage("Successfully uploaded image." + "\n");
          } catch (Exception e) {
            writeMessage("Invalid filename: " + e.getMessage() + "\n");
          }
          break;
        case "save":
          try {
            String path = sc.next();
            name = sc.next();
            String fileType = path.substring(path.length() - 3); // ".jpg" or ".png" etc
            // System.out.println("fileType: " + fileType);
            u.saveImage(fileType, path, name);
            writeMessage("Saved image " + name + " to " + path + "\n");
          } catch (Exception e1) {
            writeMessage("Unable to save. " + e1.getMessage() + "\n");
          }
          break;
        case "brighten":
          try {
            int increment = sc.nextInt();
            name = sc.next();
            String newName = sc.next();
            Command c = new Brighten(increment);
            ImageModel curImage = u.getImage(name);
            // right now we are just mutating the original image
            curImage.executeCommand(c); // perform the command
            // save the image
            u.addImage(newName, curImage);
            writeMessage("Brightened " + name + " by " + increment + "\n");
          } catch (Exception e2) {
            writeMessage("Unable to brighten.\n");
          }
          break;
        case "vertical-flip":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new FlipVertical();
            ImageModel curImage = u.getImage(name);
            curImage.executeCommand(c);
            u.addImage(newName, curImage);
            writeMessage("Flipped " + name + " vertically.\n");
          } catch (Exception e3) {
            writeMessage("Unable to flip vertically.\n");
          }
          break;
        case "horizontal-flip":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new FlipHorizontal();
            ImageModel curImage = u.getImage(name);
            curImage.executeCommand(c);
            u.addImage(newName, curImage);
            writeMessage("Flipped " + name + " horizontally.\n");
          } catch (Exception e3) {
            writeMessage("Unable to flip horizontally.\n");
          }
          break;
        case "blue-grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new BlueGrayScale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Blue-grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to blue-grayscale\n");
          }
          break;
        case "red-grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new RedGrayScale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Red-grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to red-grayscale\n");
          }
          break;
        case "green-grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new GreenGrayScale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Green-grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to green-grayscale\n");
          }
          break;
        case "luma-grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new LumaGrayScale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Luma-grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to luma-grayscale\n");
          }
          break;
        case "value-grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new ValueGrayScale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Value-grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to value-grayscale\n");
          }
          break;
        case "intensity-grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new IntensityGrayScale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Intensity-grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to intensity-grayscale\n");
          }
          break;
        case "grayscale":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new Grayscale();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Grayscaled " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to grayscale\n");
          }
          break;
        case "sepia":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new Sepia();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Sepia'd " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to sepia\n");
          }
          break;
        case "blur":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new Blur();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Blurred " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to blur\n");
          }
          break;
        case "sharpen":
          try {
            name = sc.next();
            String newName = sc.next();
            Command c = new Sharpen();
            image = u.getImage(name);
            image.executeCommand(c);
            u.addImage(newName, image);
            writeMessage("Sharpen " + name + "\n");
          } catch (Exception e4) {
            writeMessage("Unable to sharpen\n");
          }
          break;
        case "-file":
          try { // try to read the instructions from the script file
            String filePath = sc.next();
            String fileText = Files.readString(Paths.get(filePath));
            StringReader reader = new StringReader(fileText);
            ControllerImpl newC = new ControllerImpl(reader, this.appendable);
            newC.control();
          } catch (Exception e4) {
            writeMessage("Unable to read script file\n");
          }
          break;
        default: // don't do anything if the user types an incorrect command
          writeMessage("Invalid command.");
          break;
      }
    }


  }

  // try appending a given String message to the output appendable
  private void writeMessage(String s) throws IllegalStateException {
    try {
      appendable.append(s);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
