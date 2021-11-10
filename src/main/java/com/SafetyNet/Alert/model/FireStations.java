package com.SafetyNet.Alert.Model;

public class FireStations {

		String address;
		String station;
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
		@Override
		public String toString() {
			return "FireStations [address=" + address + ", station=" + station + "]";
		}
		

	


}
