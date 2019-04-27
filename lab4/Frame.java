public class Frame {
    private int pageNumber;
    private boolean taken;
    private int[] arr;
    private int page_size;
    private int frame_num;

    public Frame (int page_size, int frame_num) {
        this.page_size = page_size;
        this.frame_num = frame_num;
        taken = true;
        arr = new int[2];
    }


  }
