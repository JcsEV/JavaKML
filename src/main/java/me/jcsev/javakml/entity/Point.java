package me.jcsev.javakml.entity;

public class Point {
	private double latitude;
	private double longitude;
	private double altitude;
	
	public Point() {
		this.latitude = 0;
		this.longitude = 0;
		this.altitude = 0;
	}
	
	public Point(double latitude, double longitude, double altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getAltitude() {
		return this.altitude;
	}
}
