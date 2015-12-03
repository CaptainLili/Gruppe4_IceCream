package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.IceController;
import Model.ReadIce;

public class simpleView {

	public static ListModel dataSet = ReadIce.getListModel();
	public static String chosenDataSet;
	public static Color textColor;
	public static JTextField station;
	public static JTextField dateEntry;
	public static JTextField targetEntry;
	public static JTextField actualEntry;
	public static JTextField varianceEntry;
	public static JTextField updateActualEntry;
	public static JList list;
	public static List<String> addDataSet = new ArrayList<String>();
	
	private static String STATION_ID = "";
	private static String STATION_ACTUAL = "";
	private static String STATION_DATE = "";
	private static String STATION_TARGET = "";
	private static String STATION_VARIANCE = "";
	
	//private final IceController controller;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startGUI();
			}
		});
	}
	
	public static void startGUI() {
		
		// creates the JFrame(a window with decorations)
		final JFrame frame = new JFrame("IceCream Records");
		final JFrame frameAddDia = new JFrame("Add data");
		final JPanel addTextarea = new JPanel(new GridLayout(5, 2));
		final JFrame frameUpdateAc = new JFrame("Update actual state");
		final JPanel addActualarea = new JPanel(new GridLayout(1, 2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		// define layout main frame
		JPanel label = new JPanel(new GridLayout(2, 0));
		JPanel buttonarea = new JPanel();
		JPanel content = new JPanel(new GridLayout(3, 0)); 
		JPanel splitPane = new JPanel(new GridLayout(0, 2, 20, 10));
		JPanel listarea = new JPanel(new GridLayout(1, 0));
		JPanel textarea = new JPanel(new GridLayout(5, 2, 2, 2)); 
		JPanel actualarea = new JPanel(new GridLayout(5, 2, 2, 2));
		content.add(label);
		content.add(buttonarea);
		content.add(splitPane);
		
		// Label-->Title
		JLabel title = new JLabel("Ice-Cream air-particle concentration.", JLabel.CENTER);
		label.add(title);
		
		// buttonarea-->AddButton
		JButton addButton = new JButton("Add new station");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// define layout dialog
				final JPanel addDialog = new JPanel(new GridLayout(1, 2));
				// StationID
				JLabel addStationID = new JLabel("StationID");
				addTextarea.add(addStationID);
				JTextField addStation = new JTextField();
				addTextarea.add(addStation);
				addDialog.add(addTextarea);
				
				// Date
				JLabel addDate = new JLabel("Date");
				addTextarea.add(addDate);
				JTextField addDateRecord = new JTextField();
				addTextarea.add(addDateRecord);
				addDialog.add(addTextarea);
				
				// Target
				JLabel addTarget = new JLabel("Target");
				addTextarea.add(addTarget);
				JTextField addTargetRecord = new JTextField();
				addTextarea.add(addTargetRecord);
				addDialog.add(addTextarea);
				
				// Actual
				JLabel addActual = new JLabel("Actual");
				addTextarea.add(addActual);
				JTextField addActualRecord = new JTextField();
				addTextarea.add(addActualRecord);
				addDialog.add(addTextarea);
				
				// Variance
				JLabel addVariance = new JLabel("Variance");
				addTextarea.add(addVariance);
				JTextField addVarianceRecord = new JTextField();
				addTextarea.add(addVarianceRecord);
				addDialog.add(addTextarea);
				
				int input = JOptionPane.showConfirmDialog(frameAddDia, addDialog, "Add data", JOptionPane.OK_CANCEL_OPTION);
				if(input == JOptionPane.OK_OPTION)
				{
					String newSet = addStation.getText() + "," + addActualRecord.getText() + "," + addTargetRecord.getText() 
							+ "," + addVarianceRecord.getText() + "," + addDateRecord.getText();
			        addDataSet.add(newSet);
					ReadIce.writeCsvFile(newSet);
					ReadIce.readCsvFile(null); // redundant!!!!!
				}	
				// Sortierfunktion für Werte???
			}
		});
		buttonarea.add(addButton);
		
		// buttonarea-->updateButton
		JButton updateButton = new JButton("Update actual state");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				final JPanel updateDialog = new JPanel(new GridLayout(1, 2));

				JLabel updateActual = new JLabel("New actual state");
				addActualarea.add(updateActual);
				updateActualEntry = new JTextField();
				updateActualEntry.setText(STATION_ACTUAL);
				addActualarea.add(updateActualEntry);
				updateDialog.add(addActualarea);
				
				int input2 = JOptionPane.showConfirmDialog(frameUpdateAc, updateDialog, "Update actual state", JOptionPane.OK_CANCEL_OPTION);
				if(input2 == JOptionPane.OK_OPTION)
				{
					String updateSet = updateActualEntry.getText();
			        actualEntry.setText(updateSet);
			        STATION_ACTUAL = updateSet;
			        calcVar();
			        String newSet2 = STATION_ID + "," + STATION_ACTUAL + "," + STATION_TARGET + "," + STATION_VARIANCE + "," + STATION_DATE;
			        System.out.println(newSet2);
			        addDataSet.add(newSet2);
					ReadIce.updateCsvFile(newSet2); // ToDo delete redundance
					JOptionPane.showMessageDialog(frame, "New actual state successfully written to file!");
				}	
			}
		});
		buttonarea.add(updateButton);

		// splitPane-->listarea
		JScrollPane scrollPane = new JScrollPane(); 
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(BorderFactory.createLineBorder(Color.black));
		scrollPane.setViewportView(list);
		// set ListModel - CSVImport
		list.setModel(dataSet);
		list.addListSelectionListener(new ListSelectionListener() { // ActionListener for choice
			public void valueChanged(ListSelectionEvent le) {
		        int idx = list.getSelectedIndex();
		        if (idx != -1) {
		        	System.out.println("Current selection: " + dataSet.getElementAt(idx));
		        	chosenDataSet = (String) dataSet.getElementAt(idx);
		        	splitData(); // fill textfields
		        	station.setText(STATION_ID);
		        	actualEntry.setText(STATION_ACTUAL);
		        	dateEntry.setText(STATION_DATE);
		        	targetEntry.setText(STATION_TARGET);
		        	varianceEntry.setText(STATION_VARIANCE);
		        	setColor(); // colorize variance
		        	varianceEntry.setForeground(textColor);
		        } else {
		        	System.out.println("No station chosen.");
		        }
		    }
		});
		
		listarea.add(scrollPane);

		// splitPane-->textarea
		// StationID
		JLabel stationID = new JLabel("StationID");
		textarea.add(stationID);
		station = new JTextField();
		textarea.add(station);

		// Date
		JLabel dateRecord = new JLabel("Date");
		textarea.add(dateRecord);
		dateEntry = new JTextField();
		textarea.add(dateEntry);

		// Target
		JLabel target = new JLabel("Target");
		textarea.add(target);
		targetEntry = new JTextField();
		targetEntry.setEditable(false);
		textarea.add(targetEntry);

		// Actual
		JLabel actual = new JLabel("Actual");
		textarea.add(actual);
		actualEntry = new JTextField();
		textarea.add(actualEntry);

		// Variance
		JLabel variance = new JLabel("Variance");
		textarea.add(variance);
		varianceEntry = new JTextField();
		textarea.add(varianceEntry);

		// Add
		splitPane.add(listarea);
		splitPane.add(textarea);

		// Borders
		label.setBorder(BorderFactory.createLineBorder(Color.black));

		frame.getContentPane().add(content);
		frame.setVisible(true); 
	}

	// method to fill textfields with data
	public static String[] splitData() { 
		
		String[] arrayEntry = chosenDataSet.split("\\s* \\s*");
		STATION_ID = arrayEntry[0];
		STATION_ACTUAL = arrayEntry[1];
		STATION_TARGET = arrayEntry[2];
		STATION_VARIANCE = arrayEntry[3];
		STATION_DATE = arrayEntry[4];
		return arrayEntry;
	}
	
	// method to set textcolor of variance data
	public static void setColor() {
		
		double intVar = Double.parseDouble(STATION_VARIANCE);
		double intTar = Double.parseDouble(STATION_TARGET);
		
		if((intTar + intVar) >= (intTar + (intTar/100)*5)) {
			textColor = Color.GREEN;
		} else {
			if((intTar + intVar) <= (intTar - (intTar/100)*10)) {
				textColor = Color.RED;
			} else {
				textColor = Color.BLUE;
			}
		}
	}
	
	public static  List<String> getNewDataSet() {
		//System.out.println(listModel);
		return addDataSet;
	}
	
	// method to calc variance after update
	public static void calcVar() {
		
		int intAct = Integer.parseInt(STATION_ACTUAL);
		int intTar = Integer.parseInt(STATION_TARGET);
		
		int intVar = intAct - intTar;
		String intVarStr = Integer.toString(intVar);
		varianceEntry.setText(intVarStr);
		STATION_VARIANCE = intVarStr;
	}
	
}
