package com.sm.net.mo.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class File {

	private SimpleIntegerProperty id;
	private SimpleIntegerProperty idFile;
	private SimpleStringProperty filePath;

	public File(int id, int idFile, String filePath) {
		super();
		this.id = new SimpleIntegerProperty(id);
		this.idFile = new SimpleIntegerProperty(idFile);
		this.filePath = new SimpleStringProperty(filePath);
	}

	public SimpleIntegerProperty getId() {
		return id;
	}

	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}

	public SimpleIntegerProperty getIdFile() {
		return idFile;
	}

	public void setIdFile(SimpleIntegerProperty idFile) {
		this.idFile = idFile;
	}

	public SimpleStringProperty getFilePath() {
		return filePath;
	}

	public void setFilePath(SimpleStringProperty filePath) {
		this.filePath = filePath;
	}
}
