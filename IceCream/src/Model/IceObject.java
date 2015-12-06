package Model;

public class IceObject {

	static IceObject instance;

	private String station;
	private String actual;
	private String target;
	private String variance;
	private String date;

	public static IceObject getInstance() {

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

	public void setTarget(String target) {
		this.target = target;
	}

	public void setVariance(String variance) {
		this.variance = variance;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public IceObject() {

	}

	public IceObject(String station, String actual, String target, String variance,
			String date) {
		this.station = station;
		this.actual = actual;
		this.target = target;
		this.variance = variance;
		this.date = date;
	}

	public String getStation() {
		return station;
	}

	public String getActual() {
		return actual;
	}

	public String getTarget() {
		return target;
	}

	public String getVariance() {
		return variance;
	}
	
	public String getDate() {
		return date;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", station, actual, target, variance,
				date);
	}

}
