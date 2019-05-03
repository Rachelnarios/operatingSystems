public class FrameT {
  //Frame Table is like a multi dem array that holds process as objects

  public int framenum; //Frame we are on
  public int nextpass; //Next element
  public int pgsize; //page size
  public int pnum; //process number
  public int tablePnum; //Process in table
  public int[] size; //How big the pagesize is

  public boolean free; //Determine if frame cube is free

  public P tablecurrp; //Process to be added

  //Constructor for the frame table we pass it a page size and a frame number  
    public FrameT (int pgsize, int framenum) {
        free = false;
        size = new int[2];
        this.pgsize = pgsize;
        this.framenum = framenum;
    }
}
