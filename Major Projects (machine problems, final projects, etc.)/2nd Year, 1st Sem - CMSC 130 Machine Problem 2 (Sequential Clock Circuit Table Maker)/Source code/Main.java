import java.util.InputMismatchException;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Main {

	public static void main(String[] args) {
				
		Scanner sc = new Scanner(System.in);
		MoreMethods mm = new MoreMethods();
		
		String choice;
		
		String ff;
		String ff_uc;
		int ff_num;
		int in_num;
		int out_num;
		
		int table_length;
		int table_height;
		
		while(true) {
			
			System.out.print("Sequential Clock Circuit Table Maker\n"
					+ "1 - create table\n"
					+ "2 - exit\n"
					+ ">");
			choice = sc.nextLine();
			
			if (choice.equals("1")) {
				
				// flip flop type
				while(true) {
					System.out.print("\nInput the type of flip-flop.\n"
							+ ">");
					ff = sc.nextLine();
					ff_uc = ff.toUpperCase();
					
					if (ff_uc.equals("RS") || ff_uc.equals("JK") || ff_uc.equals("D") || ff_uc.equals("T")) {
						break;
					} else {
						System.out.println("\nInvalid flip-flop.");
					}
				}
				
				// num of flip flops
				while(true) {
					try {
						System.out.print("\nInput the number of flip-flops (1-2).\n"
								+ ">");
						ff_num = sc.nextInt();
						sc.nextLine();
						
						if (ff_num == 1 || ff_num == 2) {
							break;
						} else {
							System.out.println("\nOnly 1-2 flip-flops are allowed.");
						}
						
					} catch (Exception e) {
						System.out.println("\nInput numbers only.");
						sc.nextLine();
					}
				}
				
				// num of input var
				while(true) {
					try {
						System.out.print("\nInput the number of input variables (1-2).\n"
								+ ">");
						in_num = sc.nextInt();
						sc.nextLine();
						
						if (in_num == 1 || in_num == 2) {
							break;
						} else {
							System.out.println("\nOnly 1-2 input variables are allowed.");
						}
						
					} catch (Exception e) {
						System.out.println("\nInput numbers only.");
						sc.nextLine();
					}
				}
				
				// num of output var
				while(true) {
					try {
						System.out.print("\nInput the number of output variables (0-1).\n"
								+ ">");
						out_num = sc.nextInt();
						sc.nextLine();
						
						if (out_num == 0 || out_num == 1) {
							break;
						} else {
							System.out.println("\nOnly 0-1 output variables are allowed.");
						}
						
					} catch (Exception e) {
						System.out.println("\nInput numbers only.");
						sc.nextLine();
					}
				}
				
//				System.out.println("\nType of flip-flop: " + ff_uc + "\n"
//						+ "Number of flip flops: " + ff_num + "\n"
//						+ "Number of input var: " + in_num + "\n"
//						+ "Number of output var: " + out_num + "\n");
				
				if (ff_uc.equals("JK") || ff_uc.equals("RS")) {
					table_length = (4 * ff_num) + in_num + out_num;
				} else {
					table_length = (3 * ff_num) + in_num + out_num;
				}
				
				table_height = (int) Math.pow(2, ff_num + in_num);
				
				// table
				char[][] table = new char[table_height + 2][table_length];
								
//				for(int i = 0; i < table.length; i++) {
//					for(int j = 0; j < table[0].length; j++) {
//						table[i][j] = '*';
//					}
//				}
				
				// making the labels
				for(int i = 0; i < ff_num; i++) {
					table[1][i] = (char) (i + 65);
				}
				
				for(int i = 0; i < in_num; i++) {
					table[1][i + ff_num] = (char) (i + 120);
				}
				
				char c1, c2;
				int row_no;
				row_no = ff_num + in_num;
				
				if (ff_uc.equals("JK") || ff_uc.equals("RS")) {
					if (ff_uc.equals("JK")) {
						c1 = 'J';
						c2 = 'K';
					} else {
						c1 = 'R';
						c2 = 'S';
					}
					
					table[0][row_no] = c1;
					table[0][row_no + 1] = c2;
					
					table[1][row_no] = 'A';
					table[1][row_no + 1] = 'A';
					
					row_no += 2;
					
					if (ff_num == 2) {
						table[0][row_no] = c1;
						table[0][row_no + 1] = c2;
						
						table[1][row_no] = 'B';
						table[1][row_no + 1] = 'B';
						
						row_no += 2;
					}
				} else {
					for(int i = 0; i < ff_num; i++) {
						table[1][i + row_no] = (char) (i + 65);
					}
					
					if (ff_uc.equals("D")) {
						for(int i = 0; i < ff_num; i++) {
							table[0][i + row_no] = 'D';
						}
					} else {
						for(int i = 0; i < ff_num; i++) {
							table[0][i + row_no] = 'T';
						}
					}
					row_no++;
					if (ff_num == 2) {
						row_no++;
					}
				}
				
				for(int i = 0; i < ff_num; i++) {
					table[1][i + row_no] = (char) (i + 65);
				}
				
				if (out_num == 1) {
					if (in_num == 2) {
						table[1][table_length - 1] = 'z';
					} else {
						table[1][table_length - 1] = 'y';
					}
				}
				
				// binary minterm maker
				int mint_digits = ff_num + in_num;
				int mint = (int) Math.pow(2, mint_digits);
				
				for(int i = 0; i < mint; i++) {
					String s1 = Integer.toBinaryString(i);
					String s2 = String.format("%" + mint_digits + "s", s1).replace(' ', '0');
					for(int j = 0; j < mint_digits; j++) {
						table[i + 2][j] = s2.charAt(j);
					}
				}
				
				// input function
				char c, cc;
				String in1 = null, in2 = null, in3 = null, in4 = null;
				
				String var = null;
				int num_in = 0;
				
				if (ff_uc.equals("D") || ff_uc.equals("T")) {
					if (ff_uc.equals("D")) {
						c = 'D';
					} else {
						c = 'T';
					}
					try {
						System.out.print("\n" + c + "A: ");
						in1 = sc.nextLine();
						num_in++;
						
						if(ff_num == 2) {
							System.out.print("\n" + c + "B: ");
							in2 = sc.nextLine();
							num_in++;
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else if (ff_uc.equals("JK") || ff_uc.equals("RS")) {
					if (ff_uc.equals("JK")) {
						c = 'J';
						cc = 'K';
					} else {
						c = 'R';
						cc = 'S';
					}
					try {
						System.out.print("\n" + c + "A: ");
						in1 = sc.nextLine();
						num_in++;
						
						System.out.print("\n" + cc + "A: ");
						in2 = sc.nextLine();
						num_in++;
						
						if(ff_num == 2) {
							System.out.print("\n" + c + "B: ");
							in3 = sc.nextLine();
							num_in++;
							
							System.out.print("\n" + cc + "B: ");
							in4 = sc.nextLine();
							num_in++;
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					if(out_num == 1) {
						if (in_num == 1) {
							System.out.print("\ny: ");
							var = sc.nextLine();
						} else {
							System.out.print("\nz: ");
							var = sc.nextLine();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// boolean calculation
				String in1_bi, in2_bi, in3_bi, in4_bi, var_bi;
				int curr_col = mint_digits;
				
				for (int i = 0; i < mint; i++) {
					in1_bi = in1.replaceAll("A", Character.toString(table[i + 2][0]));
					
					if (ff_num == 2) {
						in1_bi = in1_bi.replaceAll("B", Character.toString(table[i + 2][1]));
						in1_bi = in1_bi.replaceAll("x", Character.toString(table[i + 2][2]));
						if (in_num == 2) {
							in1_bi = in1_bi.replaceAll("y", Character.toString(table[i + 2][3]));
						}
					} else {
						in1_bi = in1_bi.replaceAll("x", Character.toString(table[i + 2][1]));
						if (in_num == 2) {
							in1_bi = in1_bi.replaceAll("y", Character.toString(table[i + 2][2]));
						}
					}
					
//					System.out.println("Before compliment: " + in1_bi);
					
					String compliment = mm.takeCompliment(in1_bi);
					
//					System.out.println("After compliment: " + compliment);
					
					int s = EvaluateString.evaluate(compliment) ;
					table[i + 2][curr_col] = (char) (s + '0');
				}
				curr_col++;
				
				if (num_in > 1) {
					for (int i = 0; i < mint; i++) {
						in2_bi = in2.replaceAll("A", Character.toString(table[i + 2][0]));
						
						if (ff_num == 2) {
							in2_bi = in2_bi.replaceAll("B", Character.toString(table[i + 2][1]));
							in2_bi = in2_bi.replaceAll("x", Character.toString(table[i + 2][2]));
							if (in_num == 2) {
								in2_bi = in2_bi.replaceAll("y", Character.toString(table[i + 2][3]));
							}
						} else {
							in2_bi = in2_bi.replaceAll("x", Character.toString(table[i + 2][1]));
							if (in_num == 2) {
								in2_bi = in2_bi.replaceAll("y", Character.toString(table[i + 2][2]));
							}
						}
						
						String compliment = mm.takeCompliment(in2_bi);
						
						int s = EvaluateString.evaluate(compliment) ;
						table[i + 2][curr_col] = (char) (s + '0');
					}
					curr_col++;
				}
				
				if (num_in > 2) {
					for (int i = 0; i < mint; i++) {
						in3_bi = in3.replaceAll("A", Character.toString(table[i + 2][0]));
						
						if (ff_num == 2) {
							in3_bi = in3_bi.replaceAll("B", Character.toString(table[i + 2][1]));
							in3_bi = in3_bi.replaceAll("x", Character.toString(table[i + 2][2]));
							if (in_num == 2) {
								in3_bi = in3_bi.replaceAll("y", Character.toString(table[i + 2][3]));
							}
						} else {
							in3_bi = in3_bi.replaceAll("x", Character.toString(table[i + 2][1]));
							if (in_num == 2) {
								in3_bi = in3_bi.replaceAll("y", Character.toString(table[i + 2][2]));
							}
						}
						
						String compliment = mm.takeCompliment(in3_bi);
						
						int s = EvaluateString.evaluate(compliment) ;
						table[i + 2][curr_col] = (char) (s + '0');
					}
					curr_col++;
					
					for (int i = 0; i < mint; i++) {
						in4_bi = in4.replaceAll("A", Character.toString(table[i + 2][0]));
						
						if (ff_num == 2) {
							in4_bi = in4_bi.replaceAll("B", Character.toString(table[i + 2][1]));
							in4_bi = in4_bi.replaceAll("x", Character.toString(table[i + 2][2]));
							if (in_num == 2) {
								in4_bi = in4_bi.replaceAll("y", Character.toString(table[i + 2][3]));
							}
						} else {
							in4_bi = in4_bi.replaceAll("x", Character.toString(table[i + 2][1]));
							if (in_num == 2) {
								in4_bi = in4_bi.replaceAll("y", Character.toString(table[i + 2][2]));
							}
						}
						
						String compliment = mm.takeCompliment(in4_bi);
						
						int s = EvaluateString.evaluate(compliment) ;
						table[i + 2][curr_col] = (char) (s + '0');
					}
					curr_col++;
				}
				
				if (out_num == 1) {
					for (int i = 0; i < mint; i++) {
						var_bi = var.replaceAll("A", Character.toString(table[i + 2][0]));
						
						if (ff_num == 2) {
							var_bi = var_bi.replaceAll("B", Character.toString(table[i + 2][1]));
							var_bi = var_bi.replaceAll("x", Character.toString(table[i + 2][2]));
							if (in_num == 2) {
								var_bi = var_bi.replaceAll("y", Character.toString(table[i + 2][3]));
							}
						} else {
							var_bi = var_bi.replaceAll("x", Character.toString(table[i + 2][1]));
							if (in_num == 2) {
								var_bi = var_bi.replaceAll("y", Character.toString(table[i + 2][2]));
							}
						}
						
						String compliment = mm.takeCompliment(var_bi);
						
						int s = EvaluateString.evaluate(compliment) ;
						table[i + 2][table_length - 1] = (char) (s + '0');
					}
				}
				
				// next state
				if (ff_uc.equals("D")) {
					for (int i = 0; i < mint; i++) {
						table[i + 2][curr_col] = table[i + 2][mint_digits];
					}
					curr_col++;
					
					if (ff_num > 1) {
						for (int i = 0; i < mint; i++) {
							table[i + 2][curr_col] = table[i + 2][mint_digits + 1];
						}
						curr_col++;	
					}
				} else if (ff_uc.equals("T")) {
					for (int i = 0; i < mint; i++) {
						if (table[i + 2][mint_digits] == '0') {
							table[i + 2][curr_col] = table[i + 2][0];
						} else {
							if (table[i + 2][0] == '1') {
								table[i + 2][curr_col] = '0';
							} else {
								table[i + 2][curr_col] = '1';
							}
						}
					}
					curr_col++;
					
					if (ff_num > 1) {
						for (int i = 0; i < mint; i++) {
							if (table[i + 2][mint_digits + 1] == '0') {
								table[i + 2][curr_col] = table[i + 2][1];
							} else {
								if (table[i + 2][1] == '1') {
									table[i + 2][curr_col] = '0';
								} else {
									table[i + 2][curr_col] = '1';
								}
							}
						}
						curr_col++;	
					}
				} else if (ff_uc.equals("JK")) {
					for (int i = 0; i < mint; i++) {
						if (table[i + 2][mint_digits] == '0' && table[i + 2][mint_digits + 1] == '0') {
							table[i + 2][curr_col] = table[i + 2][0];
						} else if (table[i + 2][mint_digits] == '0' && table[i + 2][mint_digits + 1] == '1') {
							table[i + 2][curr_col] = '0';
						} else if (table[i + 2][mint_digits] == '1' && table[i + 2][mint_digits + 1] == '0') {
							table[i + 2][curr_col] = '1';
						} else {
							if (table[i + 2][0] == '1') {
								table[i + 2][curr_col] = '0';
							} else {
								table[i + 2][curr_col] = '1';
							}
						}
					}
					curr_col++;
			
					if (ff_num > 1) {
						for (int i = 0; i < mint; i++) {
							if (table[i + 2][mint_digits + 2] == '0' && table[i + 2][mint_digits + 3] == '0') {
								table[i + 2][curr_col] = table[i + 2][1];
							} else if (table[i + 2][mint_digits + 2] == '0' && table[i + 2][mint_digits + 3] == '1') {
								table[i + 2][curr_col] = '0';
							} else if (table[i + 2][mint_digits + 2] == '1' && table[i + 2][mint_digits + 3] == '0') {
								table[i + 2][curr_col] = '1';
							} else {
								if (table[i + 2][1] == '1') {
									table[i + 2][curr_col] = '0';
								} else {
									table[i + 2][curr_col] = '1';
								}
							}
						}
						curr_col++;
					}
				} else {
					for (int i = 0; i < mint; i++) {
						if (table[i + 2][mint_digits] == '0' && table[i + 2][mint_digits + 1] == '0') {
							table[i + 2][curr_col] = table[i + 2][0];
						} else if (table[i + 2][mint_digits] == '0' && table[i + 2][mint_digits + 1] == '1') {
							table[i + 2][curr_col] = '0';
						} else if (table[i + 2][mint_digits] == '1' && table[i + 2][mint_digits + 1] == '0') {
							table[i + 2][curr_col] = '1';
						} else {
							table[i + 2][curr_col] = '?';
						}
					}
					curr_col++;
					
					if (ff_num > 1) {
						for (int i = 0; i < mint; i++) {
							if (table[i + 2][mint_digits + 2] == '0' && table[i + 2][mint_digits + 3] == '0') {
								table[i + 2][curr_col] = table[i + 2][1];
							} else if (table[i + 2][mint_digits + 2] == '0' && table[i + 2][mint_digits + 3] == '1') {
								table[i + 2][curr_col] = '0';
							} else if (table[i + 2][mint_digits + 2] == '1' && table[i + 2][mint_digits + 3] == '0') {
								table[i + 2][curr_col] = '1';
							} else {
								table[i + 2][curr_col] = '?';
							}
						}
						curr_col++;
					}
				}
				
				// print table
				mm.printTable(table);
				
			} else if (choice.equals("2")) {
				sc.close();
				break;
			} else {
				System.out.println("\nPlease input 1 or 2 only\n");
			}
		}
	}
}
