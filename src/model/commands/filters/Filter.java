package model.commands.filters;

import model.ImageModel;
import model.color.IColor;
import model.commands.AbstractCommand;

/**
 * Represents a filter that is applied to every pixel of an image.
 */
public abstract class Filter extends AbstractCommand {

  protected abstract int applyKernel(int r, int c,
                                     IColor[][] pixels, int rgb);

  /**
   * Update's this pixel's R,G,B values using the applyKernel function with the kernel
   * representing the blur.
   *
   * @param r     int representing the row of this pixel.
   * @param c     int representing the column of this pixel.
   * @param image the image we are working within.
   */
  @Override
  protected void updatePixel(int r, int c, ImageModel image) {
    IColor[][] pixels = image.getImage();

    // apply kernel to each r,g,b value
    int newRed = applyKernel(r, c, pixels, 0);
    int newGreen = applyKernel(r, c, pixels, 1);
    int newBlue = applyKernel(r, c, pixels, 2);

    // change value of each r,g,b
    image.getImage()[r][c].getRGB()[0] = newRed;
    image.getImage()[r][c].getRGB()[1] = newGreen;
    image.getImage()[r][c].getRGB()[2] = newBlue;

  }

}
