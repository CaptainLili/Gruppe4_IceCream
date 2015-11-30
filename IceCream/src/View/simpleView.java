package View;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Model.ReadIce;

public class simpleView implements IceView{

	public static void main(String[] args) {
		// creates the JFrame(a window with decorations)
		JFrame frame = new JFrame("IceCream Records");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel label = new JPanel(new GridLayout(2, 0)); // the panel for the text on top
        JPanel content = new JPanel(new GridLayout(2, 0)); // the main panel of the JFrame, remembet you cant add content directly to JFrame
        JPanel splitPane = new JPanel(new GridLayout(0, 2, 20, 10));
        JPanel listarea = new JPanel(new GridLayout(1, 0));
        JPanel textarea = new JPanel(new GridLayout(5, 2, 2, 2)); // panel for the buttons, GridLayout(int rows, int columns, int horizontal_gap, int vertical_gap)
        content.add(label);
        content.add(splitPane);

        //Label-->Title
        JLabel title = new JLabel("Ice-Cream air-particle concentration.", JLabel.CENTER);
        label.add(title);
        
        //splitPane-->listarea
        JScrollPane scrollPane = new JScrollPane();
        JList list = new JList();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBorder(BorderFactory.createLineBorder(Color.black));
        //placeholder for model
        list.setModel(new AbstractListModel() {
			String[] values = new String[] {
					"AAAA", "AAAB", "AAAC", "AAAD", "AAAE", "AAAF",
					"AABA", "AABB", "AABC", "AABD", "AABE", "AABF"
					};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
        scrollPane.setViewportView(list);
        listarea.add(scrollPane);
        
        //splitPane-->textarea
        //StationID
        JLabel stationID = new JLabel("StationID");
        textarea.add(stationID);
        JTextField station = new JTextField();
        textarea.add(station);
        
        //Date
        JLabel dateRecord = new JLabel("Date");
        textarea.add(dateRecord);
        JTextField dateEntry = new JTextField();
        textarea.add(dateEntry);
        
        //Target
        JLabel target = new JLabel("Target");
        textarea.add(target);
        JTextField targetEntry = new JTextField();
        targetEntry.setText("placeholder");
        targetEntry.setEditable(false);
        textarea.add(targetEntry);
        
        //Actual
        JLabel actual = new JLabel("Actual");
        textarea.add(actual);
        JTextField actualEntry = new JTextField();
        textarea.add(actualEntry);

        //Variance
        JLabel variance = new JLabel("Variance");
        textarea.add(variance);
        JTextField varianceEntry = new JTextField();
        textarea.add(varianceEntry);
        
        //Add
        splitPane.add(listarea);
        splitPane.add(textarea);
        
        //Borders
        label.setBorder(BorderFactory.createLineBorder(Color.black));
 

        frame.getContentPane().add(content); // adds the textarea panel to the main panel
        frame.setVisible(true); // makes the window visible, put at end of program
    }

	@Override
	public ReadIce getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}
