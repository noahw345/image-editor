package model.commands;

import model.ImageModel;
import model.color.IColor;

/**
 * Represents a Vertical Flip command.
 */
public class FlipVertical extends AbstractCommand {
  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    // never called
  }

  /**
   * Executes this command on a given image.
   *
   * @param image the given image.
   */
  @Override
  public void goCommand(ImageModel image) {
    int width = image.getWidth();
    int height = image.getHeight();
    ImageModel copy = image.copy(); //a copy of the given image in a new memory location
    IColor[][] copyArr = new IColor[image.getHeight()][image.getWidth()]; //make new array for copy
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {

        //the current pixel and the pixel from the other side
        IColor keep = image.getImage()[r][c];
        IColor otherPixel = copy.getImage()[height - (r + 1)][c];

        //assign the current RGB to "the other side" of the image array
        copyArr[r][c] = otherPixel;
        copyArr[height - (r + 1)][c] = keep;
      }
    }
  }

}
