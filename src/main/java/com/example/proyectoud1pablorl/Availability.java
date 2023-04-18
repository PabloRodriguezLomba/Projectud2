package com.example.proyectoud1pablorl;

import java.util.List;

public class Availability{
	public boolean isAllYear;
	public boolean isAllDay;
	public List<Integer> monthArrayNorthern;
	public String monthSouthern;
	public List<Integer> monthArraySouthern;
	public String monthNorthern;
	public String location;
	public String time;
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