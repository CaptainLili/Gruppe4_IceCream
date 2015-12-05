package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import View.simpleView;
import au.com.bytecode.opencsv.CSVWriter;


public class ReadIce extends Observable {

	private static final String [] FILE_HEADER_MAPPING = {"station","actual","date","target","variance"};
	
	private static final String STATION_ID = "station";
	private static final String STATION_ACTUAL = "actual";
	private static final String STATION_DATE = "date";
	private static final String STATION_TARGET = "target";
	private static final String STATION_VARIANCE = "variance";
	
	public static String csv = "iceModel.csv";
	public static DefaultListModel listModel = new DefaultListModel();
	public static List<IceObject> stations = new ArrayList<IceObject>();
	public static List<String> newDataSet = simpleView.getNewDataSet();
	
	static IceObject iceobject;
	
	public ReadIce() {
		iceobject = IceObject.getInstance();

	}
	
	public static void readCsvFile(String[] args) {

		FileReader fileReader = null;		
		CSVParser csvFileParser = null;
		
		//Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
     
        try {
        	
        	//Create a new list of stations to be filled by CSV file data 
        	//List<IceObject> stations = new ArrayList<IceObject>();
            
            //initialize FileReader object
            fileReader = new FileReader(csv);
            
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            
            //Read the CSV file records starting from the first
            for (int i = 0; i < csvRecords.size(); i++) {
            	CSVRecord record = csvRecords.get(i);
            	//Create a new station object and fill his data
            	iceobject = new IceObject(record.get(STATION_ID), record.get(STATION_ACTUAL), record.get(STATION_DATE), 
            			record.get(STATION_TARGET), record.get(STATION_VARIANCE));
                stations.add(iceobject);
			}
            
            //Print the new student list
            /*for (IceObject iceobject : stations) {
				System.out.println(iceobject.toString());
			}*/
            //DefaultListModel listModel = new DefaultListModel();
            for (IceObject iceobject : stations) {
            	listModel.addElement(iceobject.toString());
            	//System.out.println(iceobject.toString());
            }
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        getListModel();
	}
	
	// method to export new data to .csv
	public void writeCsvFile(IceObject iceobject )  {
	
		try {
			FileWriter writer = new FileWriter(csv, true);
			/*for(String str: newDataSet) {
				  writer.write("\n" + str);
			}*/
			writer.write(iceobject.getStation() + ",");
			writer.write(iceobject.getActual() + ",");
			writer.write(iceobject.getTarget() + ",");
			writer.write(iceobject.getVariance() + ",");
			writer.write(iceobject.getDate() + ",");
			writer.flush();
			writer.close();
			
		} catch (IOException fne) {
			System.out.println("Error file not found!");
            fne.printStackTrace();
		}
		System.out.println("Data written to file!");
		
	}
	
	// method to export new data to .csv
	public void updateCsvFile(IceObject iceobject)  {
		
		try {
			FileWriter writer = new FileWriter(csv, true);
			for(String str: newDataSet) {
				  writer.write("\n" + str);
			}
			writer.flush();
			writer.close();
		} catch (IOException fne) {
			System.out.println("Error file not found!");
	           fne.printStackTrace();
		}
		System.out.println("Data updated!");
		
	}
	
	public static ListModel getListModel() {
		return listModel;
	}
	
	public void changeIceObject(IceObject iceobject){
		
		setChanged();
		notifyObservers(iceobject);
		
	}
	
}