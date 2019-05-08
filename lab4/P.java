import java.util.*;
public class P {
  //This is the process class keeps track of each p that will then go into the frame table

  public int actualpnum; //Page we are on
  public int currAdd; //current addres
  public int next; //Next element that will be procesed
  public int pcounter; //Page counter
  public int pgsize; //Page size
  public int pnum; //More proc numbers
  public int psize; //Process size
  public int refcurr; //Current refernce number
  public int refnum; //Refence number
  public int restime; //Time we have been in algo
  public int victims; //Number of Proc that have been evicted "They are victims"

  public double a; //Calculates skips / Which page we access next
  public double b; //2ND var to calculate skip
  public double c; //3rd var to calculate skip

  public boolean firstpass; //Check if it is first pass, we will always fail this process

    //Constructor for the process
    // public P (int numb, int psize, int pgsize, int pnum, double a, double b, double c) {
      public P (double a,double b,double c,int numb,int pgsize,int pnum,int psize){
      refnum = numb;
      this.a = a;
      this.b = b;
      this.c = c;
      this.pgsize = pgsize;
      this.pnum = pnum;
      this.psize = psize;
    }
}
