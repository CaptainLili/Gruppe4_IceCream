package Controller;

import java.util.Observable;
import java.util.Observer;

import Model.IceObject;
import Model.ReadIce;
import View.simpleView;

public class IceController implements Observer{
	
	
	
	ReadIce model = new ReadIce();
	static IceObject iceobject;
	static simpleView view;
	
	public IceController() {
		iceobject = IceObject.getInstance();

	}
	
	public IceObject updateObject(String station, String actual, String date, String target,
			String variance){
		
		iceobject.setActual(actual);
		iceobject.setDate(date);
		iceobject.setStation(station);
		iceobject.setTarget(target);
		iceobject.setVariance(variance);
		
		model.updateCsvFile(iceobject);
		return iceobject;
		
	}
	
	public IceObject addObject(String station, String actual, String date, String target,
			String variance){
		
		iceobject.setActual(actual);
		iceobject.setDate(date);
		iceobject.setStation(station);
		iceobject.setTarget(target);
		iceobject.setVariance(variance);
		
		model.writeCsvFile(iceobject);
		return iceobject;
	}
	
	public void update(Observable o , Object arg){
		System.out.println("Changed IceObject");
	}
	
	public static void main(String[] args) {
		/*IceObject object = new IceObject( "station",  "actual", " date", " target",
				" variance");
		object.toString();
		String station = "station";
		String actual = "station";
		String date = "station";
		String target = "station";
		String variance = "station";
		IceController controller = new IceController();
		controller.addObject(station,actual, date, target, variance);*/
		
		
		ReadIce model = new ReadIce();
		model.readCsvFile(args);
	
		view.startGUI();
	}

}
