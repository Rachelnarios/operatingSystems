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
static Pro AP;
static int CC = 0;
static  int BT;
static Scanner fileScanner;
static List<Pro> listForBlocked = new ArrayList<Pro>();
static List<Pro> listForCompletes = new ArrayList<Pro>();
static List<Pro> blockedDone;
static List<Pro> rdyL = new ArrayList<Pro>();

static Scanner scannerForRandoms;

Queue<Integer> blocked = new LinkedList<>();
static List<Pro> parsed = new ArrayList<Pro>();

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
  int BT = 0;
  //Create lists for each algo being parsed gloval
  parsed = new ArrayList<Pro>();
  rdyL = new ArrayList<Pro>();
  listForBlocked = new ArrayList<Pro>();
  listForCompletes = new ArrayList<Pro>();
  BT = 0;

  fileScanner = new Scanner(new File(file));
  scannerForRandoms = new Scanner(new File("random-numbers.txt"));

  get();
  CC = 0;
  while (run()!= false) {
      FCFS();
  }
  for (Pro p: parsed) {
      p.printall();
  } stats();
}
public static void passC() {
  //inrease the cycle
        CC++;
        for (Pro Pro: parsed) {
            Pro.status();
        }

        if (listForBlocked.size() > 0) {
            BT++;
        }


}
  public static void RR() throws FileNotFoundException {
    //Fifo given a quantum time
    //If it does not complete before cpu time exprires x is prempted
    //Moves on to next Pro waiting in the queue, item placed at back of lists
    //example: RR with time quantum = 20
    // Proes and burst time and limit burst time
    // p1 53     p1 - 0-20 with 33 left burst time 20
    // p2 17     executed in one quantum = 37
    // p3 68     does not finish 48 remaning time left 57
    // p4 24    4 left time is 77
    // Control goes back to one 77 + 20 = 97 with 33 remaning for p1
    //.... so on

        parsed = new ArrayList<Pro>(); //clear
        List<Pro> rdyL= new ArrayList<Pro>();
        List<Pro> listForBlocked = new ArrayList<Pro>();
        List<Pro> listForCompletes = new ArrayList<Pro>();
        int BT = 0;
        Scanner scannerForRandoms = new Scanner(new File("random-numbers.txt"));
        CC = 0;
        System.out.println("RR:");
        stats();
        //Now that we have them stored we will run  RR
        int q  = 2;
        for (Pro p: parsed) {
        // int times  = p.cpuB * p.cpuT;
        // System.out.println(times);
        // int ioTime = p.cpuT - 1;
        // p.settotz((times + ioTime));
        //
        //     p.printall();
         }



  }

  public static void FCFS() throws FileNotFoundException {
    //Cpu Burst time is how long the cpu has
    //Fifo given NO quantum time
//A perfect real life example of FCFS scheduling is buying tickets at ticket counter.
        // parsed = new ArrayList<Pro>(); //clear
        // List<Pro> rdyL= new ArrayList<Pro>();
        // List<Pro> listForBlocked = new ArrayList<Pro>();
        // List<Pro> listForCompletes = new ArrayList<Pro>();
        // int BT = 0;
        //
        // Scanner scannerForRandoms = new Scanner(new File("random-numbers.txt"));
        // stats();
        // CC = 0;

        //If verbosed and CC is zero we print it
        if (verb && CC == 0) {
            printDetails();
        }
        System.out.println();
        System.out.println();
        System.out.println("First Come First Served:");
    //    System.out.print("Sorted: " + parsed.size() + "  ");
        for (Pro Pro: parsed) {
            System.out.print(Pro.INFO() + " ");
        }
      //   for (Pro p: parsed) {
      // //  int timeLOCAL  = p.cpuB * p.cpuT;
      //   //int ioTime = p.cpuT - 1;
      //   //p.settotz((timeLOCAL + ioTime));
      // //  p.setio(ioTime);
      //   //    listForCompletes.add(p.printall());
      //    }
         if (CC == 0){
           //Do nothing the Pro has finished
         }
         for (Pro Pro: parsed) {
             if (Pro.getArrTime() == CC) {
                 rdyL.add(Pro);
                 Pro.setState("1");
             }
         }
         if (AP == null && rdyL.size() > 0) {
             rdyL.get(0).setState("1.1");
             AP = rdyL.get(0);
             rdyL.remove(0);
             AP.setCPUB(RandomOS(AP.getcpuB()));
         }


         passC();
         blockedDone = new ArrayList<Pro>();
         for (int i = 0; i < listForBlocked.size(); i++) {
             Pro curr = listForBlocked.get(i);
             if (curr.noMoBlox() == true) {
                 blockedDone.add(curr);
                 curr.setState("1");
                 listForBlocked.remove(curr);
                 i--;
             }
         }

         if (blockedDone.size() > 0) {
             loadBlockedDone();
         }
         if (AP != null) {
             if (AP.runDone() == true) {
                 if (AP.Tneed() == AP.TRun()) {
                     AP.setState("2");
                     listForCompletes.add(AP);
                     AP = null;
                 } else {
                     listForBlocked.add(AP);
                     AP.setIOBurst(RandomOS(AP.getIOBurstTime()));
                     AP.setState("-1");
                     AP = null;
                 }
             }
         }
         }
  public static void uni(){
    System.out.println("Uni scheduling:");

  }
  public static void shortjob(){
    System.out.println("Short job first:");

  }
  public static void firststats() throws FileNotFoundException {

      Scanner fileScanner = new Scanner(new File(file));
      int pieces = fileScanner.nextInt();

      for (int i = 0; i < pieces; i++) {
          parsed.add(new Pro(i, fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt()));
      }

    System.out.print("File input : " + pieces + "  ");
    System.out.println();
    for (Pro Pro: parsed) {
        System.out.print(Pro.INFO() + " ");
    }

  }
  public static void printmore(int finishTime, float CCused, float ioUsed, float ATT, float AWT, float thu){
              System.out.println("-Finishing Time: " + finishTime);
              System.out.println("-CPU Utilization: " + CCused);
              System.out.println("-I/O Utilization: " + ioUsed);
              System.out.println("-Average Turnaround Time: " + ATT);
              System.out.println("-Average Waiting Time: " + AWT);
              System.out.println("-Throughput: " + thu + " PP 100 cycles");
  }
  public static void loadBlockedDone() {
      Collections.sort(blockedDone, new Comparator<Pro>(){
          public int compare(Pro p1, Pro p2){
              if (p1.getArrTime() == p2.getArrTime()) {
                  return p1.getProNum() < p2.getProNum() ? -1 : 1;
              }
              return p1.getArrTime() < p2.getArrTime() ? -1 : 1;
          }
      });

      for (int i = 0; i < blockedDone.size(); i++) {
          rdyL.add(blockedDone.get(i));
          blockedDone.remove(i);
          i--;
      }
  }
  public static int RandomOS(int U){
    return 1 + (randomx.nextInt() % U);
  }
  public static void printDetails() {
      System.out.print("Before cycle:-" + CC + ":-");
      for (Pro p: parsed) {
          System.out.print(p.getState() + " ");
          if (p.getState() == "1") {
              if (p.currentCPUB() <= 0) {
                  System.out.print("0--");
              } else {
                  System.out.print(p.currentCPUB() + " -");
              }
          } else if (p.getState() == "-1"){
              System.out.print(p.getCurrIOBurst() + "-");
          } else if (p.getState() == "2" || p.getState() == "3") {
              System.out.print("0-");
          } else {
              System.out.print(p.currentCPUB() + "-");
          }
      }
      System.out.println();
  }
  public static void get() {
      int numPro = fileScanner.nextInt();

      for (int i = 0; i < numPro; i++) {
          parsed.add(new Pro(i, fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt()));
      }

      System.out.print("Passed input: " + numPro);
      System.out.println();
      for (Pro Pro: parsed) {
          System.out.print(Pro.INFO());
          System.out.println();
      }
      System.out.println();
  }

      public static boolean run() {
          if (AP != null) {
              return true;
          } else if (rdyL.size() > 0) {
              return true;
          } else if (listForBlocked.size() > 0) {
              return true;
          }

          for (Pro p: parsed) {
              if (p.getState() != "2") {
                  return true;
              }
          }

          return false;
      }
      public static void stats() {
          System.out.println("All:");
          int finishTime = 0;
          int cpuTime = 0;
          int waitTime = 0;
          int turnTime = 0;

          for (Pro p: parsed) {
              if (p.totz() > finishTime) {
                  finishTime = p.totz();
              }
              cpuTime += p.TRun();
              waitTime += p.getWaitTime();
              turnTime += p.totz() - p.getArrTime();
          }
          float CCused = (float)(cpuTime/finishTime);
          float ioUsed= (float)BT/finishTime;
          float thu = parsed.size();
          float ATT = (float)turnTime/parsed.size();
          float AWT = (float)waitTime/parsed.size();
          thu = thu / (float)finishTime;
          thu = 100 * thu + 2 ;
        printmore(finishTime,  CCused,  ioUsed,  ATT,  AWT,  thu-2);

      }
}
