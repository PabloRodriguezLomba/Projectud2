package com.example.proyectoud1pablorl.Object;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Fossil{
	private SimpleStringProperty Nam;
	private SimpleIntegerProperty pric;
	private SimpleStringProperty Museum;

	public Fossil(String name, int price, String museumPhrase) {
		Nam = new SimpleStringProperty(name);
		pric = new SimpleIntegerProperty(price);
		Museum = new SimpleStringProperty(museumPhrase);
	}

	public String getNam() {
		return Nam.get();
	}

	public SimpleStringProperty namProperty() {
		return Nam;
	}

	public void setNam(String nam) {
		this.Nam.set(nam);
	}

	public int getPric() {
		return pric.get();
	}

	public SimpleIntegerProperty pricProperty() {
		return pric;
	}

	public void setPric(int pric) {
		this.pric.set(pric);
	}

	public String getMuseum() {
		return Museum.get();
	}

	public SimpleStringProperty museumProperty() {
		return Museum;
	}

	public void setMuseum(String museum) {
		this.Museum.set(museum);
	}
}