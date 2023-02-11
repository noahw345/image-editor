package view;

import java.awt.image.BufferedImage;

/**
 * Represents a view for this program.
 */
public interface IGUIView {
  BufferedImage renderImageJPG(String name) throws IllegalArgumentException;
}
