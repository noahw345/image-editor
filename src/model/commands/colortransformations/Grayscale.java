package model.commands.colortransformations;

import model.ImageUtil;

/**
 * Represents the Grayscale Color Transformation.
 */
public class Grayscale extends ColorTransformation {

  @Override
  protected int[] applyMatrix(int[] rgb) {
    ImageUtil u = new ImageUtil();
    double[][] g = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}};
    return u.matMul(g, rgb);
  }
}
