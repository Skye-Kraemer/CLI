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
		String data = readFile(args[0]);
	}
	
	
	/*
	 * Prints data in YAML format
	 */
	public static void printData() {
		
	}
	
	
	/*
	 * Separates the data in a comma separated string into a hashmap and returns the hashmap
	 */
	public static ArrayList<HashMap<String, String>> parseData() {
		return null;
	}
	
	
	/*
	 * Reads given file and returns data as a string
	 */
	public static String readFile(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			try {
				StringBuilder stringBuilder = new StringBuilder();
				String line = reader.readLine();

				while (line != null) {
					stringBuilder.append(line);
					stringBuilder.append("\n");
					line = reader.readLine();
				}
				return stringBuilder.toString();
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