package com.automation.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	 private String street;
	    private String suite;
	    private String city;
	    private String zipCode;
	    private Geo geo;

	    public Address(@JsonProperty("street")String street, @JsonProperty("suite")String suite,
                @JsonProperty("city")String city, @JsonProperty("zipcode")String zipCode, @JsonProperty("geo")Geo geo) {
	        this.street = street;
	        this.suite = suite;
	        this.city = city;
	        this.zipCode = zipCode;
	        this.geo = geo;
	    }

	    @Override
	    public String toString() {
	        return "{" +
	                "street='" + street + '\'' +
	                ", suite='" + suite + '\'' +
	                ", city='" + city + '\'' +
	                ", zipCode='" + zipCode + '\'' +
	                ", geo" + geo +
	                '}';
	    }

}
