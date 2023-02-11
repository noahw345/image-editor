package model.commands;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageImpl;
import model.ImageModel;
import model.color.ColorImpl;
import model.color.IColor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Brighten class.
 */
public class BrightenTest {

  private ImageModel ex1;

  private ImageModel ex2;
  private ImageModel ex2Copy;


  @Before
  public void init() throws IOException {
    IColor[][] exampleArray1 = new ColorImpl[5][5];

    //for exampleArray1, 5x5
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        exampleArray1[r][c] = new ColorImpl(r + c, 2 * (r + c), 3 * (r + c));
      }
    }
    ex1 = new ImageImpl(exampleArray1);

    ////for exampleArray2, koala.ppg
    ex2 = new ImageImpl("startercode/Koala.ppm");
    ex2Copy = new ImageImpl("startercode/Koala.ppm");
  }

  @Test
  public void testInRGB() {
    //input to Brighten is arbitrary, because inRgb() doesn't care about increment field
    assertEquals(255, new Brighten(2342).clamp(256)); //ceiling at 255
    assertEquals(0, new Brighten(3453).clamp(-10)); //floor at 0
    assertEquals(157, new Brighten(1342).clamp(157));
  }

  @Test
  public void testGoCommand() {
    //for ex1, ensure values are as expected
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        assertEquals(r + c, ex1.getImage()[r][c].getRGB()[0]);
        assertEquals(2 * (r + c), ex1.getImage()[r][c].getRGB()[1]);
        assertEquals(3 * (r + c), ex1.getImage()[r][c].getRGB()[2]);
      }
    }

    //brighten the image
    new Brighten(10).goCommand(ex1); //brighten ex1

    //for ex1 (brightened)
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        //assert that brightened image RGB equals original image's RGB + 10
        assertEquals(r + c + 10, ex1.getImage()[r][c].getRGB()[0]);
        assertEquals(2 * (r + c) + 10, ex1.getImage()[r][c].getRGB()[1]);
        assertEquals(3 * (r + c) + 10, ex1.getImage()[r][c].getRGB()[2]);
      }
    }

    //brighten ex2, which contains the koala.ppm file
    //every pixel should now be 10 units of magnitude brighter than every pixel in ex2Copy
    new Brighten(10).goCommand(ex2); //brighten ex2

    //for ex2 (brightened)
    for (int r = 0; r < ex2.getHeight(); r++) {
      for (int c = 0; c < ex2.getWidth(); c++) {
        //assert that brightened image RGB equals original image's RGB + 10, with a max of 255
        assertEquals(Math.min(255, ex2Copy.getImage()[r][c].getRGB()[0] + 10),
                ex2.getImage()[r][c].getRGB()[0]);
        assertEquals(Math.min(255, ex2Copy.getImage()[r][c].getRGB()[1] + 10),
                ex2.getImage()[r][c].getRGB()[1]);
        assertEquals(Math.min(255, ex2Copy.getImage()[r][c].getRGB()[2] + 10),
                ex2.getImage()[r][c].getRGB()[2]);
      }
    }

    //darken ex2, which contains the koala.ppm file, also tests for consecutive commands
    //every pixel should now be 20 units of magnitude darker than every pixel in ex2Copy
    new Brighten(-10).goCommand(ex2); //darken ex2
    new Brighten(-10).goCommand(ex2); //darken ex2

    //for ex2 (brightened)
    for (int r = 0; r < ex2.getHeight(); r++) {
      for (int c = 0; c < ex2.getWidth(); c++) {
        //assert that brightened image RGB equals original image's RGB - 10, with a max of 255
        int copyRed = ex2Copy.getImage()[r][c].getRGB()[0] - 10;
        int ogRed = ex2.getImage()[r][c].getRGB()[0];
        System.out.println("(" + r + ", " + c + ")");
        System.out.println("Copy Red: " + copyRed);
        System.out.println("OG Red: " + ogRed + "\n");
        if (copyRed != ogRed) {
          System.out.println("reds not eqeual");
        }

        assertEquals(Math.min(255, ex2Copy.getImage()[r][c].getRGB()[0] - 10),
                ex2.getImage()[r][c].getRGB()[0]);
        assertEquals(Math.min(255, ex2Copy.getImage()[r][c].getRGB()[1] - 10),
                ex2.getImage()[r][c].getRGB()[1]);
        assertEquals(Math.min(255, ex2Copy.getImage()[r][c].getRGB()[2] - 10),
                ex2.getImage()[r][c].getRGB()[2]);
      }
    }
  }
}