package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ControllerImpl class.
 */
public class ControllerImplTest {


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullArguments() {
    Controller controller = new ControllerImpl(null, null);
    Controller controller1 = new ControllerImpl(null, new StringBuilder());
    Controller controller2 = new ControllerImpl(new StringReader("Test"), null);
  }

  @Test
  public void loadValidFile() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4"
            + "/startercode/Koala.ppm koala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Successfully uploaded image.", outputLines[1]);
  }

  @Test
  public void loadiInvalidFile() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load invalidfilenamepath koala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Invalid filename: invalidfilenamepath (No such file or directory)",
            outputLines[1]);
  }

  @Test
  public void brighten() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/startercode"
            + "/Koala.ppm koala\n" + "brighten 10 koala brighter-koala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Brightened koala by 10", outputLines[3]);
  }

  @Test
  public void unableToBrighten() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4"
            + "/startercode/Koala.ppm koala\n" + "brighten 10 invalidName brighter-koala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to brighten.", outputLines[3]);
  }

  @Test
  public void verticalFlip() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "vertical-flip koala koala-vertical");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Flipped koala vertically.", outputLines[3]);
  }

  @Test
  public void unableToVerticalFlip() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "vertical-flip invalidName koala-vertical");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to flip vertically.", outputLines[3]);
  }

  @Test
  public void horizontalFlip() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "horizontal-flip koala koala-horizontal");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Flipped koala horizontally.", outputLines[3]);
  }

  @Test
  public void unableToHorizontalFlip() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "horizontal-flip invalidName koala-horizontal");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to flip horizontally.", outputLines[3]);
  }

  @Test
  public void blueGrayscale() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "blue-grayscale koala blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Blue-grayscaled koala", outputLines[3]);
  }

  @Test
  public void blueGrayscaleInvalidName() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "blue-grayscale nandasdjnas blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to blue-grayscale", outputLines[3]);
  }

  @Test
  public void redGrayscale() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "red-grayscale koala blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Red-grayscaled koala", outputLines[3]);
  }

  @Test
  public void redGrayscaleInvalidName() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "red-grayscale nandasdjnas blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to red-grayscale", outputLines[3]);
  }

  @Test
  public void greenGrayscale() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "green-grayscale koala blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Green-grayscaled koala", outputLines[3]);
  }

  @Test
  public void greenGrayscaleInvalidName() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "green-grayscale nandasdjnas blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to green-grayscale", outputLines[3]);
  }

  @Test
  public void lumaGrayscale() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "luma-grayscale koala blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Luma-grayscaled koala", outputLines[3]);
  }

  @Test
  public void lumaGrayscaleInvalidName() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "luma-grayscale nandasdjnas blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to luma-grayscale", outputLines[3]);
  }

  @Test
  public void valueGrayscale() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "value-grayscale koala blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Value-grayscaled koala", outputLines[3]);
  }

  @Test
  public void valueGrayscaleInvalidName() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "value-grayscale nandasdjnas blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to value-grayscale", outputLines[3]);
  }

  @Test
  public void intensityGrayscale() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "intensity-grayscale koala blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Intensity-grayscaled koala", outputLines[3]);
  }

  @Test
  public void intensityGrayscaleInvalidName() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("load /Users/isaaclevine/Desktop/OOD/Assignment4/"
            + "startercode/Koala.ppm koala\n" + "intensity-grayscale nandasdjnas blueKoala");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Unable to intensity-grayscale", outputLines[3]);
  }

  @Test
  public void savePng() throws IOException {
    Appendable out = new StringBuilder();
    Readable in =
            new StringReader("load /Users/isaaclevine/Desktop/Assignment5/res/koala.png face\n"
                    + "save desktop face");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Saved face to desktop", outputLines[2]);
  }

  @Test
  public void parseScript() throws IOException {
    Appendable out = new StringBuilder();
    Readable in = new StringReader("-file /Users/isaaclevine/Desktop/Assignment5/res/" +
            "examplescript.txt\n");
    Controller controller = new ControllerImpl(in, out);
    controller.control();
    String[] outputLines = out.toString().split("\n");
    assertEquals("Successfuly saved face to desktop", outputLines[2]);
  }
}