package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.color.ColorImpl;
import model.color.IColor;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ImageUtil {
  private static Scanner s;
  private Map<String, ImageModel> images = new HashMap<String, ImageModel>();


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * This method will take in a PPM filename as a String just like the given method, except it will
   * employ a local variable with a value of {@code int[][][]} and fill in the RGB values of the
   * given PPM file, pixel by pixel.  This will allow us to turn a given PPM file into the array
   * of integers that we have chosen to use to represent an image in this program.
   *
   * @param filename the pathname of the given PPM file
   * @return an IColor[][] array that represents every pixel.
   * @throws FileNotFoundException if the given file is not found
   */
  public static IColor[][] ppmToArray(String filename) throws FileNotFoundException {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException(e.getMessage());
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    IColor[][] imageModel = new IColor[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imageModel[i][j] = new ColorImpl(r, g, b);
      }
    }
    return imageModel;
  }

  /**
   * Gets the height of a ppm file, given the filename.
   *
   * @param filename String representing the file's name.
   * @return int representing the image's height.
   * @throws FileNotFoundException if the file name or path is invalid.
   */
  public static int getHeightPPM(String filename) throws FileNotFoundException {
    IColor[][] arr = ppmToArray(filename);
    return arr.length;
  }

  /**
   * Gets the width of a ppm file, given the filename.
   *
   * @param filename String representing the file's name.
   * @return int representing the image's width.
   * @throws FileNotFoundException if the file name or path is invalid.
   */
  public static int getWidthPPM(String filename) throws FileNotFoundException {
    IColor[][] arr = ppmToArray(filename);
    return arr[0].length;
  }

  /**
   * Given the path of an image file, generate an IColor[][] representing the pixels of the image.
   *
   * @param pathname String representing the path of the image file.
   * @return the IColor[][] representing every pixel in the image.
   */
  public static IColor[][] readFile(String pathname) {
    BufferedImage bufferedImage = null;
    File f = new File(pathname);
    FileInputStream inputStream = null;
    // create a BufferedImage based on given pathname
    try {
      inputStream = new FileInputStream(pathname);
      bufferedImage = ImageIO.read(inputStream);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    // Creating a new IColor based on the BufferedImage created above
    IColor[][] arr = new IColor[bufferedImage.getHeight()][bufferedImage.getWidth()];

    for (int r = 0; r < bufferedImage.getHeight(); r++) {
      for (int c = 0; c < bufferedImage.getWidth(); c++) {
        int rgb = bufferedImage.getRGB(r, c);
        Color color = new Color(rgb);
        arr[r][c] = new ColorImpl(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
    return arr;
  }

  /**
   * Builds a PPM file from a given image name and map of images.
   *
   * @param name   the name of the image that we want to build a PPM for.
   * @param images hashmap of all the images we have.
   * @return the PPM String representation of the image.
   */
  public String buildPPM(String name, Map<String, ImageModel> images) {
    // verify that the image exists in the map
    if (name == null || !images.containsKey(name)) {
      throw new IllegalArgumentException("The image " + name + " does not exist.");
    }

    // grab the image from the map
    ImageModel image = images.get(name);

    StringBuilder builder = new StringBuilder();

    // write the header
    builder.append("P3" + System.lineSeparator()
            + image.getWidth() + " " + image.getHeight() + System.lineSeparator()
            + 255);

    // write the pixel values
    for (int r = 0; r < image.getHeight(); r++) {
      builder.append(System.lineSeparator());
      for (int c = 0; c < image.getWidth(); c++) {
        builder.append(image.getImage()[r][c].getRGB()[0] + " "
                + image.getImage()[r][c].getRGB()[1] + " "
                + image.getImage()[r][c].getRGB()[2]);
        if (c != image.getWidth() - 1) {
          builder.append("  "); // add double space after all but rightmost pixel of any col
        }
      }
    }
    return builder.toString();
  }

  /**
   * Saves a given image's PPM.
   *
   * @param filePath the path name of the image.
   * @param name     the name of the image.
   * @param images   a map of all the images that have been saved.
   * @throws IOException if filewriter is unable to write the file.
   */
  public void saveImagePPM(String filePath, String name, Map<String, ImageModel> images)
          throws IOException {
    String ppm = buildPPM(name, images);
    FileWriter fileWriter = new FileWriter(filePath);
    fileWriter.write(ppm);
    fileWriter.close();
  }

  /**
   * Saves an image of given type, path, name, and map of loaded images.
   *
   * @param fileType  the type of image (jpg/png,etc) that we want to save to.
   * @param pathName  the destination of where to save the image to on the computer.
   * @param imageName name of the image that we want to save.
   * @throws IOException if the image has not already been loaded.
   */
  public void saveImage(String fileType, String pathName, String imageName) throws IOException {
    // verify that the image was already loaded into images map
    if (!images.containsKey(imageName)) {
      throw new IllegalArgumentException("Given image is not uploaded.");
    }

    // if ppm image -> save old way
    if (pathName.endsWith(".ppm".toLowerCase())) {
      saveImagePPM(pathName, imageName, images);
    }


    File f = new File(pathName);

    // sets the colors of the stored image to the buffered image that we are going to save
    IColor[][] arr = images.get(imageName).getImage();
    int width = images.get(imageName).getWidth();
    int height = images.get(imageName).getHeight();
    BufferedImage jpgImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int red = arr[r][c].getRGB()[0];
        int green = arr[r][c].getRGB()[1];
        int blue = arr[r][c].getRGB()[2];
        Color newColor = new Color(red, green, blue);
        jpgImage.setRGB(r, c, newColor.getRGB());
      }
    }
    //System.out.println("File type: " + fileType);
    try {
      ImageIO.write(jpgImage, fileType, new FileOutputStream(f));
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot save image.");
    }
  }

  /**
   * Produces the rounded and capped int[] result of multiplying rgb values by a given matrix.
   *
   * @param matrix da matrix.
   * @param rgb    the rgb values.
   * @return the int[] result of the matrix multiplication.
   */
  public int[] matMul(double[][] matrix, int[] rgb) {
    int[] resultRGB = new int[3];
    for (int r = 0; r < matrix.length; r++) {
      double resultColor = 0;
      for (int c = 0; c < matrix[0].length; c++) {
        resultColor += matrix[r][c] * rgb[c];
      }
      resultRGB[r] = this.clamp(Long.valueOf(Math.round(resultColor)).intValue());
    }
    return resultRGB;
  }

  /**
   * Ensures that the rgb value is from 0 to 255 inclusive.
   *
   * @param rgb the initial rgb value.
   * @return the resulting clamped rgb value.
   */
  public int clamp(int rgb) {
    if (rgb > 255) {
      rgb = 255;
    } else if (rgb < 0) {
      rgb = 0;
    }
    return rgb;
  }

  /**
   * Adds a given image and image name to the images map.
   *
   * @param name  String name of the image to add.
   * @param image the image to be added.
   */
  public void addImage(String name, ImageModel image) {
    this.images.put(name, image);
  }

  /**
   * Removes a given image from the images map.
   *
   * @param name String name of the image to remove.
   */
  public void removeImage(String name) {
    this.images.remove(name);
  }

  /**
   * Gets a given image from the images map.
   *
   * @param name String name of the image to get.
   */
  public ImageModel getImage(String name) {
    return this.images.get(name);
  }
}

