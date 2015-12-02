package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class simpleView implements Observer {

	public static ListModel dataSet = ReadIce.getListModel();
	public static String chosenDataSet;
	public static Color textColor;
	public static JTextField station;
	public static JTextField dateEntry;
	public static JTextField targetEntry;
	public static JTextField actualEntry;
	public static JTextField varianceEntry;
	public static JList list;
	private final IceController controller;
	private static String STATION_ID = "";
	private static String STATION_ACTUAL = "";
	private static String STATION_DATE = "";
	private static String STATION_TARGET = "";
	private static String STATION_VARIANCE = "";
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startGUI();
			}
		});
	}
	
	@Override
	public void update(Observable  o, Object arg ){
		
	}
	
	public static void startGUI() {
		
		// creates the JFrame(a window with decorations)
		JFrame frame = new JFrame("IceCream Records");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		// define layout
		JPanel label = new JPanel(new GridLayout(2, 0));
		JPanel buttonarea = new JPanel();
		JPanel content = new JPanel(new GridLayout(3, 0)); 
		JPanel splitPane = new JPanel(new GridLayout(0, 2, 20, 10));
		JPanel listarea = new JPanel(new GridLayout(1, 0));
		JPanel textarea = new JPanel(new GridLayout(5, 2, 2, 2)); 
		content.add(label);
		content.add(buttonarea);
		content.add(splitPane);
		
		// Label-->Title
		JLabel title = new JLabel("Ice-Cream air-particle concentration.", JLabel.CENTER);
		label.add(title);
		
		// buttonarea-->AddButton
		JButton addButton = new JButton("Add station");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, 
						"This is the simple messag dialog box.", "Add station", 1);
				ReadIce.writeCsvFile(null);
			}
		});
		buttonarea.add(addButton);

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
	
}
