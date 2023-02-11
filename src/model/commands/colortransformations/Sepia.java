package model.commands.colortransformations;

import model.ImageUtil;

/**
 * Represents the Sepia Color Transformation.
 */
public class Sepia extends ColorTransformation {

  @Override
  protected int[] applyMatrix(int[] rgb) {
    ImageUtil u = new ImageUtil();
    double[][] sepia = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
    return u.matMul(sepia, rgb);
  }
}
