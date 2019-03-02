import java.util.*;
import java.io.*;

public class in {
// define lists
static String run = "f";
static String wait = "f";
static String next = "f";
static boolean verb = false;
static String file = "";
  public static void main(String[] args) throws FileNotFoundException {
      if (args[0].equals("--verbose")) {
          verb = true;
          file = args[1];
      } else {
          file = args[0];
          readFile();
      }


}
  public static void readFile() throws FileNotFoundException{
    //Read File
    Scanner f = new Scanner(new File(file));
    Scanner random = new Scanner(new File("random-numbers.txt"));
    int cpuTime = 0;
    //Create lists for each algo being parsed gloval
  //  List<E> scanItems = new ArrayList<E>();
  }
  public static void RR(){
    int time  = 2;
  }

}
