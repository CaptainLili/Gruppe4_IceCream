package Model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class ReadIce {

	private static final String [] FILE_HEADER_MAPPING = {"station","actual","date","target","variance"};
	
	private static final String STATION_ID = "station";
	private static final String STATION_ACTUAL = "actual";
	private static final String STATION_DATE = "date";
	private static final String STATION_TARGET = "target";
	private static final String STATION_VARIANCE = "variance";
	
	public static void main(String[] args) {

		FileReader fileReader = null;		
		CSVParser csvFileParser = null;
		
		//Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
     
        try {
        	
        	//Create a new list of student to be filled by CSV file data 
        	List<IceObject> stations = new ArrayList<IceObject>();
            
            //initialize FileReader object
            fileReader = new FileReader("filedata.csv");
            
            //initialize CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            
            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords(); 
            
            //Read the CSV file records starting from the second record to skip the header
            for (int i = 0; i < csvRecords.size(); i++) {
            	CSVRecord record = csvRecords.get(i);
            	//Create a new student object and fill his data
            	IceObject iceobject = new IceObject(record.get(STATION_ID), record.get(STATION_ACTUAL), record.get(STATION_DATE), 
            			record.get(STATION_TARGET), record.get(STATION_VARIANCE));
                stations.add(iceobject);	
			}
            
            //Print the new student list
            for (IceObject iceobject : stations) {
				System.out.println(iceobject.toString());
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

	}
	
}
