package Model;

import java.util.Calendar;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.csv.CSVParser;


import java.io.*;

public class ReadIce {
		float actual, variance, target;
		String vColor, station;
		Calendar date  = Calendar.getInstance();
		File iceModel;
		
		public void readCSV(File iceModel){
			CSVParser csvParser = new CSVParser( new FileInputStream("iceModel.csv") );
			for ( String t; (t = csvParser.nextValue()) != null; )
			  System.out.println( csvParser.lastLineNumber() + " " + t );
			
			
			
		}
		public void writeCSV(String[] entries){
		  CSVWriter writer = new CSVWriter(new FileWriter("iceModel.csv"), '\t');
		     // feed in your array (or convert your data to an array)
		     entries = "first#second#third".split("#");
		     writer.writeNext(entries);
			 writer.close();
		
		}
		
		public void getModel(){
			readCSV(iceModel);
		}
		
		
		
		

	}


