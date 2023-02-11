package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.color.ColorImpl;
import model.color.IColor;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ImageImpl class.
 */
public class ImageImplTest {

  ImageModel example1;
  ImageModel example2;
  ImageModel example3;
  ImageModel example4;
  IColor[][] exampleArray;

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


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullArray() {
    IColor[][] nullArr = null;
    ImageModel incorrect2 = new ImageImpl(nullArr);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullFileName() throws IOException {
    String nullStr = null;
    ImageModel incorrect2 = new ImageImpl(nullStr);
  }

  // Test getWidth():
  @Test
  public void testGetWidth() {
    assertEquals(5, example1.getWidth());
    assertEquals(5, example2.getWidth());
    assertEquals(75, example3.getWidth());
    assertEquals(1024, example4.getWidth());
  }

  // Test getHeight():
  @Test
  public void testGetHeight() {
    assertEquals(5, example1.getHeight());
    assertEquals(5, example2.getHeight());
    assertEquals(100, example3.getHeight());
    assertEquals(768, example4.getHeight());
  }


  // Test getImage():
  @Test
  public void testGetImage() {
    assertEquals(new ColorImpl(0, 0, 0), example1.getImage()[0][0]);
    assertEquals(new ColorImpl(20, 0, 0), example1.getImage()[4][4]);
  }

}