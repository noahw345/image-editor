package model.commands.grayscale;

import model.ImageModel;
import model.commands.AbstractCommand;

/**
 * Represents a LumaGrayScale command.
 */
public class LumaGrayScale extends AbstractCommand {

  @Override
  protected void updatePixel(int i, int j, ImageModel image) {
    double lumaSum =
            (image.getImage()[i][j].getRGB()[0] * 0.2126) +
                    (image.getImage()[i][j].getRGB()[1] * 0.7152) +
                    (image.getImage()[i][j].getRGB()[2] * 0.0722);
    image.getImage()[i][j].getRGB()[0] = this.clamp((int) lumaSum);
    image.getImage()[i][j].getRGB()[1] = this.clamp((int) lumaSum);
    image.getImage()[i][j].getRGB()[2] = this.clamp((int) lumaSum);
  }
}
