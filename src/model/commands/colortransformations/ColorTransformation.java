package model.commands.colortransformations;

import model.ImageModel;
import model.commands.AbstractCommand;

/**
 * Represents an abstract Color Transformation.
 */
public abstract class ColorTransformation extends AbstractCommand {


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
    // apply kernel to each r,g,b value
    int newRed = applyMatrix(image.getImage()[r][c].getRGB())[0];
    int newGreen = applyMatrix(image.getImage()[r][c].getRGB())[1];
    int newBlue = applyMatrix(image.getImage()[r][c].getRGB())[2];

    // change value of each r,g,b
    image.getImage()[r][c].getRGB()[0] = newRed;
    image.getImage()[r][c].getRGB()[1] = newGreen;
    image.getImage()[r][c].getRGB()[2] = newBlue;

  }

  protected abstract int[] applyMatrix(int[] rgb);

}
