import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class bank {
static int a = 0;
int b = 0;
int c = 0;
int resources = 0;
boolean isValid = false;
boolean wait = false;
static int taskNum = 0;
static int resourceT = 0;
static int resourceUnits = 0;
public static void main(String[] args) throws IOException {
  try{
    red();
    printFinal();
  }
  finally{

  }
}

public static void red() throws IOException {
    //int bankIn = new int [3];
  //The first line asserts that this run has 2 tasks and 1 resource type with 4 units.

    BufferedReader br = new BufferedReader(new FileReader("test"));
    try {
      //System.out.println("hello");
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
         taskNum = Character.getNumericValue((line.charAt(0)));
         resourceT = Character.getNumericValue((line.charAt(2)));
         resourceUnits = Character.getNumericValue((line.charAt(4)));

        System.out.println(taskNum);
        System.out.println(resourceT);
        System.out.println(resourceUnits);

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
            System.out.println(line);
            //Last line to print is null
        }
        String everything = sb.toString();
    } finally {
        br.close();
    }
}

public static void printFinal(){
  System.out.println(" ");
  System.out.println("~FIFO~");
  for(int i = 1; i <taskNum+1; i++){
    System.out.println("Task "+ i + ":");
  }
  System.out.println("Total:" + "\n");

  System.out.println("~Banker~");
  for(int i = 1; i <taskNum+1; i++){
    System.out.println("Task "+ i + ":");
  }
  System.out.println("Total:" + "\n");

}
public static void printFinalwithInfo(){
  System.out.println("Here we go");
}

}
