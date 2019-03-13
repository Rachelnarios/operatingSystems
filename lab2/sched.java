import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
public class sched {
 static boolean verbo = false;
 static List < Pro > LBS = new ArrayList < Pro > ();
 static List < Pro > compsL = new ArrayList < Pro > ();
 static List < Pro > blocked_one;
 static List < Pro > rdydL = new ArrayList < Pro > ();
 static String runx = "f";
 static String waitx = "f";
 static String nextx = "f";
 static String fileanme = "";
 static Scanner randoxmx;
 static Pro APP;
 static int CsC = 0;
 static int BsT;
 static Scanner ficanner;
 static Scanner SCANRa;
 Queue < Integer > bled = new LinkedList < > ();
 static List < Pro > parsed = new ArrayList < Pro > ();
 static boolean verb = false;
 static Scanner scan;
 static Scanner randomscan;
 static List < Pro > itemsset = new ArrayList < Pro > ();
 static Pro inProcess;
 static List < Pro > blocked = new ArrayList < Pro > ();
 static List < Pro > completed = new ArrayList < Pro > ();
 static int blockT;
 static List < Pro > blockdone;
 static List < Pro > passedItems = new ArrayList < Pro > ();
 static int cycle;
 static boolean run = true;
 static boolean wait = false;
 static boolean skip = false;
 // static String run = "f";
 // static String wait = "f";
 // static String next = "f";
 // static boolean verb = false;
 static String file = "";
 static Scanner randomx;
 static Pro AP;
 static int CC = 0;
 static int BT;
 static Scanner fileScanner;
 static List < Pro > listForBlocked = new ArrayList < Pro > ();
 static List < Pro > listForCompletes = new ArrayList < Pro > ();
 static List < Pro > blockedDone;
 static List < Pro > rdyL = new ArrayList < Pro > ();

 static Scanner scannerForRandoms;
 Queue < Integer > BB = new LinkedList < > ();
 // static List<Pro> parsed = new ArrayList<Pro>();

 public static void main(String[] args) throws FileNotFoundException {
  if (args[0].equals("--verbose")) {
   verb = true;
   file = args[1];
  } else {
   file = args[0];
  }
  FCFS();
  RR();
  UNI();
  STRN();
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
  passedItems = new ArrayList < Pro > ();
  itemsset = new ArrayList < Pro > ();
  blocked = new ArrayList < Pro > ();
  completed = new ArrayList < Pro > ();
  blockT = 0;
  scan = new Scanner(new File(file));
  randomscan = new Scanner(new File("random-numbers.txt"));
  findMore();
  cycle = 0;
  while (allowed()!=false) {
   if (cycle == 0) {
    Collections.sort(passedItems, new Comparator < Pro > () {
     public int compare(Pro p1, Pro p2) {
      if (p1.getAT() == p2.getAT()) {
       return p1.getNN() < p2.getNN() ? -1 : 1;
      }
      return p1.getAT() < p2.getAT() ? -1 : 1;
     }
    });

    for (Pro Pro: passedItems) {
     System.out.print(Pro.toString() + " ");
    }
    if (verb && cycle == 0) {
     tellMemore();
    }
   }
   for (Pro Pro: passedItems) {
    if (Pro.getAT() == cycle) {
     itemsset.add(Pro);
     Pro.sets("4");
    }
   }
   if (inProcess == null && itemsset.size() > 0) {
    itemsset.get(0).sets("11");
    inProcess = itemsset.get(0);
    itemsset.remove(0);
    inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
   }
   passCC();
   if (verb!=false) {
    tellMemore();
   }
   blockdone = new ArrayList < Pro > ();
   for (int i = 0; i < blocked.size(); i++) {
    Pro curr = blocked.get(i);
    if (curr.doneBlocked() == true) {
     blockdone.add(curr);
     curr.sets("4");
     blocked.remove(curr);
     i--;
    }
   }

   if (blockdone.size() > 0) {
    loaddone();
   }
   if (inProcess != null) {
    if (inProcess.doneRunning() == true) {
     if (inProcess.getTimeNeeded() == inProcess.getrunT()) {
      inProcess.sets("3");
      completed.add(inProcess);
      inProcess = null;
     } else {
      blocked.add(inProcess);
      inProcess.setIOBurst(randomOS(inProcess.getioBT()));
      inProcess.sets("-1");
      inProcess = null;
     }
    }
   }
  }

  System.out.println("FCFS Used ");
  for (Pro p: passedItems) {
   p.printStats();
  }
  printALL();
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
  scan = new Scanner(new File(file));
  randomscan = new Scanner(new File("random-numbers.txt"));
  completed = new ArrayList < Pro > ();
  itemsset = new ArrayList < Pro > ();
  blocked = new ArrayList < Pro > ();
  passedItems = new ArrayList < Pro > ();

  blockT = 0;
  findMore();
  cycle = 0;
  while (allowed()!=false) {
   int q = 2;
   if (cycle == 0) {
    Collections.sort(passedItems, new Comparator < Pro > () {
     public int compare(Pro p1, Pro p2) {
      if (p1.getAT() == p2.getAT()) {
       return p1.getNN() < p2.getNN() ? -1 : 1;
      }
      return p1.getAT() < p2.getAT() ? -1 : 1;
     }
    });


    for (Pro Pro: passedItems) {
     System.out.print(Pro.toString() + " ");
    }
    if (verb && cycle == 0) {

     tellMemore();
    }
   }


   for (Pro Pro: passedItems) {
    if (Pro.getAT() == cycle) {
     itemsset.add(Pro);
     Pro.sets("4");
    }
   }


   if (inProcess == null && itemsset.size() > 0) {
    itemsset.get(0).sets("11");
    inProcess = itemsset.get(0);
    itemsset.remove(0);
    inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
   }

   passCC();

   if (verb!=false) {
    tellMemore();
   }

   blockdone = new ArrayList < Pro > ();
   for (int i = 0; i < blocked.size(); i++) {
    Pro curr = blocked.get(i);
    if (curr.doneBlocked() == true) {
     blockdone.add(curr);
     curr.sets("4");
     blocked.remove(curr);
     i--;
    }
   }

   if (blockdone.size() > 0) {
    loaddone();
   }
   if (inProcess != null) {
    if (inProcess.doneRunning() == true) {
     if (inProcess.getTimeNeeded() == inProcess.getrunT()) {
      inProcess.sets("3");
      completed.add(inProcess);
      inProcess = null;
     } else {
      blocked.add(inProcess);
      inProcess.setIOBurst(randomOS(inProcess.getioBT()));
      inProcess.sets("-1");
      inProcess = null;
     }
    } else {
     q--;
     if (q == 0) {
      if (itemsset.size() >= 1) {
       inProcess.sets("4");
       blockdone.add(inProcess);
       inProcess = itemsset.get(0);
       inProcess.sets("11");
       itemsset.remove(0);
       inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
       loaddone();

       q = 2;
      } else if (blockdone.size() >= 1) {
       Collections.sort(blockdone, new Comparator < Pro > () {
        public int compare(Pro p1, Pro p2) {
         if (p1.getAT() == p2.getAT()) {
          return p1.getNN() < p2.getNN() ? -1 : 1;
         }
         return p1.getAT() < p2.getAT() ? -1 : 1;
        }
       });

       inProcess.sets("4");
       blockdone.add(inProcess);
       inProcess = blockdone.get(0);
       inProcess.sets("11");
       blockdone.remove(0);
       inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));

       loaddone();

       q = 2;
      } else {
       q = 2;
      }
     }
    }
   }

   if (blockdone.size() >= 1) {
    loaddone();
   }
  }

  System.out.println("RR used");

  for (Pro p: passedItems) {
   p.printStats();
  }

  printALL();
 }

 public static void UNI() throws FileNotFoundException {
  passedItems = new ArrayList < Pro > ();
  itemsset = new ArrayList < Pro > ();
  blocked = new ArrayList < Pro > ();
  completed = new ArrayList < Pro > ();
  blockT = 0;

  scan = new Scanner(new File(file));
  randomscan = new Scanner(new File("random-numbers.txt"));

  findMore();
  cycle = 0;

  while (allowed()!=false) {

   if (cycle == 0) {
    Collections.sort(passedItems, new Comparator < Pro > () {
     public int compare(Pro p1, Pro p2) {
      if (p1.getAT() == p2.getAT()) {
       return p1.getNN() < p2.getNN() ? -1 : 1;
      }
      return p1.getAT() < p2.getAT() ? -1 : 1;
     }
    });


    for (Pro Pro: passedItems) {
     System.out.print(Pro.toString() + " ");
    }
    System.out.println("\n");

    if (verb && cycle == 0) {

     tellMemore();
    }
   }


   for (Pro Pro: passedItems) {
    if (Pro.getAT() == cycle) {
     itemsset.add(Pro);
     Pro.sets("4");
    }
   }
   if (inProcess == null && itemsset.size() > 0 && run == true) {
    itemsset.get(0).sets("11");
    inProcess = itemsset.get(0);
    itemsset.remove(0);
    inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
   }

   passCC();
   if (verb!=false) {
    tellMemore();
   }
   if (blocked.size() == 1) {
    if (blocked.get(0).doneBlocked()) {
     blocked.get(0).sets("11");
     inProcess = blocked.get(0);
     inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
     blocked.remove(0);
     run = true;
     wait = true;
    }
   }

   if (inProcess != null) {
    if (run == true) {
     if (wait == false) {
      if (inProcess.doneRunning()) {
       if (inProcess.getTimeNeeded() == inProcess.getrunT()) {
        completed.add(inProcess);
        inProcess.sets("3");
        inProcess = null;
       } else {
        inProcess.setIOBurst(randomOS(inProcess.getioBT()));
        inProcess.sets("-1");
        blocked.add(inProcess);
        inProcess = null;
        run = false;
       }
      }
     }
    }
   }
   wait = false;

  }

  System.out.println("UNI used");

  for (Pro p: passedItems) {
   p.printStats();
  }

  printALL();
 }

 public static void STRN() throws FileNotFoundException {
  passedItems = new ArrayList < Pro > ();
  itemsset = new ArrayList < Pro > ();
  blocked = new ArrayList < Pro > ();
  completed = new ArrayList < Pro > ();
  blockT = 0;
  scan = new Scanner(new File(file));
  randomscan = new Scanner(new File("random-numbers.txt"));
  findMore();
  cycle = 0;
  while (allowed()!=false) {
   if (cycle == 0) {
    Collections.sort(passedItems, new Comparator < Pro > () {
     public int compare(Pro p1, Pro p2) {
      if (p1.getAT() == p2.getAT()) {
       return p1.getNN() < p2.getNN() ? -1 : 1;
      }
      return p1.getAT() < p2.getAT() ? -1 : 1;
     }});
    for (Pro Pro: passedItems) {
     System.out.print(Pro.toString() + " ");
    }
    if (verb && cycle == 0) {
     tellMemore();
    }
   }
   for (Pro Pro: passedItems) {
    if (Pro.getAT() == cycle) {
     itemsset.add(Pro);
     Pro.sets("4");
    }
   }

   Collections.sort(itemsset, new Comparator < Pro > () {
    public int compare(Pro p1, Pro p2) {
     if ((p1.getTimeNeeded() - p1.getrunT()) == (p2.getTimeNeeded() - p2.getrunT())) {
      return p1.getNN() < p2.getNN() ? -1 : 1;
     }
     return (p1.getTimeNeeded() - p1.getrunT()) < (p2.getTimeNeeded() - p2.getrunT()) ? -1 : 1;
    }
   });

   if (itemsset.size() > 0 && inProcess != null) {
    if ((itemsset.get(0).getTimeNeeded() - itemsset.get(0).getrunT()) < (inProcess.getTimeNeeded() - inProcess.getrunT())) {
     inProcess.sets("4");
     itemsset.add(inProcess);
     itemsset.get(0).sets("11");
     inProcess = itemsset.get(0);
     if (inProcess.getCCPUB() == -1) {
      inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
     }
     itemsset.remove(0);
    }
   }
   if (inProcess == null && itemsset.size() > 0) {
    itemsset.get(0).sets("11");
    inProcess = itemsset.get(0);
    if (inProcess.getCCPUB() == -1) {
     inProcess.setCPUBurst(randomOS(inProcess.getcpuBT()));
    }
    itemsset.remove(0);
   }
   passCC();
   if (verb!=false) {
    tellMemore();
   }
   blockdone = new ArrayList < Pro > ();
   for (int i = 0; i < blocked.size(); i++) {
    Pro curr = blocked.get(i);
    if (curr.doneBlocked() == true) {
     blockdone.add(curr);
     curr.sets("4");
     blocked.remove(curr);
     i--;
    }
   }
   if (blockdone.size() > 0) {
    loaddone();
   }
   Collections.sort(itemsset, new Comparator < Pro > () {
    public int compare(Pro p1, Pro p2) {
     if ((p1.getTimeNeeded() - p1.getrunT()) == (p2.getTimeNeeded() - p2.getrunT())) {
      return p1.getNN() < p2.getNN() ? -1 : 1;
     }
     return (p1.getTimeNeeded() - p1.getrunT()) < (p2.getTimeNeeded() - p2.getrunT()) ? -1 : 1;
    }
   });
   if (inProcess != null) {
    if (inProcess.doneRunning() == true) {
     if (inProcess.getTimeNeeded() == inProcess.getrunT()) {
      inProcess.sets("3");
      completed.add(inProcess);
      inProcess = null;
     } else {
      blocked.add(inProcess);
      inProcess.setIOBurst(randomOS(inProcess.getioBT()));
      inProcess.sets("-1");
      inProcess = null;
     }
    }
   }

   Collections.sort(itemsset, new Comparator < Pro > () {
    public int compare(Pro p1, Pro p2) {
     if ((p1.getTimeNeeded() - p1.getrunT()) == (p2.getTimeNeeded() - p2.getrunT())) {
      return p1.getNN() < p2.getNN() ? -1 : 1;
     }
     return (p1.getTimeNeeded() - p1.getrunT()) < (p2.getTimeNeeded() - p2.getrunT()) ? -1 : 1;
    }
   });}
  System.out.println("Short Job used");

  for (Pro p: passedItems) {
   p.printStats();
  }

  printALL();
 }

 public static int randomOS(int U) {
  return 1 + (randomscan.nextInt() % U);
 }

 public static void findMore() {
  int numProcess = scan.nextInt();

  for (int i = 0; i < numProcess; i++) {
   passedItems.add(new Pro(i, scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
  }

  System.out.print(numProcess + "  ");
  for (Pro Pro: passedItems) {
   System.out.print(Pro.toString() + " ");
  }
  System.out.println();
 }

 public static void passCC() {
  for (Pro Pro: passedItems) {
   Pro.passCycle();
  }

  if (blocked.size() > 0) {
   blockT++;
  }

  cycle++;
 }

 public static boolean allowed() {
  if (inProcess != null) {
   return true;
  } else if (itemsset.size() > 0) {
   return true;
  } else if (blocked.size() > 0) {
   return true;
  }

  for (Pro p: passedItems) {
   if (p.gets() != "3") {
    return true;
   }
  }

  return false;
 }

 public static void printALL() {
  System.out.println("Summary Data:");

  int finishTime = 0;
  int cpuTime = 0;
  int waitT = 0;
  int turnTime = 0;


  for (Pro p: passedItems) {
   if (p.getTotal() > finishTime) {
    finishTime = p.getTotal();
   }
   cpuTime += p.getrunT();
   waitT += p.getwaitT();
   turnTime += p.getTotal() - p.getAT();
  }
  float cpused=(float) cpuTime / finishTime;
  float ioused = (float) blockT / finishTime;
  float thruPut = passedItems.size();
  float turn = (float) turnTime / passedItems.size();
  float waitTt = (float) waitT / passedItems.size();
  thruPut = thruPut / (float) finishTime;
  thruPut = thruPut * 100;
  printInfo( finishTime,  cpused,  ioused,  thruPut,  turn, waitT,  cpuTime);
 }
 // int,float,float,int,float,float,int
public static void  printInfo(int finishTime, float cpused, float ioused, float thruPut, float turn,float waitT, int cpuTime) {
  System.out.println("Finishing: " + finishTime);
  System.out.println("CPU Used: " + cpused);
  System.out.println("I/O Used: " + ioused);
  System.out.println("Thru: " + thruPut + " processes per hundred cycles");
  System.out.println("Average Turnaround: " + turn);
  System.out.println("Average Waiting: " + waitT);
}
 public static void tellMemore() {
  System.out.print("Before cycle:" + cycle + ":");
  for (Pro p: passedItems) {
   System.out.print(p.gets() + " ");
   if (p.gets() == "4") {
    if (p.getCCPUB() <= 0) {
     System.out.print("0");
    } else {
     System.out.print(p.getCCPUB() + "");
    }
   } else if (p.gets() == "-1") {
    System.out.print(p.getCCIO() + "");
   } else if (p.gets() == "3" || p.gets() == "0") {
    System.out.print("0");
   } else {
    System.out.print(p.getCCPUB() + "");
   }
  }
  System.out.println();
 }

 public static void loaddone() {
  Collections.sort(blockdone, new Comparator < Pro > () {
   public int compare(Pro p1, Pro p2) {
    if (p1.getAT() == p2.getAT()) {
     return p1.getNN() < p2.getNN() ? -1 : 1;
    }
    return p1.getAT() < p2.getAT() ? -1 : 1;
   }
  });

  for (int i = 0; i < blockdone.size(); i++) {
   itemsset.add(blockdone.get(i));
   blockdone.remove(i);
   i--;
  }
 }

}
