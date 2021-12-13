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
		if (data != null) {
			ArrayList<HashMap<String, String>> records = parseData(data);
			printData(sortData(records));
		}
	}

	
	/*
	* Returns the 3 hashmaps with the lowest division and points in order
	*/
	private static ArrayList<HashMap<String, String>> sortData(ArrayList<HashMap<String, String>> map) {
		
		ArrayList<HashMap<String, String>> sortedMap = new ArrayList<HashMap<String, String>>();
		
		int selectionNumber = 3;
		int div, points, lowestDiv, lowestPoints;
		HashMap<String, String> lowest = map.get(0);
		
		
		for (int i = 0; i < selectionNumber; i++) {
			lowestDiv = Integer.parseInt(map.get(0).get("division"));
			lowestPoints = Integer.parseInt(map.get(0).get("points"));
			
			for (int j = 0; j < map.size(); j++) {
				div = Integer.parseInt(map.get(j).get("division"));
				points = Integer.parseInt(map.get(j).get("points"));
				
				if (div < lowestDiv || (div == lowestDiv && points <= lowestPoints)) {
					lowest = map.get(j);
					lowestDiv = div;
					lowestPoints = points;
					
				}
			}
			sortedMap.add(lowest);
			map.remove(lowest);
		}
		
		return sortedMap;
	}
	
	
	/*
	 * Separates the CSV formatted String array and returns them as a hashmap
	 */
	private static ArrayList<HashMap<String, String>> parseData(ArrayList<String> data) {

		ArrayList<String> keys = parseKeys(data.get(0));
		data.remove(0);
		
		String word = "";
		String row;
		int k;
		char c;
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < data.size(); i++) {
			map.add(new HashMap<String, String>());
			k = 0;
			row = data.get(i);
			
			for (int j = 0; j < row.length(); j++) {
				c = row.charAt(j);
				
				switch(c) {
				case ',':
					if (k < keys.size()) {
						map.get(i).put(keys.get(k), word);
					}
					else {
						System.out.println("ERROR: Input \"" + word + "\" ignored, out of range at: line " + 
								(i + 2) + ", index " + k);
					}
					word = "";
					k++;
					break;
				case '"':
					break;
				default:
					word = word + c;
				}	
			}
			
			if (k < keys.size()) {
				map.get(i).put(keys.get(k), word);
			}
			else {
				System.out.println("ERROR: Input \"" + word + "\" ignored, out of range at: line " + 
						(i + 2) + ", index " + k);
			}
			word = "";
		}
		
		return map;
	}
	
	
	/*
	 * Separates the CSV formatted keys and returns them as a String array
	 */
	private static ArrayList<String> parseKeys(String keyText) {
		
		ArrayList<String> keys = new ArrayList<String>();
		String word = "";
		char c;
		for (int i = 0; i < keyText.length(); i++) {
			c = keyText.charAt(i);
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
		
		return keys;	
	}
	
	
	/*
	 * Prints records in YAML format
	 */
	private static void printData(ArrayList<HashMap<String, String>> records) {
		
		System.out.println("records:");
		for (int i = 0; i < records.size(); i++) {
			HashMap<String, String> person = records.get(i);
			System.out.println(" - name: " + 
					person.get("firstname") + " " + 
					person.get("lastname")
				);
			System.out.println("   details: In division " + 
					person.get("division") + " from " + 
					person.get("date") + " performing " +
					person.get("summary")
				);
		}
	}
	
	
	/*
	 * Reads given file and returns data as an array of Strings
	 */
	private static ArrayList<String> readFile(String filename) {
		
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