package Model;

import java.util.Observable;

public class IceObject extends Observable {
	
	static IceObject instance;
	

	private String station;
	private  String actual;
	private  String date;
	private  String target;
	private  String variance;
	
	public static IceObject getInstance(){
		
		if (IceObject.instance == null) {
			IceObject.instance = new IceObject();
		}
		return IceObject.instance;
		
	}

	

	public void setStation(String station) {
		this.station = station;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}

	public IceObject() {
		
	}

	public IceObject(String station, String actual, String date, String target,
			String variance) {
		this.station = station;
		this.actual = actual;
		this.date = date;
		this.target = target;
		this.variance = variance;
	}
	
	public void changeIceObject(IceObject iceobject){
		
		setChanged();
		notifyObservers();
		
	}
	
	
	
	

	public String getStation() {
		return station;
	}

	public String getActual() {
		return actual;
	}

	public String getDate() {
		return date;
	}

	public String getTarget() {
		return target;
	}

	public String getVariance() {
		return variance;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", station, actual, date, target,
				variance);
	}

}
