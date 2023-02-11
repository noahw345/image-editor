package model.commands;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageImpl;
import model.ImageModel;
import model.color.ColorImpl;
import model.color.IColor;
import model.commands.grayscale.LumaGrayScale;

import static org.junit.Assert.assertEquals;

/**
 * Tests the LumaGrayScale class.
 */
public class LumaGrayScaleTest {

  private ImageModel ex1;
  private ImageModel ex1OG;
  private ImageModel ex2;


  @Before
  public void init() throws IOException {
    IColor[][] exampleArray1 = new ColorImpl[5][5];
    IColor[][] exampleArray2 = new ColorImpl[2][2];

    //for exampleArray1
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        exampleArray1[r][c] = new ColorImpl(r + c, 2 * (r + c), 3 * (r + c));
      }
    }

    ////for exampleArray2
    for (int r = 0; r < 2; r++) {
      for (int c = 0; c < 2; c++) {
        exampleArray2[r][c] = new ColorImpl(r * c, 2 * (r * c), 3 * (r * c));
      }
    }

    ex1 = new ImageImpl(exampleArray1);
    ex1OG = new ImageImpl(exampleArray1);
    ex2 = new ImageImpl(exampleArray2);
  }

  @Test
  public void updatePixel() {
    new LumaGrayScale().goCommand(ex1);
    new LumaGrayScale().goCommand(ex2);

    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        assertEquals(ex1.getImage()[r][c].getRGB()[0], ex1.getImage()[r][c].getRGB()[1]);
        //assert red == green
        assertEquals(ex1.getImage()[r][c].getRGB()[0], ex1.getImage()[r][c].getRGB()[2]);
        //assert red == blue
        assertEquals(ex1.getImage()[r][c].getRGB()[1], ex1.getImage()[r][c].getRGB()[2]);
        //assert blue == green

        double lumaSum =
                (ex1OG.getImage()[r][c].getRGB()[0] * 0.2126)
                        + (ex1OG.getImage()[r][c].getRGB()[1] * 0.7152)
                        + (ex1OG.getImage()[r][c].getRGB()[2] * 0.0722);
        assertEquals(ex1.getImage()[r][c].getRGB()[0], lumaSum, 0.01);
      }
    }
  }
}