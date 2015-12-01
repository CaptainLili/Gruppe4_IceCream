package Model;

public class IceObject {

	private final String station;
	private final String actual;
	private final String date;
	private final String target;
	private final String variance;

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
