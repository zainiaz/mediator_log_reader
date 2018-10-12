package logthingy;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MainParser{
	private static String sp_line = "(\\d){4}\\-(\\d){2}\\-(\\d){2}" +
		" (\\d){2}:(\\d){2}:(\\d){2},(\\d){3}.*\\[.*\\].*Rows *\\[\\d*\\-\\d*\\].*\\[.*\\]";
	private static String attrname = "\\[.+\\]";


	public static String[][] Parse_Lines(String string_line){
		String[][] result;
		LinkedList<String> s_list = new LinkedList<String>();//List of raw matched string
		int nummatches = 0; //Number of total matche in the log file	
		StringTokenizer tokens;

		Pattern pattern_line = Pattern.compile(sp_line);
		
		Matcher l = pattern_line.matcher(string_line);	

		while( l.find() ){
			s_list.add(l.group(0));
			++nummatches;
		}

		if(nummatches > 0){
			result = new String[nummatches][4]; //Init array to return
			String current;
			String current_token;

			for(int i = 0; i < nummatches; i++){
				current = s_list.get(i);
				result[i][0] = current.substring(0, 23).replaceAll(",", ".");
				current = current.substring(23, current.length());
				tokens = new StringTokenizer(current);
				boolean isPack = false;
				boolean isRange = false;
				boolean isPartition = false;

				while(tokens.hasMoreTokens() && (!isPack || !isRange || !isPartition) ){
					current_token = tokens.nextToken();
					pattern_line = Pattern.compile(attrname);
					l = pattern_line.matcher(current_token);

					while( l.find() ){
						if(!isPack){
							result[i][1] = current_token.substring(1, current_token.length() - 1);
							isPack = true;
						}
						else if (!isRange){
							result[i][2] = current_token.substring(1, current_token.length() - 1);
							isRange = true;
						}
						else if(!isPartition){
							result[i][3] = current_token.substring(1, current_token.length() - 1);
							isPartition = true;
						}
						else break;
					}
				}
			}

			System.out.println(result[0][0]);
			System.out.println(result[0][1]);	
			System.out.println(result[0][2]);
			System.out.println(result[0][3]);

			return result;
		}

		return null;
	}
}
