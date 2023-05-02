package com.example.proyectoud1pablorl.Object;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BugItem{

	@JsonProperty("price-flick")
	public int priceFlick;
	@JsonProperty("catch-phrase")
	public String catchPhrase;
	public String imageUri;
	public int price;

	public Name name;
	@JsonProperty("museum-phrase")
	public String museumPhrase;
	public int id;
	@JsonProperty("file-name")
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
