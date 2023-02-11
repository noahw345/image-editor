package view;


import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;


import java.util.List;
import java.util.Map;



import model.histogram.AbstractHistogram;

/**
 * renders appropriate histogram to view.
 */
public class RenderHistogram extends JPanel implements IRenderHistogram {

  List<AbstractHistogram> loh;
  private int height;
  private JPanel panel;

  /**
   * Constructs a RenderHistogram.
   * @param loh list of given gistograms
   * @param panel the containing panel on which the histograms will be drawn.
   */
  public RenderHistogram(List<AbstractHistogram> loh, JPanel panel) {
    this.loh = loh;
    this.panel = panel;
    this.height = panel.getHeight() - 100;
    panel.setPreferredSize(new Dimension(770, height));
    panel.removeAll();
  }

  /**
   * draws histograms onto the given panel in constructer.
   *
   * @param g needed to add histogram image to panel.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int maxValue = 0;
    for (int n = 0; n < loh.size(); n++) {
      switch (n) {
        case 0:
          g2.setColor(new Color(255, 0, 0, 50));
          break;
        case 1:
          g2.setColor(new Color(0, 255, 0, 50));
          break;
        case 2:
          g2.setColor(new Color(0, 0, 255, 50));
          break;
        default:
          g2.setColor(new Color(130, 130, 130, 50));
          break;
      }

      Map<Integer, Integer> curHistogram = loh.get(n).getHistogram();
      for (int t = 0; t <= 255; t++) {
        if (curHistogram.get(t) > maxValue) {
          maxValue = curHistogram.get(t);
        }
      }

      for (int x = 0; x <= 255; x++) {
        int width = 3;
        int barHeight =
                (int) (height * ((double) curHistogram.get(x)) / (maxValue + curHistogram.get(x)));
        int yValue = height - barHeight;
        g2.fillRect(x * width, yValue, width, barHeight);
      }
    }
    setVisible(true);
    setAlignmentX(CENTER_ALIGNMENT);
    setAlignmentY(BOTTOM_ALIGNMENT);
    panel.revalidate();
  }
}
