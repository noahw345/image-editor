package model.commands;

import model.ImageModel;

/**
 * Represents a Command.
 */
public interface Command {

  /**
   * Helps to ensure that any rgb value is never above 255 or below 0.
   *
   * @param rgb the red, green, or blue value to be checked.
   * @return a new int that is in between 0 and 255, inclusive
   */
  int clamp(int rgb);

  /**
   * Executes this command on a given image.
   *
   * @param image the given image.
   */
  void goCommand(ImageModel image);

  /**
   * Executes this command on a given image.
   *
   * @param image the given image.
   * @param mask the image's mask
   */
  void goCommand(ImageModel image, ImageModel mask);
}
