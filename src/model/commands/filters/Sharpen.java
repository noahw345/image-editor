package model.commands.filters;

import model.color.IColor;

/**
 * Represents a Sharpen filter.
 */
public class Sharpen extends Filter {
  /**
   * Applies the kernel to this red green or blue value and returns the updated value.
   * This method needs to be called independently on each R,G,B value for any given pixel.
   *
   * @param r   the row of the pixel that this color value came from
   * @param c   the column of the pixel that this color value came from @param pixels
   *            IColor[][] representing all the pixels in the image we are working on
   * @param rgb 0 if this value represents red, 1 if green, 2 if blue
   * @return the updated color value for this pixel's red, green, or blue value
   */
  @Override
  protected int applyKernel(int r, int c, IColor[][] pixels,
                            int rgb) {

    double[][] kernel = {
            {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8},
            {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
            {-1 / 8, 1 / 4, 1, 1 / 4, -1 / 8},
            {-1 / 8, 1 / 4, 1 / 4, 1 / 4, -1 / 8},
            {-1 / 8, -1 / 8, -1 / 8, -1 / 8, -1 / 8}};

    int kernelRow = 0;
    int kernelCol = 0;
    int colorVal = 0;
    for (int y = r - 2; y <= r + 2; y++) {
      for (int x = c - 2; x <= c + 2; x++) {
        try {
          // add product of kernel value and pixel value to this color
          colorVal += kernel[kernelRow][kernelCol] * pixels[y][x].getRGB()[rgb];
        } catch (IndexOutOfBoundsException e) {
          continue; // don't change value
        }
        kernelRow++;
      }
      kernelRow = 0;
      kernelCol++;
    }
    return clamp((int) Math.round(colorVal));
  }
}

