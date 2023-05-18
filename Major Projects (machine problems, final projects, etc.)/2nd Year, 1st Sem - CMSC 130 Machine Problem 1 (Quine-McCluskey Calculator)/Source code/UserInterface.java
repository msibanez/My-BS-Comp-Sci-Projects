import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.lang.Integer;

public class UserInterface {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		MoreMethods mm = new MoreMethods();
		
		String choice;
		
		String comma_var;
		String comma_mint;
		char[] var;
		int[] mint_dup;
		int[] mint;
		int[][] mint_group;
		
		while(true) {
			boolean end = false;
			System.out.print("** Quine-McCluskey Calculator **\n1 - calculate\n2 - exit\n>");
			choice = sc.nextLine();
			if (choice.equals("1")) {
				
				int var_count;
				
				while(true) {
					try {
						System.out.print("\nInput your variables separated by a comma (,).\n>");					
						comma_var = sc.nextLine();
						
						String[] values = comma_var.split(",");
						
						var_count = values.length;
						var = new char[var_count];
						
						for (int i = 0; i < var_count; i++) {
							var[i] = values[i].charAt(0);
						}
						if (var_count > 26) {
							System.out.println("\n** Only 1-26 variables are allowed. **");
						} else {
							break;
						}
					} catch (Exception e){
						System.out.println("\n** Please don't put the commas beside each other. **");
					}
				}
				
				int base_two = (int) Math.pow(2, var_count);
				int base_two_dec = base_two - 1;
				
				int mint_count;
				
				String var_display = Arrays.toString(var).replace("[", "(").replace("]", ")").replace(" ", "");
				
				while(true) {
					System.out.print("Input minterms that evaluate to 1 separated by a comma.\n>");
					comma_mint = sc.nextLine();
					boolean notInt = false;
					
					String[] values2 = comma_mint.split(",");
					
					mint_count = values2.length;
					mint_dup = new int[mint_count];
					mint = new int[mint_count];
					int max = 0;
					
					try {
						for (int i = 0; i < mint_count; i++) {
							mint_dup[i] = Math.abs(Integer.parseInt(values2[i]));
				            if (mint_dup[i] > max) {
				            	max = mint_dup[i];
				            }
						}
						
						Arrays.sort(mint_dup);
						mint_count = mm.removeDuplicate(mint_dup, mint_count);
						mint = new int[mint_count];
						for (int i = 0; i < mint_count; i++) {
							mint[i] = mint_dup[i];
						}
					} catch (Exception e) {
						notInt = true;
					}
					
					if (notInt) {
						System.out.println("\n** Invalid input. At least one minterm is not an integer. **\n");
					} else if(mint.length > base_two && max >= base_two) {
						System.out.println("\n** Invalid input. Minterm count and at least one minterm is greater than " + base_two + " and " + base_two_dec + ", respectively." + " **\n");
					} else if (mint.length > base_two) {
						System.out.println("\n** Invalid input. Minterm count is greater than " + base_two + " **\n");
					} else if (max >= base_two) {
						System.out.println("\n** Invalid input. At least one minterm is greater than " + base_two_dec + " **\n");
					} else {
						break;
					}
				}

				System.out.println("\nYour inputted variables: " + Arrays.toString(var));
				System.out.println("Your inputted minterms: " + Arrays.toString(mint) + "\n");
				
				if (mint_count == base_two) {
					
					System.out.println("F" + var_display + " = 1\n");
					end = true;
					
				} else { // start of else
				
				int[][] table = new int[mint_count][var_count];
				
				// binary converter
				for (int i = 0; i < table.length; i++) {
					String binary = Integer.toBinaryString(mint[i]);
					String withLeadingZeros = String.format("%" + var_count + "s", binary).replace(' ', '0');
					for (int j = 0; j < table[i].length; j++) {
						
						char bin = withLeadingZeros.charAt(j);
						String ben = Character.toString(bin);
						table[i][j] = Integer.parseInt(ben);
//						System.out.print(table[i][j]);
					}
//					System.out.println("");
				}
				
				int var_pow = (int) Math.pow(2, var_count); 
				
				int[][] group = new int[(int) Math.pow(var_pow, 2)][var_count + 1];
				mint_group = new int[(int) Math.pow(var_pow, 2)][var_pow + 1];
				
				for (int i = 0; i < mint_group.length; i++) {
					for (int j = 0; j < mint_group[0].length;j ++) {
						mint_group[i][j] = -2;
					}
				}
				
				int occur = 0;
				int occur_group = 0;
				int row_no = 0;		// determines the latest row in the group
				int first_group = 0;
				
				// making first group arranged by the number of 1's.
				while(row_no < mint_count) {
					boolean present = false;
					for (int i = 0; i < table.length; i++) {
						if(occur == mm.countOccurrences(table[i], var_count, 1)) {
							for(int j = 0; j < table[i].length; j++) {
								group[row_no][j] = table[i][j];
							}
							mint_group[row_no][1] = mint[i]; 
							present = true;
							row_no++;
						}
					}
					occur++;
					occur_group = occur;
					if(present) {
						first_group++;
					}
				}
				
//				for(int i = 0; i < table.length; i++) {
//					for (int j = 0; j < table[i].length; j++) {
//						System.out.print(group[i][j]);
//					}
//					System.out.println("");
//				}
				
				occur = 0;

//				System.out.println("There is a maximum of " + occur_group + " occurences");
//				System.out.println("There are " + first_group + " groups.");
				
				// comparing first pairs of numbers
				while(occur < occur_group + 1) {
					for(int i = 0; i < table.length; i++) {
						if (occur == mm.countOccurrences(group[i], var_count, 1)) {
							for(int j = 0; j < table.length; j++) {
								if (occur + 1 == mm.countOccurrences(group[j], var_count, 1)) {
									int num1 = mint_group[i][1];
									int num2 = mint_group[j][1];
									
									int diff = Math.abs(num1 - num2);
									double log_diff = (Math.log(diff) / Math.log(2));
									if (log_diff % 1 == 0 && num2 > num1) {
										group[i][var_count] = 9;
										group[j][var_count] = 9;
										mint_group[i][0] = -1;
										mint_group[j][0] = -1;
										
										mint_group[row_no][1] = num1;
										mint_group[row_no][2] = num2;
										
//										System.out.println("Num pair (" + num1 + ", " + num2 + ")");
										for(int k = 0 ; k < var_count; k++) {
											group[row_no][k] = group[i][k];
										}
										group[row_no][var_count - (int)log_diff - 1] = 8;
										row_no++;
									}
								}
							}
						}
					}
					occur++;
				}
				first_group--;
				
//				System.out.println("When compared");
//				for(int i = 0; i < (table.length) + 20; i++) {
//					for (int j = 0; j < table[0].length + 1; j++) {
//						System.out.print(group[i][j]);
//					}
//					System.out.println("");
//				}
				
//				for(int i = 0; i < mint_group.length; i++) {
//					for (int j = 0; j < mint_group[0].length; j++) {
//						System.out.print(mint_group[i][j]);
//					}
//					System.out.println("");
//				}
				occur_group--;
				occur = 0;
				
				int pair_gr_len = row_no - mint_count;
				
				int power = 0;
				int n = 1;
				int occur_pow = 0;
				
				int group_length = mint_count;
				int g;
				int[] pow_2;
				
				// continuous comparing
				while(first_group > 1) {
					
					occur = 0;
					occur_pow = 0;
					g = 0;
					pow_2 = new int[mint_count];
					
					power = (int) Math.pow(2, n);
					while(occur < occur_group + 1) {
						for(int i = 0; i < pair_gr_len; i++) {
							if (occur == mm.countOccurrences(group[i + group_length], var_count, 1)) {
								for(int j = 0; j < pair_gr_len; j++) {
									if (occur + 1 == mm.countOccurrences(group[j + group_length], var_count, 1)) {
										
										for (int k = 0; k < pow_2.length; k++) {
											pow_2[k] = -1;
										}
										
										int diff = 0;
										double log_diff = 0;
										for (int k = 1; k <= power; k++) {
											int num1 = mint_group[i + group_length][k];
											int num2 = mint_group[j + group_length][k];
											diff = Math.abs(num1 - num2);
											log_diff = (Math.log(diff) / Math.log(2));
											
//											System.out.println(num1 + " - " + num2 + " = " + diff);
											if (log_diff % 1 == 0 && num2 > num1) {
												pow_2[k - 1] = diff;
											}
										}
										occur_pow = mm.countOccurrences(pow_2, power, diff);
//										System.out.println("This is the pow_2 array: " + Arrays.toString(pow_2));
										if (occur_pow == power) {
//											System.out.println("*** They're equal! ***");
											
											group[i + group_length][var_count] = 9;
											group[j + group_length][var_count] = 9;
											mint_group[i + group_length][0] = -1;
											mint_group[j + group_length][0] = -1;
											
											for(int m = 1; m <= power; m++) {
												mint_group[row_no][m] = mint_group[i + group_length][m];
												mint_group[row_no][m + power] = mint_group[j + group_length][m];
											}
											
											for(int l = 0 ; l < var_count; l++) {
												group[row_no][l] = group[i + group_length][l];
											}
											group[row_no][var_count - (int)log_diff - 1] = 8;
											row_no++;
											g++;
										}
										
									}
								}
							}
						}
						occur++;
					}
					n++;
					occur_group--;
					
					pair_gr_len = g;
//					System.out.println(pair_gr_len);
					group_length = row_no - g;
//					System.out.println(group_length);
					
//					System.out.println("There are " + first_group + " groups left.");
					first_group--;
				}
				
//				System.out.println("When compared:");
//				for(int i = 0; i < (table.length) + 60; i++) {
//					for (int j = 0; j < table[0].length + 1; j++) {
//						System.out.print(group[i][j]);
//					}
//					System.out.println("");
//				}
//				
//				for(int i = 0; i < mint_group.length; i++) {
//					for (int j = 0; j < mint_group[0].length; j++) {
//						System.out.print(mint_group[i][j]);
//					}
//					System.out.println("");
//				}
				
				// next part: prime implicant chart
				
				int inc = 0;
				
				int[][] group_p = new int[((int) Math.pow(3, var_count))][var_count];				
				int[][] mint_group_p = new int[(int) Math.pow(3, var_count)][var_pow];
				
				int[] array = new int[var_count];
				
				for(int i = 0; i < row_no; i++) {
					boolean dup = false;
					if (group[i][var_count] != 9) {
						for(int j = 0; j < var_count; j++) {
							array[j] = group[i][j]; 
						}
						
						if (inc > 0) {
							for(int j = 1; j <= inc; j++) {
								if(Arrays.equals(array, group_p[inc - j])){
									dup = true;
								}
							}
							
							if (!dup) {
								for(int j = 0; j < var_count; j++) {
									group_p[inc][j] = group[i][j];
								}
								for(int j = 1; j <= var_pow; j++) {
									mint_group_p[inc][j - 1] = mint_group[i][j];
								}
								inc++;
							}
						} else {
							for(int j = 0; j < var_count; j++) {
								group_p[inc][j] = group[i][j];
							}
							for(int j = 1; j <= var_pow; j++) {
								mint_group_p[inc][j - 1] = mint_group[i][j];
							}
							inc++;
						}
					}
				}
				
//				System.out.println("There are " + inc + " values.");
//				
//				for(int i = 0; i < inc; i++) {
//					for (int j = 0; j < group_p[0].length; j++) {
//						System.out.print(group_p[i][j]);
//					}
//					System.out.println("");
//				}
//				
//				for(int i = 0; i < inc; i++) {
//					for (int j = 0; j < mint_group_p[0].length; j++) {
//						System.out.print(mint_group_p[i][j]);
//					}
//					System.out.println("");
//				}
				
				// making prime implicant chart
				int p_row = inc + 2;
				int[][] prime_chart = new int[p_row][mint_count + 2];
				
				for(int i = 0; i < prime_chart.length; i++) {
					for(int j = 0; j < prime_chart[0].length; j++) {
						prime_chart[i][j] = 0;
					}
				}
				
				for(int i = 1; i < mint_count + 1; i++) {
					prime_chart[0][i] = mint[i - 1];
				}
				
				for (int i = 1; i < inc + 1; i++) {
					int col = 0;
					prime_chart[i][0] = i - 1;
		
					while(mint_group_p[i - 1][col] != -2) {
						for (int j = 1; j < mint_count + 1; j++) {
							if(mint_group_p[i - 1][col] == mint[j - 1]) {
								prime_chart[i][j] = 7;
							}
						}
						col++;
					}
				}
			
				// check each column
				
				int row_loc = 0;
				int getMarked = 0;
				int[][] marked = new int[inc][var_count];
				
				for(int i = 1; i < mint_count + 1; i++) {
					int count = 0;
					int col = 0;
					for(int j = 1; j < inc + 1; j++) {
						if(prime_chart[j][i] == 7) {
							count++;
							row_loc = j;
						}
					}
					if (count == 1) {
						prime_chart[inc + 1][i] = 5;
						while(mint_group_p[row_loc - 1][col] != -2) {
							for (int j = 1; j < mint_count + 1; j++) {
								if(mint_group_p[row_loc - 1][col] == mint[j - 1]) {
									prime_chart[inc + 1][j] = 5;
									prime_chart[row_loc][mint_count + 1] = 8;
								}
							}
							col++;
						}
					}
				}
				
//				// extracted implicants
//				for(int i = 1; i < inc + 1; i++) {
//					if (prime_chart[i][mint_count + 1] == 8) {
//						for(int k = 0; k < var_count; k++) {
//							marked[getMarked][k] = group_p[i - 1][k];
//						}
//						getMarked++;
//					}
//				}

//				for(int i = 0; i < prime_chart.length; i++) {
//					for(int j = 0; j < prime_chart[0].length; j++) {
//						System.out.print(prime_chart[i][j] + " ");
//					}
//					System.out.println("");
//				}
//				
//				for(int i = 0; i < marked.length; i++) {
//					for(int j = 0; j < marked[0].length; j++) {
//						System.out.print(marked[i][j] + " ");
//					}
//					System.out.println("");
//				}
				
				// check remaining implicants
				int count_check = 0;
				for(int i = 1; i < mint_count + 1; i++) {
					if (prime_chart[inc + 1][i] != 5) {
						count_check++;
					}
				}
				
				boolean done = false;
				
				if (count_check == 1) {
					for(int i = 1; i < inc + 1; i++) {
						if (prime_chart[i][mint_count + 1] != 8) {
							for(int j = 1; j < mint_count + 1; j++) {
								if (prime_chart[inc + 1][j] != 5) {
									prime_chart[i][mint_count + 1] = 8;
									done = true;
								}
							}
						}
						if (done) {
							break;
						}
					}
				} else if (count_check > 1) {
					for(int i = 1; i < inc + 1; i++) {
						int count = 0;
						int col = 0;
						int length = 0;
						if (prime_chart[i][mint_count + 1] != 8) {
							for(int j = 1; j < mint_count + 1; j++) {
								if (prime_chart[inc + 1][j] != 5) {
									if (prime_chart[i][j] == 7) {
										count++;
									}
								}
							}
							while(mint_group_p[i - 1][col] != -2) {
								length++;
								col++;
							}
							col = 0;
							if (count >= length) {
								prime_chart[i][mint_count + 1] = 8;
								while(mint_group_p[i - 1][col] != -2) {
									for (int j = 1; j < mint_count + 1; j++) {
										if(mint_group_p[i - 1][col] == mint[j - 1]) {
											prime_chart[inc + 1][j] = 5;
										}
									}
									length++;
									col++;
								}
							}
						}
					}
				}

				for(int i = 1; i < inc + 1; i++) {
					if (prime_chart[i][mint_count + 1] == 8) {
						for(int k = 0; k < var_count; k++) {
							marked[getMarked][k] = group_p[i - 1][k];
						}
						getMarked++;
					}
				}
//			
//				for(int i = 0; i < prime_chart.length; i++) {
//					for(int j = 0; j < prime_chart[0].length; j++) {
//						System.out.print(prime_chart[i][j] + " ");
//					}
//					System.out.println("");
//				}
//				
//				for(int i = 0; i < marked.length; i++) {
//					for(int j = 0; j < marked[0].length; j++) {
//						System.out.print(marked[i][j] + " ");
//					}
//					System.out.println("");
//				}
			
				System.out.println("Simplified Expression:");
				System.out.print("F" + var_display + " = ");
				
				for (int i = 0; i < getMarked; i++) {
					for (int j = 0; j < var_count; j++) {
						if (marked[i][j] == 0) {
							System.out.print(var[j] + "'");
						} else if (marked[i][j] == 1) {
							System.out.print(var[j]);
						}
					}
					if (i < getMarked - 1) {
						System.out.print(" + ");
					}
				}
				System.out.println("\n");
				
				end = true;
				
				} // end of else
				
			} else if (choice.equals("2")) {
				sc.close();
				System.out.println("\n** Thanks for using! **");
				System.exit(0);
			} else {
				System.out.println("\n** Please input from 1-2 only. **\n");
			}
			
			if(end) {
//				System.out.println("\n*** ** END OF CALCULATION ** ***\n");
			}
		}
		
	}

}