package model.commands.grayscale;

import model.ImageModel;
import model.color.ColorImpl;
import model.commands.AbstractCommand;

/**
 * Represents the BlueGrayScale command.
 */
public class BlueGrayScale extends AbstractCommand {
  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    int b = image.getImage()[i][j].getRGB()[2]; // store blue value
    image.getImage()[i][j] = new ColorImpl(clamp(b), clamp(b), clamp(b)); // update pixel
  }
}
