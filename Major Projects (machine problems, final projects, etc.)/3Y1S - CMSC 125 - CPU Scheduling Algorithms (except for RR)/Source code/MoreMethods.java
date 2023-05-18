
public class MoreMethods {
	
	public int gantt_length(int[][] arr) {	// gets total burst time
		int sum = 0;
 
        for (int i = 0; i < arr.length; i++) {
        	 sum += arr[i][Scheduling.BT];
        }
        
        return sum;
	}
}
