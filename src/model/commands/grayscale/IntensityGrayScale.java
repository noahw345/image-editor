package model.commands.grayscale;

import model.ImageModel;
import model.commands.AbstractCommand;

/**
 * Represents the IntensityGrayScale command.
 */
public class IntensityGrayScale extends AbstractCommand {

  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    int curRed = image.getImage()[i][j].getRGB()[0];
    int curGreen = image.getImage()[i][j].getRGB()[1];
    int curBlue = image.getImage()[i][j].getRGB()[2];
    int avg = (curRed + curGreen + curBlue) / 3;
    image.getImage()[i][j].getRGB()[0] = avg;
    image.getImage()[i][j].getRGB()[1] = avg;
    image.getImage()[i][j].getRGB()[2] = avg;
  }
}