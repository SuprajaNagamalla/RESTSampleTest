package com.automation.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Geo {
   	private String latitude;
    private String longitude;
    
    public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

    public Geo(@JsonProperty("lat")String latitude, @JsonProperty("lng")String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}