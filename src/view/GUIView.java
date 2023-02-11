package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import controller.Controller;
import controller.GUIController;
import model.ImageImpl;
import model.ImageModel;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * This class opens the main window, that has different elements illustrated in
 * it. It also doubles up as all the listeners for simplicity. Such a design is
 * not recommended in general.
 */

public class GUIView extends JFrame implements ActionListener, IGUIView {

  ImageModel image;


  private JPanel topPanel;

  private JPanel imagePanel;
  private JScrollPane imageScrollPane;
  private JLabel imageLabel;

  private JLabel checkboxDisplay;
  private JLabel radioDisplay;
  private JLabel comboboxDisplay;
  private JLabel colorChooserDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JLabel inputDisplay;
  private JLabel optionDisplay;

  private JList<String> listOfStrings;
  private JList<Integer> listOfIntegers;

  /**
   * constructs a GUIView object.
   */
  public GUIView() {

    super();

    JPanel middlePanel;
    JPanel mainPanel;
    JScrollPane mainScrollPane;

    setTitle("Image Processor");
    setSize(1920, 1080);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // Histogram panel (TOP)
    topPanel = new JPanel();
    topPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    mainPanel.add(topPanel);

    // Middle panel to contain the file open/save panel and all commands
    middlePanel = new JPanel();
    middlePanel.setBorder(BorderFactory.createTitledBorder("Commands"));


    // Panel to contain the open/save buttons and corresponding texts next to them
    JPanel openSavePanel = new JPanel();

    // FILE OPEN BUTTON + TEXT
    middlePanel.add(openSavePanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("load");
    fileOpenButton.addActionListener(this);
    openSavePanel.add(fileOpenButton);
    // file path text to the right of the button
    fileOpenDisplay = new JLabel("File path will appear here");
    openSavePanel.add(fileOpenDisplay);

    // FILE SAVE BUTTON + TEXT
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("save");
    fileSaveButton.addActionListener(this);
    openSavePanel.add(fileSaveButton);
    // file path text to the right of the button
    fileSaveDisplay = new JLabel("File path will appear here");
    openSavePanel.add(fileSaveDisplay);

    middlePanel.add(openSavePanel);
    // middlePanel.setLayout(new FlowLayout());

    // Filter buttons
    JPanel buttonsPanel = new JPanel();
    // Sepia
    JButton sepia = new JButton();
    sepia.setText("Sepia");
    sepia.setActionCommand("sepia");
    buttonsPanel.add(sepia);

    // Blur
    JButton blur = new JButton();
    blur.setText("Blur");
    blur.setActionCommand("blur");
    buttonsPanel.add(blur);

    // Sharpen
    JButton sharpen = new JButton();
    sharpen.setText("Sharpen");
    sharpen.setActionCommand("sharpen");
    buttonsPanel.add(sharpen);

    // Vertical Flip
    JButton verticalFlip = new JButton();
    verticalFlip.setText("Vertical Flip");
    verticalFlip.setActionCommand("vertical-flip");
    buttonsPanel.add(verticalFlip);

    // Horizontal Flip
    JButton horizontalFlip = new JButton();
    horizontalFlip.setText("Horizontal Flip");
    horizontalFlip.setActionCommand("horizontal-flip");
    buttonsPanel.add(horizontalFlip);

    // Brighten (+10)
    JButton brighten = new JButton();
    brighten.setText("Brighten");
    brighten.setActionCommand("brighten");
    buttonsPanel.add(brighten);

    // Darken (-10)
    JButton darken = new JButton();
    darken.setText("Darken");
    darken.setActionCommand("darken");
    buttonsPanel.add(darken);

    // Blue Grayscale
    JButton blueGrayscale = new JButton();
    blueGrayscale.setText("Blue Grayscale");
    blueGrayscale.setActionCommand("blue-grayscale");
    buttonsPanel.add(blueGrayscale);

    // Red Grayscale
    JButton redGrayscale = new JButton();
    redGrayscale.setText("Red Grayscale");
    redGrayscale.setActionCommand("red-grayscale");
    buttonsPanel.add(redGrayscale);

    // Green Grayscale
    JButton greenGrayscale = new JButton();
    greenGrayscale.setText("Green Grayscale");
    greenGrayscale.setActionCommand("green-grayscale");
    buttonsPanel.add(greenGrayscale);

    // Luma Grayscale
    JButton lumaGrayscale = new JButton();
    lumaGrayscale.setText("Luma Grayscale");
    lumaGrayscale.setActionCommand("luma-grayscale");
    buttonsPanel.add(lumaGrayscale);

    // Value Grayscale
    JButton valueGrayscale = new JButton();
    valueGrayscale.setText("Value Grayscale");
    valueGrayscale.setActionCommand("value-grayscale");
    buttonsPanel.add(valueGrayscale);

    // Intensity Grayscale
    JButton intensityGrayscale = new JButton();
    intensityGrayscale.setText("Intensity Grayscale");
    intensityGrayscale.setActionCommand("intensity-grayscale");
    buttonsPanel.add(intensityGrayscale);

    // Grayscale
    JButton grayscale = new JButton();
    grayscale.setText("Grayscale");
    grayscale.setActionCommand("grayscale");
    buttonsPanel.add(grayscale);


    // all buttons now added to buttons panel, so add buttons panel to the middle panel
    middlePanel.add(buttonsPanel);

    // everything now added to the middle panel, so we now add the middle panel to the main panel
    mainPanel.add(middlePanel);

    //lower image panel
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    imagePanel.setLayout(new FlowLayout());
    imageLabel = new JLabel();
    imageLabel.setLayout(new FlowLayout(FlowLayout.CENTER));
    imagePanel.add(new JScrollPane());

    imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.createHorizontalScrollBar();
    imageScrollPane.createVerticalScrollBar();

    mainPanel.add(imagePanel);

  }


  /**
   * Sees which button was clicked and tells the controller to perform a specific command
   * depending on which button was clicked.
   *
   * @param event the event to be processed (the click that the switch case operates on) --
   *              for any elements in the JFrame, the .setActionCommand determines what their
   *              action command is.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    String command = event.getActionCommand();
    Controller c;

    switch (command) {
      case "load": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(GUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());

          // set the image to be the one from the file path we loaded
          try {
            this.image = new ImageImpl(f.getAbsolutePath());
          } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
          }

          // TODO render image in the bottom image panel (helper method in this class)
          // TODO render histogram (helper method in this class)
          this.renderToScreen();

        }
        break;


      }

      case "save": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(GUIView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
        }
      }
      break;
      default:

    }

    // create a new controller with our image and the new action command
    c = new GUIController(this.image, command);
    // run the controller
    try {
      c.control();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // now that the image is hopefully mutated, render image in bottom JPanel
    this.renderToScreen();
  }

  private void renderToScreen() {
    imageLabel.setIcon(new ImageIcon(this.renderImageJPG(null)));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setVerticalAlignment(SwingConstants.CENTER);
    imageLabel.setSize(new Dimension(600, 300));
    imageScrollPane.setPreferredSize(new Dimension(600, 300));
    imagePanel.add(imageScrollPane);

    RenderHistogram histo = new RenderHistogram(image.getLOH(), topPanel);
    topPanel.removeAll();
    topPanel.repaint();
    topPanel.remove(histo);
    topPanel.add(histo);
    topPanel.revalidate();
  }

  @Override
  public BufferedImage renderImageJPG(String name) throws IllegalArgumentException {
    ImageModel image = this.image;
    BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        Color newColFormat = new Color(image.getImage()[r][c].getRGB()[0],
                image.getImage()[r][c].getRGB()[1],
                image.getImage()[r][c].getRGB()[2]);
        newImage.setRGB(c, r, newColFormat.getRGB());
      }
    }
    return newImage;
  }
}