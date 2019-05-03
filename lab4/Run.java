import java.util.*;
import java.io.*;
public class Run {
    public static ArrayList<Integer> randomList = new ArrayList<Integer>();
    public static List<P> allp = new ArrayList<P>();
    public static int totalAverage = 0;
    public static int totalEvicted = 0;
    public static int totalResFinalNum = 0;
    public static int faultsFinalNum = 0;
    public static List<P> done = new ArrayList<P>();

    public static void main (String args[]) throws IOException {
      //Define what will be read from user
        String algo_name = "noalgo";
        int hit = -2;
        int machine_size = 0;
        int page_size = 0;
        int proc_size = 0;
        int job_mix = 0;
        int num_ref = 0;
        int debug_level = 0;
        int num = 0;

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
       num = machine_size / page_size;

      }

    }
    catch(Error e){
      System.out.println(e);
      }
      //Print what was given by input
      printGiven(machine_size,page_size,proc_size,job_mix, num_ref, algo_name,debug_level);
      //Determing job mix level
      jobMixlevel(job_mix, num_ref, proc_size, page_size);
      //--Start of evictions and iterations---/
        int q = 0; //Round Robin Quanta
        Iterator<P> it = allp.iterator();
        FrameT[] frametable = new FrameT[num]; //create a frame table to store proc
        List<FrameT> FIFO = new ArrayList<FrameT>(); //Last in first out (implements stack)
        List<FrameT> LRU = new ArrayList<FrameT>(); //Last Recently used
        int cycle = 1; //Time
        //While terminated is not done
        while (allp.size()!=done.size()) {
            P process;
            if (!it.hasNext()) {
                it = allp.iterator();
                process = it.next();
            }
            else {
                process = it.next();
            }
            while (q != 3) {

                if (!process.firstpass) {
                    process.firstpass = true;
                    process.currAdd = (111 * process.pnum) % process.psize;
                    process.actualpnum = process.currAdd / process.pgsize;

                }
                else {
                    process.currAdd = process.next;
                    process.actualpnum = process.currAdd / process.pgsize;
                }
                int pnum = process.pnum;
                int tablePnum = process.actualpnum;
                hit = 0;
                for (int i = 0; i < frametable.length; i++) {
                    if (frametable[i] != null ) {
                      if(pnum ==  frametable[i].pnum && frametable[i].tablePnum == tablePnum){
                        LRU.remove(frametable[i]);
                        LRU.add(frametable[i]);
                        hit = 1;
                    }
                  }
                }
                if (hit == 0) {
                  process.  pcounter++;
                  boolean processed = false;
                  for (int i = frametable.length-1; i >= 0; i--) {
                      if (frametable[i] == null) { // If frames are free
                          FrameT temp = new FrameT(process.pgsize, i);
                          temp.free = true;
                          temp.pnum =  process.pnum;
                          temp.tablePnum = process.actualpnum;
                          temp.tablecurrp = process;
                          temp.nextpass = cycle;
                          frametable[i] = temp;
                          LRU.add(temp);
                          FIFO.add(temp);
                          processed = true;
                          break;
                      }
                  }
                  if (!processed) {
                      if (algo_name.equals("lru")) {
                          FrameT temp = LRU.get(0);
                          LRU.remove(0);
                          int residency = cycle - temp.nextpass;
                          tableEnter( temp,  process,  cycle);
                          LRU.add(temp);
                      }
                      else if (algo_name.equals("random")) {
                          int r = randomList.get(0);
                          randomList.remove(0);
                          int i = r % frametable.length;
                          FrameT temp = frametable[i];
                          tableEnter( temp,  process,  cycle);
                      }
                      else if (algo_name.equals("fifo")) {
                        FrameT temp = FIFO.get(0);
                        FIFO.remove(0);
                        int residency = cycle - temp.nextpass;
                        tableEnter( temp,  process,  cycle);
                        FIFO.add(temp);
                      }
                      else{
                        System.out.println("That algo does not exist");
                        System.exit(1);
                      }

                  }
                }
                int r = randomList.get(0);
                randomList.remove(0);
                double y = r / (Integer.MAX_VALUE + 1d);
                if (y < process.a) {
                    process.next = (process.currAdd + 1) % process.psize;
                }
                else if (y < process.a + process.b) {
                    process.next = (process.currAdd - 5 + process.psize) % process.psize;
                }
                else if (y < process.a + process.b + process.c) {
                    process.next = (process.currAdd + 4) % process.psize;
                }
                else {
                    r = randomList.get(0);
                    randomList.remove(0);
                    process.next = r % process.psize;
                }
                q++;
                cycle++;
                process.refcurr++;
                if (process.refcurr == process.refnum) {
                    done.add(process);
                    q = 3;
                }
            }
            q = 0;
        }
        sections();
        overall();

    }

    public static void tableEnter(FrameT temp, P process, int cycle){
      int residency = cycle - temp.nextpass;
      temp.tablecurrp.victims ++;
      temp.tablecurrp.restime += residency;
      temp.free = true;
      temp.pnum =  process.pnum;
      temp.tablePnum = process.actualpnum;
      temp.tablecurrp = process;
      temp.nextpass = cycle;
    }
    public static void overall(){
    //  totalAverage = totalResFinalNum / (int)totalEvicted;
      if (totalEvicted == 0) {
          System.out.println("Total number of faults is " + faultsFinalNum + ".");
          System.out.println("No evcitions: the overall average residency is undefined.");

      }
      else {
          totalAverage = totalResFinalNum / (int)totalEvicted;
          System.out.println("Total number of faults is " + faultsFinalNum + " and the overall average residency is " + totalAverage + ".");
      }
    }
    public static void sections(){
      for (P process : done) {
          if (process.victims == 0) {
              System.out.println("P " + process.pnum + " had " + process.pcounter + " faults.");
              System.out.println("No evictions: the average residency is none.");
              totalResFinalNum += process.restime;
          }
            else {
                int averageResidency = process.restime / (int)process.victims;
                totalEvicted += process.victims;
                totalResFinalNum += process.restime;
                System.out.println("Faults for process " + process.pnum + " is " + process.pcounter);
                System.out.println("Average Residency " + " is " + averageResidency);
                totalAverage += averageResidency;

            }


          faultsFinalNum += process.pcounter;

      }

    }
    public static void printGiven(  int machine_sizex, int page_sizex, int proc_sizex,int job_mixx,int  num_refx, String algo_namex, int debug_levelx){
      System.out.println("\n"+"The Machine Size is " + machine_sizex);
      System.out.println("The page size is " + page_sizex);
      System.out.println("The process size is " + proc_sizex);
      System.out.println("The job mix number is " + job_mixx);
      System.out.println("The number of references per process is " + num_refx);
      System.out.println("The replacement algorithm is " + algo_namex );
      System.out.println("The level of debugging output is "+ debug_levelx + "\n");

    }
    public static void jobMixlevel(int job_mix, int num_ref, int proc_size, int page_size){
      if(job_mix == 1){
              allp.add(new P(num_ref, proc_size, page_size, 1, 1, 0, 0));
          }
      else if(job_mix == 2){
              allp.add(new P(num_ref, proc_size, page_size, 1, 1, 0, 0));
              allp.add(new P(num_ref, proc_size, page_size, 2, 1, 0, 0));
              allp.add(new P(num_ref, proc_size, page_size, 3, 1, 0, 0));
              allp.add(new P(num_ref, proc_size, page_size, 4, 1, 0, 0));
          }
      else if(job_mix == 3){

              allp.add(new P(num_ref, proc_size, page_size, 1, 0, 0, 0));
              allp.add(new P(num_ref, proc_size, page_size, 2, 0, 0, 0));
              allp.add(new P(num_ref, proc_size, page_size, 3, 0, 0, 0));
              allp.add(new P(num_ref, proc_size, page_size, 4, 0, 0, 0));
          }
          else if(job_mix == 4){
              allp.add(new P(num_ref, proc_size, page_size, 1, 0.75, 0.25, 0));
              allp.add(new P(num_ref, proc_size, page_size, 2, 0.75, 0, 0.25));
              allp.add(new P(num_ref, proc_size, page_size, 3, 0.75, 0.125, 0.125));
              allp.add(new P(num_ref, proc_size, page_size, 4, 0.5, 0.125, 0.125));
          }
        else{
          System.out.println("Wrong Job Mix, Try again :( ");

        }

    }



}
