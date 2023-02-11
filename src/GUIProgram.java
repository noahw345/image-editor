import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

import javax.swing.JFrame;

import controller.Controller;
import controller.ControllerImpl;
import view.GUIView;

/**
 * Allows user to run GUI version of program.
 */
public class GUIProgram {
  /**
   * Main method that runs the GUI.
   *
   * @param args arguments.
   */
  public static void main(String[] args) throws IOException {

    // java -jar Program.jar -file path-of-script-file
    if (args.length == 2) {
      String filePath = args[1];
      StringReader reader = new StringReader(filePath);
      //Controller c = new ControllerImpl(new InputStreamReader(System.in),
      //        new PrintStream(System.out));
      Controller c = new ControllerImpl(reader, new PrintStream(System.out));
      c.control();
    }

    // java -jar Program.jar -text
    if (args.length == 1) {
      Controller c = new ControllerImpl(new InputStreamReader(System.in),
              new PrintStream(System.out));
      c.control();
    }

    // java -jar Program.jar
    if (args.length == 0) {
      GUIView.setDefaultLookAndFeelDecorated(false);
      GUIView frame = new GUIView();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
    }
  }
}