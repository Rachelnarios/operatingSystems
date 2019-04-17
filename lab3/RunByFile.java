import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.util.ArrayList;


public class RunByFile {

  ArrayList<Integer> pending = new ArrayList<Integer>(); //
  //ArrayList<Integer> initiated = new ArrayList<Integer>(); //Sources that claim something
  ArrayList<String> initiated = new ArrayList<String>(); //Sources that claim something
  ArrayList<String> requests = new ArrayList<String>(); //Sources that claim something
  ArrayList<String> release = new ArrayList<String>();
	ArrayList<Tea> tasks = new ArrayList<Tea>();
	ArrayList<Tea> blocked = new ArrayList<Tea>();
	ArrayList<Integer> at = new ArrayList<Integer>();
	ArrayList<ArrayList<Input>> instructions;
  ArrayList<ArrayList<InputBA>> instructionsBA;

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
				ArrayList<ArrayList<Input>> actionsOP = new ArrayList<ArrayList<Input>>(); //actions given by file
        ArrayList<ArrayList<InputBA>> actionsBA = new ArrayList<ArrayList<InputBA>>();

  			for (int i = 0; i < task_number; i++){
  				actionsOP.add(new ArrayList<Input>());
          actionsBA.add(new ArrayList<InputBA>());

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
  				actionsOP.get(tasknum-1).add(new Input(activity, tasknum, delay, resourceType,trash));
          actionsBA.get(tasknum-1).add(new InputBA(activity, tasknum, delay, resourceType,trash));

  			}
        input.close();
        //OPRM = optimistic resource manager
  			Run OPRM = new Run(actionsOP, avail);
  			OPRM.runOP();
        //Banker
        // //Out of bounds exception? Separete class?
        // Run bankerAlgorithm = new Run(actionsBA, avail);
        // bankerAlgorithm.runBank();
			Banker bankerAlgorithm = new Banker(actionsBA, avail);
			bankerAlgorithm.runBank();
  		}
  		catch(FileNotFoundException e){
        System.out.println("file was not found :( ");
        }
  	}
}
