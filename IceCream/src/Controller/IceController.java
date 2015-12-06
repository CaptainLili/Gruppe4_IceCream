package Controller;

import java.util.Observable;
import java.util.Observer;

import Model.IceObject;
import Model.ReadIce;
import View.simpleView;

public class IceController implements Observer {

	ReadIce model = new ReadIce();
	static IceObject iceobject;
	static simpleView view;

	public IceController() {
		
		iceobject = IceObject.getInstance();
	}

	public IceObject updateObject(String station, String actual, String target,
			String variance, String date) {

		iceobject.setStation(station);
		iceobject.setActual(actual);
		iceobject.setTarget(target);
		iceobject.setVariance(variance);
		iceobject.setDate(date);

		model.updateCsvFile(iceobject);
		return iceobject;
	}

	public IceObject addObject(String station, String actual, String target,
			String variance, String date) {

		iceobject.setStation(station);
		iceobject.setActual(actual);
		iceobject.setTarget(target);
		iceobject.setVariance(variance);
		iceobject.setDate(date);

		model.writeCsvFile(iceobject);
		return iceobject;
	}

	public void update(Observable o, Object arg) {
		System.out.println("Changed IceObject");
	}

	public static void main(String[] args) {
		ReadIce model = new ReadIce();
		model.readCsvFile(args);

		view.startGUI();
	}

}
