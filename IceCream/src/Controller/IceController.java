package Controller;

import Model.IceObject;
import Model.ReadIce;

public class IceController {
	
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

}
