package model.commands.grayscale;

import model.ImageModel;
import model.commands.AbstractCommand;

/**
 * Represents the ValueGrayScale command.
 */
public class ValueGrayScale extends AbstractCommand {

  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    int curRed = image.getImage()[i][j].getRGB()[0];
    int curGreen = image.getImage()[i][j].getRGB()[1];
    int curBlue = image.getImage()[i][j].getRGB()[2];
    int maxVal = Math.max(Math.max(curRed, curGreen), curBlue);

    image.getImage()[i][j].getRGB()[0] = maxVal;
    image.getImage()[i][j].getRGB()[1] = maxVal;
    image.getImage()[i][j].getRGB()[2] = maxVal;
  }
}
