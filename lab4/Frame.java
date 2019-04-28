public class Frame {
    private int pageNumber;
    private boolean taken;
    private int[] arr;
    private int page_size;
    private int frame_num;
    private int procnum;
    private int busy;
    private int cycle;
    private int current;

    public Frame (int page_size, int frame_num) {
        this.page_size = page_size;
        this.frame_num = frame_num;
        taken = true;
        //create a size 2 array for page size and frame num
        arr = new int[2];
    }
    public int getPageNumber(){
      return pageNumber;
    }
    public void setPageNumber(int x){
      pageNumber = x;
    }
    public int getProcessNumber(){
      return procnum;
    }
    public void setProcessNumber(int x){
      procnum = x;
    }
    public boolean getTaken(){
      return taken;
    }
    public void setTaken(boolean x){
      taken = x;
    }
    public int[] getArr(){
      return arr;
    }
    public void setArr(int[] x){
      arr = x;
    }
    public int getPageSize(){
      return page_size;
    }
    public void setPageSize(int x){
      page_size = x;
    }
    public int getFrameNumber(){
      return frame_num;
    }
    public void setFrameNumber(int x){
      frame_num = x;
    }
    public int getBusy(){
      return busy;
    }
    public void setBusy(int x){
      busy = x;
    }
    public int getCycle(){
      return cycle;
    }
    public void setCycle(int x){
      cycle = x;
    }
    public int getProcess(){
      return current;
    }
    public void setProcess(int x){
      current = x;
    }
    public void setAll(int x, int y, int z){
      taken = true;
      this.procnum = x;
      this.pageNumber = y;
      current = z;
    }
    public String toPrint(){
      return procnum + " :" + pageNumber;
    }
    public void clearAll(){
       this.pageNumber = 0;
       this.taken = false;
       this.page_size = 0;
       this.frame_num = 0;
       this.procnum = 0;
       this.busy = 0;
       this.cycle = 0;
       this.current = 0;
    }
  }
