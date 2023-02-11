package model.histogram;

import java.util.HashMap;
import java.util.Map;

import model.color.IColor;

/**
 * Represents an abstract model of a histogram.
 * Histogram represents RGB component values.
 */
public abstract class AbstractHistogram {

  //represents a map of histogram values
  //
  //         x-axis,  y-axis
  private Map<Integer, Integer> histogram;


  /**
   * constructs an AbstractHistogram model given one of three rgb values,
   * red, green, or blue, and a 2d array of IColor objects representing an rgb value.
   *
   * @param modelArr the 2d array of IColor
   * @param rgb      the given choice of red, green, or blue values
   */
  public AbstractHistogram(IColor[][] modelArr, String rgb) {

    this.histogram = new HashMap<>();

    for (int i = 0; i < 256; i++) {
      histogram.put(i, 0);
    }

    for (int r = 0; r < modelArr.length; r++) {
      for (int c = 0; c < modelArr[0].length; c++) {
        int rgbVal;

        switch (rgb) {
          case "red":
            rgbVal = modelArr[r][c].getRGB()[0];
            break;
          case "green":
            rgbVal = modelArr[r][c].getRGB()[1];
            break;
          case "blue":
            rgbVal = modelArr[r][c].getRGB()[2];
            break;
          default:
            rgbVal = 0;
        }
        histogram.put(rgbVal, histogram.get(rgbVal) + 1);

      }
    }
  }


  public Map<Integer, Integer> getHistogram() {
    return histogram;
  }
}
