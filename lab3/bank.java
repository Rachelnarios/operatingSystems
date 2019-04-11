import java.util.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList; // import the ArrayList class

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
//    printFinal();
  }
  finally{

  }
}

//Goal of lab is to do recource allocation using both an optimistic
//Resource manager and the bankers algo
//Optimistic is simple, satisfy a request if not make it wait when a realese pccirs
// satisfy pending requests in a fifo manenr
//in a fifo manner

public static void optimistic(int taskNumber, int resourceType, int units, ArrayList <String> objMods){
  int cycle = 0;
  int available = units;
  ArrayList<Integer> pending = new ArrayList<Integer>(); //
  //ArrayList<Integer> initiated = new ArrayList<Integer>(); //Sources that claim something
  ArrayList<String> initiated = new ArrayList<String>(); //Sources that claim something
  ArrayList<String> requests = new ArrayList<String>(); //Sources that claim something
  ArrayList<String> release = new ArrayList<String>(); //Sources that claim something

  //Read initiate requests cycle 0
   for (int i = 0; i < objMods.size(); i++) {
     if(objMods.get(i).startsWith("initiate")){
       initiated.add(objMods.get(i).substring(8));
     }
     if(objMods.get(i).startsWith("request")){
       requests.add(objMods.get(i).substring(8));
     }
     if(objMods.get(i).startsWith("release")){
       release.add(objMods.get(i).substring(8));
     }
   }
  cycle += 1;
   //cycle 1
   System.out.println("Cycle "+ cycle + "-"+ (cycle+1));
   for (int i = 0; i < requests.size(); i++) {
     if(Character.getNumericValue(requests.get(i).charAt(6)) < available){
       System.out.println("Task "+requests.get(i).charAt(0)+" Requested: "+ requests.get(i).charAt(6) + " GRANTED! " );
       available = available - Character.getNumericValue(requests.get(i).charAt(6));
       System.out.println("Items available: " + available);
     }else{
       System.out.println("Task "+requests.get(i).charAt(0)+" Requested: "+ requests.get(i).charAt(6) + " IT WAS NOT GRANTED! " );

     }
   }
   System.out.println(requests);
   cycle += 1;
   // System.out.println("During 0-1 each task completes its initiate");
   // System.out.println("During " + cycle +"-" +(cycle+1));
   // System.out.println(requests);
  //Is it opssible
  //If not -> wait
  //If yes gud
  //Else put it ina FIFO ququeuue and when a releease occurs staisfy in a Fifo manner


//Print out data
  System.out.println("~FIFO~");
  for(int i = 1; i <taskNumber+1; i++){
    System.out.println("Task "+ i + ":");
  }
  System.out.println("Total:" + "\n");

}

public static void banker(){

}
public static void red() throws IOException {
    //int bankIn = new int [3];
  //The first line asserts that this run has 2 tasks and 1 resource type with 4 units.

    BufferedReader br = new BufferedReader(new FileReader("test"));
    try {
      //System.out.println("hello");
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        ArrayList<String> mods = new ArrayList<String>();
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
            mods.add(line);
      }
      mods.remove(mods.size() - 1);
      //System.out.println(mods);
      optimistic(taskNum,resourceT,resourceUnits,mods);
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
