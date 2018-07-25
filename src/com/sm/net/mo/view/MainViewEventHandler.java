package com.sm.net.mo.view;

import java.io.File;

import com.sm.net.easy.h2.db.EasyH2Database;
import com.sm.net.easy.h2.execute.EasyH2QueryBuilder;
import com.sm.net.fx.AlertDesigner;
import com.sm.net.mo.AppInfos;
import com.sm.net.mo.References;
import com.sm.net.mo.db.Database;
import com.sm.net.util.FilesFolders;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainViewEventHandler {

	private EasyH2Database database;
	private Stage stage;

	@FXML
	private Button buttonOpenFolderTemp;
	@FXML
	private Button buttonCheckFolderTemp;

	public void buttonOpenFolderTempOnClick() {
		FilesFolders.openDirectory(References.DIR_TEMP);
	}

	public void buttonCheckFolderTempOnClick() {

		AlertDesigner alert = new AlertDesigner("Do you want to start the analysis of the temporary folder?",
				"Warning! The previous analysis will be deleted.", stage, AlertType.CONFIRMATION, AppInfos.getAppName(),
				new Image(References.APP_ICON.toURI().toString()));

		if (alert.showAndWait().get() == ButtonType.OK)
			checkTempFolder();

	}

	private void checkTempFolder() {

		checkDirectory(References.DIR_TEMP);

	}

	private void checkDirectory(File directory) {

		File[] files = FilesFolders.listAllFiles(directory);
		for (File file : files) {
			if (file != null)
				if (file.exists())
					if (file.isDirectory())
						checkDirectory(file);
					else
						checkFile(file);
		}
	}

	private void checkFile(File file) {

		insertToDb(file);
		
	}

	private void insertToDb(File file) {
		EasyH2QueryBuilder builder = new EasyH2QueryBuilder(Database.SCHEMA, Database.TAB_FILETEMP);
		builder.addUpdate("FILEPATH", file.getAbsolutePath());
	}

	public void init() {

	}

	public EasyH2Database getDatabase() {
		return database;
	}

	public void setDatabase(EasyH2Database database) {
		this.database = database;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
