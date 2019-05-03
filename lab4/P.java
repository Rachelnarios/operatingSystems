import java.util.*;
public class P {
  
    public int refnum;
    public boolean firstpass;
    public int refcurr;
    public int psize;
    public int pgsize;
    public int pnum;

    public double a;
    public double b;
    public double c;


    public int currAdd;
    public int pcounter;
    public int actualpnum;
    public int restime;
    public int victims;
    public int next;

    public P (int n, int psize, int pgsize, int pnum, double a, double b, double c) {
        refnum = n;
        this.psize = psize;
        this.pnum = pnum;
        this.a = a;
        this.b = b;
        this.c = c;
        this.pgsize = pgsize;
    }
}
