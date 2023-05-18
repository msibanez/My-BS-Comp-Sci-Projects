import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner(System.in);
		
		int processors = 0;
		String[][] str_matrix = null;
		int[][] matrix = null;
		
		boolean inputExists = false;
		boolean titleIsSeen = true;
		
		while (true) {
			
			String choice;
			
			if (titleIsSeen) {
				System.out.println("== CPU Scheduling Algorithms ==\n");
			}
			System.out.print("1 - Upload Inputs\n"
					+ "2 - Calculate\n"
					+ "3 - Exit\n"
					+ ">");
			choice = sc.nextLine();
			
			titleIsSeen = false;
			
			if (choice.equals("1")) {
				
				processors = 0;
				
				String file;
				String substr;
				
				System.out.println("\nColumns Format: Arrival Time, Burst Time, Priority Number.");
				System.out.println("Each row represents a process ID.");
				System.out.print("\nUpload Inputs (CSV file): ");
				
				file = sc.nextLine();
				
				File f = new File(file);
				
			    if (f.exists()) {
			    	substr = file.substring(file.length() - 4);
			    	substr = substr.toLowerCase();
			    	
			    	if (substr.equals(".csv")) {

			    		List<String[]> rowList = new ArrayList<String[]>();
			    		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    		    String line;
			    		    while ((line = br.readLine()) != null) {
			    		        String[] lineItems = line.split(",");
			    		        rowList.add(lineItems);
			    		        processors++;
			    		    }
			    		    br.close();
			    		}
			    		catch(Exception e){
			    			e.printStackTrace();
			    		}
			    		
			    		str_matrix = new String[processors][3];
			    		for (int i = 0; i < rowList.size(); i++) {
			    		    String[] row = rowList.get(i);
			    		    str_matrix[i] = row;
			    		}
			    		
			    		matrix = new int[processors][3];
			    		
			    		try {
				    	    for (int i = 0; i < str_matrix.length; i++) {
				    	    	for (int j = 0; j < str_matrix[i].length; j++) {
				    	    		if (str_matrix[i][j] != null && !str_matrix[i][j].trim().isEmpty()) {
				    	    			matrix[i][j] = Integer.parseInt(str_matrix[i][j]);
				    	    		}
				    	    	}
				    	    }
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    		}
			    		
			    		// print matrix
//			    		for (int i = 0; i < str_matrix.length; i++) {
//			    	    	for (int j = 0; j < str_matrix[i].length; j++) {
//			    	    		System.out.print(matrix[i][j]);
//			    	    	}
//			    	    	System.out.println("");
//			    	    }
//		    	    	System.out.println("");
		    	    	
//		    	    	System.out.println("This matrix has " + size + " rows\n");
		    	    	System.out.println("\nInputs Uploaded Successfully. " + processors + " proccessors found.\n");
		    	    	
		    	    	inputExists = true;
		    	    	Thread.sleep(1000);
			    		
			    	} else {
			    		System.out.println("");
			    		System.out.println("This is not a CSV file.\n");
			    		Thread.sleep(1000);
			    	}

			    } else {
		    		System.out.println("");
			    	System.out.println("File not found.\n");
			    	Thread.sleep(1000);
			    }
			    
			    titleIsSeen = true;
			    
			} else if (choice.equals("2")) {
				
				if(inputExists) {
					
					Scheduling inputs = new Scheduling(matrix);
					
		    		// print matrix
//		    		for (int i = 0; i < str_matrix.length; i++) {
//		    	    	for (int j = 0; j < str_matrix[i].length; j++) {
//		    	    		System.out.print(matrix[i][j]);
//		    	    	}
//		    	    	System.out.println("");
//		    	    }
//	    	    	System.out.println("");
					
					String recalculate = "";
					boolean algoDone;
					boolean preem;
	    	    	String type;
	    	    	
	    	    	while(true) {
	    	    		
	    	    		while (true) {
			    	    	System.out.print("\n"
			    	    			+ "1 - FCFS\n"
			    					+ "2 - SJF\n"
			    					+ "3 - SRTF\n"
			    					+ "4 - Priority Preemptive\n"
			    					+ "5 - Priority Non-Preemptive\n"
			    					+ ">");
			    			type = sc.nextLine();
		    				
			    			algoDone = true;
			    			preem = false;
		    				
		    				switch (type) {
			    			case "1":
			    				inputs.FCFS();
			    				break;
			    				
			    			case "2":
			    				inputs.SJF();
			    				break;
			    				
			    			case "3":
			    				inputs.SRTF();
			    				preem = true;
			    				break;
			    				
			    			case "4":
			    				inputs.PriorityP();
			    				preem = true;
			    				break;
			    				
			    			case "5":
			    				inputs.PriorityNP();
			    				break;
			    				
//			    			case "6":
//			    				int time_quantum = 0;
//			    				boolean exit = false;
//			    				boolean showMsg;
//			    				
//			    				while (true) {
//			    					showMsg = true;
//			    					try {
//				    					System.out.print("\nTime Quantum: ");
//				    					time_quantum = sc.nextInt();
//			
//				    				} catch (Exception e) {
//				    					showMsg = false;
//				    					System.out.println("\nInvalid input. Positive integers only.");
//				    				}
//			    					sc.nextLine();
//			    					
//			    					if (time_quantum > 0) {
//			    						exit = true;
//			    					} else if (showMsg) {
//			    						System.out.println("\nPositive integers only.");
//			    					}
//			    					
//			    					if (exit) break;
//			    				}
//			    				
////			    				inputs.RR(time_quantum);
//			    				preem = true;
//			    				break;
			    				
			    			default:
			    				System.out.println("\nChoose between 1-5 only.");
			    				algoDone = false;
			    			}
		    				
		    				inputs.printTable();
		    				
		    				if (preem) {
		    					inputs.printGanttP();
		    				} else {
		    					inputs.printGantt();
		    				}
		    				
//		    				inputs.printTableWithST();
		    				
		    				if (algoDone) {
		    					break;
		    				}
		    			}
		    	    	
						titleIsSeen = true;

	    	    		System.out.print("\nRecalculate? (Input 'Y' to redo. Press Enter to stop)\n"
	    	    				+ ">");
	    	    		recalculate = sc.nextLine();
	    	    		
	    	    		if (!recalculate.equals("Y")) {
	    	    			System.out.println("");
	    	    			break;
	    	    		}
	    	    	}
	    	    	
				} else {
					System.out.println("\nInputs not found. Enter '1' to upload inputs.\n");
				}
				
			} else if (choice.equals("3")) {
				break;
			} else {
				System.out.println("\nChoose between 1-3 only.\n");
			}
		}
	    sc.close();
	}

}
