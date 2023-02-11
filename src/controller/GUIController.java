package controller;

import java.io.IOException;

import model.ImageModel;
import model.ImageUtil;
import model.commands.Brighten;
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
 * Represents a controller for the GUI View to interact with.
 */
public class GUIController implements Controller {

  ImageUtil u = new ImageUtil();
  private ImageModel image;
  private String command;

  /**
   * Initializes the readable and the image we are working on.
   *
   * @param image the given image model.
   * @param command the command provided from the view.
   */
  public GUIController(ImageModel image, String command) {
    this.image = image;
    this.command = command;
  }


  /**
   * Runs the controller and executes the command.
   *
   * @throws IOException if given bad readable.
   */
  @Override
  public void control() throws IOException {
    switch (command) {
      case "brighten":
        image.executeCommand(new Brighten(10));
        break;
      case "darken":
        image.executeCommand(new Brighten(-10));
        break;
      case "vertical-flip":
        image.executeCommand(new FlipVertical());
        break;
      case "horizontal-flip":
        image.executeCommand(new FlipHorizontal());
        break;
      case "blue-grayscale":
        image.executeCommand(new BlueGrayScale());
        break;
      case "red-grayscale":
        image.executeCommand(new RedGrayScale());
        break;
      case "green-grayscale":
        image.executeCommand(new GreenGrayScale());
        break;
      case "luma-grayscale":
        image.executeCommand(new LumaGrayScale());
        break;
      case "value-grayscale":
        image.executeCommand(new ValueGrayScale());
        break;
      case "intensity-grayscale":
        image.executeCommand(new IntensityGrayScale());
        break;
      case "grayscale":
        image.executeCommand(new Grayscale());
        break;
      case "sepia":
        image.executeCommand(new Sepia());
        break;
      case "blur":
        image.executeCommand(new Blur());
        break;
      case "sharpen":
        image.executeCommand(new Sharpen());
        break;
      default:
        break;
    }
  }
}
