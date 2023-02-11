package model.color;


/**
 * Represents an RGB color implementation.
 */
public class ColorImpl implements IColor {

  int[] rgb = new int[3];


  /**
   * Creates a new Color implementation.
   *
   * @param red   int representing the red magnitude.
   * @param green int representing the green magnitude.
   * @param blue  int representing the blue magnitude.
   * @throws IllegalArgumentException if any of the magnitudes are invalid.
   */
  public ColorImpl(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0 || red > 256 || green > 256 || blue > 256) {
      throw new IllegalArgumentException("Invalid input of red, blue, or green magnitude.");
    }
    rgb[0] = red;
    rgb[1] = green;
    rgb[2] = blue;
  }

  /**
   * returns this RGB array, containing the red, blue, and green values.
   *
   * @return an int array, containing the red, blue, and green values.
   */
  public int[] getRGB() {
    return this.rgb;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof IColor)) {
      return false;
    }
    IColor other = (ColorImpl) o;
    return this.getRGB()[0] == other.getRGB()[0]
            && this.getRGB()[1] == other.getRGB()[1]
            && this.getRGB()[2] == other.getRGB()[2];
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 0;
    result = prime * result + this.getRGB()[0];
    result = prime * result + this.getRGB()[1];
    result = prime * result + this.getRGB()[2];
    return result;
  }

}
