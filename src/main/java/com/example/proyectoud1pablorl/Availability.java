package com.example.proyectoud1pablorl;

import java.util.List;

public class Availability{
	private boolean isAllYear;
	private boolean isAllDay;
	private List<Integer> monthArrayNorthern;
	private String monthSouthern;
	private List<Integer> monthArraySouthern;
	private String monthNorthern;
	private String location;
	private String time;
	private List<Integer> timeArray;
	private String rarity;

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