package com.example.proyectoud1pablorl;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Availability{
	public boolean isAllYear;
	public boolean isAllDay;
	@JsonProperty("month-array-northern")
	public List<Integer> monthArrayNorthern;
	@JsonProperty("month-southern")
	public String monthSouthern;
	@JsonProperty("month-array-southern")
	public List<Integer> monthArraySouthern;
	@JsonProperty("month-northern")
	public String monthNorthern;
	public String location;
	public String time;
	@JsonProperty("time-array")
	public List<Integer> timeArray;
	public String rarity;

	public boolean isIsAllYear(){
		return isAllYear;
	}

	public boolean isIsAllDay(){
		return isAllDay;
	}

	public List<Integer> getMonthArrayNorthern(){
		return monthArrayNorthern;
	}

	public String getMonthSouthern(){
		return monthSouthern;
	}

	public List<Integer> getMonthArraySouthern(){
		return monthArraySouthern;
	}

	public String getMonthNorthern(){
		return monthNorthern;
	}

	public String getLocation(){
		return location;
	}

	public String getTime(){
		return time;
	}

	public List<Integer> getTimeArray(){
		return timeArray;
	}

	public String getRarity(){
		return rarity;
	}
}