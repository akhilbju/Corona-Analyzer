package com.coronavirus.example.model;

public class corona {
	
	private String state;
	private String country;
	private int latestTotalDeaths;
	private int differFromPrevious;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalDeaths() {
		return latestTotalDeaths;
	}
	public void setLatestTotalDeaths(int latestTotalDeaths) {
		this.latestTotalDeaths = latestTotalDeaths;
	}
	public int getDifferFromPrevious() {
		return differFromPrevious;
	}
	public void setDifferFromPrevious(int differFromPrevious) {
		this.differFromPrevious = differFromPrevious;
	}
	public corona() {
		super();
	}
	@Override
	public String toString() {
		return "corona [state=" + state + ", country=" + country + ", latestTotalDeaths=" + latestTotalDeaths
				+ ", differFromPrevious=" + differFromPrevious + "]";
	}

}
