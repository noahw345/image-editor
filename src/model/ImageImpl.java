package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.color.ColorImpl;
import model.color.IColor;
import model.commands.Command;
import model.histogram.AbstractHistogram;
import model.histogram.BlueHistogram;
import model.histogram.GreenHistogram;
import model.histogram.RedHistogram;

/**
 * Represents an Image Implementation.
 */
public class ImageImpl implements ImageModel {

  //image array [int x position][int y position][Color]
  private IColor[][] image;

  //width of image
  private int width;

  //height of image
  private int height;

  //utility object
  private ImageUtil util = new ImageUtil();

  /**
   * Constructs an ImageImpl, given a three 3d array.
   *
   * @param image the image, a 3d array list, [int x position][int y position][Color];
   */
  public ImageImpl(IColor[][] image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image can not be null");
    }
    this.image = image;
    this.width = this.image[0].length; // width = number of columns
    this.height = this.image.length; // height = number of rows
  }

  /**
   * Constructs an array representing the image using by reading from a specific filename.
   *
   * @param fileName the name of the file.
   * @throws IllegalArgumentException if any arguments are null.
   */
  public ImageImpl(String fileName) throws IllegalArgumentException, FileNotFoundException {
    if (fileName == null) {
      throw new IllegalArgumentException("File name can not be null");
    }
    this.image = util.ppmToArray(fileName);
    this.width = util.getWidthPPM(fileName);
    this.height = util.getHeightPPM(fileName);
  }


  /**
   * Produces the given image.
   *
   * @return an Array of the image pixels with their various RGB values
   */
  @Override
  public IColor[][] getImage() {
    return this.image;
  }

  /**
   * Method that gets the width of the given image.
   *
   * @return an int of the pixel width of the image
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Method that gets the height of the given image.
   *
   * @return an int of the pixel height of the image
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void executeCommand(Command c) {
    c.goCommand(this);
  }

  /**
   * Creates an identical copy of this image w/ a different memory location.
   *
   * @return an ImageModel representing the copy.
   */
  @Override
  public ImageModel copy() {
    IColor[][] copyArr = new ColorImpl[this.getHeight()][this.getWidth()];
    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        copyArr[r][c] = this.getImage()[r][c];
      }
    }
    ImageModel copyImage = new ImageImpl(copyArr);
    return copyImage;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ImageModel)) {
      return false;
    }
    ImageModel other = (ImageModel) o;

    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        if (!(this.getImage()[r][c].equals(other.getImage()[r][c]))) {
          return false;
        }
      }
    }

    return this.height == other.getHeight()
            && this.width == other.getWidth();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 0;
    result = prime * result + getImage().hashCode();
    result = prime * result + getWidth();
    result = prime * result + getHeight();
    return result;
  }

  @Override
  public List<AbstractHistogram> getLOH() {
    List<AbstractHistogram> loh = new ArrayList<>();
    loh.add(new GreenHistogram(image));
    loh.add(new RedHistogram(image));
    loh.add(new BlueHistogram(image));
    return loh;
  }

}
