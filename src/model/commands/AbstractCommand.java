package model.commands;

import model.ImageModel;

/**
 * Represents an arbitrary command.
 */
public abstract class AbstractCommand implements Command {

  // update pixel according to each command's implementation
  protected abstract void updatePixel(int r, int c, ImageModel image);

  /**
   * Helps to ensure that any rgb value is never above 255 or below 0.
   *
   * @param rgb the red, green, or blue value to be checked.
   * @return a new int that is in between 0 and 255, inclusive
   */
  @Override
  public int clamp(int rgb) {
    if (rgb > 255) {
      rgb = 255;
    } else if (rgb < 0) {
      rgb = 0;
    }
    return rgb;
  }

  /**
   * Executes this command on a given image.
   *
   * @param image the given image.
   */
  @Override
  public void goCommand(ImageModel image) {
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        updatePixel(r, c, image);
      }
    }
  }

  /**
   * Executes this command on a given image.
   *
   * @param image the given image.
   * @param mask the image's mask.
   */
  @Override
  public void goCommand(ImageModel image, ImageModel mask) {
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        if(mask.getImage()[r][c].getRGB()[0] == 0
                && mask.getImage()[r][c].getRGB()[1] == 0
                && mask.getImage()[r][c].getRGB()[2] == 0) {
          updatePixel(r, c, image);
        }
      }
    }
  }


}
