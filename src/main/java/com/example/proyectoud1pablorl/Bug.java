package com.example.proyectoud1pablorl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Bug{
	public int priceFlick;
	public String catchPhrase;
	public String imageUri;
	public int price;
	public Name name;
	public String museumPhrase;
	public int id;

	@JsonIgnoreProperties
	public String fileName;
	public Availability availability;
	public String iconUri;

	public int getPriceFlick(){
		return priceFlick;
	}

	public String getCatchPhrase(){
		return catchPhrase;
	}

	public String getImageUri(){
		return imageUri;
	}

	public int getPrice(){
		return price;
	}

	public Name getName(){
		return name;
	}

	public String getMuseumPhrase(){
		return museumPhrase;
	}

	public int getId(){
		return id;
	}

	public String getFileName(){
		return fileName;
	}

	public Availability getAvailability(){
		return availability;
	}

	public String getIconUri(){
		return iconUri;
	}
}
