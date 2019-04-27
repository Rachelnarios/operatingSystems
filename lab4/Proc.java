import java.util.*;
public class Proc {
  private int numOfReferences;
  private int curReferences;
  private int size;
  private int page_size;
  private int p_num;
  private double a;
  private double b;
  private double c;

  public Proc (int x, int size, int page_size, int p_num, double a, double b, double c) {
      numOfReferences = x;
      this.size = size;
      this.p_num = p_num;
      this.a = a;
      this.b = b;
      this.c = c;
      this.page_size = page_size;
  }
}
