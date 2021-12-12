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
		parseData(data);
	}
	
	
	/*
	 * Prints data in YAML format
	 */
	public static void printData() {
		
	}
	
	
	/*
	 * Separates the data in a comma separated string into a hashmap and returns the hashmap
	 */
	public static ArrayList<HashMap<String, String>> parseData(ArrayList<String> data) {
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
		printArray(keys);
		
		for (int i = 1; i < data.size(); i++) {
			c =  data.get(i).charAt(i);
			
		}
		
		return null;
	}
	
	//testing function
	public static void printArray(ArrayList<String> array) {
		for (int i = 0; i < array.size(); i++) {
			System.out.print(array.get(i) + ", ");
		}
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