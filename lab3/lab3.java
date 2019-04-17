import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.util.ArrayList;


public class lab3 {

  ArrayList<Integer> pending = new ArrayList<Integer>(); //
  //ArrayList<Integer> initiated = new ArrayList<Integer>(); //Sources that claim something
  ArrayList<String> initiated = new ArrayList<String>(); //Sources that claim something
  ArrayList<String> requests = new ArrayList<String>(); //Sources that claim something
  ArrayList<String> release = new ArrayList<String>(); //Sources that claim something
	ArrayList<Tea> tasks = new ArrayList<Tea>();
	ArrayList<Tea> blocked = new ArrayList<Tea>();
	ArrayList<Integer> at = new ArrayList<Integer>();
	ArrayList<ArrayList<Input>> instructions;

	int[] resources;
	int[] releases;
	int cycle = 0;
	int line;
	int lineCopy = 0;
	int wait = 0;
	//int avail;

  	public static void main(String args[]) throws FileNotFoundException{

    	try{

  			File argFile = new File(args[0]);
  			Scanner input = new Scanner(argFile);
				//Create a dynamic array based on input
				int task_number = input.nextInt(); //2
				int resource_Type = input.nextInt(); //1
				int[] avail = new int[resource_Type]; //4
				ArrayList<ArrayList<Input>> actions = new ArrayList<ArrayList<Input>>(); //actions given by file

  			for (int i = 0; i < task_number; i++){
  				actions.add(new ArrayList<Input>());
  			}
				for (int i = 0; i < resource_Type; i++){
					avail[i] = input.nextInt();
				}

  			while (input.hasNext()){
  				String activity = input.next();
  				int tasknum = input.nextInt();
  				int delay = input.nextInt();
  				int resourceType = input.nextInt();
					int trash = input.nextInt();
  				actions.get(tasknum-1).add(new Input(activity, tasknum, delay, resourceType,trash));
  			}
        //OPRM = optimistic resource manager
  			Run OPRM = new Run(actions, avail);
  			OPRM.runOP();
        //Banker
        Run Banker = new Run(actions, avail);
        Banker.runBanker();
  		}
  		catch(FileNotFoundException e){
        System.out.println("file was not found :( ");
        }
  	}
}
