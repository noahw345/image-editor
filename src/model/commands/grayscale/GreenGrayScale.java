package model.commands.grayscale;

import model.ImageModel;
import model.color.ColorImpl;
import model.commands.AbstractCommand;

/**
 * Represents the GreenGrayScale command.
 */
public class GreenGrayScale extends AbstractCommand {

  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    int curGreen = image.getImage()[i][j].getRGB()[1];
    image.getImage()[i][j] = new ColorImpl(curGreen, curGreen, curGreen);
  }
}
