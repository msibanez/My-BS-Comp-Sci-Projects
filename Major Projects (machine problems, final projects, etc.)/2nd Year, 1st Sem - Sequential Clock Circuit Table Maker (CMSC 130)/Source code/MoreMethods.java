
public class MoreMethods {

	public void printTable(char[][] table) {
		System.out.println("");
		
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[0].length; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public String takeCompliment(String s) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < s.length(); i++) {
			if (i != s.length() - 1) {
				if (s.charAt(i + 1) == 39) {
					if (s.charAt(i) == '0') {
						sb.append('1');
					} else {
						sb.append('0');
					}
					i++;
				} else {
					sb.append(s.charAt(i));
				}
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
	}
	
}
