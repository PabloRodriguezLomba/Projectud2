package com.example.proyectoud1pablorl;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Fish{

	public SimpleIntegerProperty i;
	public SimpleStringProperty Nam;
	public SimpleStringProperty shado;
	public  SimpleIntegerProperty Pric;
	public SimpleIntegerProperty Priccj;
	public SimpleStringProperty Catch;


	public Fish(int id, String name, String shadow, int price, int pricecj, String catchPhrase) {
		i = new SimpleIntegerProperty(id);
		Nam = new SimpleStringProperty(name);
		shado = new SimpleStringProperty(shadow);
		Pric = new SimpleIntegerProperty(price);
		Priccj = new SimpleIntegerProperty(pricecj);
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

	public String getShado() {
		return shado.get();
	}

	public SimpleStringProperty shadoProperty() {
		return shado;
	}

	public void setShado(String shado) {
		this.shado.set(shado);
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

	public int getPriccj() {
		return Priccj.get();
	}

	public SimpleIntegerProperty priccjProperty() {
		return Priccj;
	}

	public void setPriccj(int priccj) {
		this.Priccj.set(priccj);
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