package Model;

public class IceObject {
	
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
