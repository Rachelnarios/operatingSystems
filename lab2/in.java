import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class in {
// define lists
static String run = "f";
static String wait = "f";
static String next = "f";
static boolean verb = false;
static String file = "";
static Scanner randomx;

Queue<Integer> blocked = new LinkedList<>();


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
    randomx = new Scanner(new File("random-numbers.txt"));
    int cpuTime = 0;
    int blockedTime = 0;
    //Create lists for each algo being parsed gloval
  //  List<E> scanItems = new ArrayList<E>();
  }

  public static void RR(){
    System.out.println("Round Robin:");
    int time  = 2;
  }
  public static void FCFS(){
  System.out.println("First come first served:");

  }
  public static void uni(){
    System.out.println("Uni scheduling:");

  }
  public static void shortjob(){
  System.out.println("Short job first:");

  }
  public static void printSummary(){
    System.out.println("Summary:");
  }
  public static void printVerbose() {
    System.out.println("VERBOSE Summary:");
  }
  public static int RandomOS(int U){
    return 1 + (randomx.nextInt() % U);
  }

}
