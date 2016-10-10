package question3;

public class Location {
	private double latitute;
	private double longitute;
	
	public Location(){
	}
	
	public Location(double latitute, double longitute) {
		super();
		this.latitute = latitute;
		this.longitute = longitute;
	}
	
	public double getLatitute() {
		return latitute;
	}

	public void setLatitute(double latitute) {
		this.latitute = latitute;
	}

	public double getLongitute() {
		return longitute;
	}

	public void setLongitute(double longitute) {
		this.longitute = longitute;
	}

	public boolean equals(Location l){
		return this.latitute == l.latitute && this.longitute == l.longitute;
	}
	
	public String toString(){
		return "latitude: " + this.latitute + " longitude: " + this.longitute;
	}
}
