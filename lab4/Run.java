import java.util.*;
import java.io.*;
public class Run {
    public static ArrayList<Integer> randomList = new ArrayList<Integer>();
    public static List<P> processList = new ArrayList<P>();
    public static int overallAverageResidency = 0;
    public static int totalvictims = 0;
    public static int totalResidency = 0;
    public static int totalFaults = 0;
    public static List<P> terminatedList = new ArrayList<P>();

    public static void main (String args[]) throws IOException {
        int hit = -2;
        int machine_size = 0;
        int page_size = 0;
        int proc_size = 0;
        int job_mix = 0;
        int num_ref = 0;
        String algo_name = null;
        int debug_level = 0;
        int numOfPages = 0;
        //Read random file Scanner
        try{
            Scanner ran = new Scanner(new FileInputStream("random-numbers"));
            while(ran.hasNextInt()){
              randomList.add(ran.nextInt());
            }
          }catch(Error E){
            System.out.println("Error with Random files txt");
          }
        //read user input

        try{
      // /10 10 20 1 10 lru
      if(args.length != 7){
        System.out.println("Too little arguments :( ");
      }
      else{
      machine_size = Integer.parseInt(args[0]);
      page_size =Integer.parseInt(args[1]) ;
      proc_size = Integer.parseInt(args[2]) ;
      job_mix = Integer.parseInt(args[3]) ;
      num_ref =Integer.parseInt(args[4]) ;
      algo_name = args[5] ;
    //  System.out.println(algo_name);
      debug_level = Integer.parseInt(args[6]) ;
       numOfPages = machine_size / page_size;

      }

    }
    catch(Error e){
      System.out.println(e);
      }

      printGiven(machine_size,page_size,proc_size,job_mix, num_ref, algo_name,debug_level);
      jobMixlevel(job_mix, num_ref, proc_size, page_size);

        int q = 0;
        Iterator<P> it = processList.iterator();
        FrameT[] frameTable = new FrameT[numOfPages];
        List<FrameT> LRU = new ArrayList<FrameT>();
        Stack<FrameT> LIFO = new Stack<FrameT>();
        int cycle = 1;
        while (processList.size()!=terminatedList.size()) {

            P p;
            if (!it.hasNext()) {
                it = processList.iterator();
                p = it.next();
            }
            else {
                p = it.next();
            }
            while (q != 3) {

                if (!p.firstpass) {
                    p.firstpass = true;
                    p.currAdd = (111 * p.pnum) % p.psize;
                    p.actualpnum = p.currAdd / p.pgsize;

                }
                else {
                    p.currAdd = p.next;
                    p.actualpnum = p.currAdd / p.pgsize;
                }
                int pnum = p.pnum;
                int tablePnum = p.actualpnum;
                hit = 0;
                for (int i = 0; i < frameTable.length; i++) {
                    if (frameTable[i] != null ) {
                      if(pnum ==  frameTable[i].pnum && frameTable[i].tablePnum == tablePnum){
                        LRU.remove(frameTable[i]);
                        LRU.add(frameTable[i]);
                        hit = 1;
                    }
                  }
                }
                if (hit == 0) {
                  p.  pcounter++;
                  boolean processed = false;
                  for (int i = frameTable.length-1; i >= 0; i--) {
                      if (frameTable[i] == null) { // If frames are free
                          FrameT temp = new FrameT(p.pgsize, i);
                          temp.free = true;
                          temp.pnum =  p.pnum;
                          temp.tablePnum = p.actualpnum;
                          temp.tablecurrp = p;
                          temp.nextpass = cycle;
                          frameTable[i] = temp;
                          LRU.add(temp);
                          LIFO.add(temp);
                          processed = true;
                          break;
                      }
                  }
                  if (!processed) {
                      if (algo_name.equalsIgnoreCase("lru")) {
                          FrameT temp = LRU.get(0);
                          LRU.remove(0);
                          int residency = cycle - temp.nextpass;
                          temp.tablecurrp.victims ++;
                          temp.tablecurrp.restime += residency;
                          temp.free = true;
                          temp.pnum =  p.pnum;
                          temp.tablePnum = p.actualpnum;
                          temp.tablecurrp = p;
                          temp.nextpass = cycle;
                          LRU.add(temp);
                      }
                      else if (algo_name.equalsIgnoreCase("random")) {
                          int r = randomList.get(0);
                          randomList.remove(0);
                          int i = r % frameTable.length;
                          FrameT temp = frameTable[i];
                          int residency = cycle - temp.nextpass;
                          temp.tablecurrp.victims ++;
                          temp.tablecurrp.restime += residency;
                          temp.free = true;
                          temp.pnum =  p.pnum;
                          temp.tablePnum = p.actualpnum;
                          temp.tablecurrp = p;
                          temp.nextpass = cycle;
                      }
                      else if (algo_name.equalsIgnoreCase("lifo")) {
                          FrameT temp = LIFO.peek();
                          int residency = cycle - temp.nextpass;
                          temp.tablecurrp.victims ++;
                          temp.tablecurrp.restime += residency;
                          temp.free = true;
                          temp.pnum =  p.pnum;
                          temp.tablePnum = p.actualpnum;
                          temp.tablecurrp = p;
                          temp.nextpass = cycle;
                          LIFO.add(temp);
                      }

                  }
                }
                int r = randomList.get(0);
                randomList.remove(0);
                double y = r / (Integer.MAX_VALUE + 1d);

                if (y < p.a) {
                    p.next = (p.currAdd + 1) % p.psize;
                }
                else if (y < p.a + p.b) {
                    p.next = (p.currAdd - 5 + p.psize) % p.psize;
                }
                else if (y < p.a + p.b + p.c) {
                    p.next = (p.currAdd + 4) % p.psize;
                }
                else {
                    r = randomList.get(0);
                    randomList.remove(0);
                    p.next = r % p.psize;
                }

                cycle++;
                q++;
                p.refcurr++;
                if (p.refcurr == p.refnum) { 
                    terminatedList.add(p);
                    q = 3;


                }
            }
            q = 0;
        }
        sections();
        overall();

    }
    public static void overall(){
      if (totalvictims == 0) {
          System.out.println("\nThe total number of faults is " + totalFaults + ".");
          System.out.println("\tWith no victims, the overall average residency is undefined.");

      }
      else {
          overallAverageResidency = totalResidency / (int)totalvictims;
          System.out.println("\nThe total number of faults is " + totalFaults + " and the overall average residency is " + overallAverageResidency + ".");
      }
    }
    public static void sections(){
      for (P p : terminatedList) {
          if (p.victims == 0) {
              System.out.println("P " + p.pnum + " had " + p.pcounter + " faults.");
              System.out.println("No evictions: the average residency is none.");
              totalResidency += p.restime;
          }
            else {
                int averageResidency = p.restime / (int)p.victims;
                totalvictims += p.victims;
                totalResidency += p.restime;
                System.out.println("Faults for process " + p.pnum + " is " + p.pcounter);
                System.out.println("Average Residency " + " is " + averageResidency);
                overallAverageResidency += averageResidency;

            }


          totalFaults += p.pcounter;

      }

    }
    public static void printGiven(  int machine_sizex, int page_sizex, int proc_sizex,int job_mixx,int  num_refx, String algo_namex, int debug_levelx){
      System.out.println("\n"+"The Machine Size is " + machine_sizex);
      System.out.println("The page size is " + page_sizex);
      System.out.println("The process size is " + proc_sizex);
      System.out.println("The job mix number is " + job_mixx);
      System.out.println("The number of references per P is " + num_refx);
      System.out.println("The replacement algorithm is " + algo_namex );
      System.out.println("The level of debugging output is "+ debug_levelx + "\n");

    }
    public static void jobMixlevel(int job_mix, int num_ref, int proc_size, int page_size){
      if(job_mix == 1){
              processList.add(new P(num_ref, proc_size, page_size, 1, 1, 0, 0));
          }
      else if(job_mix == 2){
              processList.add(new P(num_ref, proc_size, page_size, 1, 1, 0, 0));
              processList.add(new P(num_ref, proc_size, page_size, 2, 1, 0, 0));
              processList.add(new P(num_ref, proc_size, page_size, 3, 1, 0, 0));
              processList.add(new P(num_ref, proc_size, page_size, 4, 1, 0, 0));
          }
      else if(job_mix == 3){

              processList.add(new P(num_ref, proc_size, page_size, 1, 0, 0, 0));
              processList.add(new P(num_ref, proc_size, page_size, 2, 0, 0, 0));
              processList.add(new P(num_ref, proc_size, page_size, 3, 0, 0, 0));
              processList.add(new P(num_ref, proc_size, page_size, 4, 0, 0, 0));
          }
          else if(job_mix == 4){
              processList.add(new P(num_ref, proc_size, page_size, 1, 0.75, 0.25, 0));
              processList.add(new P(num_ref, proc_size, page_size, 2, 0.75, 0, 0.25));
              processList.add(new P(num_ref, proc_size, page_size, 3, 0.75, 0.125, 0.125));
              processList.add(new P(num_ref, proc_size, page_size, 4, 0.5, 0.125, 0.125));
          }
        else{
          System.out.println("Wrong Job Mix, Try again :( ");

        }

    }



}
