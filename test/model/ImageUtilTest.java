package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.color.ColorImpl;
import model.color.IColor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ImageUtil class.
 */
public class ImageUtilTest {

  ImageModel example1;
  ImageModel example2;
  ImageModel example3;
  ImageModel example4;
  IColor[][] exampleArray;
  ImageUtil util = new ImageUtil();
  private ImageModel updated;

  @Before
  public void init() throws IOException {
    exampleArray = new IColor[5][5];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        // make the top left 3x3 black
        exampleArray[r][c] = new ColorImpl(0, 0, 0);
      }
    }
    for (int r = 3; r < 5; r++) {
      for (int c = 3; c < 5; c++) {
        // make the rest red
        exampleArray[r][c] = new ColorImpl(20, 0, 0);
      }
    }
    example1 = new ImageImpl(exampleArray);
    example2 = new ImageImpl(new IColor[5][5]);
    example3 = new ImageImpl(new IColor[100][75]);
    example4 = new ImageImpl("startercode/Koala.ppm");
  }

  @Test
  public void getHeightPPMValidFile() throws FileNotFoundException {
    assertEquals(768, ImageUtil.getHeightPPM("startercode/Koala.ppm"));
  }

  @Test(expected = FileNotFoundException.class)
  public void getHeightPPMInvalidFile() throws FileNotFoundException {
    assertEquals(768, ImageUtil.getHeightPPM("startercode/Not a file.ppm"));
    assertEquals(100, ImageUtil.getHeightPPM("startercode/Still not a file.ppm"));
  }

  @Test
  public void getWidthPPM() throws FileNotFoundException {
    assertEquals(1024, ImageUtil.getWidthPPM("startercode/Koala.ppm"));
  }

  @Test(expected = FileNotFoundException.class)
  public void getWidthPPMInvalidFile() throws FileNotFoundException {
    assertEquals(1024, ImageUtil.getWidthPPM("startercode/Not a file.ppm"));
    assertEquals(1024, ImageUtil.getWidthPPM("startercode/Still not a file.ppm"));
  }

  @Test
  public void matMul() {
    double[][] grayScale = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152,
            0.0722}};

    double[][] sepia = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};

    int[] rgb = {21, 21, 21};
    int[] rgb1 = {0, 15, 25};

    double num = (21 * .2126) + (21 * .7152) + (21 * .0722);
    System.out.println("num: " + num);

    double num2 = (0 * .393) + (15 * .769) + (25 * .189);
    System.out.printf("num2: " + num2);
    System.out.println("num2 rounded: " + Math.round(num2));
    System.out.println((int) (Math.round(num2)));


    int[] actualResult = util.matMul(grayScale, rgb);
    int[] expectedResult = {util.clamp((int) Math.round(num)), util.clamp((int) Math.round(num)),
            util.clamp((int) Math.round(num))};
    assertEquals(actualResult, expectedResult);

  }
}