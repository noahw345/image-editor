package model.color;


import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the RGB color implementation class "ColorImpl".
 */
public class ColorImplTest {

  @Before
  public void colorImplValidInputs() {
    IColor red = new ColorImpl(255, 0, 0);
    IColor green = new ColorImpl(0, 255, 0);
    IColor blue = new ColorImpl(0, 0, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorImplInvalidInputs() {
    IColor negativeRed = new ColorImpl(-1, 0, 0);
    IColor highRed = new ColorImpl(256, 0, 0);
    IColor negativeGreen = new ColorImpl(0, -10, 0);
    IColor highGreen = new ColorImpl(0, 256, 0);
    IColor negativeBlue = new ColorImpl(0, 0, -5);
    IColor highBlue = new ColorImpl(0, 0, 800);
    IColor veryInvalid = new ColorImpl(1238123, -1230, 800);
  }

}