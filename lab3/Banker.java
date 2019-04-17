import java.util.ArrayList;

public class Banker {
  ArrayList<Tea> tasksBA = new ArrayList<Tea>();
  ArrayList<Tea> blockedBA = new ArrayList<Tea>();
  ArrayList<Integer> positionBA = new ArrayList<Integer>();
	ArrayList<ArrayList<InputBA>> instructionsBA;
	int[] resourcesBA;
	int[] releasedBA;
	int cycleBA = 0;
	int remainingBA;
	int remainingCopyBA = 0;


	public Banker(ArrayList<ArrayList<InputBA>> instruct, int[] recli){
		instructionsBA = instruct;
		resourcesBA = recli;
		remainingBA = instruct.size();
		releasedBA = new int[resourcesBA.length];
    initiate(instruct);
	}

public void initiate(ArrayList<ArrayList<InputBA>> instruct){
  for (int i = 0; i < instruct.size(); i++){
    tasksBA.add(new Tea(i+1, resourcesBA.length));
  }
}
// public int greedyCheck(int in){
//   int s = -1;
//   for(int c = 0; c < arguments.get(in).size(); c++){
//     //hello
//     if(res_array[c] > blocked.get(c).claims[c] ){
//       //unsafe claims too much make 'em' wait
//       //Positive number means true
//       s = 2;
//       System.out.println(s);
//       return s;
//     }
//   }
//   return s;
// }
  public void safer(){
    for (int p = 0; p < blockedBA.size(); p++){
      InputBA currentInstruction = instructionsBA.get(blockedBA.get(p).numberBA-1).get(0);
      int taskIndex = blockedBA.get(p).numberBA-1;
      boolean unsafe = false;

      //check to see if all resouces are able to be satisfied
      for (int m = 0; m < instructionsBA.get(taskIndex).size(); m++){
        if (instructionsBA.get(taskIndex).get(m).activity.equals("request")){
          int max = tasksBA.get(taskIndex).claimsBA[instructionsBA.get(taskIndex).get(m).resourceType-1] - tasksBA.get(taskIndex).resourcesBA[instructionsBA.get(taskIndex).get(m).resourceType-1];
          if (max > resourcesBA[instructionsBA.get(taskIndex).get(m).resourceType-1]){
            unsafe = true;
            break;
          }
        }
      }

      //Check if claimsBA exceeds resourcesBA, even if it does not use that resource
      for (int n = 0; n < resourcesBA.length; n++){
        if (blockedBA.get(p).claimsBA[n] > resourcesBA[n]){
          unsafe = true;
          break;}
      }

      //safer state detected - OK to grant request
      if (unsafe == false){
        blockedBA.get(p).resourcesBA[currentInstruction.resourceType-1] += currentInstruction.var;
        resourcesBA[currentInstruction.resourceType-1] -= currentInstruction.var;
        instructionsBA.get(taskIndex).remove(0);
        positionBA.add(p);}

      //Unsafe state detected - do not grant request
      else{
        tasksBA.get(taskIndex).waitBA += 1;}

    }
  }

public void allTask(){
  //now goe through all tasksks same as ORM but with a tweak
  //now goe through all tasksks same as ORM but with a tweak
for (int i = 0; i < instructionsBA.size(); i++){
  ArrayList<InputBA> currentTask = instructionsBA.get(i);
  if (tasksBA.get(i).endBA == true || blockedBA.contains(tasksBA.get(i))){
    continue;
  }

  else if (currentTask.get(0).delay > 0){
    currentTask.get(0).delay -= 1;
  }

  else if (currentTask.get(0).activity.equals("initiate")){
    //abort!
    if (resourcesBA[currentTask.get(0).resourceType-1] < currentTask.get(0).var){
      System.out.println("Bankers Algo: During cycle "+cycleBA+ " - "+ (cycleBA+1));
      System.out.print("Banker aborts task " + (i+1 ));
      System.out.print( " its claims " + currentTask.get(0).var );
      System.out.print(  " for resource " + currentTask.get(0).resourceType);
     System.out.print( " exceeds number avail of " + resourcesBA[currentTask.get(0).resourceType-1] );
     System.out.println( "");

      tasksBA.get(i).endBA = true;
      remainingBA -=1;
      tasksBA.get(i).finishBA = -1;
    }
    else{
      tasksBA.get(i).claimsBA[currentTask.get(0).resourceType-1] = currentTask.get(0).var;
      tasksBA.get(i).availableBA[currentTask.get(0).resourceType-1] = currentTask.get(0).var;
      currentTask.remove(0);
    }
  }

  else if (currentTask.get(0).activity.equals("request")){
    boolean unsafe = false;
    int exceedClaimCheck = tasksBA.get(i).resourcesBA[currentTask.get(0).resourceType-1] + currentTask.get(0).var;

    if (exceedClaimCheck > tasksBA.get(i).claimsBA[currentTask.get(0).resourceType-1]){
      System.out.println("Bankers Algo: During cycle "+cycleBA+ " - "+ (cycleBA+1));
      System.out.print("Banker aborts task " + (i+1 ));
      System.out.print( " its claims " + currentTask.get(0).var );
      System.out.print(  " for resource " + currentTask.get(0).resourceType);
     System.out.print( " exceeds number avail of " + resourcesBA[currentTask.get(0).resourceType-1] );
     System.out.println( "");
      tasksBA.get(i).endBA = true;
      remainingCopyBA += 1;
      tasksBA.get(i).finishBA = -1;

      for (int n = 0; n < tasksBA.get(i).resourcesBA.length; n++){
        releasedBA[n] += tasksBA.get(i).resourcesBA[n];
        tasksBA.get(i).resourcesBA[n] = 0;
      }
      currentTask.remove(0);
      continue;
    }

    for (int m = 0; m < currentTask.size(); m++){
      if (currentTask.get(m).activity.equals("request")){
        int max = tasksBA.get(i).claimsBA[currentTask.get(m).resourceType-1] - tasksBA.get(i).resourcesBA[currentTask.get(m).resourceType-1];
        if (max > resourcesBA[currentTask.get(m).resourceType-1]){ //Unsafe state detected
          tasksBA.get(i).waitBA += 1;
          blockedBA.add(tasksBA.get(i));
          unsafe = true;
          break;
        }
      }
    }
    if (unsafe == false){
      tasksBA.get(i).availableBA[currentTask.get(0).resourceType-1] -= currentTask.get(0).var;
      tasksBA.get(i).resourcesBA[currentTask.get(0).resourceType-1] += currentTask.get(0).var;
      resourcesBA[currentTask.get(0).resourceType-1] -= currentTask.get(0).var;
      currentTask.remove(0);
    }
  }

  else if (currentTask.get(0).activity.equals("release")){
    releasedBA[currentTask.get(0).resourceType-1] += currentTask.get(0).var;
    tasksBA.get(i).resourcesBA[currentTask.get(0).resourceType-1] -= currentTask.get(0).var;
    currentTask.remove(0);
  }

  if (currentTask.get(0).activity.equals("terminate") && currentTask.get(0).delay == 0){
    tasksBA.get(i).endBA = true;
    remainingCopyBA += 1;
    tasksBA.get(i).finishBA = cycleBA + 1;

    for (int n = 0; n < tasksBA.get(i).resourcesBA.length; n++){
      resourcesBA[n] += tasksBA.get(i).resourcesBA[n];
      tasksBA.get(i).resourcesBA[n] = 0;
    }
    currentTask.remove(0);
  }
}

}
public void releaseres(){
  for (int length = 0; length < resourcesBA.length; length++){
    resourcesBA[length] += releasedBA[length];
    releasedBA[length] = 0;
  }
}
public void removeblocked(){
  if (positionBA.size() > 0){
    for (int unblock = 0; unblock < positionBA.size(); unblock++){
      blockedBA.remove(tasksBA.get(positionBA.get(unblock)).numberBA-1);
      positionBA.remove(unblock);
    }
  }


}

	public void runBank(){
		while(remainingBA != 0){
		    safer();
        allTask();
        releaseres();
        removeblocked();
  			remainingBA -= remainingCopyBA;
  			remainingCopyBA = 0;
  			cycleBA++;
		}
    printBA();
  }

public void printBA(){
  System.out.println("");
  System.out.println("△ Banker's Algorithm △");
  int finalFinishingTime = 0;
  int finalWaitingTime = 0;
  for (int ii = 0; ii < tasksBA.size(); ii++){
    if (tasksBA.get(ii).finishBA < 0){
      System.out.println("Task: "+ " | "+ (ii+1) + " | " + "aborted :( ");
    }
    else{
      finalFinishingTime += tasksBA.get(ii).finishBA;
      finalWaitingTime += tasksBA.get(ii).waitBA;
      // int math = (float)finalWaitingTime/finalFinishingTime)*100;

      System.out.println("Task " + (ii+1) + " | " + tasksBA.get(ii).finishBA + " | " + tasksBA.get(ii).waitBA + " | " + Math.round((((float)tasksBA.get(ii).waitBA/tasksBA.get(ii).finishBA)*100)) + "%");
    }
  }
  System.out.println("Totals" + "   | " +finalFinishingTime + " | " + finalWaitingTime + " | " + Math.round(((float)finalWaitingTime/finalFinishingTime)*100) + "%");
  System.out.println("");

}


}
