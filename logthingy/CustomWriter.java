package logthingy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CustomWriter{

	public static boolean CreateCSV(String[][] l){
		String file_name;
		BufferedWriter buffet_writer = null;
		FileWriter file_writer = null;

		DateFormat dformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		file_name =  dformat.format(date).replaceAll("/", "-");
		file_name = file_name.replaceAll(":", "_");

		file_name = System.getProperty("user.dir") + file_name.replaceAll(" ", "_") + ".csv";

		try{
			file_writer = new FileWriter(file_name);

			buffet_writer = new BufferedWriter(file_writer);

			for(String[] current : l){
				buffet_writer.append(current[0] + ",");
				buffet_writer.append(current[1] + ",");
				buffet_writer.append(current[2].substring(0, current[2].indexOf("-")) + ",");
				buffet_writer.append(current[2].substring(current[2].indexOf("-") + 1, current[2].length()) + ",");
				buffet_writer.append(current[3] + "\n");
			}

			buffet_writer.close();
			file_writer.close();

			return true;

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}


	}
}
