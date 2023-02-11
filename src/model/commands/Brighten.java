package model.commands;

import model.ImageModel;

/**
 * Represents the Brighten command.
 */
public class Brighten extends AbstractCommand {

  //the increment to brighten each by pixel by
  int increment;

  /**
   * Generates a new Brighten command object.
   *
   * @param increment how much to increase/decrease the value of each color.
   */
  public Brighten(int increment) {
    this.increment = increment;
  }


  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    // store current r,g,b values
    int curRed = image.getImage()[i][j].getRGB()[0];
    int curGreen = image.getImage()[i][j].getRGB()[1];
    int curBlue = image.getImage()[i][j].getRGB()[2];

    // change value of each r,g,b
    image.getImage()[i][j].getRGB()[0] = this.clamp(curRed + increment);
    image.getImage()[i][j].getRGB()[1] = this.clamp(curGreen + increment);
    image.getImage()[i][j].getRGB()[2] = this.clamp(curBlue + increment);
  }
}
