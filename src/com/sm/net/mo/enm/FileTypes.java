package com.sm.net.mo.enm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum FileTypes {

	PHOTOS("Photos", 0), IMAGES("Images", 1), VIDEOS("Videos", 2), FILES("Files", 3);

	private StringProperty name;
	private IntegerProperty ordinal;

	FileTypes(String name, int ordinal) {
		this.name = new SimpleStringProperty(name);
		this.ordinal = new SimpleIntegerProperty(ordinal);
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public IntegerProperty getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(IntegerProperty ordinal) {
		this.ordinal = ordinal;
	}

	@Override
	public String toString() {
		return this.getName().get();
	}
}
