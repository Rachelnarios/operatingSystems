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

        }

  		}
  		catch(Error e){
        System.out.println(e);
        }
        printAll( page_size,  machine_size, proc_size,  job_mix,  num_ref,  debug_level,  avg_res,  p_num,  faults,  algo_name);
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
}
