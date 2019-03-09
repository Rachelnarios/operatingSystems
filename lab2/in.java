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
static Process activeProcess;
static int cpuCycle = 0;
Queue<Integer> blocked = new LinkedList<>();
static List<Process> scannedList = new ArrayList<Process>();

  public static void main(String[] args) throws FileNotFoundException {
      if (args[0].equals("--verbose")) {
          verb = true;
          file = args[1];
      } else {
          file = args[0];
          readFile();
      }
    //s  RR();
      FCFS();



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
  public static void RR() throws FileNotFoundException {
    //Fifo given a quantum time
    //If it does not complete before cpu time exprires x is prempted
    //Moves on to next process waiting in the queue, item placed at back of lists
    //example: RR with time quantum = 20
    // Processes and burst time and limit burst time
    // p1 53     p1 - 0-20 with 33 left burst time 20
    // p2 17     executed in one quantum = 37
    // p3 68     does not finish 48 remaning time left 57
    // p4 24    4 left time is 77
    // Control goes back to one 77 + 20 = 97 with 33 remaning for p1
    //.... so on

        scannedList = new ArrayList<Process>(); //clear
        List<Process> readyList= new ArrayList<Process>();
        List<Process> blockedList = new ArrayList<Process>();
        List<Process> completedList = new ArrayList<Process>();
        int blockTime = 0;
        Scanner randomScanner = new Scanner(new File("random-numbers.txt"));
        cpuCycle = 0;
        System.out.println("Round Robin:");
        printSummary();
        //Now that we have them stored we will run  RR
        int q  = 2;
        for (Process p: scannedList) {
        int times  = p.cpuBurstTime * p.cpuTimeNeeded;
        System.out.println(times);
        // int ioTime = p.cpuTimeNeeded - 1;
        // p.setTotal((times + ioTime));
        //
        //     p.printall();
         }



  }

  public static void FCFS() throws FileNotFoundException {
    //Cpu Burst time is how long the cpu has
    //Fifo given NO quantum time
//A perfect real life example of FCFS scheduling is buying tickets at ticket counter.
        scannedList = new ArrayList<Process>(); //clear
        List<Process> readyList= new ArrayList<Process>();
        List<Process> blockedList = new ArrayList<Process>();
        List<Process> completedList = new ArrayList<Process>();
        int blockTime = 0;
        Scanner randomScanner = new Scanner(new File("random-numbers.txt"));
        cpuCycle = 0;
        System.out.println("First Come First Served:");
        printSummary();
        for (Process p: scannedList) {
        int timeLOCAL  = p.cpuBurstTime * p.cpuTimeNeeded;
        int ioTime = p.cpuTimeNeeded - 1;
        p.setTotal((timeLOCAL + ioTime));
        p.setio(ioTime);
            p.printall();
         }



  }
  public static void uni(){
    System.out.println("Uni scheduling:");

  }
  public static void shortjob(){
  System.out.println("Short job first:");

  }
  public static void printSummary() throws FileNotFoundException {

      Scanner fileScanner = new Scanner(new File(file));
      int pieces = fileScanner.nextInt();

      for (int i = 0; i < pieces; i++) {
          scannedList.add(new Process(i, fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt()));
      }

    System.out.print("File input : " + pieces + "  ");
    System.out.println();
    System.out.println("Summary:");
  }

  public static int RandomOS(int U){
    return 1 + (randomx.nextInt() % U);
  }
  public static void printVerbose() {
      System.out.print("Before cycle:\t" + cpuCycle + ":\t");
      for (Process p: scannedList) {
          System.out.print(p.getState() + " ");
          if (p.getState() == "Ready") {
              if (p.getCurrCPUBurst() <= 0) {
                  System.out.print("0\t\t");
              } else {
                  System.out.print(p.getCurrCPUBurst() + " \t");
              }
          } else if (p.getState() == "Blocked"){
              System.out.print(p.getCurrIOBurst() + "\t");
          } else if (p.getState() == "Completed" || p.getState() == "Unstarted") {
              System.out.print("0\t");
          } else {
              System.out.print(p.getCurrCPUBurst() + "\t");
          }
      }
      System.out.println();
  }

}
