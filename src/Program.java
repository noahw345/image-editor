import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import controller.Controller;
import controller.ControllerImpl;


/**
 * Class containing a main method to run the program, the user will
 * then interact through the console.
 */
public class Program {

  /**
   * Main method to run the program.
   *
   * @param args arguments inputted in the console
   */
  public static void main(String[] args) throws IOException {

    //String fileText = Files.readString(Paths.get("examplescript.txt"));
    //StringReader reader = new StringReader(fileText);
    //Controller c = new ControllerImpl(new InputStreamReader(System.in),
            //new PrintStream(System.out));
    Controller c = new ControllerImpl(new InputStreamReader(System.in), new PrintStream(System.out));
    c.control();
  }

}