package Model;

import java.util.Calendar;

public class IceObject {
	float actual, variance, target;
	String vColor, station ;
	Calendar date  = Calendar.getInstance();
	
	public float getActual() {
		return actual;
	}
	public void setActual(float actual) {
		this.actual = actual;
	}
	public float getVariance() {
		return variance;
	}
	public void setVariance(float variance) {
		this.variance = variance;
	}
	public float getTarget() {
		return target;
	}
	public void setTarget(float target) {
		this.target = target;
	}
	public String getvColor() {
		return vColor;
	}
	public void setvColor(String vColor) {
		this.vColor = vColor;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public void changeObject(){
		
	}
	
	

}
