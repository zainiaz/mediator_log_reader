package logthingy;

import java.util.Arrays;
import java.util.LinkedList;

public class Logs{
	private Hashtable<String, LinkedList<String>> line;
	
	public Logs(){
		line = new Hashtable<>();
	}
	
	public void Add_Line(String date, String pack, String low, String high, String partition){
		line.put(date, new Linkedlist<String>(Array.asList(pack, low, high, partition)) );
		
	}
	
}