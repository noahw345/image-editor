package model.commands.filters;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageImpl;
import model.ImageModel;
import model.ImageUtil;
import model.color.ColorImpl;
import model.color.IColor;
import model.commands.AbstractCommand;
import model.commands.colortransformations.Grayscale;

import static org.junit.Assert.assertEquals;

/**
 * Tests GrayScale.
 */
public class GrayscaleTest {

  private ImageModel ex1;

  private ImageUtil u = new ImageUtil();


  @Before
  public void init() throws IOException {

    IColor[][] exampleArray1 = new ColorImpl[2][2];
    //for exampleArray1
    for (int r = 0; r < 2; r++) {
      for (int c = 0; c < 2; c++) {
        exampleArray1[r][c] = new ColorImpl(u.clamp(r + c), u.clamp(15 + r - c),
                u.clamp(25 + r * c));
      }
    }
    ex1 = new ImageImpl(exampleArray1);
  }

  @Test
  public void applyMatrix() {

    // print initial rgb values for each pixel of the ex1 image
    for (int r = 0; r < ex1.getImage().length; r++) {
      for (int c = 0; c < ex1.getImage()[0].length; c++) {
        System.out.println("Pixel at position (" + r + "," + c
                + ") has RGB: " + ex1.getImage()[r][c].getRGB()[0]
                + " " + ex1.getImage()[r][c].getRGB()[1]
                + " " + ex1.getImage()[r][c].getRGB()[2]);
      }
    }


    // call the grayscale command
    AbstractCommand grayscale = new Grayscale();
    ex1.executeCommand(grayscale);

    System.out.println(" ");
    System.out.println("NEW VALUES");

    // print rgb values for each pixel of the ex1 image again
    for (int r = 0; r < ex1.getImage().length; r++) {
      for (int c = 0; c < ex1.getImage()[0].length; c++) {
        System.out.println("Pixel at position (" + r + "," + c
                + ") has RGB: " + ex1.getImage()[r][c].getRGB()[0]
                + " " + ex1.getImage()[r][c].getRGB()[1]
                + " " + ex1.getImage()[r][c].getRGB()[2]);
      }
    }

    // check expected vs. actual of each pixel of ex1 image
    // check red value of pixel at row 0 col 0
    assertEquals((int) Math.round(0 * .2126 + 15 * 0.7152 + 25 * 0.0722),
            ex1.getImage()[0][0].getRGB()[0]);
    // check red value of pixel at row 0 col 1
    assertEquals((int) Math.round(1 * .2126 + 14 * 0.7152 + 25 * 0.0722),
            ex1.getImage()[0][1].getRGB()[0]);
    // check green value of pixel at row 1 col 0
    assertEquals((int) Math.round(1 * 0.2126 + 16 * 0.7152 + 25 * .0722),
            ex1.getImage()[1][0].getRGB()[1]);
    // check blue value of pixel at row 1 col 1
    assertEquals((int) Math.round(2 * 0.2126 + 15 * 0.7152 + 26 * .0722),
            ex1.getImage()[1][1].getRGB()[2]);
  }
}