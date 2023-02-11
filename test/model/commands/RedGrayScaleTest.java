package model.commands;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageImpl;
import model.ImageModel;
import model.color.ColorImpl;
import model.color.IColor;
import model.commands.grayscale.RedGrayScale;

import static org.junit.Assert.assertEquals;

/**
 * Tests the RedGrayScale class.
 */
public class RedGrayScaleTest {

  private ImageModel ex1;
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
    ex2 = new ImageImpl(exampleArray2);
  }

  @Test
  public void testGoCommand() {
    new RedGrayScale().goCommand(ex1);
    new RedGrayScale().goCommand(ex2);


    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        assertEquals(ex1.getImage()[r][c].getRGB()[0], ex1.getImage()[r][c].getRGB()[1]);
        assertEquals(ex1.getImage()[r][c].getRGB()[0], ex1.getImage()[r][c].getRGB()[2]);
        assertEquals(ex1.getImage()[r][c].getRGB()[1], ex1.getImage()[r][c].getRGB()[2]);
      }
    }

    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        assertEquals(ex2.getImage()[row][col].getRGB()[0], ex2.getImage()[row][col].getRGB()[1]);
        assertEquals(ex2.getImage()[row][col].getRGB()[0], ex2.getImage()[row][col].getRGB()[2]);
        assertEquals(ex2.getImage()[row][col].getRGB()[1], ex2.getImage()[row][col].getRGB()[2]);
      }
    }
  }
}