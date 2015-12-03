package Controller;

import java.util.Observable;
import java.util.Observer;

import Model.IceObject;
import Model.ReadIce;

public class IceController implements Observer{
	
	
	
	ReadIce model;
	static IceObject iceobject;
	
	public IceController() {
		iceobject = IceObject.getInstance();

	}
	
	public void updateObject(String station, String actual, String date, String target,
			String variance){
		
		iceobject.setActual(actual);
		iceobject.setDate(date);
		iceobject.setStation(station);
		iceobject.setTarget(target);
		iceobject.setVariance(variance);
		
		model.updateCsvFile(iceobject);
		
	}
	
	public void addObject(String station, String actual, String date, String target,
			String variance){
		
		iceobject.setActual(actual);
		iceobject.setDate(date);
		iceobject.setStation(station);
		iceobject.setTarget(target);
		iceobject.setVariance(variance);
		
		model.writeCsvFile(iceobject);
		
	}
	
	public void update(Observable o , Object arg){
		System.out.println("Changed IceObject");
	}

}
