package CLI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CLI {
	
	
	/*
	* Accepts a CSV file and outputs the contents in YAML format to the console
	*/
	public static void main(String[] args) {
		ArrayList<String> data = readFile(args[0]);
		ArrayList<HashMap<String, String>> records = parseData(data);
		printData(records);
	}
	
	
	/*
	 * Prints data in YAML format
	 */
	public static void printData(ArrayList<HashMap<String, String>> records) {
		
		System.out.println("records:");
		for (int i = 0; i < records.size(); i++) {
			HashMap<String, String> person = records.get(i);
			System.out.println(" -name: " + person.get("firstname") + " " + person.get("lastname"));
			System.out.println("details: In division " + person.get("division") + " from " + person.get("date") + 
					" performing " + person.get("summary"));
		}
	}
	
	
	/*
	 * Separates the data in a comma separated string into a hashmap and returns the hashmap
	 */
	public static ArrayList<HashMap<String, String>> parseData(ArrayList<String> data) {
		String word = "";
		char c;
		
		ArrayList<String> keys = new ArrayList<String>();
	
		for (int i = 0; i < data.get(0).length(); i++) {
			c =  data.get(0).charAt(i);
			switch(c) {
			case ',':
				keys.add(word);
				word = "";
				break;
			default:
				word = word + c;
			}
		}
		keys.add(word);
		word = "";
		
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		int k;
		for (int i = 1; i < data.size(); i++) {
			map.add(new HashMap<String, String>());
			k = 0;
			
			for (int j = 0; j < data.get(i).length(); j++) {
				c = data.get(i).charAt(j);
				
				switch(c) {
				case ',':
					map.get(i - 1).put(keys.get(k), word);
					word = "";
					k++;
					break;
				case '"':
					break;
				default:
					word = word + c;
				}	
			}
			
			map.get(i - 1).put(keys.get(k), word);
			word = "";
			
		}
		
		return map;
	}
	
	
	/*
	 * Reads given file and returns data as an array of Strings
	 */
	public static ArrayList<String> readFile(String filename) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			try {
				ArrayList<String> data =  new ArrayList<String>();
				String line = reader.readLine();
				while (line != null) {
					data.add(line);
					line = reader.readLine();
				}
				return data;
			} 
			finally {
				reader.close();
			}
		}
		catch (IOException e) {
			System.out.println("ERROR: Failed to read: " + filename);
			return null;
		}
		
	}
}