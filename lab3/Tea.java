public class Tea {
	//Tea = task executed at
  //fifo
  boolean end = false;
	int TeaNumber;
  int finish = 0;
	int waitTime = 0;
	int[] res_array;
	int[] claims;
	int[] available;
//bankers
  boolean endBA = false;
	int numberBA;
  int finishBA = 0;
	int waitBA = 0;
	int[] resourcesBA;
	int[] claimsBA;
	int[] availableBA;

	public Tea(int N, int allowed_R){
		TeaNumber = N;
    numberBA = N;
		res_array = new int[allowed_R];
    resourcesBA = new int[allowed_R];
		claims = new int[allowed_R];
    claimsBA = new int[allowed_R];
		available = new int[allowed_R];
    availableBA = new int[allowed_R];
	}

}
