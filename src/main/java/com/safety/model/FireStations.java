package com.safety.model;
public class FireStations {
	String address;
	String station;
	public FireStations(String address, String station) {
		this.address = address;
		this.station = station;
	}

	public FireStations() {
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
}
