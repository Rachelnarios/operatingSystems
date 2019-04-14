import java.util.ArrayList;

public class optimistic {
  ArrayList<Tea> tasks = new ArrayList<Tea>();
  ArrayList<Tea> blocked = new ArrayList<Tea>();
  ArrayList<Integer> position = new ArrayList<Integer>();
	ArrayList<ArrayList<Input>> instructions;
	int[] res_array;
  ArrayList<Integer> resource2 = new ArrayList<Integer>();

	int[] released;
	int cycle = 0;
	int remaning;
	int remaningCopy = 0;
	int waitCheck = 0;

	public optimistic(ArrayList<ArrayList<Input>> instructionList, int[] resourceList){
		instructions = instructionList;
		res_array = resourceList;
		remaning = instructionList.size();
		released = new int[res_array.length];

		for (int i = 0; i < instructionList.size(); i++){
			tasks.add(new Tea(i+1, res_array.length));
		}
	}
  	public void runOP(){
      //Driver method start by seeing remaining tasks
    boolean done = false;
    while(done != true){
    //  System.out.println("i am runnin");
      if(remaning == 0){
        done = true;
        //We finished and we break
        break;
      }
      //else continue with inputs
      //Start with blocked in a  fifo manner
      checkBlocked();
      done = checkOther();

    }
    printOP();
    }

    //1st check blocked if fifo this will run TEAS that have been waiting FIO
    public void checkBlocked(){
      for(int i = 0; i < blocked.size(); i ++){
        Input current = instructions.get(blocked.get(i).TeaNumber-1).get(0);
          if(current.activity.equals("request")){
            //see if request is feasable again
            //IF NOT add time
            if(current.wat > res_array[current.resourceType-1]){
              tasks.get(blocked.get(i).TeaNumber-1).waitTime += 1;
              waitCheck += 1;
            }
            if(res_array[current.resourceType-1]>=current.wat){
              tasks.get(blocked.get(i).TeaNumber-1).res_array[current.resourceType-1] += current.wat;
              res_array[current.resourceType-1] -= current.wat;
              //granted ^
              instructions.get(blocked.get(i).TeaNumber-1).remove(0);
              position.add(i);
            }
          }

      }
    }

    //Run all other tasks
  public boolean checkOther(){
      //check other tasks
      for(int i = 0; i<instructions.size();i++){
        //get line current
        ArrayList<Input> current = instructions.get(i);

  if (tasks.get(i).end == true || blocked.contains(tasks.get(i))){
					continue; }

    if (current.get(0).delay > 0){
    			current.get(0).delay -= 1;
    			}

  if (current.get(0).activity.equals("initiate")){
    //done in cycle 0-1
    current.remove(0);
  }
  if (current.get(0).activity.equals("request")) {
//1. check if there is deadlock
  if (res_array[current.get(0).resourceType-1] < current.get(0).wat){
    tasks.get(i).waitTime += 1;
    waitCheck += 1;
    blocked.add(tasks.get(i));
  }
  if (res_array[current.get(0).resourceType-1] >= current.get(0).wat){
    tasks.get(i).res_array[current.get(0).resourceType-1] += current.get(0).wat;
    res_array[current.get(0).resourceType-1] -= current.get(0).wat;
    current.remove(0);
  }
  if (current.get(0).activity.equals("release")){
    released[current.get(0).resourceType-1] += current.get(0).wat;
    tasks.get(i).res_array[current.get(0).resourceType-1] -= current.get(0).wat;
    current.remove(0);
  }
  if (current.get(0).activity.equals("terminate")){

    if(current.get(0).delay == 0){
      tasks.get(i).end = true;
      remaningCopy += 1;
      tasks.get(i).finish = cycle + 1;
      for (int n = 0; n < tasks.get(i).res_array.length; n++){
        res_array[n] += tasks.get(i).res_array[n];
        tasks.get(i).res_array[n] = 0;
      }

      current.remove(0);
    }

  }
  }
}
deadlockCheck();
return true;
}

//Check for deadlock
public void deadlockCheck(){

}

public void printOP(){
	System.out.println("~FIFO~");
  int time_finished = 0;
  int time_Waiting = 0;
  //Zero gives an error for 0/0
  int finalTime = Math.round(((float)time_Waiting/time_finished)*100);
  //System.out.println(tasks.size());
  for(int i = 0; i< tasks.size(); i++){
    // /System.out.println("hello");
    if(tasks.get(i).finish > 0){
      time_Waiting += tasks.get(i).waitTime;
      time_finished += tasks.get(i).finish;
      StringBuilder result = new StringBuilder("Task ");
      int clean_FINISH = Math.round(((float)time_Waiting/time_finished)*100);
      result.append(i);
      result.append(" ");
      result.append(tasks.get(i).finish );
      result.append(" ");
      result.append(tasks.get(i).waitTime );
      result.append(" ");
      result.append(clean_FINISH);
      result.append("%");
      //System.out.println("bye");
      System.out.println(result);


    }else{
      System.out.println("Task " + (i+1) + " was aborted :( ");

    }
    StringBuilder result2 = new StringBuilder("Total : ");
    int clean_finish =  Math.round((((float)tasks.get(i).waitTime/tasks.get(i).finish))*100);
    result2.append(" ");
    result2.append(time_finished);
    result2.append(" ");
    result2.append(time_Waiting );
    result2.append(" ");
    result2.append(finalTime);
    System.out.println(result2);


  }
}
}
