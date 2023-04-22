package com.example.proyectoud1pablorl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Bug{

	public SimpleIntegerProperty i;
	public SimpleStringProperty Nam;
	public  SimpleIntegerProperty Pric;
	public SimpleIntegerProperty Flick;
	public SimpleStringProperty Catch;

	public Bug(int id, String name, int price, int priceFlick, String catchPhrase) {
		i = new SimpleIntegerProperty(id);
		Nam = new SimpleStringProperty(name);
		Pric = new SimpleIntegerProperty(price);
		Flick = new SimpleIntegerProperty(priceFlick);
		Catch = new SimpleStringProperty(catchPhrase);
	}


	public int getI() {
		return i.get();
	}

	public SimpleIntegerProperty iProperty() {
		return i;
	}

	public void setI(int i) {
		this.i.set(i);
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
		return Pric.get();
	}

	public SimpleIntegerProperty pricProperty() {
		return Pric;
	}

	public void setPric(int pric) {
		this.Pric.set(pric);
	}

	public int getFlick() {
		return Flick.get();
	}

	public SimpleIntegerProperty flickProperty() {
		return Flick;
	}

	public void setFlick(int flick) {
		this.Flick.set(flick);
	}

	public String getCatch() {
		return Catch.get();
	}

	public SimpleStringProperty catchProperty() {
		return Catch;
	}

	public void setCatch(String aCatch) {
		this.Catch.set(aCatch);
	}
}