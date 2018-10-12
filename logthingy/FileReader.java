package logthingy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileReader{
	
	public static String ReadLogs(String fn)
	{
		Path fp = Paths.get(fn);
		String str;
		try
		{
			byte[] byte_arr = Files.readAllBytes(fp);

			System.out.println("Reading successfull\n");
			
			str = new String(byte_arr);

			//System.out.println(str);

			return str;
	
		} catch (IOException e)
			{
				e.printStackTrace();
				return null;
			}
	}
}
