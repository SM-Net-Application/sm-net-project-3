package com.sm.net.mo.view;

import com.sm.net.easy.h2.db.EasyH2Database;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewEventHandler {

	private EasyH2Database database;

	@FXML
	private Button buttonOpenFolderTemp;

	public void buttonOpenFolderTempOnClick() {

	}

	public void init() {

	}

	public EasyH2Database getDatabase() {
		return database;
	}

	public void setDatabase(EasyH2Database database) {
		this.database = database;
	}

}
