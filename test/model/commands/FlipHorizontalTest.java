package model.commands;

import org.junit.Test;

import java.io.FileNotFoundException;

import model.ImageImpl;
import model.ImageModel;
import model.color.ColorImpl;
import model.color.IColor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the FlipHorizontal class.
 */
public class FlipHorizontalTest {


  @Test
  public void testGoCommand() throws FileNotFoundException {

    IColor[][] exampleArray1 = new ColorImpl[5][5];
    IColor[][] exampleArray2 = new ColorImpl[2][2];
    IColor[][] exampleArray2Copy = new ColorImpl[2][2];

    ImageModel koala = new ImageImpl("startercode/Koala.ppm");
    IColor[][] koalaArrayOG = koala.getImage();


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
        //exampleArray2Copy[r][c] = new ColorImpl(r * c, 2 * (r * c), 3 * (r * c));

      }
    }

    ImageModel ex1 = new ImageImpl(exampleArray1);
    ImageModel ex2 = new ImageImpl(exampleArray2);

    exampleArray2Copy = exampleArray2;
    ImageModel ex2Copy = new ImageImpl(exampleArray2Copy);
    //koalaArrayFlipped = new FlipHorizontal().goCommand(koala);
    for (int row = 0; row < koala.getHeight(); row++) {
      for (int col = 0; col < koala.getWidth(); col++) {
        assertEquals(ex2.getImage()[row][col].getRGB()[0],
                ex2Copy.getImage()[row][ex2Copy.getWidth() - col - 1].getRGB()[0]);

        assertEquals(ex2.getImage()[row][col].getRGB()[1],
                ex2Copy.getImage()[row][ex2Copy.getWidth() - col - 1].getRGB()[1]);

        assertEquals(ex2.getImage()[row][col].getRGB()[2],
                ex2Copy.getImage()[row][ex2Copy.getWidth() - col - 1].getRGB()[2]);

      }
    }

    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 2; col++) {
        System.out.println("Original Red @ (" + row + ", " + col + "): "
                + ex2.getImage()[row][col].getRGB()[0]);
        System.out.println("Modified Red @ (" + row + ", " + col + "): "
                + ex2.getImage()[row][col].getRGB()[0]);
        assertEquals(ex2.getImage()[row][col].getRGB()[0],
                ex2Copy.getImage()[row][ex2Copy.getWidth() - col - 1].getRGB()[0]);

        assertEquals(ex2.getImage()[row][col].getRGB()[1],
                ex2Copy.getImage()[row][ex2Copy.getWidth() - col - 1].getRGB()[1]);

        assertEquals(ex2.getImage()[row][col].getRGB()[2],
                ex2Copy.getImage()[row][ex2Copy.getWidth() - col - 1].getRGB()[2]);
      }
    }


  }
}