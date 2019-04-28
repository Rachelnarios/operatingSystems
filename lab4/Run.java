import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.util.ArrayList;


public class Run {

  	public static void main(String args[]){
      int page_size = 0;
      int machine_size = 0;
      int proc_size = 0;
      int job_mix = 0;
      int num_ref = 0;
      int debug_level = 0;
      int avg_res = 0;
      int p_num = 1;
      int faults = 0;
      int num_total_page;
      int q;
      String algo_name = "none";

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
         num_total_page = machine_size / page_size;
         Frame[] frameTable = new Frame[num_total_page];
         testMix(job_mix,num_ref, proc_size, page_size);
      //   printAll( page_size,  machine_size, proc_size,  job_mix,  num_ref,  debug_level,  avg_res,  p_num,  faults,  algo_name);

        }

  		}
  		catch(Error e){
        System.out.println(e);
        }
        // printAll();
  	}

    public static void printAll(int page_sizex, int machine_sizex, int proc_sizex, int job_mixx, int num_refx, int debug_levelx, int avg_resx, int p_numx, int faultsx, String algo_namex){

      System.out.println("\n"+"The Machine Size is " + machine_sizex);
      System.out.println("The page size is " + page_sizex);
      System.out.println("The process size is " + proc_sizex);
      System.out.println("The job mix number is " + job_mixx);
      System.out.println("The number of references per process is " + num_refx);
      System.out.println("The replacement algorithm is " + algo_namex );
      System.out.println("The level of debugging output is "+ debug_levelx + "\n");
      System.out.println("Process "+ p_numx + " had " + faultsx + " and "  + avg_resx+ " average residency."+ "\n");
      System.out.println("The total number of faults is "  + faultsx + " and the overall average residency is "+ avg_resx +"\n");


    }
    public static void testMix(int num, int num_refxx, int p_size, int p2_size){
      ArrayList<Proc> allProc = new ArrayList<Proc>();
//       There are four possible sets of processes (i.e., values for J)

      if(num == 1){
        // J=1: One Proc with A=1 and B=C=0, the simplest (fully sequential) case.
         allProc.add(new Proc(num_refxx, p_size, p2_size, 1, 1, 0, 0));
      }
      else if(num == 2){
        // J=2: Four processes, each with A=1 and B=C=0.
        allProc.add(new Proc(num_refxx, p_size, p2_size, 1, 1, 0, 0));
        allProc.add(new Proc(num_refxx, p_size, p2_size, 2, 1, 0, 0));
        allProc.add(new Proc(num_refxx, p_size, p2_size, 3, 1, 0, 0));
        allProc.add(new Proc(num_refxx, p_size, p2_size, 4, 1, 0, 0));
        // System.out.println("2 ");

      }
      else if(num == 3){
        // System.out.println("3 ");
        // J=3: Four processes, each with A=B=C=0 (fully random references).
        allProc.add(new Proc(num_refxx, p_size, p2_size, 1, 0, 0, 0));
        allProc.add(new Proc(num_refxx, p_size, p2_size, 2, 0, 0, 0));
        allProc.add(new Proc(num_refxx, p_size, p2_size, 3, 0, 0, 0));
        allProc.add(new Proc(num_refxx, p_size, p2_size, 4, 0, 0, 0));

      }
      else if (num == 4){
        // J=4: Four processes. The first process has A=.75, B=.25 and C=0;
        // the second process has A=.75, B=0, and C=.25;
        // the third process has A=.75, B=.125 and C=.125;
        // and the fourth process has A=.5, B=.125 and C=.125.
        // System.out.println("4 ");
          allProc.add(new Proc(num_refxx, p_size, p2_size, 1, 0.75, 0.25, 0));
          allProc.add(new Proc(num_refxx, p_size, p2_size, 2, 0.75, 0, 0.25));
          allProc.add(new Proc(num_refxx, p_size, p2_size, 3, 0.75, 0.125, 0.125));
          allProc.add(new Proc(num_refxx, p_size, p2_size, 4, 0.5, 0.125, 0.125));

      }else{
        System.out.println("Wrong Job Mix, Try again :( ");
      }
      for(Proc p : allProc) {
        System.out.println(p.getSize());
      }
    }
    //Simulated Working Action
    public static void simulatePage(){
      //simulations
      int q = 3;
    //refernce word 111*k*mod(S)


    }
    public static void randomNumberSelector(){}
    public static void FIFO(){}
    public static void LRU(){}
    public static void resolve(){}
    public static void evict(){}
}
