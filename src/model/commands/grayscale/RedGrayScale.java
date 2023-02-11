package model.commands.grayscale;

import model.ImageModel;
import model.color.ColorImpl;
import model.commands.AbstractCommand;

/**
 * Represents the RedGrayScale command.
 */
public class RedGrayScale extends AbstractCommand {

  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    int curRed = image.getImage()[i][j].getRGB()[0];
    image.getImage()[i][j] = new ColorImpl(curRed, curRed, curRed);
  }
}
