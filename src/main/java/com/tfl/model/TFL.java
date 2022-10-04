package com.tfl.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TFL {
	    public String type;
	    public String id;
	    public int operationType;
	    public String vehicleId;
	    public String naptanId;
	    public String stationName;
	    public String lineId;
	    public String lineName;
	    public String platformName;
	    public String direction;
	    public String bearing;
	    public String destinationNaptanId;
	    public String destinationName;
	    public Date timestamp;
	    public int timeToStation;
	    public String currentLocation;
	    public String towards;
	    
	    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	    public Date expectedArrival;
	    
	    public Date timeToLive;
	    public String modeName;
	    public Timing timing;
	    
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getOperationType() {
			return operationType;
		}
		public void setOperationType(int operationType) {
			this.operationType = operationType;
		}
		public String getVehicleId() {
			return vehicleId;
		}
		public void setVehicleId(String vehicleId) {
			this.vehicleId = vehicleId;
		}
		public String getNaptanId() {
			return naptanId;
		}
		public void setNaptanId(String naptanId) {
			this.naptanId = naptanId;
		}
		public String getStationName() {
			return stationName;
		}
		public void setStationName(String stationName) {
			this.stationName = stationName;
		}
		public String getLineId() {
			return lineId;
		}
		public void setLineId(String lineId) {
			this.lineId = lineId;
		}
		public String getLineName() {
			return lineName;
		}
		public void setLineName(String lineName) {
			this.lineName = lineName;
		}
		public String getPlatformName() {
			return platformName;
		}
		public void setPlatformName(String platformName) {
			this.platformName = platformName;
		}
		public String getDirection() {
			return direction;
		}
		public void setDirection(String direction) {
			this.direction = direction;
		}
		public String getBearing() {
			return bearing;
		}
		public void setBearing(String bearing) {
			this.bearing = bearing;
		}
		public String getDestinationNaptanId() {
			return destinationNaptanId;
		}
		public void setDestinationNaptanId(String destinationNaptanId) {
			this.destinationNaptanId = destinationNaptanId;
		}
		public String getDestinationName() {
			return destinationName;
		}
		public void setDestinationName(String destinationName) {
			this.destinationName = destinationName;
		}
		public Date getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}
		public int getTimeToStation() {
			return timeToStation;
		}
		public void setTimeToStation(int timeToStation) {
			this.timeToStation = timeToStation;
		}
		public String getCurrentLocation() {
			return currentLocation;
		}
		public void setCurrentLocation(String currentLocation) {
			this.currentLocation = currentLocation;
		}
		public String getTowards() {
			return towards;
		}
		public void setTowards(String towards) {
			this.towards = towards;
		}
		public Date getExpectedArrival() {
			return expectedArrival;
		}
		public void setExpectedArrival(Date expectedArrival) {
			this.expectedArrival = expectedArrival;
		}
		public Date getTimeToLive() {
			return timeToLive;
		}
		public void setTimeToLive(Date timeToLive) {
			this.timeToLive = timeToLive;
		}
		public String getModeName() {
			return modeName;
		}
		public void setModeName(String modeName) {
			this.modeName = modeName;
		}
		public Timing getTiming() {
			return timing;
		}
		public void setTiming(Timing timing) {
			this.timing = timing;
		}
}
