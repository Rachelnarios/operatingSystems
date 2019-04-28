import java.util.*;
public class Proc {
  private int numOfReferences;
  private int curReferences;
  private int size;
  private int proc;
  private int page_size;
  private int p_num;
  private double a;
  private double b;
  private double c;
  private int evictions;
  private int pagecrash;
  private int resTime;
  private int currAdd;
  private int nextAdd;
  private int currentPageNumber;
  private boolean firstWord;
  private boolean PASS;
  private int CRA;
  private int counter;
  private int RTfinal;
  private int nextRefo;

  public Proc (int x, int size, int page_size, int p_num, double a, double b, double c) {
      numOfReferences = x;
      this.size = size;
      this.p_num = p_num;
      this.a = a;
      this.b = b;
      this.c = c;
      this.page_size = page_size;
  }
//Create the get and replace methods
  public int getSize(){
    return size;
  }
  public void setSize(int x ){
    size = x;
  }
  public int getEvicted(){
    return evictions;
  }
  public void setEvicted(int x){
    evictions = x;
  }
  public void addEvictions(){
    evictions += 1;
  }
  public void subtractEvictions(){
    evictions =+ 1;
  }
  public int getPageFaultsNum(){
    return pagecrash;
  }
  public void setPageFaultsNum(int x){
      pagecrash = x;
  }
  public int getResTime(){
    return resTime;
  }
  public void setResTime(int x){
    resTime += c;
  }
  public void getCurrent(){
    return currAdd;
  }
  public void setCurrent(){
    currAdd = nextAdd;
    currentPageNumber = currAdd / page_size;
  }
  public void getWord(){
    return firstWord;
  }
  public void setWordAcess(){
    firstWord = true;
    currAdd = (111 * p_num) % proc;
    currentPageNumber = currAdd / page_size;

  }
  //Figure out how to set the nect word Address
}
