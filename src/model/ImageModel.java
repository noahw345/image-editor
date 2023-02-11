package model;

import java.util.List;

import model.color.IColor;
import model.commands.Command;
import model.histogram.AbstractHistogram;

/**
 * Represents an Image model.
 */
public interface ImageModel {

  /**
   * Produces the given image.
   *
   * @return an Array of the image pixels with their various RGB values
   */
  IColor[][] getImage();

  /**
   * Method that gets the width of the given image.
   *
   * @return an int of the pixel width of the image
   */
  int getWidth();

  /**
   * Method that gets the height of the given image.
   *
   * @return an int of the pixel height of the image
   */
  int getHeight();

  /**
   * Accepts a Command object and executes it on the image.
   *
   * @param c the Command object.
   */
  void executeCommand(Command c);

  /**
   * Creates an identical copy of this image w/ a different memory location.
   *
   * @return an ImageModel representing the copy.
   */
  ImageModel copy();

  List<AbstractHistogram> getLOH();
}
