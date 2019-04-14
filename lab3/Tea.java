public class Tea {
	//Tea = task executed at
  boolean end = false;
	int TeaNumber;
	int waitTime = 0;
	int[] res_array;
	int[] claims;
	int[] available;
	int finish = 0;
	public Tea(int N, int allowed_R){
		TeaNumber = N;
		res_array = new int[allowed_R];
		claims = new int[allowed_R];
		available = new int[allowed_R];
	}

}
