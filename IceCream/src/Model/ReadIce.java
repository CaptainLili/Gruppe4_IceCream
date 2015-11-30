package Model;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class ReadIce {

	String vColor, station, actual, variance, target, date;

	// float actual, variance, target;
	// String vColor, station ;
	// Calendar date = Calendar.getInstance();

	public static void main(String[] args) {

		BufferedReader br = null;
		String line = "";

		try {
			br = new BufferedReader(new FileReader("iceModel.csv"));
			while ((line = br.readLine()) != null) {
				System.out.println("Converted ArrayList data: " + converted(line) + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException brException) {
				brException.printStackTrace();
			}
		}
	}
	
	public static ArrayList<String> converted(String iceModelCSV) {
		
		ArrayList<String> result = new ArrayList<String>();
		if (iceModelCSV != null) {
			String[] splitData = iceModelCSV.split("\\s*,\\s*");
			for (int i = 0; i < splitData.length; i++) {
				if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
					result.add(splitData[i].trim());
				}
			}
		}
		//System.out.println(result.get(2));
		return result;
	}
	/*
	 * public void getModel(){ readCSV(iceModel); }
	 */
}
