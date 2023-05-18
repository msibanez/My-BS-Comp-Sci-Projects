import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Scheduling {

	static final int AT = 0;
	static final int BT = 1;
	static final int PNum = 2;
	static final int RT = 3;
	static final int WT = 4;
	static final int TT = 5;
	static final int CT = 6; // CT = Completion Time
	static final int ST = 7; // ST = Start Time
	
	private int Total_WT;
	private int Total_RT;
	private int Total_TT;
	private float Ave_WT;
	private float Ave_RT;
	private float Ave_TT;
	
	private int[][] matrix;
	private int[][] table;
	
	private boolean isPreemptive;
	private int time;
	private int curr_low;
	private int total_time;
	private int add_id;
	private int timestamp;
	
	private int[] target_col;
	private boolean[] isDone;
	
	private String gantt_proc_id;
	private String[] gantt;
	private int[] gantt_ct;
	private int[] gantt_st;
		
	private ArrayList<Integer> target_col_adj1 = new ArrayList<Integer>();
	private ArrayList<Integer> target_col_adj2 = new ArrayList<Integer>();
	private ArrayList<Integer> ready_proc_id = new ArrayList<Integer>();
	private ArrayList<Integer> ready_proc_id2 = new ArrayList<Integer>();
	private ArrayList<Integer> ready_proc_id3 = new ArrayList<Integer>();
	
	private ArrayList<String> temp_gantt = new ArrayList<String>();
	private ArrayList<Integer> timestamp_list = new ArrayList<Integer>();

	MoreMethods mm = new MoreMethods();
	
	public Scheduling(int[][] matrix) {
		this.matrix = matrix;
		target_col = new int[matrix.length];
		isDone = new boolean[matrix.length];
		this.table = new int[matrix.length][8];
		total_time = mm.gantt_length(matrix);
		
		gantt = new String[matrix.length];
		gantt_ct = new int[matrix.length];
		gantt_st = new int[matrix.length + 1];
	}
	
	public void FCFS() { // matrix.length = column length (no. of rows), matrix[0].length = row length (no. of columns)
		time = 0;
		curr_low = 0;
		total_time = mm.gantt_length(matrix);	// resets total burst time because when idle is used, it increments the entire value
		
		for (int i = 0; i < matrix.length; i++) {
			target_col[i] = matrix[i][AT];
			isDone[i] = false;
		}
		Arrays.sort(target_col);
		boolean passedHere;

		while (time < total_time) {
			passedHere = false;
			
			for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
				if (matrix[proc_id][AT] <= time) {
					if (!isDone[proc_id]) {
						if (target_col[curr_low] == matrix[proc_id][AT]) {
							passedHere = true;
							
							table[proc_id][ST] = time;
							gantt_st[curr_low] = time;
							
							time += matrix[proc_id][BT];
							gantt[curr_low] = "P" + (proc_id + 1);
							table[proc_id][CT] = time;
							gantt_ct[curr_low] = time;

							curr_low++;
							isDone[proc_id] = true;
							break;
						}
					}
				}
			}
			
			if (!passedHere) {
				time++;
				total_time++;	// if it's idle, add 1 to total burst time
			}
		}
		calculateTableNP();
	}
	
	public void SJF() {
		time = 0;
		curr_low = 0;
		total_time = mm.gantt_length(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			isDone[i] = false;
		}
		boolean passedHere;
		boolean addId;

		while (time < total_time) {
			add_id = 0;
			addId = false;
			passedHere = false;
			
			ready_proc_id.clear();
			ready_proc_id2.clear();
			ready_proc_id3.clear();		
			target_col_adj1.clear();
			target_col_adj2.clear();
			
			for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
				if (matrix[proc_id][AT] <= time) {
					if (!isDone[proc_id]) {
						ready_proc_id.add(proc_id);
						target_col_adj1.add(matrix[proc_id][BT]);
					}
				}
			}
			
			if (!ready_proc_id.isEmpty()) {
				passedHere = true;
				for (int proc_id : ready_proc_id) {
					if (matrix[proc_id][BT] == Collections.min(target_col_adj1)) {
						ready_proc_id2.add(proc_id);
						target_col_adj2.add(matrix[proc_id][AT]);
					}
				}
				
				if (!ready_proc_id2.isEmpty()) {
					if(ready_proc_id2.size() > 1) {
						for (int proc_id : ready_proc_id2) {
							if (matrix[proc_id][AT] == Collections.min(target_col_adj2)) {
								ready_proc_id3.add(proc_id);
							}
						}
						
						if (!ready_proc_id3.isEmpty()) {
							add_id = ready_proc_id3.get(0);
							addId = true;
						}
						
					} else {
						add_id = ready_proc_id2.get(0);
						addId = true;
					}
				}
			}
			
			if (addId) {
				table[add_id][ST] = time;
				gantt_st[curr_low] = time;
				
				time += matrix[add_id][BT];
				gantt[curr_low] = "P" + (add_id + 1);
				table[add_id][CT] = time;
				gantt_ct[curr_low] = time;

				curr_low++;
				isDone[add_id] = true;
			}
			
			if (!passedHere) {
				time++;
				total_time++;
			}
		}
		calculateTableNP();
	}
	
	public void SRTF() {
		time = 0;
		total_time = mm.gantt_length(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			target_col[i] = matrix[i][BT];
			isDone[i] = false;
		}
		boolean addId;

		while (time < total_time) {
			add_id = 0;
			addId = false;
			gantt_proc_id = "";
			
			ready_proc_id.clear();
			ready_proc_id2.clear();
			ready_proc_id3.clear();		
			target_col_adj1.clear();
			target_col_adj2.clear();
			
			for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
				if (matrix[proc_id][AT] <= time) {
					if (!isDone[proc_id]) {
						ready_proc_id.add(proc_id);
						target_col_adj1.add(target_col[proc_id]);
					}
				}
			}
			
			if (!ready_proc_id.isEmpty()) {
				for (int proc_id : ready_proc_id) {
					if (target_col[proc_id] == Collections.min(target_col_adj1)) {
						ready_proc_id2.add(proc_id);
						target_col_adj2.add(matrix[proc_id][AT]);
					}
				}
				
				if (!ready_proc_id2.isEmpty()) {
					if(ready_proc_id2.size() > 1) {
						for (int proc_id : ready_proc_id2) {
							if (matrix[proc_id][AT] == Collections.min(target_col_adj2)) {
								ready_proc_id3.add(proc_id);
							}
						}
						
						if (!ready_proc_id3.isEmpty()) {
							add_id = ready_proc_id3.get(0);
							addId = true;
						}
						
					} else {
						add_id = ready_proc_id2.get(0);
						addId = true;
					}
				}
			}
			
			if (addId) {
				gantt_proc_id = "P" + (add_id + 1);
				temp_gantt.add(gantt_proc_id);
				
				if (target_col[add_id] > 1) {
					target_col[add_id]--;
				} else {
					isDone[add_id] = true;
				}
				
			} else {
				temp_gantt.add("idle");
				total_time++;
			}
			
			time++;
		}
		
		// add to gantt
		for (int time = 1; time <= temp_gantt.size(); time++) {
			if (time < temp_gantt.size()) {
				if ( !(temp_gantt.get(time - 1).equals(temp_gantt.get(time))) ) {
					timestamp_list.add(time);
				}
			} else {
				timestamp_list.add(time);
			}
		}
		
		gantt_proc_id = "P";
		for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
			table[proc_id][ST] = temp_gantt.indexOf(gantt_proc_id + (proc_id + 1));
			table[proc_id][CT] = temp_gantt.lastIndexOf(gantt_proc_id + (proc_id + 1)) + 1;
		}
		
		
		
//		System.out.println("temp gantt: " + temp_gantt);
//		System.out.println("time stamps: " + timestamp_list);
		
		calculateTableNP();
	}
	
	public void PriorityP() {
		time = 0;
		total_time = mm.gantt_length(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			target_col[i] = matrix[i][BT];
			isDone[i] = false;
		}
		boolean addId;

		while (time < total_time) {
			add_id = 0;
			addId = false;
			gantt_proc_id = "";
			
			ready_proc_id.clear();
			ready_proc_id2.clear();
			ready_proc_id3.clear();		
			target_col_adj1.clear();
			target_col_adj2.clear();
			
			for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
				if (matrix[proc_id][AT] <= time) {
					if (!isDone[proc_id]) {
						ready_proc_id.add(proc_id);
						target_col_adj1.add(matrix[proc_id][PNum]);
					}
				}
			}
			
			if (!ready_proc_id.isEmpty()) {
				for (int proc_id : ready_proc_id) {
					if (matrix[proc_id][PNum] == Collections.min(target_col_adj1)) {
						ready_proc_id2.add(proc_id);
						target_col_adj2.add(matrix[proc_id][AT]);
					}
				}
				
				if (!ready_proc_id2.isEmpty()) {
					if(ready_proc_id2.size() > 1) {
						for (int proc_id : ready_proc_id2) {
							if (matrix[proc_id][AT] == Collections.min(target_col_adj2)) {
								ready_proc_id3.add(proc_id);
							}
						}
						
						if (!ready_proc_id3.isEmpty()) {
							add_id = ready_proc_id3.get(0);
							addId = true;
						}
						
					} else {
						add_id = ready_proc_id2.get(0);
						addId = true;
					}
				}
			}
			
			if (addId) {
				gantt_proc_id = "P" + (add_id + 1);
				temp_gantt.add(gantt_proc_id);
				
				if (target_col[add_id] > 1) {
					target_col[add_id]--;
				} else {
					isDone[add_id] = true;
				}
				
			} else {
				temp_gantt.add("idle");
				total_time++;
			}
			
			time++;
		}
		
		// add to gantt
		for (int time = 1; time <= temp_gantt.size(); time++) {
			if (time < temp_gantt.size()) {
				if ( !(temp_gantt.get(time - 1).equals(temp_gantt.get(time))) ) {
					timestamp_list.add(time);
				}
			} else {
				timestamp_list.add(time);
			}
		}
		
		gantt_proc_id = "P";
		for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
			table[proc_id][ST] = temp_gantt.indexOf(gantt_proc_id + (proc_id + 1));
			table[proc_id][CT] = temp_gantt.lastIndexOf(gantt_proc_id + (proc_id + 1)) + 1;
		}
		
//		System.out.println("temp gantt: " + temp_gantt);
//		System.out.println("time stamps: " + timestamp_list);
		
		calculateTableNP();
		
	}
	
	public void PriorityNP() {
		time = 0;
		curr_low = 0;
		total_time = mm.gantt_length(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			isDone[i] = false;
		}
		boolean passedHere;
		boolean addId;

		while (time < total_time) {
			add_id = 0;
			addId = false;
			passedHere = false;
			
			ready_proc_id.clear();
			ready_proc_id2.clear();
			ready_proc_id3.clear();		
			target_col_adj1.clear();
			target_col_adj2.clear();
			
			for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
				if (matrix[proc_id][AT] <= time) {
					if (!isDone[proc_id]) {
						ready_proc_id.add(proc_id);
						target_col_adj1.add(matrix[proc_id][PNum]);
					}
				}
			}
			
			if (!ready_proc_id.isEmpty()) {
				passedHere = true;
				for (int proc_id : ready_proc_id) {
					if (matrix[proc_id][PNum] == Collections.min(target_col_adj1)) {
						ready_proc_id2.add(proc_id);
						target_col_adj2.add(matrix[proc_id][AT]);
					}
				}
				
				if (!ready_proc_id2.isEmpty()) {
					if(ready_proc_id2.size() > 1) {
						for (int proc_id : ready_proc_id2) {
							if (matrix[proc_id][AT] == Collections.min(target_col_adj2)) {
								ready_proc_id3.add(proc_id);
							}
						}
						
						if (!ready_proc_id3.isEmpty()) {
							add_id = ready_proc_id3.get(0);
							addId = true;
						}
						
					} else {
						add_id = ready_proc_id2.get(0);
						addId = true;
					}
				}
			}
			
			if (addId) {
				table[add_id][ST] = time;
				gantt_st[curr_low] = time;
				
				time += matrix[add_id][BT];
				gantt[curr_low] = "P" + (add_id + 1);
				table[add_id][CT] = time;
				gantt_ct[curr_low] = time;

				curr_low++;
				isDone[add_id] = true;
			}
			
			if (!passedHere) {
				time++;
				total_time++;
			}
		}
		calculateTableNP();
	}
	
//	public void RR(int time_quantum) {		// discontinued
//		time = 0;
//		curr_low = 0;
//		
//		for (int i = 0; i < matrix.length; i++) {
//			target_col[i] = matrix[i][BT];
//			isDone[i] = false;
//		}
//		boolean addId;
//
//		while (time < 1) {
//			add_id = 0;
//			addId = false;
//			gantt_proc_id = "";
//			
//			// diff algo
//			ready_proc_id.clear();
//			ready_proc_id2.clear();
//			ready_proc_id3.clear();		
//			target_col_adj1.clear();
//			target_col_adj2.clear();
//			
//			for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
//				if (matrix[proc_id][AT] <= time) {
//					if (!isDone[proc_id]) {
//						ready_proc_id.add(proc_id);
//						target_col_adj1.add(matrix[proc_id][AT]);
//					}
//				}
//			}
//			
//			ready_proc_id.sort(Comparator.comparingInt(target_col_adj1::indexOf));
//			
////			System.out.println("First ready: " + ready_proc_id);
//			
//			if (time > 0) {
//				checkOtherProcessors();
//			}
//			
//			if (addId) {
//				gantt_proc_id = "P" + (add_id + 1);
//				temp_gantt.add(gantt_proc_id);
//				
//				if (target_col[add_id] > 1) {
//					target_col[add_id]--;
//				} else {
//					isDone[add_id] = true;
//				}
//				
//				time += time_quantum;
//				
//			} else {
//				temp_gantt.add("idle");
//				total_time++;
//				time++;
//			}
//		}
//		
//		// add to gantt
//		for (int time = 1; time <= temp_gantt.size(); time++) {
//			if (time < temp_gantt.size()) {
//				if ( !(temp_gantt.get(time - 1).equals(temp_gantt.get(time))) ) {
//					timestamp_list.add(time);
//				}
//			} else {
//				timestamp_list.add(time);
//			}
//		}
//		
//		gantt_proc_id = "P";
//		for (int proc_id = 0; proc_id < matrix.length; proc_id++) {
//			table[proc_id][ST] = temp_gantt.indexOf(gantt_proc_id + (proc_id + 1));
//			table[proc_id][CT] = temp_gantt.lastIndexOf(gantt_proc_id + (proc_id + 1)) + 1;
//		}
//		
//		System.out.println("temp gantt: " + temp_gantt);
//		System.out.println("time stamps: " + timestamp_list);
//		
//		calculateTableNP();
//	}
//	
//	public void checkOtherProcessors() {
//		
//	}
	
	public void calculateTableNP() {
		Total_TT = 0; Total_WT = 0; Total_RT = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			table[i][TT] = table[i][CT] - matrix[i][AT];
			table[i][WT] = table[i][TT] - matrix[i][BT];
			table[i][RT] = table[i][ST] - matrix[i][AT];
			
			table[i][AT] = matrix[i][AT];
			table[i][BT] = matrix[i][BT];
			table[i][PNum] = matrix[i][PNum];
			
			Total_TT += table[i][TT];
			Total_WT += table[i][WT];
			Total_RT += table[i][RT];
		}
		Ave_TT = (float) Total_TT / matrix.length;
		Ave_WT = (float) Total_WT / matrix.length;
		Ave_RT = (float) Total_RT / matrix.length;
	}
	
	public void printTable() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		System.out.printf("%n--------------------------------------------------%n");
		System.out.printf("| %-4s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s |%n",
				"Proc", "AT", "BT", "P", "RT", "WT", "TT", "CT");
		System.out.printf("--------------------------------------------------%n");
		for (int i = 0; i < table.length; i++) {
			System.out.printf("| %-4s | %-3d | %-3d | %-3d | %-3d | %-3d | %-3d | %-3d |%n",
					"P" + (i + 1), table[i][AT], table[i][BT], table[i][PNum], table[i][RT],
					table[i][WT], table[i][TT], table[i][CT]);
		}
		System.out.printf("--------------------------------------------------%n");
		System.out.println("Ave. WT: " + df.format(Ave_WT) + " || Ave. RT: " + df.format(Ave_RT) + " || Ave. TT: " + df.format(Ave_TT));
	}
	
	public void printTableWithST() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		
		System.out.printf("%n--------------------------------------------------%n");
		System.out.printf("| %-4s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s | %-3s |%n",
				"Proc", "AT", "BT", "P", "RT", "WT", "TT", "CT", "ST");
		System.out.printf("--------------------------------------------------%n");
		for (int i = 0; i < table.length; i++) {
			System.out.printf("| %-4s | %-3d | %-3d | %-3d | %-3d | %-3d | %-3d | %-3d | %-3d |%n",
					"P" + (i + 1), table[i][AT], table[i][BT], table[i][PNum], table[i][RT],
					table[i][WT], table[i][TT], table[i][CT], table[i][ST]);
		}
		System.out.printf("--------------------------------------------------%n");
	}
	
	public void printGantt() {
		System.out.println("\nGantt Chart:");
		System.out.print("0");
		
		if (0 != gantt_st[0]) {
			System.out.print("[ idle ]" + gantt_st[0]);
		}
		
		for (int i = 0; i < gantt.length; i++) {
			System.out.print("[ " + gantt[i] + " ]");
			System.out.print(gantt_ct[i]);
			if (gantt_ct[i] != gantt_st[i + 1]) {
				if (i < gantt.length - 1) {
					System.out.print("[ idle ]" + gantt_st[i + 1]);
				}
			}
		}
		System.out.println();
		
		
		temp_gantt.clear();
		timestamp_list.clear();
	}
	
	public void printGanttP() {
		System.out.print("\nGantt Chart:\n0");
		
		for (int timestamp : timestamp_list) {
			System.out.print("[ " + temp_gantt.get(timestamp - 1) + " ]");
			System.out.print(timestamp);
		}
		System.out.println();
		
		
		temp_gantt.clear();
		timestamp_list.clear();
	}
}
