package model.histogram;

import org.junit.Before;
import org.junit.Test;

import model.ImageImpl;
import model.ImageModel;
import model.color.ColorImpl;
import model.color.IColor;

import static org.junit.Assert.assertEquals;

/**
 * tests AbstractHistogram class.
 */
public class AbstractHistogramTest {

  IColor[][] modelArr;
  ImageModel model;
  AbstractHistogram histRed;
  AbstractHistogram histGreen;
  AbstractHistogram histBlue;

  @Before
  public void setup() {
    modelArr = new IColor[2][2];
    for (int r = 0; r < 2; r++) {
      for (int c = 0; c < 2; c++) {
        modelArr[r][c] = new ColorImpl(r, c, r + c);
      }
    }


    model = new ImageImpl(modelArr);
    histRed = new RedHistogram(modelArr);
    histGreen = new GreenHistogram(modelArr);
    histBlue = new BlueHistogram(modelArr);
  }

  @Test
  public void testHistograms() {
    assertEquals(2, (int) histRed.getHistogram().get(0));
    assertEquals(2, (int) histRed.getHistogram().get(1));
    assertEquals(0, (int) histRed.getHistogram().get(2));
  }

}