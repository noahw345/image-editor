package model.commands.filters;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageImpl;
import model.ImageModel;
import model.color.ColorImpl;
import model.color.IColor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Blur class.
 */
public class BlurTest {

  private ImageModel ex1;
  private IColor[][] exampleArray1;

  @Before
  public void init() throws IOException {
    exampleArray1 = new ColorImpl[3][3];

    // make an image where for each pixel, red value is the row
    // green value is the column, and blue value is the sum of row + col.
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        exampleArray1[r][c] = new ColorImpl(20, 20, 20);
        System.out.println("Color values at (" + r + "," + c + ")"
                + " are " + 20 + " " + 20 + " " + 20);
      }
    }

    ex1 = new ImageImpl(exampleArray1);
  }

  @Test
  public void applyKernel() {
    ex1.executeCommand(new Blur());

    System.out.println("");
    System.lineSeparator();
    System.out.println("New values after kernel applied");

    double[][] kernel = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    IColor[][] pixels = exampleArray1;

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {

        // store each color for each pixel
        int newRedVal = ex1.getImage()[r][c].getRGB()[0];
        int newGreenVal = ex1.getImage()[r][c].getRGB()[1];
        int newBlueVal = ex1.getImage()[r][c].getRGB()[2];

        System.out.println("Color values at (" + r + "," + c + ")"
                + " are " + newRedVal + " " + newGreenVal + " " + newBlueVal);
      }
    }

    // manually calculating what the new value should be for certain rgb channels
    // of specific pixels

    // the red value of position (0,0) is initially 20
    double expectedFinalRedValTopLeftCorner = (0.25 * 20) + (.125 * 20) + (.0625 * 20)
            + (.125 * 20);
    assertEquals(ex1.getImage()[0][0].getRGB()[0], (Math.round(expectedFinalRedValTopLeftCorner)));

    double expectedFinalBlueValBottomRightCorner = (0.25 * 20) + (.125 * 20) + (.0625 * 20)
            + (.125 * 20);
    assertEquals(ex1.getImage()[2][2].getRGB()[1],
            (Math.round(expectedFinalBlueValBottomRightCorner)));

  }

}