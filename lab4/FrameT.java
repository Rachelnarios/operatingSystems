public class FrameT {
    public int tablePnum;
    public boolean free;
    public int[] size;
    public int pnum;
    public int pgsize;
    public int framenum;
    public int nextpass;
    public P tablecurrp;
    public FrameT (int pgsize, int framenum) {
        free = false;
        size = new int[2];
        this.pgsize = pgsize;
        this.framenum = framenum;
    }
}
