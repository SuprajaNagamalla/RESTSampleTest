package com.automation.restapi.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
    
	private String name;
    private String catchPhrase;
    private String business;

    public Company(@JsonProperty("name")String name, @JsonProperty("catchPhrase")String catchPhrase, @JsonProperty("bs")String business) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.business = business;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatchPhrase() {
		return catchPhrase;
	}

	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", catchPhrase='" + catchPhrase + '\'' +
                ", business='" + business + '\'' +
                '}';
    }
}
