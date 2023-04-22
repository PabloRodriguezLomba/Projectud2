package com.example.proyectoud1pablorl;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FossilItem{
	public String imageUri;
	public int price;
	public Name name;
	@JsonProperty("museum-phrase")
	public String museumPhrase;
	@JsonProperty("part-of")
	public String partOf;
	@JsonProperty("file-name")
	public String fileName;

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

	public String getPartOf(){
		return partOf;
	}

	public String getFileName(){
		return fileName;
	}
}
