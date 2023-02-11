package view;

import java.awt.Graphics;

/**
 * interface for RenderHistogram objects. Pulls paintComponent() override method.
 */
public interface IRenderHistogram {
  void paintComponent(Graphics graphics);
}
