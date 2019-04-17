import java.util.ArrayList;
//ERROR ABORTED TASKS DOUBLE CHECKK
public class Run {
//optimistic
ArrayList<Tea> tasks = new ArrayList<Tea>();
ArrayList<Tea> blocked = new ArrayList<Tea>();
ArrayList<Integer> position = new ArrayList<Integer>();
ArrayList<ArrayList<Input>> arguments;
ArrayList<Integer> resource2 = new ArrayList<Integer>();
int[] res_array;
int[] released;
int cycle = 0;
int remaning;
int remaningCopy = 0;
int checkWtime = 0;

//banker
ArrayList<Tea> tasksBA = new ArrayList<Tea>();
ArrayList<ArrayList<Input>> instructionsBA;
ArrayList<Tea> blockedBA = new ArrayList<Tea>();
ArrayList<Integer> positionBA = new ArrayList<Integer>();
int cycleBA = 0;
int[] resourcesBA;
int[] releasedBA;
int remainingCopyBA = 0;
int remainingBA;


  //Works both for bankers and fifo vvv
  public Run(ArrayList<ArrayList<Input>> instructionList, int[] resourceList){
    arguments = instructionList;
    res_array = resourceList;
    remaning = instructionList.size();
    released = new int[res_array.length];
    instructionsBA = instructionList;
    resourcesBA = resourceList;
    remainingBA = instructionList.size();
    releasedBA = new int[resourcesBA.length];
    addAll(arguments);
  }
  public void runBanker(){
    while(remaning != 0){
      //check blocked first
      blockTasksBN();
      break;
    }
    printBanker();
  }
  public void runOP(){
    //Driver method start by seeing remaining tasks
    boolean done = false;
    while(remaning != 0){
      //Check blocked tasks first
      blockTasksFirstOP();
      allTasks();
      deadlockCheck();
      removeBlocked();
      release();
      remaning -= remaningCopy;
      remaningCopy = 0;
      checkWtime = 0;
      cycle++;

    }
    printOP();
  }
  public void addAll(ArrayList<ArrayList<Input>> instructionList){
    for (int i = 0; i < instructionList.size(); i++){
      tasks.add(new Tea(i+1, res_array.length));
    }
  }
  public void blockTasksBN(){
    for (int posBlo = 0; posBlo < blocked.size(); posBlo++){
      Input current = arguments.get(blocked.get(posBlo).TeaNumber-1).get(0);
      int tasknum = blocked.get(posBlo).TeaNumber-1;
      int safe = -1; //negative number means unsafe
      //call all claims and check they can pass
      safe = greedyCheck(tasknum);
      for(int i = 0; i < res_array.length;i++){
        //greedy
        if(blocked.get(posBlo).claims[i] > res_array[i]){
          safe = 2;
          break;
        }
      }
      //no issues
      if(safe < 0){
        blocked.get(posBlo).res_array[current.resourceType-1] += current.var;
        res_array[current.resourceType-1] -= current.var;
        arguments.get(tasknum).remove(0);
        position.add(posBlo);}
      }
    }

    public int greedyCheck(int in){
      int s = -1;
      for(int c = 0; c < arguments.get(in).size(); c++){
        //hello
        if(res_array[c] > blocked.get(c).claims[c] ){
          //unsafe claims too much make 'em' wait
          //Positive number means true
          s = 2;
          System.out.println(s);
          return s;
        }
      }
      return s;
    }
    //Check for deadlock
    public void blockTasksFirstOP(){
      for (int posBlo = 0; posBlo < blocked.size(); posBlo++){
        Input current = arguments.get(blocked.get(posBlo).TeaNumber-1).get(0);
        if (current.activity.equals("request")){
          if (res_array[current.resourceType-1] < current.var){
            tasks.get(blocked.get(posBlo).TeaNumber-1).waitTime += 1;
            checkWtime += 1;}

            else if (res_array[current.resourceType-1] >= current.var){
              tasks.get(blocked.get(posBlo).TeaNumber-1).res_array[current.resourceType-1] += current.var;
              res_array[current.resourceType-1] -= current.var;
              arguments.get(blocked.get(posBlo).TeaNumber-1).remove(0);
              position.add(posBlo);}
            }
          }
        }
        public void deadlockCheck(){
          int ok = 0;
          while (remaning != 0 && checkWtime == remaning){
            //Delete all aborted tasks
            abort();

            checkWtime = 0;
            for (int i = 0; i < arguments.size(); i++){
              if (arguments.get(i).size() == 0){
                ok = 1;
              }
              if(tasks.get(i).end == true){
                ok = 1;
              }
              else if (arguments.get(i).get(0).activity.equals("request")){
                if (res_array[arguments.get(i).get(0).resourceType-1] < arguments.get(i).get(0).var){
                  checkWtime += 1;
                }
                else if (res_array[arguments.get(i).get(0).resourceType-1] >= arguments.get(i).get(0).var){
                  break;
                }
              }
            }
          }
        }
        public Boolean abort(){
          for (int ab = 0; ab < arguments.size(); ab++){
            if (tasks.get(ab).end == false){
              tasks.get(ab).end = true;
              remaning -=1;
              tasks.get(ab).finish = -1;

              //Release its res_array
              for (int n = 0; n < tasks.get(ab).res_array.length; n++){
                res_array[n] += tasks.get(ab).res_array[n];
                tasks.get(ab).res_array[n] = 0;
                //  remaning -=1;

              }
              blocked.remove(tasks.get(ab));
              return true;

            }
          }
          return false;
        }
        public void allTasks(){
          for (int i = 0; i < arguments.size(); i++){

            ArrayList<Input> taskAt = arguments.get(i);

            if (blocked.contains(tasks.get(i))){
              continue;
            }
            if(tasks.get(i).end != false){
              continue;
            }

            if (taskAt.get(0).delay > 0){
              taskAt.get(0).delay -= 1;
            }

            else if (taskAt.get(0).activity.equals("initiate")){
              taskAt.remove(0);
            }
            else if (taskAt.get(0).activity.equals("request")){
              //Check if there is a deadlock: if TeaNumber requested > available res_array, then there is a deadlock
              Boolean ded = res_array[taskAt.get(0).resourceType-1] < taskAt.get(0).var;
              Boolean notDed = res_array[taskAt.get(0).resourceType-1] >= taskAt.get(0).var;
              //important because of the equal sign else we get eror
              if (ded){
                tasks.get(i).waitTime += 1;
                checkWtime += 1;
                blocked.add(tasks.get(i));
              }

              else if (notDed){
                tasks.get(i).res_array[taskAt.get(0).resourceType-1] += taskAt.get(0).var;
                res_array[taskAt.get(0).resourceType-1] -= taskAt.get(0).var;
                taskAt.remove(0);
              }

            }

            else if (taskAt.get(0).activity.equals("release")){
              released[taskAt.get(0).resourceType-1] += taskAt.get(0).var;
              tasks.get(i).res_array[taskAt.get(0).resourceType-1] -= taskAt.get(0).var;
              taskAt.remove(0);
            }

            if (taskAt.get(0).activity.equals("terminate")){

              if(taskAt.get(0).delay == 0){
                tasks.get(i).end = true;
                remaningCopy += 1;
                tasks.get(i).finish = cycle+1;
                //tasks.get(i).finish += 1;

                //Release res_array
                for (int n = 0; n < tasks.get(i).res_array.length; n++){
                  res_array[n] += tasks.get(i).res_array[n];
                  tasks.get(i).res_array[n] = 0;
                }

                taskAt.remove(0);
              }
            }
          }
        }
        public void noDelay(){
        }
        public void removeBlocked(){
          if (position.size() > 0){
            //Remove any blocked tasks from the list if request is granted
            for (int unblock = 0; unblock < position.size(); unblock++){

              blocked.remove(tasks.get(position.get(unblock)).TeaNumber-1);
              position.remove(unblock);
            }
          }
        }
        public void free(int k){

        }
        public void release(){
          for (int length = 0; length < res_array.length; length++){
            res_array[length] += released[length];
            released[length] = 0;
          }
        }
        public void removeblocked(){
        }

        public void printOP(){
          System.out.println(" ");
          System.out.println("⚬ FIFO ⚬");
          int finalFinishingTime = 0;
          int totalWaiting = 0;
          //HMMMM bad idea using string builder check
          StringBuilder result = new StringBuilder("");
          StringBuilder total = new StringBuilder("");

          for (int ii = 0; ii < tasks.size(); ii++){
            if (tasks.get(ii).finish < 0){
              result.append("Task: ");
              result.append(" | ");
              result.append(ii+1);
              result.append(" | ");
              result.append("%");
              System.out.println(result);
              result.setLength(0);
            }
            else{
              finalFinishingTime += tasks.get(ii).finish;
              totalWaiting += tasks.get(ii).waitTime;
              result.append("Task: ");
              result.append(ii+1);
              result.append(" | ");
              result.append(tasks.get(ii).finish );
              result.append(" | ");
              result.append(tasks.get(ii).waitTime );
              result.append(" | ");
              result.append(((((float)tasks.get(ii).waitTime/tasks.get(ii).finish))*100));
              result.append("%");
              System.out.println(result);
              result.setLength(0);

            }
          }
          // /System.out.println("hello");
          total.append("Totals");
          total.append(" \t ");
          total.append(" ");
          total.append(" | ");
          total.append(finalFinishingTime);
          total.append(" | ");
          total.append(totalWaiting);
          total.append(" | ");
          total.append((((float)totalWaiting/finalFinishingTime)*100));
          total.append( "%");
          System.out.println(total);
          System.out.println(" ");

        }
        public void printBanker(){
          System.out.println(" ");
          System.out.println("=================");      	}


      }
