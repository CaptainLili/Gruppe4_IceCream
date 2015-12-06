package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import View.simpleView;

public class ReadIce extends Observable {

	private static final String[] FILE_HEADER_MAPPING = { "station", "actual",
			"target", "variance", "date" };

	private static final String STATION_ID = "station";
	private static final String STATION_ACTUAL = "actual";
	private static final String STATION_TARGET = "target";
	private static final String STATION_VARIANCE = "variance";
	private static final String STATION_DATE = "date";

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

		// Create the CSVFormat object with the header mapping
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withHeader(FILE_HEADER_MAPPING);

		try {
			// initialize FileReader object
			fileReader = new FileReader(csv);

			// initialize CSVParser object
			csvFileParser = new CSVParser(fileReader, csvFileFormat);

			// Get a list of CSV file records
			List<CSVRecord> csvRecords = csvFileParser.getRecords();

			// Read the CSV file records starting from the first
			for (int i = 0; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				// Create a new station object and fill his data
				iceobject = new IceObject(record.get(STATION_ID),
						record.get(STATION_ACTUAL), record.get(STATION_TARGET),
						record.get(STATION_VARIANCE),
						record.get(STATION_DATE));
				stations.add(iceobject);
			}
			// create ListModel for JList
			for (IceObject iceobject : stations) {
				listModel.addElement(iceobject.toString());
			}
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out
						.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
		getListModel();
	}

	// method to export new data to .csv
	public void writeCsvFile(IceObject iceobject) {

		try {
			FileWriter writer = new FileWriter(csv, true);
			writer.write("\n");
			writer.write(iceobject.getStation() + ",");
			writer.write(iceobject.getActual() + ",");
			writer.write(iceobject.getTarget() + ",");
			writer.write(iceobject.getVariance() + ",");
			writer.write(iceobject.getDate());
			writer.flush();
			writer.close();
			listModel.addElement(iceobject);
		} catch (IOException fne) {
			System.out.println("Error file not found!");
			fne.printStackTrace();
		}
		System.out.println("Data written to file!");
	}

	// method to export new data to .csv
	public void updateCsvFile(IceObject iceobject) {

		try {
			FileWriter writer = new FileWriter(csv, true);
			writer.write("\n");
			writer.write(iceobject.getStation() + ",");
			writer.write(iceobject.getActual() + ",");
			writer.write(iceobject.getTarget() + ",");
			writer.write(iceobject.getVariance() + ",");
			writer.write(iceobject.getDate());
			writer.flush();
			writer.close();
			listModel.addElement(iceobject);
			
			//sreadCsvFile(null);
			/*
			for (String str : newDataSet) {
				writer.write("\n" + str);
			}
			writer.flush();
			writer.close();*/
		} catch (IOException fne) {
			System.out.println("Error file not found!");
			fne.printStackTrace();
		}
		System.out.println("Data updated!");

	}

	public static ListModel getListModel() {
		return listModel;
	}

	public void changeIceObject(IceObject iceobject) {

		setChanged();
		notifyObservers(iceobject);
	}

}