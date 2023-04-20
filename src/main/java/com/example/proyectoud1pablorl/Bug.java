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
}