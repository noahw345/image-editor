package controller;

import java.io.IOException;

/**
 * Represents a controller for the image processor.
 */
public interface Controller {

  /**
   * Runs the controller.
   *
   * @throws IOException if given invalid command.
   */
  void control() throws IOException;
}
